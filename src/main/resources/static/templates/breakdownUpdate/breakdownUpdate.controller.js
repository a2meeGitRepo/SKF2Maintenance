(function() {
	'use strict';

	angular.module('myApp.breakdownUpdate').controller('BreakdownUpdateController', BreakdownUpdateController);

	BreakdownUpdateController.$inject = [ '$state', '$log',
			'$scope', 'toastr','localStorageService','ApiEndpoint','loginFactory','genericFactory','$document','$rootScope' ];

	/* @ngInject */
	function BreakdownUpdateController($state, $log, $scope, toastr,localStorageService,ApiEndpoint,loginFactory,genericFactory,$document,$rootScope) {
		var breakdownUrl = ApiEndpoint.url+"breakdown";
		var machineUrl = ApiEndpoint.url+"machine";
		var user = localStorageService.get(ApiEndpoint.userKey);																	
		console.log("user: "+JSON.stringify(user))
		var vm = angular.extend(this, {
			
		});
	
		(function activate() {
			
			getBreakdowns();
		})();
		
		$scope.view=function(breakdown){
			$scope.updatebreakdown=breakdown
			console.log("$scope.updateMaintenance: "+JSON.stringify($scope.updatebreakdown))
			$scope.addTab=true
			loadMachine();
			
		}
		function loadMachine(){
			 var msg=""
				 var url =machineUrl+"/getAllMachines"; 
			genericFactory.getAll(msg,url).then(function(response) {
				vm.machines = response.data;
				
				//console.log("allMachines: "+JSON.stringify(vm.machines))
								
			});
			
			
		}
		function getBreakdowns (){
	
			var msg=""
				var url=breakdownUrl+"/getAllOpenBreakdownForUser?userId="+user.user_id
				genericFactory.getAll(msg,url).then(function(response) {
						vm.breakdowns= response.data;
						console.log("Pending breakdowns: "+JSON.stringify(vm.breakdowns))
						
						
					});
		}
		
		
		$scope.update=function(){
			  var msg=""
					 var url =breakdownUrl+"/updateBreakdown";
			console.log("url : "+url)
						genericFactory.add(msg,url,$scope.updatebreakdown).then(function(response) {
							var responceObj=response.data
						
							getBreakdowns ();
							$scope.addTab=false
							
							
							if(responceObj.code==200){
								toastr.success(responceObj.message);	
							}else{
								toastr.error(responceObj.message);
							}
							
					});	
			
		}
		
}
	
	
	
	
})();
