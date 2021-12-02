(function() {
	'use strict';

	angular.module('myApp.scheduleMaintenance').controller('ScheduleMaintenanceController', ScheduleMaintenanceController);

	ScheduleMaintenanceController.$inject = [ '$state', '$log',
			'$scope', 'toastr','localStorageService','ApiEndpoint','loginFactory','genericFactory','$document','$rootScope' ];

	/* @ngInject */
	function ScheduleMaintenanceController($state, $log, $scope, toastr,localStorageService,ApiEndpoint,loginFactory,genericFactory,$document,$rootScope) {
		var machineUrl = ApiEndpoint.url+"machine";
		var maintenanceUrl = ApiEndpoint.url+"maintenance";
		var user = localStorageService.get(ApiEndpoint.userKey);																	
		console.log("user: "+JSON.stringify(user))
		var vm = angular.extend(this, {
			changeMode:changeMode,
			save:save,
		});
	
		(function activate() {
			$scope.monthArr = ['jan','feb', 'mar', 'apr', 'may', 'jun', 'jul', 'aug', 'sep', 'aug', 'nov', 'dec'];

		loadMachine();
		})();
		function loadMachine(){
			 var msg=""
				 var url =machineUrl+"/getAllMachines"; 
			genericFactory.getAll(msg,url).then(function(response) {
				vm.machines = response.data;
				
				console.log("allMachines: "+JSON.stringify(vm.machines))
								
			});
			
			
		}
		
		
		function changeMode(frequency){
			$scope.frequency = frequency;
			var year = new Date().getFullYear();
			
			setTimeout(function(){
				window.scroll({
					  top: document.body.scrollHeight, 
					  left: 0, 
					  behavior: 'smooth' 
				});
			},0);
			
			if($scope.frequency == 'yearly'){
				$scope.yearDate = null;
			}
			if($scope.frequency == 'monthly'){
				$scope.monthlyArray = [{month: 'Jan'},{month: 'Feb'},{month: 'Mar'},{month: 'Apr'},{month: 'May'},{month: 'Jun'},{month: 'Jul'},{month: 'Aug'},{month: 'Sep'},{month: 'Oct'},{month: 'Nov'},{month: 'Dec'}];
				for(var i = 0; i < $scope.monthlyArray.length; i++){
					
					/*var FirstDay = new Date(year, month, 1);
			        var LastDay = new Date(year, month + 1, 0);*/
					
					var FirstDay = formatDate(new Date(year, i, 1));
			        var LastDay = formatDate(new Date(year, i + 1, 0));
			        
					$scope.monthlyArray[i].max = LastDay;
					$scope.monthlyArray[i].min = FirstDay;
//					$scope.monthlyArray[i].selectedDate = ;
				}
			}
			console.log("$scope.frequency :: "+$scope.frequency)
			if($scope.frequency == 'weekly'){
				$scope.weeklyArray = [{month: 'Jan'},{month: 'Feb'},{month: 'Mar'},{month: 'Apr'},{month: 'May'},{month: 'Jun'},{month: 'Jul'},{month: 'Aug'},{month: 'Sep'},{month: 'Oct'},{month: 'Nov'},{month: 'Dec'}];
				for(var i = 0; i < $scope.weeklyArray.length; i++){
					
					/*var FirstDay = new Date(year, month, 1);
			        var LastDay = new Date(year, month + 1, 0);*/
					
					var weekArr = getWeeksInMonth(i, year);
					$scope.weeklyArray[i].weeks = weekArr;
//					$scope.weeklyArray[i].max = LastDay;
//					$scope.weeklyArray[i].min = FirstDay;
//					$scope.monthlyArray[i].selectedDate = ;
				}
			}
			
			if($scope.frequency == 'quarterly' || $scope.frequency == 'halfyearly'){
				
				console.log("QUATERLY  ")
				$scope.quarterlyArray = [{month: 'Jan'},{month: 'Feb'},{month: 'Mar'},{month: 'Apr'},{month: 'May'},{month: 'Jun'},{month: 'Jul'},{month: 'Aug'},{month: 'Sep'},{month: 'Oct'},{month: 'Nov'},{month: 'Dec'}];
				for(var i = 0; i < $scope.quarterlyArray.length; i++){
					
					/*var FirstDay = new Date(year, month, 1);
			        var LastDay = new Date(year, month + 1, 0);*/
					
					var FirstDay = formatDate(new Date(year, i, 1));
			        var LastDay = formatDate(new Date(year, i + 1, 0));
			       // console.log("QUATERLY  max "+FirstDay)
					//console.log("QUATERLY min "+LastDay)
					$scope.quarterlyArray[i].max = LastDay;
					$scope.quarterlyArray[i].min = FirstDay;
					//$scope.monthlyArray[i].selectedDate ="" ;
					console.log("QUATERLY  "+JSON.stringify($scope.quarterlyArray))
				}
			}
		}
		
		function getWeeksInMonth(month, year){
			   var weeks=[],
			       firstDate=new Date(year, month, 1),
			       lastDate=new Date(year, month+1, 0), 
			       numDays= lastDate.getDate();
			   
			   var start=1;
			   var end=7-firstDate.getDay();
			   while(start<=numDays){
				   var m = (month + 1) < 10 ? '0'+ (month + 1) : (month + 1);
				   var s = start < 10 ? '0' + start : start;
				   var e = end < 10 ? '0' + end : end;
			       weeks.push({min:year + '-' + m + '-' + s,max: year + '-' + m + '-' + e});
			       start = end + 1;
			       end = end + 7;
			       if(end>numDays)
			           end=numDays;    
			   }        
			    return weeks;
			} 
		function formatDate(date) {
		    var d = new Date(date),
		        month = '' + (d.getMonth() + 1),
		        day = '' + d.getDate(),
		        year = d.getFullYear();

		    if (month.length < 2) month = '0' + month;
		    if (day.length < 2) day = '0' + day;

		    return [year, month, day].join('-');
		}
		
		
		
		
		function save(){
			//console.log("Sve "+JSON.stringify($scope.maintance.frequency))
			var dateArr = [];
			if($scope.maintance==undefined){
				$scope.machineError=true
				
				return;
			}else{
				$scope.machineError=false
			}
			if($scope.maintance.frequency==undefined){
				$scope.frquencyError=true
				
				return;
			}else{
				$scope.frquencyError=false
			}
			if($scope.maintance.frequency == 'monthly'){
				dateArr = monthly();
			}else if($scope.maintance.frequency == 'weekly'){
				dateArr = weekly();
			}else if($scope.maintance.frequency == 'quarterly'){
				dateArr = quarterly();
			}else if($scope.maintance.frequency == 'halfyearly'){
				dateArr = quarterly();		//halfYearly();
			}else if($scope.maintance.frequency == 'yearly'){
				dateArr = yearly();
			}
			
			if(dateArr.length==0){
				/*var containt={}
				containt.title="Alert"
				containt.massage="Select at least one date "
				genericFactory.showAlert();*/
				alert("Select at least one date ")
				return;
			}
			
			var iObj =  {
			        "dates": dateArr,
			       
			        "frequency": $scope.maintance.frequency,
			        
			        "createdBy":user.firstName+" "+user.lastName ,
			        "machine": $scope.maintance.machine
			 }
			var msg=""
				 var url =maintenanceUrl+"/scheduleMaintenance";
					genericFactory.add(msg,url,iObj).then(function(response) {
						var responceObj=response.data
						$scope.maintance={}
						if(responceObj.code==200){
							toastr.success(responceObj.message);	
						}else{
							toastr.error(responceObj.message);
						}
						
				});
			
			
		}
		var monthly = function(){
			var arr = [];
			for(var i = 0; i < $scope.monthlyArray.length; i++){
				if(isDate($scope.monthlyArray[i].selectedDate))
					arr.push($scope.monthlyArray[i].selectedDate);
			}
			
			return arr;
		}
		
		var weekly = function(){
			var arr = [];
			for(var i = 0; i < $scope.weeklyArray.length; i++){
				for(var j = 0; j < $scope.weeklyArray[i].weeks.length; j++){
					if(isDate($scope.weeklyArray[i].weeks[j].selectedDate))
						arr.push($scope.weeklyArray[i].weeks[j].selectedDate);
				}
			}
			
			return arr;
		}
		
		var quarterly = function(){
			var arr = [];
			for(var i = 0; i < $scope.quarterlyArray.length; i++){
				if(isDate($scope.quarterlyArray[i].selectedDate))
					arr.push($scope.quarterlyArray[i].selectedDate);
			}
			
			return arr;
		}
		
		var halfYearly = function(){
			var arr = [];
			for(var i = 0; i < $scope.monthArr.length; i++){
				if(isDate(document.getElementById($scope.monthArr[i]).value))
					arr.push(document.getElementById($scope.monthArr[i]).value);
			}
			
			return arr;
		}
		
		var yearly = function(){
			var arr = [];
			if(isDate(document.getElementById('jan').value))
				arr.push(document.getElementById('jan').value);
			
			return arr;
		}
		function isDate(value) {
		    switch (typeof value) {
		        case 'number':
		            return true;
		        case 'string':
		            return !isNaN(Date.parse(value));
		        case 'object':
		            if (value instanceof Date) {
		                return !isNaN(value.getTime());
		            }
		        default:
		            return false;
		    }
		}

	
}
	
	
	
	
})();
