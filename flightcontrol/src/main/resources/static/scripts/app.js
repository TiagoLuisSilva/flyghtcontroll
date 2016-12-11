var app = angular.module('flightcontroll', ["ngRoute","ui-notification","ui.bootstrap"]);

app.constant('SERVER', {
	 url: document.location.origin + '/flightcontroll',
});


app.controller('mainController', function($scope,$rootScope, $http, SERVER, $location) { 

   $rootScope.activetab = $location.path();
	$scope.header = {
		name : 'header',
		url : 'views/templates/header.html'
	}

	$scope.mensagem = "";  
  
});
 

 
 
app.config(function($routeProvider) {
 
	
	$routeProvider.when("/", { 
		templateUrl : "views/templates/home.html" 
	}).when("/ordemdeservico", {
		templateUrl : "views/ordemdeservico/ordemdeservicoCons.html",
		controller : "ordemDeServicoController"
	}).when("/ordemdeservico/cadastro", {
		templateUrl : "views/ordemdeservico/ordemdeservicoForm.html",
		controller : "ordemDeServicoController"
	}).when("/clientes", {
		templateUrl : "views/cliente/clienteCons.html",
		controller : "clienteController"
	}).when("/clientes/cadastro", {
		templateUrl : "views/cliente/clienteForm.html",
		controller : "clienteController"
	}).when("/usuarios", {
		templateUrl : "views/usuario/usuarioCons.html",
		controller : "usuarioController"
	}).when("/usuarios/cadastro", {
		templateUrl : "views/usuario/usuarioForm.html",
		controller : "usuarioController"
	}).otherwise ({ redirectTo: '/' });
});
 

 function clone(obj) {
    if (null == obj || "object" != typeof obj) return obj;
    var copy = obj.constructor();
    for (var attr in obj) {
        if (obj.hasOwnProperty(attr)) copy[attr] = obj[attr];
    }
    return copy;
}

 app.filter('startFrom', function() {
	    return function(input, start) {
	    	if (input != null){
		        start = +start; //parse to int
		        return input.slice(start);
	    	}
	    }
});
 
 function encodeImageFileAsURL() {

	    var filesSelected = document.getElementById("file").files;
	    if (filesSelected.length > 0) {
	      var fileToLoad = filesSelected[0];

	      var fileReader = new FileReader();

	      fileReader.onload = function(fileLoadedEvent) {
	        var srcData = fileLoadedEvent.target.result; // <--- data: base64

	        var newImage = document.createElement('img');
	        newImage.src = srcData;

	        document.getElementById("imgTest").innerHTML = newImage.outerHTML; 
	      }
	      fileReader.readAsDataURL(fileToLoad);
	    }
	  }