<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="generator"
content="HTML Tidy for Java (vers. 2009-12-01), see jtidy.sourceforge.net" />
<title></title>
</head>
<body>
<script type="text/javascript">
//<![CDATA[
function f1()
{

function fA(a) {

	if(a <= 0) {
		return 0;
		}
	
	if(a % 2 == 0) {
	document.write("a Goes to fA"+a);
	document.write("<br>");
	
		fA(--a);
	} 
	
	else {
	document.write("a Goes to fB"+a);
	document.write("<br>");
		fB(--a);
	}
	return a;
}

function fB(b) {
	if(b <= 0) {return 0;}
	if(b % 2 == 0) {
	document.write("b Goes to fA"+b);
	document.write("<br>");
		fA(--b);
	} else {
	document.write("b Goes to fB"+b);
	document.write("<br>");
		fB(--b);
	}
	return b;
}

function fC(m) {
	
	var x,y,z;
	x = 3;
	y = 5;
	z = x*y;
	
	z = z + m
	return z;
}

function fD() {
	return 1;
}
}
//]]>
</script>
<div class="r_arow" id="topp" value="l"
onclick="cs1.startSlide('l','cs1');cs1.as=false;">a</div>
</body>
</html>

