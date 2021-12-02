(function() {
	'use strict';

	angular
		.module('myApp.main')
		.controller('mainController', mainController);

		mainController.$inject = ['localStorageService', 'ApiEndpoint', '$state','$rootScope','$timeout','$scope','loginFactory'];

	/* @ngInject */
	function mainController(localStorageService, ApiEndpoint, $state,$rootScope,$timeout,$scope,loginFactory) {
		var userDetails = localStorageService.get(ApiEndpoint.userKey);
		var tansactionUrl = ApiEndpoint.url+"tansaction";
		
		console.log("HELLO I AM IN MAIN ")
		var vm = angular.extend(this, {
		
		});

		(function activate() {
			
			 
			 
		})();
		$scope.logOut=function(){
			loginFactory.ClearCredentials();
			$state.go('login');
			localStorageService.remove(ApiEndpoint.userKey);
		}

	}
})();
