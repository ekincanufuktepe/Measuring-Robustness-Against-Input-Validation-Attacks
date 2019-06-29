function css (name, value) {
    return jQuery.access(this, function (elem, name, value) {
        var len, styles, map = {},
            i = 0;

        if (jQuery.isArray(name)) {
            styles = getStyles(elem);
            len = name.length;

            for (; i < len; i++) {
                map[name[i]] = jQuery.css(elem, name[i], false, styles);
            }

            return map;
        }

        return value !== undefined ? jQuery.style(elem, name, value) : jQuery.css(elem, name);
    },
    name, value, arguments.length > 1);
}