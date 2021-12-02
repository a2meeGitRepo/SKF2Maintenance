(function() {
	'use strict';

	angular.module('myApp.auditStagesAndItems').controller('AuditStagesAndItemsController', AuditStagesAndItemsController);

	AuditStagesAndItemsController.$inject = [ '$state', '$log',
			'$scope', 'toastr','localStorageService','ApiEndpoint','loginFactory','genericFactory','$document','$rootScope' ];

	/* @ngInject */
	function AuditStagesAndItemsController($state, $log, $scope, toastr,localStorageService,ApiEndpoint,loginFactory,genericFactory,$document,$rootScope) {
		var machineUrl = ApiEndpoint.url+"machine";
		var auditUrl = ApiEndpoint.url+"audit";
		
		var vm = angular.extend(this, {
			getMachineByChannel:getMachineByChannel,
			cancel:cancel
		
		});
	
		(function activate() {
			getAuditStage()
			getAuditItems()
		
		})();
		 function cancel(){
			 $scope.addStageTab=false
				$scope.addItemeTab=false
				vm.machines={}
			 vm.channels={}
		 }
		$scope.addStage = function(){
			$scope.addStageTab=true
			$scope.addItemeTab=false
			loadChannel();
			
		}
		$scope.addItems = function(){
			$scope.addStageTab=false
			$scope.addItemeTab=true
			loadChannel();
			$scope.auditItems=[]
		}
		$scope.pushItem = function(){
			var auditItem={}
			auditItem.itemName=""
			auditItem.description=""
			auditItem.fullScore=0
			$scope.auditItems.push(auditItem);
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
		$scope.getStagesByMachine=function(machineId){
			var msg="";
			var url=auditUrl+"/getAuditStage2sByMachine?machineId="+machineId;
			genericFactory.getAll(msg,url).then(function(response) {
					vm.stagesByMachhine = response.data;
					console.log("stagesByMachhine"+JSON.stringify(vm.stagesByMachhine))				
				});
		}
		function getMachine(){
			var msg="";
			var url=machineUrl+"/getAllMachines";
			genericFactory.getAll(msg,url).then(function(response) {
					vm.machines = response.data;
					console.log("machines"+JSON.stringify(vm.machines))				
				});
			}
			
		function getAuditStage(){
			var msg="";
			var url=auditUrl+"/getAuditStages";
			genericFactory.getAll(msg,url).then(function(response) {
					vm.auditStages = response.data;
					console.log("auditStages"+JSON.stringify(vm.auditStages))				
				});
			}
		function getAuditItems(){
			var msg="";
			var url=auditUrl+"/getAuditItems";
			genericFactory.getAll(msg,url).then(function(response) {
					vm.auditItems = response.data;
					console.log("auditItems"+JSON.stringify(vm.auditItems))				
				});
			}
		$scope.saveItems=function(auditItem){
			console.log("auditItem"+JSON.stringify(auditItem))				

			   if(auditItem==undefined||auditItem.machine==undefined){
					$scope.machineError=true;
					return;
				}else{
					$scope.machineError=false;
				}
	        if(auditItem.auditStage==undefined||auditItem.auditStage==""){
				$scope.auditStageError=true;
				return;
			}else{
				$scope.auditStageError=false;
			}
	    	console.log("$scope.auditItems.length"+JSON.stringify($scope.auditItems.length))	
	        if($scope.auditItems.length==0){
				$scope.auditItemsErr=true;
				return;
			}else{
				$scope.auditItemsErr=false;
			}
	    	auditItem.items=$scope.auditItems
	       
	        
	        var msg=""
				 var url =auditUrl+"/addAuditItem";
					genericFactory.add(msg,url,auditItem).then(function(response) {
						var responceObj=response.data
						getAuditItems();
						 $scope.addItemeTab=false;
						$scope.auditItem={}
						
						if(responceObj.code==200){
							toastr.success(responceObj.msg);	
						}else{
							toastr.error(responceObj.msg);
						}
						
				});
		}
		
		$scope.save=function(auditStage){
			console.log("auditStage"+JSON.stringify(auditStage))				

			   if(auditStage==undefined||auditStage.machine==undefined){
					$scope.machineError=true;
					return;
				}else{
					$scope.machineError=false;
				}
	        if(auditStage.stageNo==undefined||auditStage.stageNo==""){
				$scope.stageNoErr=true;
				return;
			}else{
				$scope.stageNoErr=false;
			}
			
	        if(auditStage.stageName==undefined||auditStage.stageName==""){
				$scope.stageNameErr=true;
				return;
			}else{
				$scope.stageNameErr=false;
			}
	        
	        if(auditStage.maximumScore==undefined||auditStage.maximumScore==""){
				$scope.maximumScoreErr=true;
				return;
			}else{
				$scope.maximumScoreErr=false;
			}
	        
	        var msg=""
				 var url =auditUrl+"/addAuditStage";
					genericFactory.add(msg,url,auditStage).then(function(response) {
						var responceObj=response.data
						getAuditStage();
						 $scope.addStageTab=false;
						$scope.auditStage={}
						
						if(responceObj.code==200){
							toastr.success(responceObj.msg);	
						}else{
							toastr.error(responceObj.msg);
						}
						
				});
			console.log("auditStage"+JSON.stringify(auditStage))		
		}

}
	
	
	
	
})();
