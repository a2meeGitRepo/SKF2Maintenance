(function() {
	'use strict';

	angular.module('myApp.home').controller('HomeController', HomeController);

	HomeController.$inject = [ '$state', '$log',
			'$scope', 'toastr','localStorageService','ApiEndpoint','loginFactory','genericFactory','$document' ];

	/* @ngInject */
	function HomeController($state, $log, $scope, toastr,localStorageService,ApiEndpoint,loginFactory,genericFactory,$document) {
		var assetRegistationUrl = ApiEndpoint.url+"assetRegistation";
		
		var vm = angular.extend(this, {
			
		
		});
	
		(function activate() {
		
		})();
		
	}	
	

	
	
	
	
	
})();
