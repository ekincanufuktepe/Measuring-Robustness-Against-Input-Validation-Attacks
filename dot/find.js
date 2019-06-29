function find (selector) {
    var i, ret, self, len = this.length;

    if (typeof selector !== "string") {
        self = this;
        return this.pushStack(jQuery(selector).filter(function () {
            for (i = 0; i < len; i++) {
                if (jQuery.contains(self[i], this)) {
                    return true;
                }
            }
        }));
    }

    ret = [];
    for (i = 0; i < len; i++) {
        jQuery.find(selector, this[i], ret);
    }

    // Needed because $( selector, context ) becomes $( context ).find( selector )
    ret = this.pushStack(len > 1 ? jQuery.unique(ret) : ret);
    ret.selector = (this.selector ? this.selector + " " : "") + selector;
    return ret;
}