(function(){
	'use strict';

	angular.module('myApp.machineOwner',[

		])
	.config(function($stateProvider){
		$stateProvider
		.state('main.machineOwner', {
            url: "/machineOwner",
            views: {
                "sub": {templateUrl: "templates/machineOwner/machineOwner.html",
                		controller : "machineOwnerController as vm"}
            }
        })
       
        
	});

})();