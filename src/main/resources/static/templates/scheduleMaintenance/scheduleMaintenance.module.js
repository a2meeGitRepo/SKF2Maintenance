(function(){
	'use strict';

	angular.module('myApp.scheduleMaintenance',[

		])
	.config(function($stateProvider){
		$stateProvider
		.state('main.scheduleMaintenance', {
            url: "/scheduleMaintenance",
            views: {
                "sub": {templateUrl: "templates/scheduleMaintenance/scheduleMaintenance.html",
                		controller : "ScheduleMaintenanceController as vm"}
            }
        })
       
        
	});

})();