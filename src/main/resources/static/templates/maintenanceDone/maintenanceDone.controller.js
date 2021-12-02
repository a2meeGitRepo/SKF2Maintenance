(function() {
	'use strict';

	angular.module('myApp.maintenanceDone').controller('MaintenanceDoneController', MaintenanceDoneController);

	MaintenanceDoneController.$inject = [ '$state', '$log',
			'$scope', 'toastr','localStorageService','ApiEndpoint','loginFactory','genericFactory','$document','$rootScope' ];

	/* @ngInject */
	function MaintenanceDoneController($state, $log, $scope, toastr,localStorageService,ApiEndpoint,loginFactory,genericFactory,$document,$rootScope) {
		var maintenanceUrl = ApiEndpoint.url+"maintenance";
		var machineUrl = ApiEndpoint.url+"machine";
		var user = localStorageService.get(ApiEndpoint.userKey);																	
		console.log("user: "+JSON.stringify(user))
		var vm = angular.extend(this, {
			machineDoneCheckPoints:[],
		});
	
		(function activate() {
			
			getMaintanance();
		})();
		
	
		function getMaintanance (){
	
			var msg=""
				var url=maintenanceUrl+"/getAllPendingMaintenanceForUser?userId="+user.user_id
				genericFactory.getAll(msg,url).then(function(response) {
						vm.maintenances= response.data;
						console.log("Pending maintenances: "+JSON.stringify(vm.maintenances.length))
						
						
					});
		}
		$scope.view=function(maintanance){
			$scope.updateMaintenance=maintanance
			console.log("$scope.updateMaintenance: "+JSON.stringify($scope.updateMaintenance))
			$scope.addTab=true
			loadMachine();
			loadMaintenanceCheckPoint(maintanance);
		}
		
		$scope.update=function (){
			console.log("machineDoneCheckPoints: "+JSON.stringify(vm.machineDoneCheckPoints))
			$scope.updateMaintenance.status="Closed"
			$scope.updateMaintenance.updatedDate=new Date()
			$scope.updateMaintenance.updatedBy=user.id
			$scope.updateMaintenance.maintenanceCloseDate=new Date()
			$scope.updateMaintenance.maintenanceDoneBy=user.id
			$scope.updateMaintenance.maintenanceCheckPoints=vm.machineDoneCheckPoints
			  var msg=""
					 var url =maintenanceUrl+"/updateMaintenance";
			console.log("url : "+url)
						genericFactory.add(msg,url,$scope.updateMaintenance).then(function(response) {
							var responceObj=response.data
						
							getMaintanance ();
							$scope.addTab=false
							
							
							if(responceObj.code==200){
								toastr.success(responceObj.message);	
							}else{
								toastr.error(responceObj.message);
							}
							
					});		
			
			
			
		}
		
		$scope.cancel=function (){
			
			$scope.addTab=false
			
			
		}
		function loadMachine(){
			 var msg=""
				 var url =machineUrl+"/getAllMachines"; 
			genericFactory.getAll(msg,url).then(function(response) {
				vm.machines = response.data;
				
				//console.log("allMachines: "+JSON.stringify(vm.machines))
								
			});
			
			
		}
		
		function loadMaintenanceCheckPoint(maintanance){
			 var msg=""
				 var url =maintenanceUrl+"/getMachineCheckPointByMachine?machineId="+maintanance.machine.machineId; 
			genericFactory.getAll(msg,url).then(function(response) {
				vm.machineCheckPoints = response.data;
				
				angular.forEach(vm.machineCheckPoints, function(machineCheckPoint, key) {
						var maintenanceDone={}
					//	maintenanceDone.machine=maintanance.machine
					//	maintenanceDone.maintenance=maintanance
						maintenanceDone.checkPoint=machineCheckPoint
						maintenanceDone.maintenanceMode=""
						maintenanceDone.status=""
						maintenanceDone.actualValue=""
						maintenanceDone.doneBy=user.id
						maintenanceDone.doneDate=new Date();
						maintenanceDone.addedDate=new Date();
						vm.machineDoneCheckPoints.push(maintenanceDone)
					});
				console.log("machineDoneCheckPoints: "+JSON.stringify(vm.machineDoneCheckPoints))
								
			});
			
			
		}
}
	
	
	
	
})();
