(function() {
	'use strict';

	angular.module('myApp.machineOwner').controller('machineOwnerController', machineOwnerController);

	machineOwnerController.$inject = [ '$state', '$log',
			'$scope', 'toastr','localStorageService','ApiEndpoint','loginFactory','genericFactory','$document','$rootScope' ];

	/* @ngInject */
	function machineOwnerController($state, $log, $scope, toastr,localStorageService,ApiEndpoint,loginFactory,genericFactory,$document,$rootScope) {
		var machineOwnerUrl = ApiEndpoint.url+"machine";
		var machineUrl = ApiEndpoint.url+"machine";
		var userUrl = ApiEndpoint.url+"userLogin";
		
		var vm = angular.extend(this, {
			perPage : 10,
			total_count:0,
			pageno:1,
		});
	
		(function activate() {
			loadMachineOwner();
		
		})();
		
		// current page
		$scope.pagination = {
		        current: 1
		    };
		
		// page changed 
		$scope.pageChanged = function(pageNo){
			vm.pageno=pageNo;
			loadMachineOwner();
			
		}
		$scope.searchByPagination=function (search){
			loadMachineOwner();
			console.log("I AM Seraching ")
			
		}
		
		$scope.add=function (){
			$scope.addTab=true;
			getMachineOwner();
			getAllUser();
		}
		
		$scope.cancel=function (){
			$scope.addTab=false;
		}	
		
		function getMachineOwner(){
		var msg="";
		var url=machineUrl+"/getAllMachines";
		genericFactory.getAll(msg,url).then(function(response) {
				vm.machines = response.data;
								
			});
		}
		
		function getAllUser(){
		var msg="";
		var url=userUrl+"/getAllUsers";
		genericFactory.getAll(msg,url).then(function(response) {
				vm.users = response.data;
								
			});
		}
		
		$scope.editMachineOwner=function(machineOwner){
			getMachineOwner();
			getAllUser();
			$scope.machineOwner=machineOwner;
			$scope.addTab=true;
			
		}
		$scope.changeStatus=function(machineOwner){
			  var msg=""
					 var url =machineOwnerUrl+"/changeStatusMachineOwner";
						genericFactory.add(msg,url,machineOwner).then(function(response) {
							var responceObj=response.data
							loadMachineOwner();
					
							if(responceObj.code==200){
								toastr.success(responceObj.msg);	
							}else{
								toastr.error(responceObj.msg);
							}
							
					});
		}
		$scope.deleteMachineOwner=function(machineOwner){
			  var msg=""
					 var url =machineOwnerUrl+"/deleteMachineOwner";
						genericFactory.add(msg,url,machineOwner).then(function(response) {
							var responceObj=response.data
							loadMachineOwner();
					
							if(responceObj.code==200){
								toastr.success(responceObj.msg);	
							}else{
								toastr.error(responceObj.msg);
							}
							
					});
		}
		
		//************************* EXPORT *****************************//
		$scope.filename="MachineOwners"
			vm.labels={'machineid.machineName': 'Machine Name','userid.firstName':'User Name','status': 'Status'}
		
		$scope.newExcel= function(){
	    	  getAllMachineOwnerForExport();
				 //$rootScope.loader=true;
	    	  //document.getElementById('btnExport').click();
				 setTimeout(function(){
					 
					 document.getElementById('btnExport').click();
					 //$rootScope.loader=false;
					  $rootScope.$digest();
					},1000);		
				
			}
			function getAllMachineOwnerForExport(){
				 var msg=""
					 var url =machineOwnerUrl+"/getAllMachineOwner"; 
				genericFactory.getAll(msg,url).then(function(response) {
					vm.allMachineOwners = response.data;
					
					console.log("allMachineOwners: "+JSON.stringify(vm.allMachineOwners))
									
				});
				
			}

		function loadMachineOwner(){
			loadMachineOwnerCount();			
			
			var url;
			var msg=""
			
				if($scope.search==""||$scope.search==undefined){
					url =machineOwnerUrl+"/getlistMachineOwnerByLimit/"+vm.pageno+'/'+vm.perPage;
				}
			else{
				url=machineOwnerUrl+"/getlistMachineOwnerByLimitAndSearch?searchText="+$scope.search+"&pageNo="+vm.pageno+'&perPage='+vm.perPage;
			}
				
				
				// var url =machineOwnerUrl+"/getlistMaterialsByLimit/"+vm.pageno+'/'+vm.perPage;
				genericFactory.getAll(msg,url).then(function(response) {
				vm.machineOwners = response.data;
				
				console.log("Location: "+JSON.stringify(vm.machineOwners))
								
			});
			
			
		}
		function loadMachineOwnerCount(){
			var msg=""
				 var url =""
				if($scope.search==""||$scope.search==undefined){
					url =machineOwnerUrl+"/getMachineOwnerCount"
				}
			else{
				url=machineOwnerUrl+"/getMachineOwnerCountAndSearch?searchText="+$scope.search;
			}
			 //var url =machineOwnerUrl+"/getMaterialsCount";
			genericFactory.getAll(msg,url).then(function(response) {
			vm.total_count = response.data;
			console.log("total_count: "+JSON.stringify(vm.total_count))
							
		});
		}
		
		$scope.save=function (machineOwner){
          
			console.log("machineOwner: "+JSON.stringify(machineOwner))
        if(machineOwner==undefined||machineOwner.machine==undefined||machineOwner.machine==""){
				$scope.machineError=true;
				return;
			}else{
				$scope.machineError=false;
			}
        if(machineOwner.user==undefined||machineOwner.user==""){
				$scope.userError=true;
				return;
			}else{
				$scope.userError=false;
			}
		
          
         $scope.loderview=true;
     
         
       	console.log("MachineOwner "+JSON.stringify(machineOwner))

         var msg=""
			 var url =machineOwnerUrl+"/addMachineOwner";
				genericFactory.add(msg,url,machineOwner).then(function(response) {
					var responceObj=response.data
					getMachineOwner();
					getAllUser();
					loadMachineOwner();
					$scope.addTab=false;
					$scope.machineOwner={}
					
					if(responceObj.code==200){
						toastr.success(responceObj.msg);	
					}else{
						toastr.error(responceObj.msg);
					}
					
			});
       /* toastr.success('Submitted successfully!!')
       $scope.addTab=false;
        return;*/
    }
		
}
	
})();
