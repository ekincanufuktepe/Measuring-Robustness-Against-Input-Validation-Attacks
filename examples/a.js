var assert = require('assert'),
    random = require('JavaScript-fuzz');
	//jsdom = require('jsdom').jsdom;

var countErrs = 0;
	
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
	  //console.log(e.name);     // 'MyError'
	  console.log(e.message);  // 'Default Message'
	  countErrs++;
	}
    
}
	
//assert.equal(typeof random.boolean(), 'boolean');

//assert.equal(typeof dummy(random.number()), 'number');
//console.log(dummy(random.string(5)));
//assert.equal(typeof fA(random.undefined()), 'number');
//assert.equal(typeof dummy(random.string(5)), 'number');
//var m = assert.ok(typeof dummy(random.string(5)), 'number');

Array.prototype.allValuesSame = function() {

    for(var i = 1; i < this.length; i++)
    {
        if(this[i] !== this[0])
            return false;
    }

    return true;
}

var ar = [];
ar.push(typeof dummy(random.string(5)));
ar.push(typeof dummy(random.null()));
ar.push( typeof dummy(random.boolean()));
console.log(ar);
console.log("Result: "+ar.allValuesSame());
console.log('\n\n\n');
ar.length = 0;

ar.push(typeof fA(random.string(5)));
ar.push(typeof fA(random.null()));
ar.push(typeof fA(random.boolean()));
console.log(ar.allValuesSame());


/*var toClass2 = typeof dummy(random.null());
console.log(toClass2);

var toClass3 = typeof dummy(random.boolean());
console.log(toClass3);
*/
assertEqual(typeof dummy(random.string(5)), 'number');
assertEqual(typeof dummy(random.null()), 'number');
assertEqual(typeof dummy(random.boolean()), 'number');
console.log('\n');
assertEqual(typeof fA(random.string(5)), 'number');
assertEqual(typeof fA(random.null()), 'number');
assertEqual(typeof fA(random.boolean()), 'number');
console.log('\n');
assertEqual(typeof fB(random.string(5)), 'number');
assertEqual(typeof fB(random.null()), 'number');
assertEqual(typeof fB(random.boolean()), 'number');
//console.log(assertEqual(typeof dummy(random.object()), 'number'));
//console.log(assertEqual(typeof dummy(random.string(5)), 'number'));
console.log('\n');


console.log("Total Errors: "+countErrs);
//assert.equal(typeof fB(random.number()), 'number');

/*console.log(random.number());
console.log(random.undefined());
console.log(random.string(10));
console.log(random.null());
console.log(random.object.simple(10));*/

//console.log(dummy(random.boolean()));
//console.log(dummy(random.string(5)));
//console.log(assert.equal(typeof dummy(random.number()), 'number'));
//console.log(assert.fail(typeof dummy(random.number()), 'number'));

function dummy(a)
{
	console.log(a);
	if(isNaN(a))
	{
		return -1;
	}
	else{
		var x = 0;
		x = x + a;
		
		return x;
	}
}

function fA(a) {
	
	//if(a <= 0 || isNaN(a))	
	if(a <= 0) 
	{
		return 0;
	}
	
	if(a % 2 == 0) {
		//a = a + 1;
		fA(--a);
	} else {
		fB(--a);
	}
}

function fB(b) {
	var mnm;
	//console.log("fB: "+b);
	mnm = "hey";
	//console.log(b);
	//if(b <= 0 || isNaN(b)) {
	if(b <= 0) {
		return 0;
	}
	else
	{
		return;
	}
	mnm = mnm + " added ";
	if(b % 2 == 0) {
		fA(--b);
	} 
	else {
		fB(--b);
	}
	
}