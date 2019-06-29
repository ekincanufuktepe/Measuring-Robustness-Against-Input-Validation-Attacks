var assert = require('assert'),
	random = require('JavaScript-fuzz'),
	fs = require('fs'),
	vm = require('vm');
var countErrs = 0;

function include(path) {
	 var code = fs.readFileSync(path, 'utf-8');
	 vm.runInThisContext(code, path);
}
include('C:/Users/ekincan/InputAnalysisJS/TAJS/examples/TestCase_1.js');

function assertEqual(a, b, message)
{
	try {
		if (a != b)
			throw new Error(message + " mismatch: " + a + " != " + b);
		else if(a == RangeError)
			throw new Error(message + " mismatch: " + a + " != " + b);
		else
			console.log("passed");
	} catch (e) {
		console.log(e.message);  // 'Default Message'
		countErrs++;
	}
}

Array.prototype.allValuesSame = function() {
	for(var i = 1; i < this.length; i++)
	{
		if(this[i] !== this[0])
			return false;
	}
	return true;
}

function mode(array)
{
	if(array.length == 0)
		return null;
	var modeMap = {};	var maxEl = array[0], maxCount = 1;
	for(var i = 0; i < array.length; i++)
	{
		var el = array[i];
		if(modeMap[el] == null)
			modeMap[el] = 1;
		else
			modeMap[el]++;
		if(modeMap[el] > maxCount)
		{
			maxEl = el;
			maxCount = modeMap[el];
		}
	}
	return maxEl;
}

var ar = [];
var testCases = [undefined, null, true, false, random.string(5), NaN, -Infinity, Infinity, 5, -5, 0.15, -0.15, random.object.date(), Error, RangeError, ReferenceError, SyntaxError, TypeError, URIError, 1.3987869234598501e+308, random.object.regexp()];

for(i0=0; i0<testCases.length; i0++)
{
	for(i1=0; i1<testCases.length; i1++)
	{
		ar.push(typeof fA(testCases[i0],testCases[i1]));
	}
}

//console.log(ar);
console.log("FUNCTION: [fA] Result: "+ar.allValuesSame()+ ', Expected Return: '+mode(ar));
ar.length = 0;


for(i0=0; i0<testCases.length; i0++)
{
	for(i1=0; i1<testCases.length; i1++)
	{
		ar.push(typeof fB(testCases[i0],testCases[i1]));
	}
}

//console.log(ar);
console.log("FUNCTION: [fB] Result: "+ar.allValuesSame()+ ', Expected Return: '+mode(ar));
ar.length = 0;


for(i0=0; i0<testCases.length; i0++)
{
	for(i1=0; i1<testCases.length; i1++)
	{
		ar.push(typeof fC(testCases[i0],testCases[i1]));
	}
}

//console.log(ar);
console.log("FUNCTION: [fC] Result: "+ar.allValuesSame()+ ', Expected Return: '+mode(ar));
ar.length = 0;


for(i0=0; i0<testCases.length; i0++)
{
	for(i1=0; i1<testCases.length; i1++)
	{
		ar.push(typeof fD(testCases[i0],testCases[i1]));
	}
}

//console.log(ar);
console.log("FUNCTION: [fD] Result: "+ar.allValuesSame()+ ', Expected Return: '+mode(ar));
ar.length = 0;


