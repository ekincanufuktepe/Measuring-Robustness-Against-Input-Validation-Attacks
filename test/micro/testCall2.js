var x = {q: 42, m: function() {return this.q;}}

with (x) {
	var y = m();
	dumpValue(y);
}
