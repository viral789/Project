(function () {

var controller = angular.module('Controller', []);

//------------------------------index.html controller-----------------------------------
controller.controller('indexController', ['$scope', '$state', '$modal', '$rootScope', function($scope, $state, $modal, $rootScope){
	 
	urlBase = "http://localhost:8080/SE"
	$rootScope.$on('authenticate', function(event, flag){
		$scope.authenticate = flag;
	});
	
	$scope.type = "";
	
	$rootScope.$on('userAuthenticate', function(event, flag){
		$scope.userName = flag.fName;
		$scope.type = flag.type;
		$rootScope.userId = flag.userId;
		$rootScope.user = flag;
	});
	
	$scope.active = function(route){
		return $state.is(route);
	}
	
	$scope.tabs =[
      { heading: "Home", route:"home", active:true },
      { heading: "Book Depot", route:"bookDepot", active:false },
      { heading: "Add Book", route:"addBook", active:false },
      { heading: "Checkout Book", route:"checkout", active:false },
      { heading: "Books with Student", route:"bookWithStudent", active:false },
      { heading: "Return book", route:"studentReturnBook", active:false },
      { heading: "Penalty List", route:"studentPenalty", active:false },
	];
	
	$scope.$on("$stateChangeSuccess", function() {
		$scope.tabs.forEach(function(tab) {
			tab.active = $scope.active(tab.route);
		});
	});
	
	 $state.go("home");
	 
	$scope.signin = function(){
		$modal.open({
            templateUrl: 'resources/views/login.html',
            controller: 'loginController',
            size: 'sm',
            windowClass: 'center-modal'
        });
	}
	
	$scope.editInformation = function(){
		
		$modal.open({
            templateUrl: 'resources/views/editUsersInformation.html',
            controller: 'editUsersInformationController',
            backdrop: 'static', 
            windowClass: 'center-modal'
        });
	}
	
	$scope.logout = function(){
		$scope.authenticate = false;
		$scope.type = "";
		$state.reload();
		$state.go("home");
	}
	
	$scope.requestBook = function(){
		$state.go("requestBook")
	}
	
	$scope.checkoutBookForTeacher = function(){
		$state.go("checkoutBookForTeacher");
	}
}]);

//------------------------------home.html controller--------------------------------------
controller.controller('homeController', ['$scope', '$state', function($scope, $state){

	$scope.timings=[
       {id: "1", day: "", time: "Timings"},
       {id: "2", day: "Sunday", time:"12pm - overnight"},
       {id: "3", day: "Monday-Thrusday", time: "24 hours"},
       {id: "4", day: "Friday", time:"until 7 pm"},
       {id: "5", day: "Saturday", time: "8.30am - 5.30pm"}
	];
	
}]);

//------------------------------login.html controller--------------------------------------
controller.controller('loginController', ['$scope', '$state', '$modal', '$modalInstance', '$http', '$rootScope', function($scope, $state, $modal, $modalInstance, $http, $rootScope){

	$scope.submitForm = function(emailId, password){
		$scope.user = {};
		$scope.user.emailId = emailId.toLowerCase();
		$scope.user.password = password;
		$http.post(urlBase+'/users/login', $scope.user)
		.success(function(data){
			$scope.userAuthenticate = data;
			$rootScope.$emit('authenticate', true);
			$rootScope.$emit('userAuthenticate', $scope.userAuthenticate);
		})
		.error(function(){
			alert("Error while authenticating!");
		});
		$modalInstance.close();
	}
	
	$scope.cancelForm = function(){
		$modalInstance.dismiss();
	}
	
	$scope.registration = function(){
		$modalInstance.dismiss();
		$modal.open({
            templateUrl: 'resources/views/registration.html',
            controller: 'registrationController',
            backdrop: 'static',
            windowClass: 'center-modal'
        });
	}
}]);

//------------------------------registration.html controller--------------------------------------
controller.controller('registrationController', ['$scope', '$state', '$modalInstance', '$http', '$rootScope', function($scope, $state, $modalInstance, $http, $rootScope){

	
	$scope.user = {};
	$scope.user_student = {};
	$scope.type = null;
	$scope.userType =[{type: '1', role :'student'}, {type: '2', role :'teacher'}];
	$scope.repassword = null;
	
	$scope.cancelSignup = function(){
		$modalInstance.dismiss();
	}
	
	$scope.registration = function(){
		$modalInstance.close();
		$scope.user.type = $scope.type.type;
		$scope.user.emailId = $scope.user.emailId.toLowerCase();
		$scope.user_student.emailId = $scope.user.emailId.toLowerCase();
		$http.post(urlBase+'/users/insert', $scope.user)
		.success(function(data){
			$scope.userAuthenticate = data;
			$scope.user_student.userId = $scope.userAuthenticate.userId;
			$rootScope.$emit('authenticate', true);
			$rootScope.$emit('userAuthenticate', $scope.userAuthenticate);
			
			if($scope.userAuthenticate.type == "1"){
				$http.post(urlBase+'/student/insert', $scope.user_student)
				.success(function(data){
					$scope.user_student = data;
				})
				.error(function(){
					alert("Error while registration for student");
				});
			}
		})
		.error(function(){
			alert("Error while registration!");
		});
	}
}]);

//------------------------------editUsersInformation.html controller--------------------------------------
controller.controller('editUsersInformationController', ['$scope', '$state', '$modalInstance', '$http', '$rootScope', function($scope, $state, $modalInstance, $http, $rootScope){

	/*$rootScope.$on('userAuthenticate', function(event, flag){
		$scope.emailId = flag.emailId;
	});*/
	
	$scope.type = {};
	$scope.userType =[{type: '1', role :'student'}, {type: '2', role :'teacher'}];
	
	$http.get(urlBase+'/user/'+$rootScope.userId)
	.success(function(data){
		$scope.user = data;
		
		if($scope.user.type == "1"){
			$http.get(urlBase+'/student/'+$rootScope.userId)
			.success(function(data){
				$scope.user_student = data;
			})
			.error(function(){
				alert("Error while generating student data");
			});
		}
	})
	.error(function(){
		alert("Error while generating users data");
	});
	
	$scope.updateUser = function(){
		$modalInstance.close();
		$scope.user.type = $scope.type.type;
		$scope.user.emailId = $scope.user.emailId.toLowerCase();
		$scope.user_student.emailId = $scope.user.emailId.toLowerCase();
		$http.put(urlBase+'/users/update', $scope.user)
		.success(function(data){
			$scope.userAuthenticate = data;
			$rootScope.$emit('authenticate', true);
			$rootScope.$emit('userAuthenticate', $scope.userAuthenticate);
			$scope.user_student.userId = $scope.userAuthenticate.userId;
			
			if($scope.userAuthenticate.type == "1"){
				$http.put(urlBase+'/student/update', $scope.user_student)
				.success(function(data){
					$scope.user_student = data;
				})
				.error(function(){
					alert("Error while updating for student");
				});
			}
		})
		.error(function(){
			alert("Error while updating user!");
		});
	}
	
	$scope.cancelUpdate = function(){
		$modalInstance.dismiss();
	}
	
}]);

//------------------------------bookDepot.html controller--------------------------------------
controller.controller('bookDepotController', ['$scope', '$state', '$http', '$rootScope', function($scope, $state, $http, $rootScope){

	$scope.bookName="";
	var bookName = "software Engineering";
	$http.get(urlBase+'/searchBook/'+ bookName)
	.success(function(data){
		$scope.book = data;
	})
	.error(function(data){
		alert("Not able to get information about book!");
	});
	$rootScope.requestBook = [];
	
	$scope.searchBook= function(){
		$http.get(urlBase+'/searchBook/'+$scope.bookName)
		.success(function(data){
			$scope.book = data;
			$scope.bookName="";
		}).error(function(data){
			alert("Not able to retrieve book Information!")
		});
	}
	
	$http.get(urlBase+'/student/'+$rootScope.userId)
	.success(function(data){
		$scope.user_student = data;
	})
	.error(function(){
		alert("Error while generating student data");
	});
	
	$scope.checkout = function(){
		if(($rootScope.user.type == 1) && ($scope.user_student.noOfcoursesRegister > $rootScope.requestBook.length)){
			$rootScope.requestBook.push($scope.book);
			alert('Proceeded for request');
		}
		else if($rootScope.user.type == 2){
			$rootScope.requestBook.push($scope.book);
			alert('Proceeded for checkout');
		}
		else{
			alert('Cannot checkout! Reached total number of checkout limit.');
		}
	}
	
	
}]);

//------------------------------addBook.html controller--------------------------------------
controller.controller('addBookController', ['$scope', '$state', '$http', function($scope, $state, $http){

	$scope.bookName="";
	$scope.addbook = {};
	$scope.searchedbook = false;
	$scope.searchBook= function(){
		var bookName = $scope.bookName.split(' ').join('+');
		$scope.bookName="";
		$http.get("https://www.googleapis.com/books/v1/volumes?q="+bookName)
		.success(function(data){
			$scope.book = data;
			$scope.addbook.bookName = $scope.book.items[0].volumeInfo.title;
			$scope.addbook.isbn = $scope.book.items[0].volumeInfo.industryIdentifiers[0].identifier;
			$scope.addbook.author = $scope.book.items[0].volumeInfo.authors[0];
			$scope.addbook.publisher = $scope.book.items[0].volumeInfo.publisher;
			$scope.searchedbook = true;
		}).error(function(data){
			alert("Not able to retrieve book Information!")
		});
	}
	
	$scope.addbooktoDB = function(){
		$http.post(urlBase+'/books/insert', $scope.addbook)
		.success(function(data){
			console.log("books added");
			alert('Book added');
		})
		.error(function(){
			alert('Error while adding book into library');
		});
	}
	
	
}]);

//------------------------------requestBook.html controller--------------------------------------
controller.controller('requestBookController', ['$scope', '$state', '$http', '$rootScope', function($scope, $state, $http, $rootScope){

	$scope.requestBook = $rootScope.requestBook;
	$scope.removeBook = function($index){
		$scope.requestBook.splice($scope.requestBook[$index], 1);
	}
	$scope.requestedBook = [];
	$scope.bookRequest = function(){
		for (var i in $scope.requestBook){
			$scope.requestedBook.push($scope.requestBook[i]);
		}
		for(var i in $scope.requestedBook){
			$scope.requestedBook[i].studentName = $rootScope.user.fName;
			$scope.requestedBook[i].studentEmail = $rootScope.user.emailId;
			$scope.requestedBook[i].bookIsbn = $scope.requestBook[i].isbn;
		}
		$http.post(urlBase+'/requests/insert', $scope.requestedBook)
		.success(function(data){
			console.log('inserted');
			alert('Requested submitted');
			$scope.requestBook = [];
		})
		.error(function(data){
			alert('Cannot insert requested book');
		});
	}
}]);

//------------------------------checkout.html controller--------------------------------------
controller.controller('checkoutController', ['$scope', '$state', '$http', function($scope, $state, $http){

	$http.get(urlBase+'/allBookRequest')
	.success(function(data){
		$scope.studentRequestBook = data;
	})
	.error(function(data){
		alert('Not able to get all the request data');
	});
	
	$scope.accpetStudentRequest = {}
	$scope.accept = function($index){
		$scope.accpetStudentRequest.studentEmail = $scope.studentRequestBook[$index].studentEmail;
		$scope.accpetStudentRequest.bookIsbn = $scope.studentRequestBook[$index].bookIsbn;
		$scope.accpetStudentRequest.status = true;
		$scope.accpetStudentRequest.acceptDate = new Date();
		
		$http.post(urlBase+'/bookAccepted/insert', $scope.accpetStudentRequest)
		.success(function(data){
			$scope.accpetStudentRequest = data;
			$state.reload();
		})
		.error(function(data){
			alert('Error in inserted accepted student books');
		});
	}
	$scope.reject = function($index){
		
		$scope.accpetStudentRequest.studentEmail = $scope.studentRequestBook[$index].studentEmail;
		$scope.accpetStudentRequest.bookIsbn = $scope.studentRequestBook[$index].bookIsbn;
		$scope.accpetStudentRequest.status = true;
		$scope.accpetStudentRequest.acceptDate = new Date();
		$http.post(urlBase+'/requestCancel', $scope.accpetStudentRequest)
		.success(function(data){
			console.log('Deleted');
			$state.reload();
		})
		.error(function(data){
			alert('Error!! Cannot delete');
		});
		
	}
	
}]);

//-------------------------------------------checkoutBookForTeacher.html--------------------------------------
controller.controller('checkoutBookForTeacherController', ['$scope', '$state', '$http', '$rootScope', function($scope, $state, $http, $rootScope){
	$scope.requestTeacherBook = $rootScope.requestBook;
	
	$scope.removeBook = function($index){
		$scope.requestTeacherBook.splice($scope.requestTeacherBook[$index], 1);
	}
	
	$scope.bookcheckout = [];
	$scope.checkoutBook = function(){
		for (var i in $scope.requestTeacherBook){
			$scope.bookcheckout.push($scope.requestTeacherBook[i]);
		}
		for(var i in $scope.bookcheckout){
			$scope.bookcheckout[i].userEmail = $rootScope.user.emailId;
			$scope.bookcheckout[i].bookIsbn = $scope.requestTeacherBook[i].isbn;
			$scope.bookcheckout[i].checkoutDate = new Date();
		}
		$http.post(urlBase+'/teacherCheckout/insert', $scope.bookcheckout)
		.success(function(data){
			console.log('inserted');
			alert('Book checkout successfully');
			$scope.requestTeacherBook = [];
		})
		.error(function(data){
			alert('Cannot checkout book');
		});
	}
}]);

//-------------------------------------------bookWithStudent.html--------------------------------------
controller.controller('bookWithStudentController', ['$scope', '$state', '$http', '$rootScope', function($scope, $state, $http, $rootScope){
	
	$http.get(urlBase+'/getBooksWithStudent')
	.success(function(data){
		$scope.booksWithStudents = data;
	})
	.error(function(data){
		alert("Error in getting books");
	});
	
}]);

//-------------------------------------------studentPenalty.html--------------------------------------
controller.controller('studentPenaltyController', ['$scope', '$state', '$http', '$rootScope', function($scope, $state, $http, $rootScope){
	
	$http.get(urlBase+'/getallStudentPenalty')
	.success(function(data){
		$scope.studentPenalty = data;
	})
	.error(function(data){
		alert("Error in getting books");
	});
	
}]);

//-------------------------------------------returnBook.html--------------------------------------
controller.controller('returnBookController', ['$scope', '$state', '$http', '$rootScope', function($scope, $state, $http, $rootScope){
	
	$http.get(urlBase+'/getBooksWithStudent')
	.success(function(data){
		$scope.returnBook = data;
	})
	.error(function(data){
		alert("Error in getting books");
	});
	
	$scope.bookReturn = function($index){
		$scope.returnBook[$index].actualReturnDate = new Date();
		$scope.returnBook[$index].penalty = "0";
		$http.post(urlBase+'/return/insert', $scope.returnBook[$index])
		.success(function(data){
			alert('Book Returned successfully');
			$state.reload();z
		})
		.error(function(data){
			alert('Error while returning book');
		});
	}
}]);



}());