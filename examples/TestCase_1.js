
var qq,ww,ee;
var rr,tt,asf;
var qwe, asdgq, test1;

function fA(a1, a2) {
	
	if(a1 != null && a2 != null)
	{
		alert("both are null")
	}
	
}

function fB(b1, b2) {
	
	if(b1 != null || b2 != null)
		{
			alert("at least 1 null")
		}
	
}

function fC(c1, c2) {
	
	if(c1 != null)
	{
		alert("c1 null")
	}
	else if(c2 != null)
	{
		alert("c2 null")
	}
	else
		alert("no null")
	
}

function fD(d1, d2) {
	var sum = 0;
	if(d1 != null && d2 != null)
	{
		sum = d1+d2;
	}
}

//function fA(a) {
//
//	//	var abc;
//	//	test1 = a + 10;
//	//	abc = abc + asf;
//
//
//	if(a <= 0) {return 0;}
//
//
//	if(a % 2 == 0) {
//		document.write("a Goes to fA"+a);
//		document.write("<br>");
//		a = a + test1;
//		fA(--a);
//	} else {
//		document.write("a Goes to fB"+a);
//		document.write("<br>");
//		fB(--a);
//	}
//
//
//	return a;
//}
//
//function fB(b) {
//	var mnm;
//
//	mnm = "hey";
//	if(b <= 0) {return 0;}
//	if(b % 2 == 0) {
////		document.write("b Goes to fA"+b);
////		document.write("<br>");
//		fA(--b);
//	} else {
////		document.write("b Goes to fB"+b);
////		document.write("<br>");
//		fB(--b);
//	}
//
//	mnm = mnm + " added ";
//	if(asf != null)
//	{
//		asf = asf+" concat"+ mnm;
//	}
//	else
//	{
//		asf = "this was null, not anymore";
//	}
//	return b;
//}
//
//function fC(m, s, q, as) {
//	fD();
//	var x,y,z;
//
////	z = m + q;
//	
//
//	if((q != null || s != null) &&  m != null)
//	{
//		qwe = m +qwe;
//		fA(3);
//
//		as = q *q;
//	}
//
//	if(s != null)
//	{
//		qwe = 5+5;
//	}
//
//	var t1 =1;
//
//	x = 3;
//	y = 5;
//	var t2;
//	z = x*y;
//	t2 = t1 + z;
//
//
//	return z;
//}
//
//function fD( d ) {
//	
//	fC(1,2,3,4);
//	
//	var t = d;
//	
//	if(t != null)
//	{
//		alert("Check taint analysis");
//	}
//		
//	
//	var r = asdgq + 3;
//	fB(10);
//	return 1;
//}
//
//
//function fE(inp){
//
//	fA(2);
//	while(inp.length != 0)
//	{
//		ww = inp + qwe;
//	}
//}
//
//function fF(x, y, z, q){
//
////	var x = bnh * ee;
////	var x = 3 + ee;
////	if(ww != null)
////	{
////		var x = 54 + ww;
////	}
////	fG();
//	var s = 0;
//	if(x != 0)
//	{
//		s = x*x;
//		if(y == Infinity)
//		{
//			document.write("To infinity and beyond!");
//		}
//	}
//	
//	if(q != null)
//	{
//		y = 0;
//	}
//	
//	z = z + s;
//	
//	return z;
//	
//}
//
//function fG(a, b){
//	var x;
//	var c = "ads"; 
//	
//	if(a != null)
//	{
//		alert("a is not null");
//	}
//	
//	c = b*b;
//	document.write("b square: "+c);
//	
//}
//
//function fM(a, b, c){
//	
//	for(i=0; i<a; i++)
//	{
////		b = b + 1;
//	}
//	if(a != null)
//	{
//		if(isNaN(b))
//		{
//			document.write("b is not a number");
//		}
//		else
//		{
//			document.write("b ="+b);
//		}
//		
//		c = c - b;
//		alert("c value popped!");
//	}
//	else if(a == null)
//	{
//		alert("a is null")
//	}
//
//}

//function fN(n1, n2, n3, n4)
//{
//	if(n1.indexOf("test", 3))
//	{
//		alert("test Found")
//	}
//	if(n2.lastIndexOf("error"))
//	{
//		document.write("Error!");
//	}
////	for(i=0; i<n3.length; i++)
////	{
////		alert(1);
////	}
//	
//	if(n3.length<=100)
//	{
//		alert("ok");
//	}
//	
//	while(n4.substring("<script>"))
//	{
//		alert(2);
//	}
//}