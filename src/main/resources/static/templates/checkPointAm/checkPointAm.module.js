(function(){
	'use strict';

	angular.module('myApp.checkPointAm',[

		])
	.config(function($stateProvider){
		$stateProvider
		.state('main.checkPointAm', {
            url: "/checkPointAm",
            views: {
                "sub": {templateUrl: "templates/checkPointAm/checkPointAm.html",
                		controller : "AmCheckPointsController as vm"}
            }
        })
       
        
	});

})();