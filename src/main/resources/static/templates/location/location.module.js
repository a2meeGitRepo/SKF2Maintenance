(function(){
	'use strict';

	angular.module('myApp.location',[

		])
	.config(function($stateProvider){
		$stateProvider
		.state('main.location', {
            url: "/location",
            views: {
                "sub": {templateUrl: "templates/location/location.html",
                		controller : "LocationController as vm"}
            }
        })
       
        
	});

})();