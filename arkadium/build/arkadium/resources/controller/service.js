(function () {

var service = angular.module('Service', []);

service.service('movieDatabase', function($http){
	this.getMovieData = function(){
		var promise = $http({
			method : 'GET',
	        url : 'resources/json/themoviedb_data.json'
		})
		.then(function(data){
			return data;
		});
		return promise;
	};
});

}());