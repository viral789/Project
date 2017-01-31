(function(){
	var app = angular.module('app', ['ui.router', 'ui.bootstrap.tpls', 'ui.bootstrap', 'Controller', 'Service', 'Filter', 'Directive']);
	
	app.config(['$stateProvider', function($stateProvider){
		$stateProvider
			.state('index', {
	        	url: '/',
	            templateUrl: 'resources/views/index.html',
	            controller: 'indexController'
	        })
			.state('home',{
				url: '/home',
	        	templateUrl : 'resources/views/home.html',
	        	controller : 'homeController'
			})
			.state('payment',{
				url: '/payment',
	        	templateUrl : 'resources/views/payment.html',
	        	controller : 'paymentController'
			})
			.state('addProduct',{
				url: '/addProduct',
	        	templateUrl : 'resources/views/addProduct.html',
	        	controller : 'addProductController'
			})
	}]);
}());