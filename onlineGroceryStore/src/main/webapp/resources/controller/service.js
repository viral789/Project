(function () {

var service = angular.module('Service', []);

service.service('urlName', function($http){
	this.getUrl = function(){
		var promise = $http({
			method : 'GET',
	        url : 'resources/scripts/url.property'
		})
		.success(function(data){
			return data;
		});
		return promise;
	};
});

}());