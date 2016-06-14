/**
 * @author Thomas Yuan <pcomy@163.com>
 */

/**
 * Main AngularJS Web Application
 */
var app = angular.module('tutorialWebApp', [
  'ngRoute'
]);

/**
 * Configure the Routes
 */
app.config(['$routeProvider', function ($routeProvider) {
  $routeProvider
    // Home
    .when("/", {templateUrl: "partials/home.html", controller: "PageCtrl"})
    .when("/home", {templateUrl: "partials/home.html", controller: "PageCtrl"})
    // Pages
    .when("/query", {templateUrl: "partials/query.html", controller: "QueryCtrl"})
    .when("/query/:customer", {templateUrl: "partials/query-details.html", controller: "QueryDetailsCtrl"})
    .when("/create", {templateUrl: "partials/create.html", controller: "CreateCtrl"})
    // More
    .when("/about", {templateUrl: "partials/about.html", controller: "PageCtrl"})
    .when("/contact", {templateUrl: "partials/contact.html", controller: "PageCtrl"})
    // else 404
    .otherwise("/404", {templateUrl: "partials/404.html", controller: "PageCtrl"});
}]);

/**
 * Controls the Customer Create
 */
app.controller('CreateCtrl', function ($scope, $location, $http) {

	$scope.resetInput = function(){
    	$scope.successResult = false;
    	$scope.failResult = false;
    	$scope.message = '';
        $scope.input = {
        	customer : {
            	number: Math.uuid(),
            	nickName: '',
        		legalName: '',
        		personalHomePageUrl: '',
        		mobileNumber: '',
        		emailAddress: '',
        		password: '',
        		category: 'INDIVIDUAL',
        		role: 'ROLESUBBER',
        		avatarUrl: ''
            }    	
        };
    };
    
	$scope.resetInput();
           
    $scope.createCustomer = function(){
    	var passwordHash = md5($scope.input.customer.password);
    	var createCustomerRequest = {
        	customerId: $scope.input.customer.number,
        	nickName: $scope.input.customer.nickName,
    		legalName: $scope.input.customer.legalName,
    		personalHomePageUrl: $scope.input.customer.personalHomePageUrl,
    		mobileNumber: $scope.input.customer.mobileNumber,
    		emailAddress: $scope.input.customer.emailAddress,
    		passwordHash: $scope.input.customer.passwordHash,
    		category: $scope.input.customer.category,
    		role: $scope.input.customer.role,
    		avatarUrl: $scope.input.customer.avatarUrl,    			
    	}
        $http({url:"/customer/rest/customers",method:"post",data: createCustomerRequest})
        .success(function(data,status,headers,config){
        	$scope.message = 'Customer Created!';
        	$scope.successResult = true;
        	$scope.failResult = false;
        })
        .error(function(data,status,headers,config){
        	$scope.message = 'Service not available';
        	$scope.successResult = false;
        	$scope.failResult = true;
        });    	
    };
    $scope.queryCustomer = function(){
    	$location.path("/query/" + $scope.input.customer.number);    	
    };
});
/**
 * Controls the Customer List
 */
app.controller('QueryCtrl', function ($scope, $location, $http) {

	$scope.listCustomers = function(){
    	$scope.successResult = false;
    	$scope.failResult = false;
    	$scope.message = '';
        
        $http({url:"/query/rest/customers",method:"get"})
        .success(function(data,status,headers,config){
        	$scope.customers = data;
        })
        .error(function(data,status,headers,config){
        	$scope.message = 'Service not available';
        });    	
    };
    
	$scope.listCustomers();
           
    $scope.queryCustomer = function(c){
    	$location.path("/query/" + c.identifier);    	
    };
});
/**
 * Controls the Customer Details Inquiry
 */
app.controller('QueryDetailsCtrl', function ($scope, $location, $http, $routeParams) {

	$scope.getCustomer = function(){
    	$scope.message = '';
        
        $http({url:"/query/rest/customers/" + $routeParams.customer, method:"get"})
        .success(function(data,status,headers,config){
        	$scope.query = {
        		customer : {
                	number: data.identifier,
                	nickName: data.nickName,
            		legalName: data.legalName,
            		personalHomePageUrl: data.personalHomePageUrl,
            		mobileNumber: data.mobileNumber,
            		emailAddress: data.emailAddress,
            		category: data.category,
            		role: data.role,
            		avatarUrl: data.avatarUrl,   			
        		}	
        	};
        })
        .error(function(data,status,headers,config){
        	$scope.message = 'Service not available';
        });    	
    };
    
	$scope.getCustomer();
    
});
/**
 * Controls all other Pages
 */
app.controller('PageCtrl', function (/* $scope, $location, $http */) {
  console.log("Page Controller reporting for duty.");

  // Activates the Carousel
  $('.carousel').carousel({
    interval: 5000
  });

  // Activates Tooltips for Social Links
  $('.tooltip-social').tooltip({
    selector: "a[data-toggle=tooltip]"
  })
});
