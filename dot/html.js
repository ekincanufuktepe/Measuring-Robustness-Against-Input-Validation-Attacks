find();
//removeData();
//hasData();
//data();
//val();
//each();
//attr();
//css();
//text();
//html();

//contains
function contains ( context, elem ) {
	// Set document vars if needed
	if ( ( context.ownerDocument || context ) !== document ) {
		setDocument( context );
	}
	return contains( context, elem );
}

//append
function append () {
    return this.domManip(arguments, true, function (elem) {
        if (this.nodeType === 1 || this.nodeType === 11 || this.nodeType === 9) {
            this.appendChild(elem);
        }
    });
}



//find
function find (selector) {
    var i, ret, self, len = this.length;

    if (typeof selector !== "string") {
        self = this;
        return pushStack(jQuery(selector).filter(function () {
            for (i = 0; i < len; i++) {
                if (contains(self[i], this)) {
                    return true;
                }
            }
        }));
    }

    ret = [];
    for (i = 0; i < len; i++) {
        find(selector, this[i], ret);
    }

    // Needed because $( selector, context ) becomes $( context ).find( selector )
    ret = pushStack(len > 1 ? jQuery.unique(ret) : ret);
    ret.selector = (this.selector ? this.selector + " " : "") + selector;
    return ret;
}

//removeData
function removeData( elem, name ) {
	return internalRemoveData( elem, name );
}

//hasData
function hasData( elem ) {
	elem = elem.nodeType ? jQuery.cache[ elem[jQuery.expando] ] : elem[ jQuery.expando ];
	return !!elem && !isEmptyDataObject( elem );
}

//isFunction
function isFunction( obj ) {
	return type(obj) === "function";
}

//data
function data( elem, name, data ) {
	return internalData( elem, name, data );
}

//internalData
function internalData( elem, name, data, pvt /* Internal Use Only */ ){
	if ( !acceptData( elem ) ) {
		return;
	}

	var thisCache, ret,
		internalKey = jQuery.expando,
		getByName = typeof name === "string",

		// We have to handle DOM nodes and JS objects differently because IE6-7
		// can't GC object references properly across the DOM-JS boundary
		isNode = elem.nodeType,

		// Only DOM nodes need the global jQuery cache; JS object data is
		// attached directly to the object so GC can occur automatically
		cache = isNode ? jQuery.cache : elem,

		// Only defining an ID for JS objects if its cache already exists allows
		// the code to shortcut on the same path as a DOM node with no cache
		id = isNode ? elem[ internalKey ] : elem[ internalKey ] && internalKey;

	// Avoid doing any more work than we need to when trying to get data on an
	// object that has no data at all
	if ( (!id || !cache[id] || (!pvt && !cache[id].data)) && getByName && data === undefined ) {
		return;
	}

	if ( !id ) {
		// Only DOM nodes need a new unique ID for each element since their data
		// ends up in the global cache
		if ( isNode ) {
			elem[ internalKey ] = id = core_deletedIds.pop() || jQuery.guid++;
		} else {
			id = internalKey;
		}
	}

	if ( !cache[ id ] ) {
		cache[ id ] = {};

		// Avoids exposing jQuery metadata on plain JS objects when the object
		// is serialized using JSON.stringify
		if ( !isNode ) {
			cache[ id ].toJSON = jQuery.noop;
		}
	}

	// An object can be passed to jQuery.data instead of a key/value pair; this gets
	// shallow copied over onto the existing cache
	if ( typeof name === "object" || typeof name === "function" ) {
		if ( pvt ) {
			cache[ id ] = jQuery.extend( cache[ id ], name );
		} else {
			cache[ id ].data = jQuery.extend( cache[ id ].data, name );
		}
	}

	thisCache = cache[ id ];

	// jQuery data() is stored in a separate object inside the object's internal data
	// cache in order to avoid key collisions between internal data and user-defined
	// data.
	if ( !pvt ) {
		if ( !thisCache.data ) {
			thisCache.data = {};
		}

		thisCache = thisCache.data;
	}

	if ( data !== undefined ) {
		thisCache[ jQuery.camelCase( name ) ] = data;
	}

	// Check for both converted-to-camel and non-converted data property names
	// If a data property was specified
	if ( getByName ) {

		// First Try to find as-is property data
		ret = thisCache[ name ];

		// Test for null|undefined property data
		if ( ret == null ) {

			// Try to find the camelCased property
			ret = thisCache[ jQuery.camelCase( name ) ];
		}
	} else {
		ret = thisCache;
	}

	return ret;
}

//each
function each (callback, args) {
    return each(this, callback, args);
}

//val
function val (value) {
	isFunction(value);
	isArray(val);
    var ret, hooks, isFunction, elem = this[0];

    if (!arguments.length) {
        if (elem) {
            hooks = jQuery.valHooks[elem.type] || jQuery.valHooks[elem.nodeName.toLowerCase()];

            if (hooks && "get" in hooks && (ret = hooks.get(elem, "value")) !== undefined) {
                return ret;
            }

            ret = elem.value;

            return typeof ret === "string" ?
            // handle most common string cases
            ret.replace(rreturn, "") :
            // handle cases where value is null/undef or number
            ret == null ? "" : ret;
        }

        return;
    }

    isFunction = isFunction(value);

    return this.each(function (i) {
        var val, self = jQuery(this);

        if (this.nodeType !== 1) {
            return;
        }

        if (isFunction) {
            val = value.call(this, i, self.val());
        } else {
            val = value;
        }
        
        // Treat null/undefined as ""; convert numbers to string
        if (val == null) {
            val = "";
        } else if (typeof val === "number") {
            val += "";
        } else if (isArray(val)) {
            val = map(val, function (value) {
                return value == null ? "" : value + "";
            });
        }

        hooks = jQuery.valHooks[this.type] || jQuery.valHooks[this.nodeName.toLowerCase()];

        // If set returns undefined, fall back to normal setting
        if (!hooks || !("set" in hooks) || hooks.set(this, val, "value") === undefined) {
            this.value = val;
        }
    });
}

//attr
function attr (name, value) {
    return access(this, attr, name, value, arguments.length > 1);
}

//html
function html (value) {
    return access(this, function (value) {
        var elem = this[0] || {},
            i = 0,
            l = this.length;

        if (value === undefined) {
            return elem.nodeType === 1 ? elem.innerHTML.replace(rinlinejQuery, "") : undefined;
        }

        // See if we can take a shortcut and just use innerHTML
        if (typeof value === "string" && !rnoInnerhtml.test(value) && (jQuery.support.htmlSerialize || !rnoshimcache.test(value)) && (jQuery.support.leadingWhitespace || !rleadingWhitespace.test(value)) && !wrapMap[(rtagName.exec(value) || ["", ""])[1].toLowerCase()]) {

            value = value.replace(rxhtmlTag, "<$1></$2>");

            try {
                for (; i < l; i++) {
                    // Remove element nodes and prevent memory leaks
                    elem = this[i] || {};
                    if (elem.nodeType === 1) {
                        cleanData(getAll(elem, false));
                        elem.innerHTML = value;
                    }
                }

                elem = 0;

                // If using innerHTML throws an exception, use the fallback method
            } catch(e) {}
        }

        if (elem) {
            this.empty().append(value);
        }
    },
    null, value, arguments.length);
}

//type
function type ( obj ) {
	if ( obj == null ) {
		return String( obj );
	}
	return typeof obj === "object" || typeof obj === "function" ?
		class2type[ core_toString.call(obj) ] || "object" :
		typeof obj;
}

//access
function access( elems, fn, key, value, chainable, emptyGet, raw ) {
	var i = 0,
		length = elems.length,
		bulk = key == null;

	// Sets many values
	if ( type( key ) === "object" ) {
		chainable = true;
		for ( i in key ) {
			access( elems, fn, i, key[i], true, emptyGet, raw );
		}

	// Sets one value
	} else if ( value !== undefined ) {
		chainable = true;

		if ( !jQuery.isFunction( value ) ) {
			raw = true;
		}

		if ( bulk ) {
			// Bulk operations run against the entire set
			if ( raw ) {
				fn.call( elems, value );
				fn = null;

			// ...except when executing function values
			} else {
				bulk = fn;
				fn = function( elem, key, value ) {
					return bulk.call( jQuery( elem ), value );
				};
			}
		}

		if ( fn ) {
			for ( ; i < length; i++ ) {
				fn( elems[i], key, raw ? value : value.call( elems[i], i, fn( elems[i], key ) ) );
			}
		}
	}

	return chainable ?
		elems :

		// Gets
		bulk ?
			fn.call( elems ) :
			length ? fn( elems[0], key ) : emptyGet;
}

//cleanData
function cleanData( elems, /* internal */ acceptData ) {
	var elem, type, id, data,
		i = 0,
		internalKey = jQuery.expando,
		cache = jQuery.cache,
		deleteExpando = jQuery.support.deleteExpando,
		special = jQuery.event.special;

	for ( ; (elem = elems[i]) != null; i++ ) {

		if ( acceptData || acceptData( elem ) ) {

			id = elem[ internalKey ];
			data = id && cache[ id ];

			if ( data ) {
				if ( data.events ) {
					for ( type in data.events ) {
						if ( special[ type ] ) {
							jQuery.event.remove( elem, type );

						// This is a shortcut to avoid jQuery.event.remove's overhead
						} else {
							jQuery.removeEvent( elem, type, data.handle );
						}
					}
				}

				// Remove cache only if it was not already removed by jQuery.event.remove
				if ( cache[ id ] ) {

					delete cache[ id ];

					// IE does not allow us to delete expando properties from nodes,
					// nor does it have a removeAttribute function on Document nodes;
					// we must handle all of these cases
					if ( deleteExpando ) {
						delete elem[ internalKey ];

					} else if ( typeof elem.removeAttribute !== core_strundefined ) {
						elem.removeAttribute( internalKey );

					} else {
						elem[ internalKey ] = null;
					}

					core_deletedIds.push( id );
				}
			}
		}
	}
}

//acceptData
function acceptData( elem ) {
	// Do not set data on non-element because it will not be cleared (#8335).
	if ( elem.nodeType && elem.nodeType !== 1 && elem.nodeType !== 9 ) {
		return false;
	}

	var noData = elem.nodeName && jQuery.noData[ elem.nodeName.toLowerCase() ];

	// nodes accept data unless otherwise specified; rejection can be conditional
	return !noData || noData !== true && elem.getAttribute("classid") === noData;
}

//text
function text (value) {
    return access(this, function (value) {
        return value === undefined ? text(this) : empty().append((this[0] && this[0].ownerDocument || document).createTextNode(value));
    },
    null, value, arguments.length);
}

//empty
function empty () {
	var elem,
		i = 0;

	for ( ; (elem = this[i]) != null; i++ ) {
		// Remove element nodes and prevent memory leaks
		if ( elem.nodeType === 1 ) {
			jQuery.cleanData( getAll( elem, false ) );
		}

		// Remove any remaining nodes
		while ( elem.firstChild ) {
			elem.removeChild( elem.firstChild );
		}

		// If this is a select, ensure that it displays empty (#12336)
		// Support: IE<9
		if ( elem.options && jQuery.nodeName( elem, "select" ) ) {
			elem.options.length = 0;
		}
	}

	return this;
}

//css
function css (name, value) {
    return access(this, function (elem, name, value) {
        var len, styles, map = {},
            i = 0;

        if (isArray(name)) {
            styles = getStyles(elem);
            len = name.length;

            for (; i < len; i++) {
                map[name[i]] = css(elem, name[i], false, styles);
            }

            return map;
        }

        return value !== undefined ? style(elem, name, value) : css(elem, name);
    },
    name, value, arguments.length > 1);
}

//isArray
function isArray( obj ) {
	return type(obj) === "array";
}

//style
function style ( elem, name, value, extra ) {
	// Don't set styles on text and comment nodes
	if ( !elem || elem.nodeType === 3 || elem.nodeType === 8 || !elem.style ) {
		return;
	}
}


//map
function map( elems, callback, arg ) {
	var value,
		i = 0,
		length = elems.length,
		isArray = isArraylike( elems ),
		ret = [];

	// Go through the array, translating each of the items to their
	if ( isArray ) {
		for ( ; i < length; i++ ) {
			value = callback( elems[ i ], i, arg );

			if ( value != null ) {
				ret[ ret.length ] = value;
			}
		}

	// Go through every key on the object,
	} else {
		for ( i in elems ) {
			value = callback( elems[ i ], i, arg );

			if ( value != null ) {
				ret[ ret.length ] = value;
			}
		}
	}

	// Flatten any nested arrays
	return core_concat.apply( [], ret );
}

//camelCase
function camelCase( string ) {
	return string.replace( rmsPrefix, "ms-" ).replace( rdashAlpha, fcamelCase );
}

//isEmptyDataObject
function isEmptyDataObject( obj ) {
	var name;
	for ( name in obj ) {

		// if the public data object is empty, the private is still empty
		if ( name === "data" && isEmptyObject( obj[name] ) ) {
			continue;
		}
		if ( name !== "toJSON" ) {
			return false;
		}
	}

	return true;
}

//isEmptyObject
function isEmptyObject( obj ) {
	var name;
	for ( name in obj ) {
		return false;
	}
	return true;
}

//internalRemoveData
function internalRemoveData( elem, name, pvt ) {
	if ( !acceptData( elem ) ) {
		return;
	}

	var i, l, thisCache,
		isNode = elem.nodeType,

		// See jQuery.data for more information
		cache = isNode ? jQuery.cache : elem,
		id = isNode ? elem[ jQuery.expando ] : jQuery.expando;

	// If there is already no cache entry for this object, there is no
	// purpose in continuing
	if ( !cache[ id ] ) {
		return;
	}

	if ( name ) {

		thisCache = pvt ? cache[ id ] : cache[ id ].data;

		if ( thisCache ) {

			// Support array or space separated string names for data keys
			if ( !isArray( name ) ) {

				// try the string as a key before any manipulation
				if ( name in thisCache ) {
					name = [ name ];
				} else {

					// split the camel cased version by spaces unless a key with the spaces exists
					name = camelCase( name );
					if ( name in thisCache ) {
						name = [ name ];
					} else {
						name = name.split(" ");
					}
				}
			} else {
				// If "name" is an array of keys...
				// When data is initially created, via ("key", "val") signature,
				// keys will be converted to camelCase.
				// Since there is no way to tell _how_ a key was added, remove
				// both plain key and camelCase key. #12786
				// This will only penalize the array argument path.
				name = name.concat( map( name, camelCase() ) );
			}

			for ( i = 0, l = name.length; i < l; i++ ) {
				delete thisCache[ name[i] ];
			}

			// If there is no data left in the cache, we want to continue
			// and let the cache object itself get destroyed
			if ( !( pvt ? isEmptyDataObject : jQuery.isEmptyObject )( thisCache ) ) {
				return;
			}
		}
	}

	// See jQuery.data for more information
	if ( !pvt ) {
		delete cache[ id ].data;

		// Don't destroy the parent cache unless the internal data object
		// had been the only thing left in it
		if ( !isEmptyDataObject( cache[ id ] ) ) {
			return;
		}
	}

	// Destroy the cache
	if ( isNode ) {
		jQuery.cleanData( [ elem ], true );

	// Use delete when supported for expandos or `cache` is not a window per isWindow (#10080)
	} else if ( jQuery.support.deleteExpando || cache != cache.window ) {
		delete cache[ id ];

	// When all else fails, null
	} else {
		cache[ id ] = null;
	}
}

//pushStack
function pushStack( elems ) {

	// Build a new jQuery matched element set
	var ret = merge( this.constructor(), elems );

	// Add the old object onto the stack (as a reference)
	ret.prevObject = this;
	ret.context = this.context;

	// Return the newly-formed element set
	return ret;
}

//merge
function merge( first, second ) {
	var l = second.length,
		i = first.length,
		j = 0;

	if ( typeof l === "number" ) {
		for ( ; j < l; j++ ) {
			first[ i++ ] = second[ j ];
		}
	} else {
		while ( second[j] !== undefined ) {
			first[ i++ ] = second[ j++ ];
		}
	}

	first.length = i;

	return first;
}
