var x;
function fA(a)
{
	fC();
	return a;
}
function fB(str)
{
	if(str == null)
		x = str + "init";
}
function fC(c)
{
	if(isNaN(c))
	{
		//alert("POP!");
		return -1;
	}
	else
		return c;
}

function fD(a, b, c)
{
	var hello = "h";
	
	if(a == hello)
	{
		return true;
	}
	else
	{
		return false; 
	}
}