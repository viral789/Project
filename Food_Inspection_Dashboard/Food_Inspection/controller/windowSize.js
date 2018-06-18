function getWindowDimension() {
	    // Get the dimensions of the viewport
	    var width = window.innerWidth ||
	                document.documentElement.clientWidth ||
	                document.body.clientWidth;
	    var height = window.innerHeight ||
	                 document.documentElement.clientHeight ||
	                 document.body.clientHeight;
	    
	    return height + "||" + width;
}