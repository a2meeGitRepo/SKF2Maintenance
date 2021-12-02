(function() {
	'use strict';

	angular.module('myApp.breakdown').controller('BreakdownController', BreakdownController);

	BreakdownController.$inject = [ '$state', '$log',
			'$scope', 'toastr','localStorageService','ApiEndpoint','loginFactory','genericFactory','$document','$rootScope' ];

	/* @ngInject */
	function BreakdownController($state, $log, $scope, toastr,localStorageService,ApiEndpoint,loginFactory,genericFactory,$document,$rootScope) {
		var breakdownUrl = ApiEndpoint.url+"breakdown";
		
		var vm = angular.extend(this, {
			getBreakDown:getBreakDown
		});
	
		(function activate() {
			getBreakDown
		
		})();
	
		function getBreakDown (status){
			console.log(" status "+status)
			if(status==undefined){
				status="All"
			}
			var msg=""
				var url=breakdownUrl+"/getBreakDownByStatus?status="+status
				genericFactory.getAll(msg,url).then(function(response) {
						vm.breakDowns= response.data;
						console.log("breakDowns: "+JSON.stringify(vm.breakDowns))
						
						
					});
		}
		
}
	
	
	
	
})();
