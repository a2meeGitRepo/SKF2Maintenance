(function(){
	'use strict';

	angular.module('myApp.upload',[

		])
	.config(function($stateProvider){
		$stateProvider
		.state('main.upload', {
            url: "/upload",
            views: {
                "sub": {templateUrl: "templates/upload/upload.html",
                		controller : "UploadController as vm"}
            }
        })
       
        
	});

})();