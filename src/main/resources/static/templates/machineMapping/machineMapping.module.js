(function(){
	'use strict';

	angular.module('myApp.machineMapping',[

		])
	.config(function($stateProvider){
		$stateProvider
		.state('main.machineMapping', {
            url: "/machineMapping",
            views: {
                "sub": {templateUrl: "templates/machineMapping/machineMapping.html",
                		controller : "MachineMappingController as vm"}
            }
        })
       
        
	});

})();