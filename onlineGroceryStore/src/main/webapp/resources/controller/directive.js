(function () {

var directive = angular.module('Directive', []);

directive.directive('monitoringPaginate',function(){
  return {
      restrict:'E',
      scope: {
          'recordsPerPage' : "=",
          'list': "=",
          'currentIndex': "="
      },
      link: function($scope) {
      	
          $scope.getNumberOfPages = function(list,recordsPerPage){
        	  if (!list || !list.length) { return; }
              return Math.ceil(list.length/recordsPerPage);
          };
          
          $scope.getPages = function(list,recordsPerPage,currentIndex){
        	  if (!list || !list.length) { return; }
              var length = parseInt(list.length);
              var pages = Math.ceil(length/recordsPerPage);
              var arr = [];
              var size = pages;
              if(pages<=3){
                  for(var i=0;i<pages;i++)
                      arr.push(i);
                  return arr;
              }
              else{
                  if(currentIndex>=0&&currentIndex<pages-2){
                      for(var i=currentIndex;currentIndex<pages&&arr.length<=2;i++)
                          arr.push(i);
                      return arr;
                  }
                  else if(currentIndex==pages-2){
                      arr.push(currentIndex-1);
                      arr.push(currentIndex);
                      arr.push(currentIndex+1);
                      return arr;
                  }
                  else{
                      arr.push(currentIndex-2);
                      arr.push(currentIndex-1);
                      arr.push(currentIndex);
                      return arr;
                  }	
              }
          };

          $scope.changePageSize = function(){
              $scope.currentIndex = 0;
          };

          $scope.changeIndex = function(i){
              $scope.currentIndex = i;
          };

          $scope.decrementCurrentIndex = function(){
              if($scope.currentIndex>0)
                  $scope.currentIndex--;
          };

          $scope.incrementCurrentIndex = function(){
              if($scope.currentIndex<(Math.ceil($scope.list.length/$scope.recordsPerPage)-1))
                  $scope.currentIndex++;
          };
      },
     templateUrl: 'resources/views/paginationPage.html'
  };
});

}());