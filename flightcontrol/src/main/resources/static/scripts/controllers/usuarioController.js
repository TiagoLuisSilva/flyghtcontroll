'use strict';

app.controller('usuarioController', function($scope, $rootScope, $http, SERVER, $location, Notification) { 
	 if ($rootScope.usuario !=null){
		 $scope.usuarios = clone($rootScope.usuario);
	 } 
	 
	 $scope.consultar = function() {  
	    $scope.usuarioConsulta = {
	    		id : null,
	    		nome: null,
	    		userName: null,
	    		admin: false,
	    		senha: null
	    }; 
	    if ($scope.usuarioPesquisa == null){
	    	$scope.usuarioPesquisa = "";
	    }
	    $scope.usuarioConsulta.nome = $scope.usuarioPesquisa;
	    var data = JSON.stringify($scope.usuarioConsulta);
		$http.post(SERVER.url+ '/api/usuarios/consultar',data).success(function(data){ 
			$scope.usuarios = data;
		}).error(function(data){ 
	        Notification.error({message: data[0], positionY: 'botton', positionX: 'left'});
		});  
	    $scope.currentPage = 0;
	};

	$scope.voltar = function() {
		$rootScope.usuario=null;
		$location.path('/usuarios');
	}
	
	$scope.novo = function() { 
		$rootScope.usuario=null;
	    $scope.usuario = {
	    		id : null,
	    		nome: null,
	    		userName: null,
	    		admin: false,
	    		senha: null
	    }; 		
		$location.path('/usuarios/cadastro');
	};
	
	$scope.salvar = function() { 
	    if ($scope.usuario.id == null && ( $scope.usuario.senha=="" || $scope.usuario.senha == null)){
	        Notification.error({message: 'Senha deve ser informada!', positionY: 'botton', positionX: 'left'});
	    } else {
		    var data = JSON.stringify($scope.usuario);
			$http.post(SERVER.url+ '/api/usuarios',data).success(function(){ 
		        Notification.success({message: 'Dados salvos com Sucesso!', positionY: 'botton', positionX: 'left'});
				$scope.novo();
				$scope.consultar();
			}).error(function(data){ 
		        Notification.error({message: data[0], positionY: 'botton', positionX: 'left'});
			});
		}
	};

	$scope.editar = function(usuarioConsulta) {  
		$rootScope.usuario = clone(usuarioConsulta);
		$location.path('/usuarios/cadastro');
	};
	
	$scope.excluir = function() { 
		if ($scope.usuario != null && $scope.usuario.id != null){
			$('#md-exluir').modal('hide');  
		    var data = JSON.stringify($scope.usuario);
			$http.post(SERVER.url+ '/api/usuarios/excluir',data).success(function(){ 
		    	
				$scope.novo();    
			}).error(function(data){

		        Notification.error({message: data[0], positionY: 'botton', positionX: 'left'});
			});
	  }
	};
	

	$scope.resetSenha = function() { 
		$scope.usuario.senha = null;
		$scope.confirmaSenha = null;
		$scope.haserror = "";
        $scope.haserrorSenha =""
	}
	
	$scope.confirmarSenha = function() {  
	    if ($scope.usuario.senha=="" || $scope.usuario.senha == null){
	        Notification.error({message: 'Senha deve ser informada!', positionY: 'botton', positionX: 'left'});
			$scope.haserror = "";	    	
			$scope.haserrorSenha = "has-error";
	    } else if ($scope.confirmaSenha=="" || $scope.confirmaSenha == null){	
	        Notification.error({message: 'Contra Senha deve ser informada!', positionY: 'botton', positionX: 'left'});
	        $scope.haserrorSenha =""
			$scope.haserror = "has-error";	    	
	    } else {
			if ($scope.usuario.senha != $scope.confirmaSenha){
		        Notification.error({message: 'Senhas não conferem!', positionY: 'botton', positionX: 'left'});
				$scope.haserror = "has-error";
		    } else {
		    	$('#md-alterarSenha').modal('hide');    	
		    	return true;
		    } 
	    }
	    return false;
	};

	$scope.cancelarSenha = function() {  
		$scope.usuario.senha = null;
	}
	$scope.pesquisar = function(keyEvent) {
		  if (keyEvent.which === 13)
			  $scope.consultar();
	}
	// paginacao //
  $scope.currentPage = 0;
  $scope.pageSize = 2; 
  $scope.numberOfPages=function(){
  	if ($scope.usuarios !=null){
  		return Math.ceil($scope.usuarios.length/$scope.pageSize);
  	}
  }
	
	$scope.consultar(" "); 
});
