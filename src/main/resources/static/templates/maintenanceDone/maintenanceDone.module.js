(function(){
	'use strict';

	angular.module('myApp.maintenanceDone',[

		])
	.config(function($stateProvider){
		$stateProvider
		.state('main.maintenanceDone', {
            url: "/maintenanceDone",
            views: {
                "sub": {templateUrl: "templates/maintenanceDone/maintenanceDone.html",
                		controller : "MaintenanceDoneController as vm"}
            }
        })
       
        
	});

})();