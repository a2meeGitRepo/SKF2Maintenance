(function() {
	'use strict';

	angular.module('myApp.machineMapping').controller('MachineMappingController', MachineMappingController);

	MachineMappingController.$inject = [ '$state', '$log',
			'$scope', 'toastr','localStorageService','ApiEndpoint','loginFactory','genericFactory','$document','$rootScope' ];

	/* @ngInject */
	function MachineMappingController($state, $log, $scope, toastr,localStorageService,ApiEndpoint,loginFactory,genericFactory,$document,$rootScope) {
		var machineUrl = ApiEndpoint.url+"machine";
		var sparesUrl = ApiEndpoint.url+"spares";
		var locationUrl = ApiEndpoint.url+"location";
		var spindleDetailsUrl = ApiEndpoint.url+"spindleDetails";
		var machineMappingUrl = ApiEndpoint.url+"machineMapping";
		
		var vm = angular.extend(this, {
			availspares:[],
			mappeddLocations:[],
			mappedDrawings:[],
			getMachineByChannel:getMachineByChannel,
			
		});
	
		(function activate() {
			$scope.machineSpindleTable=true;
			$scope.machineSpareTable=false;
			$scope.machineLocationTable=false;
			loadMachineSpindles()
		
		})();
		$scope.add=function(){
			$scope.addTab=true;
			loadChannel()
		}
		$scope.cancel=function(){
			$scope.addTab=false;
		}
		$scope.showMachineSpindle=function(){
			$scope.machineSpindleTable=true;
			$scope.machineSpareTable=false;
			$scope.machineLocationTable=false;
			loadMachineSpindles()
		}
		$scope.showMachineSpare=function(){
			$scope.machineSpindleTable=false;
			$scope.machineSpareTable=true;
			$scope.machineLocationTable=false;
			loadMachineSpares()
		}
		$scope.showMachineLocation=function(){
			$scope.machineSpindleTable=false;
			$scope.machineSpareTable=false;
			$scope.machineLocationTable=true;
			loadMachineLocations()
		}
		
		$scope.showMachineDrawing=function(){
			$scope.machineSpindleTable=false;
			$scope.machineSpareTable=false;
			$scope.machineLocationTable=false;
			$scope.machineDrawingTable=true;
			loadMachineDrawings()
		}
		function loadChannel(){
			var msg=""
			var url=machineUrl+"/getAllChannels"
			genericFactory.getAll(msg,url).then(function(response) {
					vm.channels= response.data;
					console.log("channels : "+JSON.stringify(vm.channels))
					
					
				});
			
		}
		function loadMachineDrawings(){
			var msg=""
			var url=machineMappingUrl+"/getMachineDrawings"
			genericFactory.getAll(msg,url).then(function(response) {
					vm.machineDrawings= response.data;
					console.log("machineDrawings: "+JSON.stringify(vm.machineDrawings))
					
					
				});
			
		}
		function loadMachineSpindles(){
			var msg=""
			var url=machineMappingUrl+"/getAllMappedSpindles"
			genericFactory.getAll(msg,url).then(function(response) {
					vm.machineSpindles= response.data;
					console.log("machineSpindles: "+JSON.stringify(vm.machineSpindles))
					
					
				});
			
		}
		function loadMachineLocations(){
			var msg=""
			var url=machineMappingUrl+"/getMappedLocations"
			genericFactory.getAll(msg,url).then(function(response) {
					vm.machineLocations= response.data;
					console.log("machineLocations: "+JSON.stringify(vm.machineLocations))
					
					
				});
			
		}
		function loadMachineSpares(){
			var msg=""
			var url=machineMappingUrl+"/getMappedSpares"
			genericFactory.getAll(msg,url).then(function(response) {
					vm.machineSpares= response.data;
					console.log("machineSpares: "+JSON.stringify(vm.machineSpares))
					
					
				});
			
		}
		
		
		$scope.checkSpare=function(spare){
			if(spare.checked){
				var mappedSpare={}
				mappedSpare.spare=spare
				mappedSpare.machine=$scope.machineMapping.machine
				vm.mappedSpares.push(mappedSpare);
			}else{
				angular.forEach(vm.mappedSpares, function(mappedSpare, key) {
					console.log("s: "+JSON.stringify(mappedSpare.spare))
					console.log("s1: "+JSON.stringify(spare))
						if(mappedSpare.spare.id==spare.id){
							vm.mappedSpares.splice(key)
						}
					});
			}
		
		}
		$scope.checkDrawing=function(drawing){
			if(drawing.checked){
				var mappedDrawing={}
				mappedDrawing.drawing=drawing
				mappedDrawing.machine=$scope.machineMapping.machine
				vm.mappedDrawings.push(mappedDrawing);
			}else{
				angular.forEach(vm.mappedDrawings, function(mappedDrawing, key) {
					
						if(mappedDrawing.drawing.id==drawing.id){
							vm.mappedDrawings.splice(key)
						}
					});
			}
		
		}
		
		
		$scope.checkLocation=function(location){
			if(location.checked){
				var mappedLocation={}
				mappedLocation.location=location
				mappedLocation.machine=$scope.machineMapping.machine
				vm.mappeddLocations.push(mappedLocation);
			}else{
				angular.forEach(vm.mappeddLocations, function(mappedLocation, key) {
					
						if(mappedLocation.location.id==location.id){
							vm.mappeddLocations.splice(key)
						}
					});
			}
		
		}
		$scope.checkSpindle=function(spindle){
			if(spindle.checked){
				var mappedSpindle={}
				mappedSpindle.spindle=spindle
				mappedSpindle.machine=$scope.machineMapping.machine
				vm.mappeddSpindles.push(mappedSpindle);
			}else{
				angular.forEach(vm.mappeddSpindles, function(mappeddSpindle, key) {
					
						if(mappeddSpindle.spindle.id==spindle.id){
							vm.mappeddSpindles.splice(key)
						}
					});
			}
		
		}
		function getMachineByChannel(channelNo){
			
			var msg="";
			var url=machineUrl+"/getAllMachineByChannerNo?channelNo="+channelNo;
			genericFactory.getAll(msg,url).then(function(response) {
					vm.machines = response.data;
					console.log("machines: "+JSON.stringify(vm.machines))			
				});
		}
		$scope.selMachine=function(machineId){
			console.log("machineId: "+JSON.stringify(machineId))
			if(machineId!=undefined){
				$scope.showMappingType=true	
			}else{
				$scope.showMappingType=false
			}
			
		}
		$scope.getMappingTypes=function (mappingType){
			
			if(mappingType=="Spare"){
				callSpare();
			}
			if(mappingType=="Location"){
				callLocation();
			}
			if(mappingType=="Spindle"){
				callSpindle()
			}
			if(mappingType=="Drawing"){
				callDrawing()
			}
			if(mappingType=="SetupChart"){
				callSetupChart()
			}
				

			
		}
		function callDrawing(){
			
			var msg="";
			var url=machineMappingUrl+"/getAvailabelDrawingMappingMachine?machineId="+$scope.machineMapping.machine.machineId;;
			genericFactory.getAll(msg,url).then(function(response) {
					vm.drawings = response.data;
					console.log("drawings: "+JSON.stringify(vm.drawings))	
					
					var url=machineMappingUrl+"/getMappedDrawingByMachine?machineId="+$scope.machineMapping.machine.machineId;
					genericFactory.getAll(msg,url).then(function(response) {
							vm.mappedDrawings = response.data;
							console.log("mappedDrawings: "+JSON.stringify(vm.mappedDrawings))
							
						
							
							
						});
				});
		}
		function callSetupChart(){
			
			var msg="";
			var url=machineMappingUrl+"/getAvailabelSetupChartMappingMachine?machineId="+$scope.machineMapping.machine.machineId;;
			genericFactory.getAll(msg,url).then(function(response) {
					vm.setupsCharts = response.data;
					console.log("setupsCharts: "+JSON.stringify(vm.setupsCharts))	
					
					var url=machineMappingUrl+"/getMappedSetupChartByMachine?machineId="+$scope.machineMapping.machine.machineId;
					genericFactory.getAll(msg,url).then(function(response) {
							vm.mappedSetupsCharts = response.data;
							console.log("mappedSetupsCharts: "+JSON.stringify(vm.mappedSetupsCharts))
							
						
							
							
						});
				});
		}
		function callSpare(){
					
					var msg="";
					var url=machineMappingUrl+"/getAvailabelSparesMappingMachine?machineId="+$scope.machineMapping.machine.machineId;;
					genericFactory.getAll(msg,url).then(function(response) {
							vm.spares = response.data;
							console.log("spares: "+JSON.stringify(vm.spares))	
							
							var url=machineMappingUrl+"/getMappedSparesByMachine?machineId="+$scope.machineMapping.machine.machineId;
							genericFactory.getAll(msg,url).then(function(response) {
									vm.mappedSpares = response.data;
									console.log("mappedSpares: "+JSON.stringify(vm.mappedSpares))
									
								
									
									
								});
						});
				}
		function callLocation(){
			
			var msg="";
			var url=machineMappingUrl+"/getAvailabelLocationMappingMachine?machineId="+$scope.machineMapping.machine.machineId;;
			genericFactory.getAll(msg,url).then(function(response) {
					vm.locations = response.data;
					console.log("locations: "+JSON.stringify(vm.locations))
					var url=machineMappingUrl+"/getMappedLocationByMachine?machineId="+$scope.machineMapping.machine.machineId;
					genericFactory.getAll(msg,url).then(function(response) {
							vm.mappeddLocations = response.data;
							console.log("mappedLocations: "+JSON.stringify(vm.mappedLocations))
							
						
							
							
						});
				});
		}
		function callSpindle(){
			
			var msg="";
			var url=machineMappingUrl+"/getAvailabelSpindleByMachine?machineId="+$scope.machineMapping.machine.machineId;
			genericFactory.getAll(msg,url).then(function(response) {
					vm.spindles = response.data;
					console.log("spindles : "+JSON.stringify(vm.spindles ))	
					var url=machineMappingUrl+"/getMappedSpindleByMachine?machineId="+$scope.machineMapping.machine.machineId;
					genericFactory.getAll(msg,url).then(function(response) {
							vm.mappeddSpindles = response.data;
							console.log("mappeddSpindles: "+JSON.stringify(vm.mappeddSpindles))
							
						
							
							
						});
				});
		}
		
	$scope.save=function (){
		if($scope.machineMapping.mappingType=="Spare"){
			
			
			if(vm.mappedSpares.length==0){
				
				
				$scope.mappedSpareErr=true
				
				return;
				
			}else{
				$scope.mappedSpareErr=false
			}
			
			
			console.log("mappedSpares: "+JSON.stringify(vm.mappedSpares))			
			 var msg=""
				 var url =machineMappingUrl+"/addMmachineSpare";
					genericFactory.add(msg,url,vm.mappedSpares).then(function(response) {
						var responceObj=response.data
					
						 $scope.addTab=false;
						$scope.mappedSpares={}
						
						if(responceObj.code==200){
							toastr.success(responceObj.msg);	
						}else{
							toastr.error(responceObj.msg);
						}
						
				});
			
			
			
			
			
			
			
		}
		if($scope.machineMapping.mappingType=="Location"){
					if(vm.mappeddLocations.length==0){
						
						
						$scope.mappedLocationErr=true
						
						return;
						
					}else{
						$scope.mappedLocationErr=false
					}
					
					
					console.log("mappedSpares: "+JSON.stringify(vm.mappeddLocations))			
					 var msg=""
						 var url =machineMappingUrl+"/addMachineLocation";
							genericFactory.add(msg,url,vm.mappeddLocations).then(function(response) {
								var responceObj=response.data
							
								 $scope.addTab=false;
								$scope.mappedSpares={}
								
								if(responceObj.code==200){
									toastr.success(responceObj.msg);	
								}else{
									toastr.error(responceObj.msg);
								}
								
						});
			
		}
		
		
		if($scope.machineMapping.mappingType=="Drawing"){
			if(vm.mappedDrawings.length==0){
				
				
				$scope.mappedDrawingErr=true
				
				return;
				
			}else{
				$scope.mappedDrawingErr=false
			}
			
			
			console.log("mappedSpares: "+JSON.stringify(vm.mappedDrawings))			
			 var msg=""
				 var url =machineMappingUrl+"/addMachineDrawing";
					genericFactory.add(msg,url,vm.mappedDrawings).then(function(response) {
						var responceObj=response.data
					
						 $scope.addTab=false;
						$scope.mappedSpares={}
						
						if(responceObj.code==200){
							toastr.success(responceObj.msg);	
						}else{
							toastr.error(responceObj.msg);
						}
						
				});
	
		}
		if($scope.machineMapping.mappingType=="Spindle"){
			if(vm.mappeddSpindles.length==0){
				
				
				$scope.mappedSpindleErr=true
				
				return;
				
			}else{
				$scope.mappedSpindleErr=false
			}
			
			
			console.log("mappedSpares: "+JSON.stringify(vm.mappeddSpindles))			
			 var msg=""
				 var url =machineMappingUrl+"/addMachineSpindle";
					genericFactory.add(msg,url,vm.mappeddSpindles).then(function(response) {
						var responceObj=response.data
					
						 $scope.addTab=false;
						$scope.mappedSpares={}
						
						if(responceObj.code==200){
							toastr.success(responceObj.msg);	
						}else{
							toastr.error(responceObj.msg);
						}
						
				});
	
		}
	}

}
	
	
	
	
})();
