function jq_attr (elem, name, value) {
    var hooks, notxml, ret, nType = elem.nodeType;

    // don't get/set attributes on text, comment and attribute nodes
    if (!elem || nType === 3 || nType === 8 || nType === 2) {
        return;
    }

    // Fallback to prop when attributes are not supported
    if (typeof elem.getAttribute === core_strundefined) {
        return jQuery.prop(elem, name, value);
    }

    notxml = nType !== 1 || !jQuery.isXMLDoc(elem);

    // All attributes are lowercase
    // Grab necessary hook if one is defined
    if (notxml) {
        name = name.toLowerCase();
        hooks = jQuery.attrHooks[name] || (rboolean.test(name) ? boolHook : nodeHook);
    }

    if (value !== undefined) {

        if (value === null) {
            jQuery.removeAttr(elem, name);

        } else if (hooks && notxml && "set" in hooks && (ret = hooks.set(elem, value, name)) !== undefined) {
            return ret;

        } else {
            elem.setAttribute(name, value + "");
            return value;
        }

    } else if (hooks && notxml && "get" in hooks && (ret = hooks.get(elem, name)) !== null) {
        return ret;

    } else {

        // In IE9+, Flash objects don't have .getAttribute (#12945)
        // Support: IE9+
        if (typeof elem.getAttribute !== core_strundefined) {
            ret = elem.getAttribute(name);
        }

        // Non-existent attributes return null, we normalize to undefined
        return ret == null ? undefined : ret;
    }
}