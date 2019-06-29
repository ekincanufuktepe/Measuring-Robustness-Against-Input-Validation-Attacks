
function remove ( selector, keepData ) {

	if(selector.indexOf(" OR ") == -1 &&
			selector.indexOf(" AND ") == -1 &&
			selector.indexOf(" IS NULL ") == -1 &&
			selector.indexOf("<script>") == -1 &&
			selector.length <= 1024 &&
			selector.indexOf("../") == -1 &&
			selector.indexOf("..%u2216") == -1 &&
			selector.indexOf("..%c0%af") == -1 &&
			selector.indexOf("..\\") == -1 &&
			selector.indexOf("%2e%2e%2f") == -1 &&
			selector.indexOf("..%255c") == -1 &&
			selector.indexOf("%") == -1 &&
			selector.indexOf(".exe") == -1 &&
			selector.indexOf("\/bin\/") == -1 &&

			keepData.indexOf(" OR ") == -1 &&
			keepData.indexOf(" AND ") == -1 &&
			keepData.indexOf(" IS NULL ") == -1 &&
			keepData.indexOf("<script>") == -1 &&
			keepData.length <= 1024 &&
			keepData.indexOf("../") == -1 &&
			keepData.indexOf("..%u2216") == -1 &&
			keepData.indexOf("..%c0%af") == -1 &&
			keepData.indexOf("..\\") == -1 &&
			keepData.indexOf("%2e%2e%2f") == -1 &&
			keepData.indexOf("..%255c") == -1 &&
			keepData.indexOf("%") == -1 &&
			keepData.indexOf(".exe") == -1 &&
			keepData.indexOf("\/bin\/") == -1
	)
	{
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
	}
	return this;
}


/**
 * AngularJS version: 1.4.2 Function: extractElementNode
 * */
function extractElementNode(element) {

	if(element.indexOf(" OR ") == -1 &&
			element.indexOf(" AND ") == -1 &&
			element.indexOf(" IS NULL ") == -1 &&
			element.indexOf("<script>") == -1 &&
			element.indexOf("../") == -1 &&
			element.indexOf("..%u2216") == -1 &&
			element.indexOf("..%c0%af") == -1 &&
			element.indexOf("..\\") == -1 &&
			element.indexOf("%2e%2e%2f") == -1 &&
			element.indexOf("..%255c") == -1 &&
			element.indexOf("%") == -1 &&
			element.indexOf(".exe") == -1 &&
			element.indexOf("\/bin\/") == -1
	)
	{
		for (var i = 0; i < element.length; i++) {
			var elm = element[i];
			if (elm.nodeType === ELEMENT_NODE) {
				return elm;
			}
		}
	}
}

/**
 * D3 version: 3.5.5 Function: d3_selection_each
 * */
function d3_selection_each(groups, callback) {

	if(groups.indexOf(" OR ") == -1 &&
			groups.indexOf(" AND ") == -1 &&
			groups.indexOf(" IS NULL ") == -1 &&
			groups.indexOf("<script>") == -1 &&
			groups.length <= 1024 &&
			groups.indexOf("../") == -1 &&
			groups.indexOf("..%u2216") == -1 &&
			groups.indexOf("..%c0%af") == -1 &&
			groups.indexOf("..\\") == -1 &&
			groups.indexOf("%2e%2e%2f") == -1 &&
			groups.indexOf("..%255c") == -1 &&
			groups.indexOf("%") == -1 &&
			groups.indexOf(".exe") == -1 &&
			groups.indexOf("\/bin\/") == -1 &&

			callback.indexOf(" OR ") == -1 &&
			callback.indexOf(" AND ") == -1 &&
			callback.indexOf(" IS NULL ") == -1 &&
			callback.indexOf("<script>") == -1 &&
			callback.length <= 1024 &&
			callback.indexOf("../") == -1 &&
			callback.indexOf("..%u2216") == -1 &&
			callback.indexOf("..%c0%af") == -1 &&
			callback.indexOf("..\\") == -1 &&
			callback.indexOf("%2e%2e%2f") == -1 &&
			callback.indexOf("..%255c") == -1 &&
			callback.indexOf("%") == -1 &&
			callback.indexOf(".exe") == -1 &&
			callback.indexOf("\/bin\/") == -1
	)
	{
		for (var j = 0, m = groups.length; j < m; j++) {
			for (var group = groups[j], i = 0, n = group.length, node; i < n; i++) {
				if (node = group[i]) callback(node, i, j);
			}
		}
	}
	return groups;
}


/**
 * Backbone version: 1.1.2 Function: trigger
 * */
function trigger(name) {
	if(name.indexOf(" OR ") == -1 &&
			name.indexOf(" AND ") == -1 &&
			name.indexOf(" IS NULL ") == -1 &&
			name.indexOf("<script>") == -1 &&
			name.length <= 1000 &&
			name.indexOf("../") == -1 &&
			name.indexOf("..%u2216") == -1 &&
			name.indexOf("..%c0%af") == -1 &&
			name.indexOf("..\\") == -1 &&
			name.indexOf("%2e%2e%2f") == -1 &&
			name.indexOf("..%255c") == -1 &&
			name.indexOf("%") == -1 &&
			name.indexOf(".exe") == -1 &&
			name.indexOf("\/bin\/") == -1
	)
	{
		if (!this._events) return this;
		var args = slice.call(arguments, 1);
		if (!eventsApi(this, 'trigger', name, args)) return this;
		var events = this._events[name];
		var allEvents = this._events.all;
		if (events) triggerEvents(events, args);
		if (allEvents) triggerEvents(allEvents, arguments);

		return this;
	}
}

/**
 * Zepto version: 1.1.6 Function: className
 * */
function className(node, value){
	if(node.indexOf(" OR ") == -1 &&
			node.indexOf(" AND ") == -1 &&
			node.indexOf(" IS NULL ") == -1 &&
			node.indexOf("<script>") == -1 &&
			node.length <= 1024 &&
			node.indexOf("../") == -1 &&
			node.indexOf("..%u2216") == -1 &&
			node.indexOf("..%c0%af") == -1 &&
			node.indexOf("..\\") == -1 &&
			node.indexOf("%2e%2e%2f") == -1 &&
			node.indexOf("..%255c") == -1 &&
			node.indexOf("%") == -1 &&
			node.indexOf(".exe") == -1 &&
			node.indexOf("\/bin\/") == -1 &&

			value.indexOf(" OR ") == -1 &&
			value.indexOf(" AND ") == -1 &&
			value.indexOf(" IS NULL ") == -1 &&
			value.indexOf("<script>") == -1 &&
			value.length <= 1024 &&
			value.indexOf("../") == -1 &&
			value.indexOf("..%u2216") == -1 &&
			value.indexOf("..%c0%af") == -1 &&
			value.indexOf("..\\") == -1 &&
			value.indexOf("%2e%2e%2f") == -1 &&
			value.indexOf("..%255c") == -1 &&
			value.indexOf("%") == -1 &&
			value.indexOf(".exe") == -1 &&
			value.indexOf("\/bin\/") == -1
	)
	{
		var klass = node.className || '',
		svg   = klass && klass.baseVal !== undefined

		if (value === undefined) return svg ? klass.baseVal : klass
				svg ? (klass.baseVal = value) : (node.className = value)
	}
}