fA(26);
fB(33);
fC();

function fA(a) {
	if(a <= 0) {return 0;}
	if(a % 2 == 0) {
	document.write("a Goes to fA"+a);
	document.write("<br>");
		fA(--a);
	} else {
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

function fC() {
	return -1;
}
