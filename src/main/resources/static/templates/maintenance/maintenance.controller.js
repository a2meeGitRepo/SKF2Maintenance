(function() {
	'use strict';

	angular.module('myApp.maintenance').controller('MaintenanceController', MaintenanceController);

	MaintenanceController.$inject = [ '$state', '$log',
			'$scope', 'toastr','localStorageService','ApiEndpoint','loginFactory','genericFactory','$document','$rootScope' ];

	/* @ngInject */
	function MaintenanceController($state, $log, $scope, toastr,localStorageService,ApiEndpoint,loginFactory,genericFactory,$document,$rootScope) {
		var maintenanceUrl = ApiEndpoint.url+"maintenance";
		
		var vm = angular.extend(this, {
			getMaintanance:getMaintanance
		});
	
		(function activate() {
			$scope.maintenanceType="All"
				getMaintanance()
		
		})();
		$scope.changeMaintenanceTypes=function (maintenanceType){
			console.log(" maintenanceType "+maintenanceType)
		}
		function getMaintanance (maintenanceType){
			console.log(" maintenanceType "+maintenanceType)
			if(maintenanceType==undefined){
				maintenanceType="All"
			}
			var msg=""
				var url=maintenanceUrl+"/getMaintenanceByStatus?status="+maintenanceType
				genericFactory.getAll(msg,url).then(function(response) {
						vm.maintenances= response.data;
						console.log("maintenances: "+JSON.stringify(vm.maintenances))
						
						
					});
		}
		

}
	
	
	
	
})();
