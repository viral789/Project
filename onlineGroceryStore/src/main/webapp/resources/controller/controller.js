(function(){
	
	var controller = angular.module('Controller', []);
	
//--------------------------------index.html controller------------------------------------
	controller.controller('indexController', ['$scope', '$state', 'urlName', function($scope, $state, urlName){
		
		$scope.template = "resources/views/indexLoader.html";
		urlBase = "http://localhost:8080/onlineGroceryStore";
		$state.go("home");
	}]);
	
	
//--------------------------------indexLoader.html Controller--------------------------------------
	controller.controller('loaderController', ['$scope', '$rootScope', '$state', '$modal', function($scope, $rootScope, $state, $modal){
		$scope.authenticate = false;
		$rootScope.added = false;
		$rootScope.$on('authenticate', function(event, flag){
			$scope.authenticate = flag;
		});
		
		$scope.search = "";
		
		$rootScope.$on('userAuthenticate', function(event, flag){
			$scope.username = flag.firstName;
			$scope.userId = flag.userId;
			$scope.usertyp = flag.type;
			if($scope.usertyp === 2){
				$scope.staff = true;
			}
			else{
				$scope.staff = false;
			}
		});
		
		$scope.active = function(route){
			return $state.is(route);
		}
		
		$scope.tabs =[
	      { heading: "Home", route:"home", active:true },
	      { heading: "Add Product", route:"addProduct", active:false }
		];
		
		$scope.$on("$stateChangeSuccess", function() {
			$scope.tabs.forEach(function(tab) {
				tab.active = $scope.active(tab.route);
			});
		});
		
		$scope.login = function(){
			$modal.open({
	            templateUrl: 'resources/views/login.html',
	            controller: 'loginController',
	            backdrop: 'static',
	            size: 'sm',
	            windowClass: 'center-modal'
	        });
		}
		
		$scope.logout = function(){
			$scope.authenticate = false;
			$state.reload();
			$scope.staff = false;
			$state.go("home");
		}
		
		$scope.checkOut = function(){
			$modal.open({
	            templateUrl: 'resources/views/cart.html',
	            controller: 'cartController',
	            backdrop: 'static',
	            windowClass: 'center-modal'
	        });
		}
	}]);
	
//-------------------------------------------login.html controller------------------------------------------
	controller.controller('loginController', ['$scope',  '$modalInstance', '$modal', '$http', '$rootScope', function($scope, $modalInstance, $modal, $http, $rootScope){
		var matched = false;
		/*$scope.loggedin = true;*/
		
		$scope.submitForm = function(username, password){
			$scope.user = {};
			$scope.user.userName = username.toLowerCase();
			$scope.user.passWord = password;
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
		
		$scope.signUp = function(){
			$modalInstance.dismiss();
			$modal.open({
	            templateUrl: 'resources/views/signUp.html',
	            controller: 'signUpController',
	            backdrop: 'static',
	            windowClass: 'center-modal'
	        });
		}
	}]);
	
//-------------------------------------------signUp.html controller------------------------------------------
	controller.controller('signUpController', ['$scope', '$modalInstance', '$http', '$rootScope', function($scope, $modalInstance, $http, $rootScope){
		$scope.user = {};
		$scope.staff = {};
		$scope.type = null;
		$scope.userType =[{type: '1', role :'customer'}, {type: '2', role :'staff'}];
		$scope.repassword = null;
		
		$scope.cancelSignup = function(){
			$modalInstance.dismiss();
		}
		
		$scope.registration = function(){
			$modalInstance.close();
			$scope.user.type = $scope.type.type;
			$scope.user.username = $scope.user.username.toLowerCase();
			$http.post(urlBase+'/users/insert', $scope.user)
			.success(function(data){
				$scope.userAuthenticate = data;
				//$scope.authenticate = true;
				$rootScope.$emit('authenticate', true);
				$rootScope.$emit('userAuthenticate', $scope.userAuthenticate);
				
				$scope.staff.userId = $scope.userAuthenticate.userId;
				if($scope.userAuthenticate.type == 2){
					$http.post(urlBase+'/staff/insert', $scope.staff)
					.success(function(data){
						$scope.staff = data;
					})
					.error(function(){
						alert("Error while registration for staff");
					});
				}
			})
			.error(function(){
				alert("Error while registration!");
			});
		}
	}]);
	
	
//-------------------------------------------home.html controller--------------------------------------------
	controller.controller('homeController', ['$scope', '$http', '$rootScope', function($scope, $http, $rootScope){
		
		$http.get(urlBase+'/products')
		.success(function (data){
			$rootScope.getProduct = data;
			for(var p in $rootScope.getProduct){
				$rootScope.getProduct[p].images = 'resouces/images/'+$rootScope.getProduct[p].productName.toLowerCase()+'.jpg';
				//$rootScope.getProduct[p].images = 'resouces/images/banner3.jpg';
			}
			
			
			$http.get(urlBase+'/price')
			.success(function(data){
				$scope.allProductPrice = data;
				
				for(var i in $rootScope.getProduct){
					for(var j in $scope.allProductPrice){
						if($rootScope.getProduct[i].productId == $scope.allProductPrice[j].productId){
							$rootScope.getProduct[i].price = $scope.allProductPrice[j].price;
						}
					}
				}
			})
		})
		.error(function(data){
			alert('Error while getting all Products');
		});
		
		$scope.productAddedToCart = false;
		$rootScope.cart = [];
		
		
		$rootScope.eventLogObjects = {};
		$rootScope.eventLogObjects.pageSize = 3;
		$rootScope.eventLogObjects.currentPageIndex = 0;
		
		$scope.addtoCart = function(product){
			$rootScope.cart.push(product);
			$rootScope.added = true;
		}
		
		$scope.deletefromCart = function(id){
			for(var i in $rootScope.cart){
				if($rootScope.cart[i].productId === id){
					$rootScope.cart.splice(i,1);
				}
			}	 
		}
	}]);
	
//-------------------------------------------addProduct.html controller--------------------------------------------
	controller.controller('addProductController', ['$scope', '$http','$state', function($scope, $http, $state){
		$scope.product = {};
		$scope.prdtype ={};
		$scope.productPrice = {};
		$scope.stocks = {};
		$scope.zipcode = "";
		
		$scope.productType =[{name :'Fruit'}, 
		                     {name :'Vegetables'},
		                     {name :'Dairy Products'},
		                     {name :'Frozen Food'},
		                     {name :'Bread and Bakery'},
		                     {name :'Drinks'}];
		
		$http.get(urlBase+"/allWarehouse")
		.success(function(data){
			$scope.warehouse = data;
		})
		.error(function(data){
			alert('Error while getting all the warehouse');
		});
		
		$scope.addProduct = function(){
			$scope.product.warehouseId = $scope.zipcode.warehouseId; 
			$scope.product.productType = $scope.prdtype.name;
			$http.post(urlBase+'/product/insert', $scope.product)
			.success(function(data){
				$scope.getProject = data;
				
				$scope.productPrice.zipcode = $scope.zipcode.zipcode;
				$scope.productPrice.productId =  $scope.product.productId;
				
				$http.post(urlBase+'/price/insert', $scope.productPrice)
				.success(function(data){
					
					$scope.stocks.productID = $scope.product.productId;
					$scope.stocks.wareHOUSEID = $scope.product.warehouseId;
					
					$http.post(urlBase+'/stocks/insert', $scope.stocks)
					.success(function(data){
						console.log("stocks added");
					})
					.error(function(data){
						alert('Error in adding stocks');
					});
				})
				.error(function (data){
					alert('Error while adding product price')
				});
			})
			.error(function (data){
				alert('Error while adding product');
			});
			/*$scope.product = {};
			$scope.prdtype ={};
			$scope.zipcode = "";*/
			$state.go("home");
		}
	}]);

//-------------------------------------------cart.html controller--------------------------------------------
	controller.controller('cartController', ['$scope', '$rootScope', '$state', '$modalInstance', function($scope, $rootScope, $state, $modalInstance){
		$rootScope.order = {};
		$rootScope.order.totalPRICE = 0;
		$rootScope.orderForEachProduct = [];
		$rootScope.changeStock = [];
		$scope.finalOrder = $rootScope.cart;
		$scope.calculate = function(productId, quantity, price, index){
			var total = $rootScope.order.totalPRICE;
			$scope.finalOrder[index].totalPriceForThatProduct = quantity * price;
			total += (quantity * price);
			$rootScope.order.totalPRICE = total;
		};
		
		$scope.proceedToPayment = function(){
			//$rootScope.orderForEachProduct.push($scope.finalOrder);
			for(var i in $scope.finalOrder){
				$scope.orderForEachProducts = {};
				$scope.changeStocks = {};
				$scope.changeStocks.productID = "";
				$scope.changeStocks.noOFITEM = 0;
				$scope.changeStocks.wareHOUSEID = 0;
				$scope.orderForEachProducts.productId = "";
				$scope.orderForEachProducts.quantity = 0;
				$scope.orderForEachProducts.totalPriceForEachProduct = "";
				$scope.orderForEachProducts.productId = $scope.finalOrder[i].productId;
				$scope.orderForEachProducts.quantity = parseInt($scope.finalOrder[i].quantity);
				$scope.changeStocks.productID = $scope.finalOrder[i].productId;
				$scope.changeStocks.noOFITEM = parseInt($scope.finalOrder[i].quantity);
				$scope.changeStocks.wareHOUSEID = parseInt($scope.finalOrder[i].warehouseId);
				$scope.orderForEachProducts.totalPriceForEachProduct = $scope.finalOrder[i].totalPriceForThatProduct;
				$rootScope.orderForEachProduct.push($scope.orderForEachProducts);
				$rootScope.changeStock.push($scope.changeStocks);
			}
			$modalInstance.dismiss();
			$state.go("payment");
		};
	}]);
	
//------------------------------------payment.html controller----------------------------------------------------
	controller.controller('paymentController', ['$scope', '$http','$rootScope', '$state',function($scope, $http, $rootScope, $state){
		
		var cus_creditcard = '';
		
		$http.get(urlBase+'/customers/'+$scope.userId)
		.success(function(data){
			$scope.customer = data;
			if($scope.customer.creditCardNo == 0){
				$scope.customer.creditCardNo = '';
			}
			if($scope.customer.expMONTH == 0){
				$scope.customer.expMONTH = '';
			}
			if($scope.customer.expYEAR == 0){
				$scope.customer.expYEAR = '';
			}
			cus_creditcard = $scope.customer.creditCardNo;
			if( $scope.customer.creditCardNo != ''){
				$http.get(urlBase+'/address/byCreditCard/'+$scope.customer.creditCardNo)
				.success(function(data){
					$scope.address= data;
					if($scope.address.zipcode == 0){
						$scope.address.zipcode = '';
					}
					
					$http.get(urlBase+'/orders/getByUserId/'+$scope.userId)
					.success(function(data){
						$scope.getOrderByUserID = data;
						var length = $scope.getOrderByUserID.length - 1;
						if(length == -1){
							$rootScope.order.totalPRICEWITHREAMINING = $rootScope.order.totalPRICE
							$scope.remaining = $scope.getOrderByUserID[length].remBALANCE;
						}
						else{
							$rootScope.order.totalPRICEWITHREAMINING = $rootScope.order.totalPRICE + $scope.getOrderByUserID[length].remBALANCE;
							$scope.remaining = $scope.getOrderByUserID[length].remBALANCE;
						}
					})
					.error(function(data){
						alert('Error while getting orders');
					});
				})
				.error(function(data){
					alert('Error while getting address of customer')
				});
			}
		})
		.error(function(data){
			alert('Error while getting customer');
		});
		
		$scope.deleteAddress = function(){
			$http({
        		method: 'delete',
        		url: urlBase+'/address/delete/'+$scope.userId
        	})
        	.success(function(data){
        		$state.reload();
        	})
        	.error(function(data){
        		alert('Error while removing address');
        	});
		}
		
		
		$scope.payNow = function(){
			$rootScope.order.status = 1;
			$rootScope.order.userID = 0;
			$rootScope.order.userID = $scope.userId;
			$rootScope.order.creditCARDNUMBER = "";
			$scope.customer.userId = $scope.userId;
			
			if(cus_creditcard == ''){
				$http.post(urlBase+'/customer/insert', $scope.customer)
				.success(function(data){
					$scope.address.userID = $scope.userId;
					$scope.address.creditCARDNO = $scope.customer.creditCardNo;
					$scope.address.type = 1;
					$rootScope.order.creditCARDNUMBER = $scope.customer.creditCardNo;
					$rootScope.order.remBALANCE = ($scope.remaining + ($rootScope.order.totalPRICE - parseInt($scope.amtPaid)));
					
					
					$http.post(urlBase+'/orders/insert', $rootScope.order)
					.success(function(data){
						$scope.orders = data;
						for(var i in $rootScope.orderForEachProduct){
							$rootScope.orderForEachProduct[i].orderId = $scope.orders.orderID;
						}
						
						$http.post(urlBase+'/address/insert', $scope.address)
						.success(function(data){
							
							
							$http.post(urlBase+'/ordersPerProduct/insert', $rootScope.orderForEachProduct)
							.success(function(data){
								
								$http.get(urlBase+'/allStocks')
								.success(function(data){
									$scope.allStocks = data;
									
									for(var i in $rootScope.changeStock){
										for(var j in $scope.allStocks){
											if($rootScope.changeStock[i].productID === $scope.allStocks[j].productID){
												$rootScope.changeStock[i].noOFITEM = $scope.allStocks[j].noOFITEM - $rootScope.changeStock[i].noOFITEM;
											}
										}
									}
									$http.put(urlBase+'/stocks/update', $rootScope.changeStock)
									.success(function(data){
										console.log("updated");
									})
									.error(function(data){
										alert('Error while updating stocks');
									});
								})
								.error(function(data){
									alert('Error while getting stocks');
								})
							})
							.error(function(data){
								alert('Error while adding order per product');
							});
						})
						.error(function(data){
							alert('Error while adding address');
						});
					})
					.error(function(data){
						alert('Error while adding order');
					});
				})
				.error(function(data){
					alert('Error while adding customer');
				});
			}
			else{
				$http.put(urlBase+'/customer/update',$scope.customer)
				.success(function(data){
					$scope.address.userID = $scope.userId;
					$scope.address.creditCARDNO = $scope.customer.creditCardNo;
					$scope.address.type = 1;
					$rootScope.order.creditCARDNUMBER = $scope.customer.creditCardNo;
					$rootScope.order.remBALANCE = ($scope.remaining + ($rootScope.order.totalPRICE - parseInt($scope.amtPaid)));
					
					$http.post(urlBase+'/orders/insert', $rootScope.order)
					.success(function(data){
						$scope.orders = data;
						for(var i in $rootScope.orderForEachProduct){
							$rootScope.orderForEachProduct[i].orderId = $scope.orders.orderID;
						}
						
						$http.put(urlBase+'/address/update',$scope.address)
						.success(function(data){
							
							$http.post(urlBase+'/ordersPerProduct/insert', $rootScope.orderForEachProduct)
							.success(function(data){
								
								$http.get(urlBase+'/allStocks')
								.success(function(data){
									$scope.allStocks = data;
									
									for(var i in $rootScope.changeStock){
										for(var j in $scope.allStocks){
											if($rootScope.changeStock[i].productID === $scope.allStocks[j].productID){
												$rootScope.changeStock[i].noOFITEM = $scope.allStocks[j].noOFITEM - $rootScope.changeStock[i].noOFITEM;
											}
										}
									}
									$http.put(urlBase+'/stocks/update', $rootScope.changeStock)
									.success(function(data){
										console.log("updated");
									})
									.error(function(data){
										alert('Error while updating stocks');
									});
								})
								.error(function(data){
									alert('Error while getting stocks');
								})
							})
							.error(function(data){
								alert('Error while adding order per product');
							});
						})
						.error(function(data){
							alert('Error While updating address');
						});
					})
					.error(function(data){
						alert('Error while adding orders');
					});
				})
				.error(function(data){
					alert('Error while updating customers');
				});
			}
			$state.go("home");
		}
	}]);
}());