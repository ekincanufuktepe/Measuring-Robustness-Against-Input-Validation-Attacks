var esprima = require('esprima'),
	fs = require('fs');
var code = fs.readFileSync('C:/Users/ekincan/InputAnalysisJS/TAJS/examples/TestCase_1.js');
var syntax = esprima.parse(code);
console.log(JSON.stringify(syntax, null, 4));
