function attr (name, value) {
	
//	if(name == null)
//	{
//		return null;
//	}
//	if(value == null)
//	{
//		return null;
//	}
    return jQuery.access(this, jQuery.attr, name, value, arguments.length > 1);
}