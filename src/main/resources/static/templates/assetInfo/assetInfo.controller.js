(function() {
	'use strict';

	angular.module('myApp.assetInfo').controller('AssetInfoController', AssetInfoController);

	AssetInfoController.$inject = [ '$state', '$log',
			'$scope', 'toastr','localStorageService','ApiEndpoint','loginFactory','genericFactory','$document','$rootScope','$window' ];

	/* @ngInject */
	function AssetInfoController($state, $log, $scope, toastr,localStorageService,ApiEndpoint,loginFactory,genericFactory,$document,$rootScope,$window) {
		var setupchartUrl = ApiEndpoint.url+"setupchart";
		var machineUrl = ApiEndpoint.url+"machine";
		var sparesUrl = ApiEndpoint.url+"spares";
		var locationUrl = ApiEndpoint.url+"location";
		var spindleDetailsUrl = ApiEndpoint.url+"spindleDetails";
		var machineMappingUrl = ApiEndpoint.url+"machineMapping";
		var uploadUrl = ApiEndpoint.url+"upload";
		var vm = angular.extend(this, {
			
			
		});
	
		(function activate() {
			
			loadMachine();
		})();
		$scope.download=function(obj){
			/*var url="D:/A2MEE_DATTA/Manuals/"+obj.manualName
			console.log("URL ="+url)
			$window.open(url, '_blank');*/
			
			var msg="";
			var url=uploadUrl+"/downloadFile/"+obj.fileName;
			console.log("URL ="+url)
			$window.open(url, '_blank');
			genericFactory.getAll(msg,url).then(function(response) {
				
			});
		}
		$scope.selMachine=function(machine){
			console.log("machine: "+JSON.stringify(machine))
			$scope.machine=machine
			getSpindle()
			$scope.machineSpindleTable=true
			
		}
		$scope.showMachineSpindle=function(){
			$scope.machineSpindleTable=true
			$scope.machineSpareTable=false
			$scope.setUpChartTable=false
			$scope.dwaringTable=false
		}
		$scope.showMachineSpare=function(){
			$scope.machineSpareTable=true
			$scope.machineSpindleTable=false
			$scope.setUpChartTable=false
			$scope.dwaringTable=false
			$scope.machineLocationTable=false
			getSpare()
		}
		$scope.showMachineLocation=function(){
			$scope.machineSpareTable=false
			$scope.machineSpindleTable=false
			$scope.setUpChartTable=false
			$scope.dwaringTable=false
			$scope.machineLocationTable=true
			getLocation()
		}
		$scope.showDrawing=function(){
			$scope.machineSpareTable=false
			$scope.machineSpindleTable=false
			$scope.setUpChartTable=false
			$scope.dwaringTable=true
			$scope.machineLocationTable=false
			getDrawings()
		}
		$scope.showSetUpChart=function(){
			$scope.machineSpareTable=false
			$scope.machineSpindleTable=false
			$scope.setUpChartTable=true
			$scope.dwaringTable=false
			$scope.machineLocationTable=false
			getSetupChart()
		}
		
		function getSetupChart(){
				
			var msg=""
				var url=setupchartUrl+"/getSetupChartTablesByMachine?machineId="+$scope.machine.machineId;
				genericFactory.getAll(msg,url).then(function(response) {
						vm.tables = response.data;
						vm.allData=[]
						console.log("tables: "+JSON.stringify(vm.tables))
						   angular.forEach(vm.tables, function (table) {
							   var obj={};
							   obj.table=table
				              
				            	console.log("table: "+JSON.stringify(table))
				                var msg=""
				        			var url=setupchartUrl+"/getSetupChartByMachineAndTable?machineId="+$scope.machine.machineId+"&table="+table;
				        			genericFactory.getAll(msg,url).then(function(response) {
				        					
				        					obj.data=response.data;
				        					vm.allData.push(obj);
				        					
				        					
				        				});
				                
				                
				            });  
					
						
						
				});
			
		}
		function getDrawings(){
			var msg=""
			var url=machineMappingUrl+"/getMachineDrawingByMachine?machineId="+$scope.machine.machineId;
			genericFactory.getAll(msg,url).then(function(response) {
					vm.mappedDrawings = response.data;
					console.log("mappedDrawings: "+JSON.stringify(vm.mappedDrawings))
					
				
					
					
				});
		}
		function getSpare(){
			var msg=""
			var url=machineMappingUrl+"/getMappedSparesByMachine?machineId="+$scope.machine.machineId;
			genericFactory.getAll(msg,url).then(function(response) {
					vm.mappedSpares = response.data;
					console.log("mappedSpares: "+JSON.stringify(vm.mappedSpares))
					
				
					
					
				});
		}

		function getLocation(){
			var msg=""
				var url=machineMappingUrl+"/getMappedLocationByMachine?machineId="+$scope.machine.machineId;
			genericFactory.getAll(msg,url).then(function(response) {
					vm.mappeddLocations = response.data;
					console.log("mappedLocations: "+JSON.stringify(vm.mappedLocations))
					
				
					
					
				});
		}
		
		
		
		function getSpindle(){
			var msg=""
			var url=machineMappingUrl+"/getMappedSpindleByMachine?machineId="+$scope.machine.machineId;
			genericFactory.getAll(msg,url).then(function(response) {
					vm.mappeddSpindles = response.data;
					console.log("mappeddSpindles: "+JSON.stringify(vm.mappeddSpindles))
					
				});
		}
		function loadMachine(){
			
			var msg="";
			var url=machineUrl+"/getAllMachines";
			genericFactory.getAll(msg,url).then(function(response) {
					vm.machines = response.data;
					console.log("machines: "+JSON.stringify(vm.machines))			
				});
		}
		
		
		
	

}
	
	
	
	
})();
