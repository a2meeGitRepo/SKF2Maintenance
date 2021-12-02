(function() {
	'use strict';

	angular.module('myApp.maintanceCheckList').controller('MaintenanceCheckListController', MaintenanceCheckListController);

	MaintenanceCheckListController.$inject = [ '$state', '$log',
			'$scope', 'toastr','localStorageService','ApiEndpoint','loginFactory','genericFactory','$document','$rootScope' ];

	/* @ngInject */
	function MaintenanceCheckListController($state, $log, $scope, toastr,localStorageService,ApiEndpoint,loginFactory,genericFactory,$document,$rootScope) {
		var maintenanceCheckListUrl = ApiEndpoint.url+"machine";
		var machineUrl = ApiEndpoint.url+"machine";
		var loadAmCheckPointUrl = ApiEndpoint.url+"amCheckPoints";
		
		var vm = angular.extend(this, {
			
			perPage : 10,
			total_count:0,
			pageno:1,
		});
	
		(function activate() {
			loadMaintenanceCheckList();
		$scope.maintanceCheckList=[]
		$scope.maintance={}
		//$scope.maintance.machine={}
		$scope.maintance.frequency=""
		})();
		
		// current page
		$scope.pagination = {
		        current: 1
		    };
		
		// page changed 
		$scope.pageChanged = function(pageNo){
			vm.pageno=pageNo;
			loadMaintenanceCheckList();
			
		}
		$scope.searchByPagination=function (search){
			loadMaintenanceCheckList();
			console.log("I AM Seraching ")
			
		}
		$scope.add=function (){
			$scope.addTab=true;
			$scope.addNewTab=false;
			getMachine();
			getAmCheckPoints();
		}
		$scope.cancel=function (){
			$scope.addTab=false;
			$scope.maintanceCheckList={};
		}
		
		$scope.addCheckList=function (){
		console.log("Add Checkpoint")
		getAmCheckPoints()
		var checkpoint={}
	//	checkpoint.amCheckPoints=""
		checkpoint.operation=""
		checkpoint.defaultValue=""
		checkpoint.amCheckPointsERR=false
		checkpoint.operationERR=false
		checkpoint.defaultValueERR=false
		$scope.maintanceCheckList.push(checkpoint)
			
		}
		$scope.removeNewColumns=function (index){
			$scope.maintanceCheckList.splice(index,1);
		}
		
		function getMachine(){
		var msg="";
		var url=machineUrl+"/getAllMachines";
		genericFactory.getAll(msg,url).then(function(response) {
				vm.machines = response.data;
								
			});
		}
		
		function getAmCheckPoints(){
		var msg="";
		var url=loadAmCheckPointUrl+"/getAllAMCheckPoints";
		genericFactory.getAll(msg,url).then(function(response) {
				vm.amCheckPointsCodes = response.data;
								
			});
		}
		
		$scope.editMaintenance=function(maintanceCheckList){
			$scope.addTab=true;
			getMachine();
			getAmCheckPoints();
			$scope.maintanceCheckList=maintanceCheckList
		}
		$scope.deleteMaintenance=function(maintanceCheckList){
			  var msg=""
					 var url =maintenanceCheckListUrl+"/deleteMachineChecpPoint";
						genericFactory.add(msg,url,maintanceCheckList).then(function(response) {
							var responceObj=response.data
							loadMaintenanceCheckList();
							
							
							if(responceObj.code==200){
								toastr.success(responceObj.msg);	
							}else{
								toastr.error(responceObj.msg);
							}
							
					});
		}
		$scope.changeStatus=function(maintanceCheckList){
			  var msg=""
					 var url =maintenanceCheckListUrl+"/changeStatusMachineCheckPoint";
						genericFactory.add(msg,url,maintanceCheckList).then(function(response) {
							var responceObj=response.data
							loadMaintenanceCheckList();
							
							
							if(responceObj.code==200){
								toastr.success(responceObj.msg);	
							}else{
								toastr.error(responceObj.msg);
							}
							
					});
		}
		
		//************************* EXPORT *****************************//
		$scope.filename="Maintenances"
			vm.labels={'machineId.machineName': 'Machine Name','amcheckvalue.amCheckPoints.checkPointCode':'Check Point','frequency':'Frequency','operation':'Operation','defaultValue':'Default Value','status':'Status'}
		
		$scope.newExcel= function(){
	    	  getAllMaintenanceCheckListForExport();
				 //$rootScope.loader=true;
	    	  //document.getElementById('btnExport').click();
				 setTimeout(function(){
					 
					 document.getElementById('btnExport').click();
					 //$rootScope.loader=false;
					  $rootScope.$digest();
					},1000);		
				
			}
			function getAllMaintenanceCheckListForExport(){
				 var msg=""
					 var url =maintenanceCheckListUrl+"/getAllMaintenanceCheckList"; 
				genericFactory.getAll(msg,url).then(function(response) {
					vm.allMaintenances = response.data;
					
					console.log("allMaintenances: "+JSON.stringify(vm.allMaintenances))
									
				});
				
			}
		
		
		
		//*************************************************************//
		
		function loadMaintenanceCheckList(){
			loadMaintenanceCheckListCount();			
			
			var url;
			var msg=""
			
				if($scope.search==""||$scope.search==undefined){
					url =maintenanceCheckListUrl+"/getlistMaintanceCheckPointByLimit/"+vm.pageno+'/'+vm.perPage;
				}
			else{
				url=maintenanceCheckListUrl+"/getlistMaintanceCheckPointListByLimitAndSearch?searchText="+$scope.search+"&pageNo="+vm.pageno+'&perPage='+vm.perPage;
			}
				
				
				// var url =maintenanceCheckListUrl+"/getMaintanceCount/"+vm.pageno+'/'+vm.perPage;
				genericFactory.getAll(msg,url).then(function(response) {
				vm.maintenanceCheckPointLists = response.data;
				
				console.log("maintenanceCheckPointLists : "+JSON.stringify(vm.maintenanceCheckPointLists))
								
			});
			
			
		}
		function loadMaintenanceCheckListCount(){
			var msg=""
				 var url =""
				if($scope.search==""||$scope.search==undefined){
					url =maintenanceCheckListUrl+"/getMachienChekPointCount"
				}
			else{
				url=maintenanceCheckListUrl+"/getMachienChekPointCountBySearch?searchText="+$scope.search;
			}
			 //var url =maintenanceCheckListUrl+"/getMaterialsCount";
			genericFactory.getAll(msg,url).then(function(response) {
			vm.total_count = response.data;
			console.log("total_count: "+JSON.stringify(vm.total_count))
							
		});
		}
		
		$scope.save=function (maintance,maintanceCheckList){
		
		console.log(maintance.frequency=="")
		console.log("maintance: "+JSON.stringify(maintance))
		console.log("maintanceCheckList: "+JSON.stringify(maintanceCheckList))
			
		if(maintance==undefined||maintance.machine==undefined){
				$scope.machineError=true;
				return;
			}else{
				$scope.machineError=false;
			}
		if(maintance.frequency==""){
			$scope.frequencyError=true;
			console.log("Feq is man")
			return;
		}else{
			$scope.frequencyError=false;
		}
		if(maintanceCheckList.length==0){
		$scope.checkpointErr=true;
			return;
		}else{
		$scope.checkpointErr=false;
		}
          var index=0;
        angular.forEach(maintanceCheckList, function (items) {
               if(items.checkPointName==undefined||items.checkPointName==""||items.checkPointName=="{}"){
					items.checkPointNameERR=true;
					return;
				}else{
					items.checkPointNameERR=false;
				}
               if(items.operation==undefined||items.operation==""){
					items.operationERR=true;
					return;
				}else{
					items.operationERR=false;
				}
               if(items.defaultValue==undefined||items.defaultValue==""){
					items.defaultValueERR=true;
					return;
				}else{
					items.defaultValueERR=false;
				}
               
               items.machine=maintance.machine
               items.frequency=maintance.frequency
               index+=1
                var msg=""
			 var url =machineUrl+"/addMachineCheckPoint";
				genericFactory.add(msg,url,items).then(function(response) {
					var responceObj=response.data
					loadMaintenanceCheckList();
					
					$scope.addTab=false;
					$scope.maintanceCheckList={}
					
					if(responceObj.code==200){
						toastr.success(responceObj.msg);	
					}else{
						toastr.error(responceObj.msg);
					}
					
			});
               
            });  
        
      
         $scope.loderview=true;
     
         
       	console.log("maintanceCheckList "+JSON.stringify(maintanceCheckList))

        
       /* toastr.success('Submitted successfully!!')
       $scope.addTab=false;
        return;*/
    }
	


}
	
})();
