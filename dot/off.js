function off (types, selector, fn) {
    var handleObj, type;
    if (types && types.preventDefault && types.handleObj) {
        // ( event )  dispatched jQuery.Event
        handleObj = types.handleObj;
        jQuery(types.delegateTarget).off(
        handleObj.namespace ? handleObj.origType + "." + handleObj.namespace : handleObj.origType, handleObj.selector, handleObj.handler);
        return this;
    }
    if (typeof types === "object") {
        // ( types-object [, selector] )
        for (type in types) {
            this.off(type, selector, types[type]);
        }
        return this;
    }
    if (selector === false || typeof selector === "function") {
        // ( types [, fn] )
        fn = selector;
        selector = undefined;
    }
    if (fn === false) {
        fn = returnFalse;
    }
    return this.each(function () {
        jQuery.event.remove(this, types, fn, selector);
    });
}