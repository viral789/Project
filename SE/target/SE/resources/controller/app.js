(function () {

var app = angular.module('app',['ui.router', 'ui.bootstrap.tpls', 'ui.bootstrap', 'Controller']);

/**
 * Controller to control which controller to call for which html file
 */
app.config(['$stateProvider','$locationProvider', function ($stateProvider) {
	
	    $stateProvider
	        .state('index', {
	        	url: '/',
	            templateUrl: 'resources/views/index.html',
	            controller: 'indexController'
	        })
	        .state('home', {
	        	url: '/',
	        	templateUrl: 'resources/views/home.html',
	        	controller: 'homeController'
	        })
	        .state('bookDepot', {
	        	url: '/',
	        	templateUrl: 'resources/views/bookDepot.html',
	        	controller: 'bookDepotController'
	        })
	        .state('addBook', {
	        	url: '/',
	        	templateUrl: 'resources/views/addBook.html',
	        	controller: 'addBookController'
	        })
	        .state('checkout', {
	        	url: '/',
	        	templateUrl: 'resources/views/checkout.html',
	        	controller: 'checkoutController'
	        })
	        .state('requestBook', {
	        	url: '/',
	        	templateUrl: 'resources/views/requestBook.html',
	        	controller: 'requestBookController'
	        }) 
	        .state('checkoutBookForTeacher', {
	        	url: '/',
	        	templateUrl: 'resources/views/checkoutBookForTeacher.html',
	        	controller: 'checkoutBookForTeacherController'
	        })
	        .state('bookWithStudent', {
	        	url: '/',
	        	templateUrl: 'resources/views/bookWithStudent.html',
	        	controller: 'bookWithStudentController'
	        })
	        .state('studentReturnBook', {
	        	url: '/',
	        	templateUrl: 'resources/views/returnBook.html',
	        	controller: 'returnBookController'
	        })
	        .state('studentPenalty', {
	        	url: '/',
	        	templateUrl: 'resources/views/studentPenalty.html',
	        	controller: 'studentPenaltyController'
	        });
	    
	    
	    
}]);

}());