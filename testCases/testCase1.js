var assert = require('assert'),
	random = require('JavaScript-fuzz');

/****************************************************/
/**START FOR THE SOLUTION TO RUN EXTERNAL .JS FILES**/
/****************************************************/

var fs = require("fs"),
	vm = require("vm");
//var t = require("./test");

function include(path) {
    var code = fs.readFileSync(path, 'utf-8');
    vm.runInThisContext(code, path);
}

include('./test.js');

/**************************************************/
/**END FOR THE SOLUTION TO RUN EXTERNAL .JS FILES**/
/**************************************************/
var countErrs = 0;

function assertEqual(a, b, message)
{
	try {
		if (a != b)
			throw new Error(message + " mismatch: " + a + " != " + b);		else if(a == RangeError)
			throw new Error(message + " mismatch: " + a + " != " + b);
		else
			console.log("passed");
	} catch (e) {
		console.log(e.message);  // 'Default Message'
		countErrs++;
	}}

Array.prototype.allValuesSame = function() {
	for(var i = 1; i < this.length; i++)
	{
		if(this[i] !== this[0])
			return false;
	}
	return true;
}

var ar = [];
var testCases = [undefined, null, true, false, random.string(5)];

for(i0=0; i0<testCases.length; i0++)
{
	ar.push(typeof fA(testCases[i0]));
}

console.log(ar);
console.log("Result: "+ar.allValuesSame());
ar.length = 0;


for(i0=0; i0<testCases.length; i0++)
{
	ar.push(typeof fB(testCases[i0]));
}

console.log(ar);
console.log("Result: "+ar.allValuesSame());
ar.length = 0;


for(i0=0; i0<testCases.length; i0++)
{
	for(i1=0; i1<testCases.length; i1++)
	{
		for(i2=0; i2<testCases.length; i2++)
		{
			ar.push(typeof fD(testCases[i0],testCases[i1],testCases[i2]));
		}
	}
}

console.log(ar);
console.log("Result: "+ar.allValuesSame());
ar.length = 0;


