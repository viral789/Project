(function () {

var app = angular.module('app',['ui.router', 'ui.bootstrap.tpls', 'ui.bootstrap', 'Controller', 'Service']);

app.config(['$stateProvider','$locationProvider', function ($stateProvider) {
	
	$stateProvider
		.state('quiz', {
			url: '/',
		    templateUrl: 'resources/views/quiz.html',
		    controller: 'quizController'
		})
		.state('answer', {
			url: '/',
		    templateUrl: 'resources/views/answer.html',
		    controller: 'answerController'
		});
}]);

}());