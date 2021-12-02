(function() {
	'use strict';

	angular
	.module('myApp.login')
	.controller('loginController', loginController);


		loginController.$inject = ['$rootScope','$stateParams','$scope','$state','localStorageService','toastr', 'ApiEndpoint','$location','$window','genericFactory','loginFactory'];

	/* @ngInject */
	function loginController($rootScope, $stateParams, $scope, $state, localStorageService,toastr, ApiEndpoint,$location,$window,genericFactory,loginFactory) {
		var userUrl = ApiEndpoint.url+"userLogin";
		
		var vm = angular.extend(this, {
			signIn:signIn,

		});

		(function activate() {
			console.log("HELLO I AM IN LOGIN ")
		})();
		/**/
		// ******************************************************
		function signIn(login){
			console.log("LOGIN :: "+JSON.stringify(login))
			var msg=""								
				var  url =userUrl+"/login"
				console.log("URL :: "+url)
				genericFactory.add(msg,url,login).then(function(response) {
					vm.user = response.data;
					
					if (vm.user.responceCode==200) {
						
					
						loginFactory.SetCredentials(login);
						$location.path('/main/home');
						toastr.success(vm.user.responceMsg);
						localStorageService.set(ApiEndpoint.userKey, response.data.data);
						//$window.location.reload();
						//$state.go('main.home');
					} else {
						
						toastr.error(vm.user.responceMsg);
					}
					
				});
			}
			
			
		
		
		
		
	}
})();
