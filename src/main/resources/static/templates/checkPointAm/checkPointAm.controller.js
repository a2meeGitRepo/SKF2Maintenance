(function() {
	'use strict';

	angular.module('myApp.checkPointAm').controller('AmCheckPointsController', AmCheckPointsController);

	AmCheckPointsController.$inject = [ '$state', '$log',
			'$scope', 'toastr','localStorageService','ApiEndpoint','loginFactory','genericFactory','$document','$rootScope' ];

	/* @ngInject */
	function AmCheckPointsController($state, $log, $scope, toastr,localStorageService,ApiEndpoint,loginFactory,genericFactory,$document,$rootScope) {
		var loadAmCheckPointUrl = ApiEndpoint.url+"amCheckPoints";
		var machineUrl = ApiEndpoint.url+"machine";
		
		var vm = angular.extend(this, {
			perPage : 10,
			total_count:0,
			pageno:1,
			getMachineByChannel:getMachineByChannel,
		});
	
		(function activate() {
			loadAmCheckPoint();
		
		})();
		
		// current page
		$scope.pagination = {
		        current: 1
		    };
		
		// page changed 
		$scope.pageChanged = function(pageNo){
			vm.pageno=pageNo;
			loadAmCheckPoint();
			
		}
		$scope.searchByPagination=function (search){
			loadAmCheckPoint();
			console.log("I AM Searching ")
			
		}
		
		$scope.add=function (){
			$scope.AmCheckPoint={}
			$scope.AmCheckPoint.tight=false
			$scope.AmCheckPoint.inspect=false
			$scope.AmCheckPoint.clean=false
			$scope.AmCheckPoint.lubricate=false
			$scope.addTab=true;
			loadChannel();
			loadAmCheckPoint();
		}
		
		$scope.cancel=function (){
			$scope.addTab=false;
			$scope.AmCheckPoint={}
		}	
		function loadChannel(){
			var msg=""
			var url=machineUrl+"/getAllChannels"
			genericFactory.getAll(msg,url).then(function(response) {
					vm.channels= response.data;
					console.log("channels : "+JSON.stringify(vm.channels))
					
					
				});
			
		}
		
function getMachineByChannel(channelNo){
			
			var msg="";
			var url=machineUrl+"/getAllMachineByChannerNo?channelNo="+channelNo;
			genericFactory.getAll(msg,url).then(function(response) {
					vm.machines = response.data;
					console.log("machines: "+JSON.stringify(vm.machines))			
				});
		}
		
		$scope.editAMCheckPoint=function(AmCheckPoint){
			$scope.addTab=true;
			$scope.AmCheckPoint=AmCheckPoint
			$scope.machine.manTime=new Date(machine.manTime)
			getAllMachine();
			loadAmCheckPoint();
		}
		
		$scope.deleteAMCheckPoint=function(AmCheckPoint){
			  var msg=""
					 var url =loadAmCheckPointUrl+"/deleteAmCheckPoints";
						genericFactory.add(msg,url,AmCheckPoint).then(function(response) {
							var responceObj=response.data
							loadAmCheckPoint();
							
							
							if(responceObj.code==200){
								toastr.success(responceObj.msg);	
							}else{
								toastr.error(responceObj.msg);
							}
							
					});
		}
		$scope.changeStauts=function(AmCheckPoint){
			  var msg=""
					 var url =loadAmCheckPointUrl+"/AmCheckPointsChangeStatus";
						genericFactory.add(msg,url,AmCheckPoint).then(function(response) {
							var responceObj=response.data
							loadAmCheckPoint();
							
							
							if(responceObj.code==200){
								toastr.success(responceObj.msg);	
							}else{
								toastr.error(responceObj.msg);
							}
							
					});
			
		}
		
		//************************* EXPORT *****************************//
		$scope.filename="AMCheckPoints"
			vm.labels={'machine.machineName': 'Machine Name','item':'Item','satandard':'Standard','checkPointCode':'Check Point Code','tool': 'Tool','frequency': 'Frequency','manTime': 'Man Time','status': 'Status'}
		
		$scope.newExcel= function(){
	    	  getAllAMCheckPointsForExport();
				 //$rootScope.loader=true;
	    	  //document.getElementById('btnExport').click();
				 setTimeout(function(){
					 
					 document.getElementById('btnExport').click();
					 //$rootScope.loader=false;
					  $rootScope.$digest();
					},1000);		
				
			}
			function getAllAMCheckPointsForExport(){
				 var msg=""
					 var url =loadAmCheckPointUrl+"/getAllAMCheckPoints"; 
				genericFactory.getAll(msg,url).then(function(response) {
					vm.allAMCheckPoints = response.data;
					
					console.log("allAMCheckPoints : "+JSON.stringify(vm.allAMCheckPoints))
									
				});
				
			}
		
		
		
		//*************************************************************//
		
		function loadAmCheckPoint(){
			loadAmCheckPointCount();			
			
			var url;
			var msg=""
			
				if($scope.search==""||$scope.search==undefined){
					url =loadAmCheckPointUrl+"/getlistAMCheckPointByLimit/"+vm.pageno+'/'+vm.perPage;
				}
			else{
				url=loadAmCheckPointUrl+"/getlistAMCheckPointsByLimitAndSearch?searchText="+$scope.search+"&pageNo="+vm.pageno+'&perPage='+vm.perPage;
			}
				
				
				// var url =loadAmCheckPointUrl+"/getlistMaterialsByLimit/"+vm.pageno+'/'+vm.perPage;
				genericFactory.getAll(msg,url).then(function(response) {
				vm.AmCheckPoints = response.data;
				
				console.log("AmCheckPoints : "+JSON.stringify(vm.AmCheckPoints))
								
			});
			
			
		}
		function loadAmCheckPointCount(){
			var msg=""
				 var url =""
				if($scope.search==""||$scope.search==undefined){
					url =loadAmCheckPointUrl+"/getAMCheckPointsCount"
				}
			else{
				url=loadAmCheckPointUrl+"/getAMCheckPointsCountAndSearch?searchText="+$scope.search;
			}
			 //var url =loadAmCheckPointUrl+"/getMaterialsCount";
			genericFactory.getAll(msg,url).then(function(response) {
			vm.total_count = response.data;
			console.log("total_count: "+JSON.stringify(vm.total_count))
							
		});
		}
		
		$scope.save=function (AmCheckPoint){
          
			console.log("AmCheckPoint: "+JSON.stringify(AmCheckPoint))
        if(AmCheckPoint==undefined||AmCheckPoint.machine==undefined||AmCheckPoint.machine==""){
				$scope.MachineError=true;
				return;
			}else{
				$scope.MachineError=false;
			}
        if(AmCheckPoint.item==undefined||AmCheckPoint.item==""){
			$scope.ItemError=true;
			return;
		}else{
			$scope.ItemError=false;
		}
		
		if(AmCheckPoint.satandard==undefined||AmCheckPoint.satandard==""){
			$scope.satandardError=true;
			return;
		}else{
			$scope.satandardError=false;
		}
        
       
       
       if(AmCheckPoint.tool==undefined||AmCheckPoint.tool==""){
			$scope.toolError=true;
			return;
		}else{
			$scope.toolError=false;
		}
		
		if(AmCheckPoint.frequency==undefined||AmCheckPoint.frequency==""){
			$scope.frequencyError=true;
			return;
		}else{
			$scope.frequencyError=false;
		}
       
       if(AmCheckPoint.manTime==undefined){
			$scope.manTimeError=true;
			return;
		}else{
			$scope.manTimeError=false;
		}
		
		
          
         $scope.loderview=true;
     
         
       	console.log("AmCheckPoint "+JSON.stringify(AmCheckPoint))
         var msg=""
			 var url =loadAmCheckPointUrl+"/addAmCheckPoints";
				genericFactory.add(msg,url,AmCheckPoint).then(function(response) {
					var responceObj=response.data
					loadAmCheckPoint();
					getAllMachine();
					 $scope.addTab=false;
					$scope.AmCheckPoint={}
					
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
