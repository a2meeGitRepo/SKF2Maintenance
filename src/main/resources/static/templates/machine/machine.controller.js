(function() {
	'use strict';

	angular.module('myApp.machine').controller('MachineController', MachineController);

	MachineController.$inject = [ '$state', '$log',
			'$scope', 'toastr','localStorageService','ApiEndpoint','loginFactory','genericFactory','$document','$rootScope' ];

	/* @ngInject */
	function MachineController($state, $log, $scope, toastr,localStorageService,ApiEndpoint,loginFactory,genericFactory,$document,$rootScope) {
		var machineUrl = ApiEndpoint.url+"machine";
		
		var vm = angular.extend(this, {
			
			perPage : 10,
			total_count:0,
			pageno:1,
		});
	
		(function activate() {
			loadMachines()
		
		})();
		
		// current page
		$scope.pagination = {
		        current: 1
		    };
		
		// page changed 
		$scope.pageChanged = function(pageNo){
			vm.pageno=pageNo;
			loadMachines();
			
		}
		$scope.searchByPagination=function (search){
			loadMachines();
			console.log("I AM Seraching ")
			
		}
		$scope.add=function (){
			$scope.addTab=true;
		}
		$scope.cancel=function (){
			$scope.addTab=false;
		}	
		$scope.editmachine=function(machine){
			$scope.addTab=true;
			$scope.machine=machine
			$scope.machine.purchaseDate=new Date(machine.purchaseDate)
		}
		$scope.deletMachine=function(machine){
			  var msg=""
					 var url =machineUrl+"/deleteMachine";
						genericFactory.add(msg,url,machine).then(function(response) {
							var responceObj=response.data
							loadMachines();
							
							
							if(responceObj.code==200){
								toastr.success(responceObj.msg);	
							}else{
								toastr.error(responceObj.msg);
							}
							
					});
		}
		$scope.changeStatus=function(machine){
			  var msg=""
					 var url =machineUrl+"/changeStatus";
						genericFactory.add(msg,url,machine).then(function(response) {
							var responceObj=response.data
							loadMachines();
							
							
							if(responceObj.code==200){
								toastr.success(responceObj.msg);	
							}else{
								toastr.error(responceObj.msg);
							}
							
					});
			
		}
		//************************* EXPORT *****************************//
		$scope.filename="Machines"
			vm.labels={'machineName': 'Machine Name','assetType':'Asset Type','assetNumber':'Asset Number','application':'Application','currentCostCenter':'Current Cost Center','status':'Status','purchaseDate':'Purchase Date'}
		
		$scope.newExcel= function(){
	    	  getAllMachineForExport();
				 //$rootScope.loader=true;
	    	  //document.getElementById('btnExport').click();
				 setTimeout(function(){
					 
					 document.getElementById('btnExport').click();
					 //$rootScope.loader=false;
					  $rootScope.$digest();
					},1000);		
				
			}
			function getAllMachineForExport(){
				 var msg=""
					 var url =machineUrl+"/getAllMachines"; 
				genericFactory.getAll(msg,url).then(function(response) {
					vm.allMachines = response.data;
					
					console.log("allMachines: "+JSON.stringify(vm.allMachines))
									
				});
				
			}
		
		
		
		//*************************************************************//
		$scope.checkAssetNo=function(assetNumber){
			 var msg=""
				 var url =machineUrl+"/checkAssetNumber?assetNumber="+assetNumber;
					genericFactory.getAll(msg,url).then(function(response) {
						var responceObj=response.data
						if(responceObj){
							$scope.assetNoExits=true
							$scope.assetNoError=false
						}else{
							$scope.assetNoExits=false
						}
						console.log("machines: "+JSON.stringify(responceObj))
						
						/*if(responceObj.code==200){
							toastr.success(responceObj.msg);	
						}else{
							toastr.error(responceObj.msg);
						}*/
						
				});
		}
		
		
		
		function loadMachines(){
			loadMachinesCount();			
			
			var url;
			var msg=""
			
				if($scope.search==""||$scope.search==undefined){
					url =machineUrl+"/getlistMachineByLimit/"+vm.pageno+'/'+vm.perPage;
				}
			else{
				url=machineUrl+"/getlistMachineByLimitAndSearch?searchText="+$scope.search+"&pageNo="+vm.pageno+'&perPage='+vm.perPage;
			}
				
				
				// var url =materialUrl+"/getlistMaterialsByLimit/"+vm.pageno+'/'+vm.perPage;
				genericFactory.getAll(msg,url).then(function(response) {
				vm.machines = response.data;
				
				console.log("machines: "+JSON.stringify(vm.machines))
								
			});
			
			
		}
		function loadMachinesCount(){
			var msg=""
				 var url =""
				if($scope.search==""||$scope.search==undefined){
					url =machineUrl+"/getMachineCount"
				}
			else{
				url=machineUrl+"/getMachineCountAndSearch?searchText="+$scope.search;
			}
			 //var url =materialUrl+"/getMaterialsCount";
			genericFactory.getAll(msg,url).then(function(response) {
			vm.total_count = response.data;
			console.log("total_count: "+JSON.stringify(vm.total_count))
							
		});
		}
		
	

    $scope.save=function (machine){
    	 if(machine==undefined||machine.chennelNo==undefined){
				$scope.chennelNoError=true;
				return;
			}else{
				$scope.chennelNoError=false;
			}
          
        if(machine.machineName==undefined){
				$scope.machinError=true;
				return;
			}else{
				$scope.machinError=false;
			}
        if(machine.assetNumber==undefined||machine.assetNumber==""){
			$scope.assetNoError=true;
			return;
		}else{
			$scope.assetNoError=false;
		}
        if($scope.assetNoExits){
        	return;
        }
        
        if(machine.assetType==undefined){
				$scope.assettypeError=true;
				return;
			}else{
				$scope.assettypeError=false;
			}
        
        if(machine.application==undefined){
				$scope.applicationErr=true;
				return;
			}else{
				$scope.applicationErr=false;
			}
        
        if(machine.purchaseDate==undefined){
				$scope.dateError=true;
				return;
			}else{
				$scope.dateError=false;
			}
        if(machine.currentCostCenter==undefined){
				$scope.currentErr=true;
				return;
			}else{
				$scope.currentErr=false;
			}
        /* if(machine.status==undefined){
				$scope.statusError=true;
				return;
			}else{
				$scope.statusError=false;
			}*/
       
          
         $scope.loderview=true;
     
         
       	console.log("machine "+JSON.stringify(machine))

         var msg=""
			 var url =machineUrl+"/addMachine";
				genericFactory.add(msg,url,machine).then(function(response) {
					var responceObj=response.data
					loadMachines();
					 $scope.addTab=false;
					$scope.machine={}
					
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
