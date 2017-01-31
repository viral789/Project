(function () {

var filter = angular.module('Filter', []);

filter.filter('pageDisplay',function(){
    return function(input,recordsPerPage,currentIndex){
    	if (!input || !input.length) { return; }
        return input.slice(parseInt(currentIndex * recordsPerPage), parseInt((currentIndex + 1)*recordsPerPage + 1) - 1);
    }
});

/*filter.filter('productImages',function(){
	return function(input){
		return 'resources/images/'+input+'.jpg'
	}
});*/

}());