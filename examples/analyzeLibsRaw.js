
/**
 * jQuery version 1.9.1 Functions: remove
 * */

function remove ( selector, keepData ) {
	var elem,
	i = 0;

	for ( ; (elem = this[i]) != null; i++ ) {
		if ( !selector || jQuery.filter( selector, [ elem ] ).length > 0 ) {
			if ( !keepData && elem.nodeType === 1 ) {
				jQuery.cleanData( getAll( elem ) );
			}

			if ( elem.parentNode ) {
				if ( keepData && jQuery.contains( elem.ownerDocument, elem ) ) {
					setGlobalEval( getAll( elem, "script" ) );
				}
				elem.parentNode.removeChild( elem );
			}
		}
	}

	return this;
}

/**
 * AngularJS version: 1.4.2 Function: extractElementNode
 * */
function extractElementNode(element) {
	  for (var i = 0; i < element.length; i++) {
	    var elm = element[i];
	    if (elm.nodeType === ELEMENT_NODE) {
	      return elm;
	    }
	  }
	}

/**
 * D3 version: 3.5.5 Function: d3_selection_each
 * */
function d3_selection_each(groups, callback) {
    for (var j = 0, m = groups.length; j < m; j++) {
      for (var group = groups[j], i = 0, n = group.length, node; i < n; i++) {
        if (node = group[i]) callback(node, i, j);
      }
    }
    return groups;
  }

/**
 * Backbone version: 1.1.2 Function: trigger
 * */
function trigger(name) {
    if (!this._events) return this;
    var args = slice.call(arguments, 1);
    if (!eventsApi(this, 'trigger', name, args)) return this;
    var events = this._events[name];
    var allEvents = this._events.all;
    if (events) triggerEvents(events, args);
    if (allEvents) triggerEvents(allEvents, arguments);
    return this;
  }

/**
 * Zepto version: 1.1.6 Function: className
 * */
function className(node, value){
    var klass = node.className || '',
        svg   = klass && klass.baseVal !== undefined

    if (value === undefined) return svg ? klass.baseVal : klass
    svg ? (klass.baseVal = value) : (node.className = value)
  }