(function() {
	'use strict';

	angular.module('myApp.user').controller('UserController', UserController);

	UserController.$inject = [ '$state', '$log',
			'$scope', 'toastr','localStorageService','ApiEndpoint','loginFactory','genericFactory','$document','$rootScope' ];

	/* @ngInject */
	function UserController($state, $log, $scope, toastr,localStorageService,ApiEndpoint,loginFactory,genericFactory,$document,$rootScope) {
		var userLoginUrl = ApiEndpoint.url+"userLogin";
		var commonUrl = ApiEndpoint.url+"common";
		var userDetails = localStorageService.get(ApiEndpoint.userKey);
		var vm = angular.extend(this, {
			edit:edit,
			delet:delet,
			changeStatus:changeStatus,
		});
	
		(function activate() {
			loadUsers();
		})();
		
		// current page
		
		
		$scope.add=function (){
			$scope.addTab=true;
			loadBranch();
			loadRole()
		
		}
		
		function edit (user){
			$scope.addTab=true;
			vm.user=user
			loadBranch();
			loadRole()
		}
		
		$scope.cancel=function (){
			$scope.addTab=false;
			vm.user={}
		}	
		function delet(user){
			var msg="";
			var url=userLoginUrl+"/deletUser";
			genericFactory.add(msg,url,user).then(function(response) {
					loadUsers();		
				});
			}
		function changeStatus(user){
			var msg="";
			var url=userLoginUrl+"/changeStatus";
			genericFactory.add(msg,url,user).then(function(response) {
				loadUsers();
				});
			}
		function loadUsers(){
		var msg="";
		var url=userLoginUrl+"/getAllUsers";
		genericFactory.getAll(msg,url).then(function(response) {
				vm.users = response.data;
			  	console.log("users "+JSON.stringify(vm.users))		
			});
		}
		function loadBranch(){
			var msg="";
			var url=commonUrl+"/getAllBranch";
			genericFactory.getAll(msg,url).then(function(response) {
					vm.branchs = response.data;
				  	console.log("branch "+JSON.stringify(vm.branchs))		
				});
			}
		function loadRole(){
			var msg="";
			var url=userLoginUrl+"/getAllRole";
			genericFactory.getAll(msg,url).then(function(response) {
					vm.roles= response.data;
				  	console.log("roles "+JSON.stringify(vm.roles))		
				});
			}
		
		
		
		
	
		
		$scope.save=function (user){
		 	console.log("user "+JSON.stringify(user))
          
      
        if(user==undefined|| user.branch==undefined||user.branch==""){
			$scope.branchError=true;
			return;
		}else{
			$scope.branchError=false;
		}
		
		if(user.role==undefined||user.role==""){
			$scope.roleError=true;
			return;
		}else{
			$scope.roleError=false;
		}
        
       if(user.firstName==undefined||user.firstName==""){
			$scope.firstNameErr=true;
			return;
		}else{
			$scope.firstNameErr=false;
		}
       
       if(user.lastName==undefined||user.lastName==""){
			$scope.lastNameErr=true;
			return;
		}else{
			$scope.lastNameErr=false;
		}
      
       if(user.userName==undefined||user.userName==""){
			$scope.userNameErr=true;
			return;
		}else{
			$scope.userNameErr=false;
		}
      
       if(user.password==undefined||user.password==""){
			$scope.passwordErr=true;
			return;
		}else{
			$scope.passwordErr=false;
		}
     
          
         $scope.loderview=true;
         user.active=1
         user.addedDate=new Date();
         user.addedBy=userDetails.user_id
         
       	console.log("spare "+JSON.stringify(user))

         var msg=""
			 var url =userLoginUrl+"/addUser";
				genericFactory.add(msg,url,user).then(function(response) {
					var responceObj=response.data
					loadUsers();
					 $scope.addTab=false;
					vm.user={}
					
					if(responceObj.code==200){
						toastr.success(responceObj.msg);	
					}else{
						toastr.error(responceObj.msg);
					}
					
			});
       /* toastr.success('Submitted successfully!!')
       $scope.addTab=false;
        return;*/
    }
		
}
	
})();
