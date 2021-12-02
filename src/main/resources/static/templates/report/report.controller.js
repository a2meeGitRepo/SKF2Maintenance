(function() {
	'use strict';

	angular.module('myApp.report').controller('ReportController', ReportController);

	ReportController.$inject = [ '$state', '$log',
			'$scope', 'toastr','localStorageService','ApiEndpoint','loginFactory','genericFactory','$document','$rootScope','$http'];

	/* @ngInject */
	function ReportController($state, $log, $scope, toastr,localStorageService,ApiEndpoint,loginFactory,genericFactory,$document,$rootScope,$http) {
		var reportUrl = ApiEndpoint.url+"report";
		
		var userDetail = localStorageService.get(ApiEndpoint.userKey);
		console.log("User :: "+JSON.stringify(userDetail))

		var vm = angular.extend(this, {
		});
	
		(function activate() {
			$scope.report={}
			$scope.report.reportType=""
				$scope.report.year=""
		})();
		
		$scope.loadReport=function(report){
			console.log("Report "+JSON.stringify(report))
			if(report.reportType==null){
				$scope.reportTypeErr=true
			}else{
				$scope.reportTypeErr=false
			}
			var url=""
			if(report.reportType=="MMTR"){
				 url=reportUrl+"/getMTTRReportByYear"
				
			}else if(report.reportType=="MTBF"){
				 url=reportUrl+"/getMTBFReportByYear"
			}
			else if(report.reportType=="Downtime"){
				 url=reportUrl+"/getDowntimeReportByYear"
			}
			else if(report.reportType=="Breakdown"){
				 url=reportUrl+"/getBreakdownReportByYear"
			}
			url+="?year="+report.year
			console.log("URL :: "+url)
			var msg="";
			genericFactory.getAll(msg,url).then(function(response) {
				vm.reports = response.data;
				
				console.log("reports : "+JSON.stringify(vm.reports))
								
			});
			
		}
		
		
	
}
	
})();
