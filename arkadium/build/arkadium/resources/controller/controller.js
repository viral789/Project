(function () {

var controller = angular.module('Controller', ['Service']);

//------------------------------index.html controller-----------------------------------
controller.controller('indexController', ['$scope', '$state', '$http', function($scope, $state, $http){
	$scope.active = function(route){
		return $state.is(route);
	}
	
	$scope.tabs =[
      { heading: "Quiz", route:"quiz", active:true },
      { heading: "Answer", route:"answer", active:false }
	];
	
	$state.go("quiz");
}]);

//------------------------------quiz.html controller-----------------------------------
controller.controller('quizController', ['$scope', '$state', 'movieDatabase', '$http', '$rootScope', function($scope, $state, movieDatabase, $http, $rootScope){

	$scope.questions = [
	   {
		   "id" : "1", "question": "When was this movie released?", "answer": "release_date"
	   },
	   {
		   "id" : "2", "question": "What was the main overview of the movie?", "answer": "overview"
	   },
	   {
		   "id" : "3", "question": "Was this movie an adult movie?", "answer": "adult"
	   },
	   {
		   "id" : "4", "question": "In which language was this movie made?", "answer": "original_language"
	   },
	]
	
	$rootScope.randomQuestion =  $scope.questions[Math.floor(Math.random() * $scope.questions.length)];
	
	movieDatabase.getMovieData().then(function(response){
		$scope.movieData = response.data;
		$rootScope.randomMovieData = $scope.movieData.results[Math.floor(Math.random() * $scope.movieData.results.length)];
		
		 $http({
			    method: 'GET',
			    url: 'https://image.tmdb.org/t/p/w500' + $scope.randomMovieData.poster_path,
			    responseType: 'arraybuffer'
			  }).then(function(response) {
			    $scope.image = _arrayBufferToBase64(response.data);   // $scope.image is base64 encoded.
			  }, function(response) {
			    console.error('Error in getting static image');
		});
		 		
		 var _arrayBufferToBase64 = function(buffer) {
		    var binary = '';
		    var bytes = new Uint8Array(buffer);
		    var len = bytes.byteLength;
		    for (var i = 0; i < len; i++) {
		      binary += String.fromCharCode(bytes[i]);
		    }
		    return window.btoa(binary);
		  }
		 
		 $scope.submit = function(){
			 $rootScope.userAnswer = $scope.answer;
			 $rootScope.image = $scope.image;
			 $state.go("answer");
		 }
	})
}]);

//------------------------------answer.html controller-----------------------------------
controller.controller('answerController', ['$scope', '$state', '$rootScope', function($scope, $state, $rootScope){
	if($rootScope.userAnswer != null){
		$scope.isAnswer = true;
		var answer = $rootScope.randomQuestion.answer;
		 switch(answer){
		 	case "release_date" : 
		 		$scope.actualAnswer = $rootScope.randomMovieData.release_date;
		 		break;
		 	case "overview" : 
		 		$scope.actualAnswer = $rootScope.randomMovieData.overview;
		 		break;
		 	case "adult" : 
		 		$scope.actualAnswer = $rootScope.randomMovieData.adult;
		 		break;
		 	case "original_language" : 
		 		$scope.actualAnswer = $rootScope.randomMovieData.original_language;
		 		if($scope.actualAnswer == "en"){
		 			$scope.actualAnswer = "English"
		 		}
		 		break;
		 }
		 
		 $scope.play = function(){
			 $state.go("quiz");
			 $scope.actualAnswer = "";
		 }
	} 
	else{
		alert('Please answer the Quiz question');
		 $state.go("quiz");
	}
}]);

}());