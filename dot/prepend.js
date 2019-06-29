function prepend () {
    return this.domManip(arguments, true, function (elem) {
        if (this.nodeType === 1 || this.nodeType === 11 || this.nodeType === 9) {
            this.insertBefore(elem, this.firstChild);
        }
    });
}