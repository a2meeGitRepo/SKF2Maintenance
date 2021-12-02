(function(){
	'use strict';

	angular.module('myApp.report',[

		])
	.config(function($stateProvider){
		$stateProvider
		.state('main.report', {
            url: "/report",
            views: {
                "sub": {templateUrl: "templates/report/report.html",
                		controller : "ReportController as vm"}
            }
        })
       
        
	});

})();