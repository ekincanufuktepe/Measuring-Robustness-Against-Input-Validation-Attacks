
var asf, asdgq;

var qwe;

function fA(a, test1) {

	var abc;
	document.write("This is global variable qwe"+qwe);
	//test1 = a + 10;
	abc = abc + qwe;
	if(a <= 0) {return 0;}
	if(a % 2 == 0) {
		document.write("a Goes to fA"+a);
		document.write("<br>");
		a = a + test1;
		fA(--a);
	} else {
		document.write("a Goes to fB"+a);
		document.write("<br>");
		fB(--a);
	}


	return a;
}

function fB(b) {
	var mnm;

	mnm = "hey";
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
	
	mnm = mnm + " added ";
	if(asf != null)
	{
		asf = asf+" concat"+ mnm;
	}
	else
	{
		asf = "this was null, not anymore";
	}
	return b;
}

function fC(m, s, q, as) {

	var x,y,z;
	
	z = z + q;

	if(q != null || s != null || m != null)
	{
		qwe = m +4;
		fA(3);

		as = q *s;
	}

	var t1 =1;
	
	x = 3;
	y = 5;
	var t2;
	z = x*y;
	t2 = t1 + z;
	
	
	return z;
}

function fD() {
	fC(22);
	return 1;
}

fA();
fB();
fC();
fD();

