'use strict';

app.controller('clienteController', function($scope, $rootScope, $http, SERVER, $location, Notification) { 

    $scope.cliente = {
    		id : null,
    		nome: null,
    		tipoPessoa : "PF",
    		cpfCnpj : null,
    		endereco : null,
    		numero : null,
    		bairro : null,
    		cep :  null,
    		cidade : null
    }; 	
	 if ($rootScope.cliente !=null){
		 $scope.cliente = clone($rootScope.cliente);
	 } 

	 $scope.consultar = function() {  
	    $scope.clienteConsulta = {
	    		id : null,
	    		nome: null,
	    		tipoPessoa : "PF",
	    		cpfCnpj : null,
	    		endereco : null,
	    		numero : null,
	    		bairro : null,
	    		cep :  null,
	    		cidade : null
	    };
	    if ($scope.clientePesquisa == null){
	    	$scope.clientePesquisa = "";
	    }
	    $scope.clienteConsulta.nome = $scope.clientePesquisa;
	    var data = JSON.stringify($scope.clienteConsulta);
		$http.post(SERVER.url+ '/api/clientes/consultar',data).success(function(data){ 
			$scope.clientes = data;
		}).error(function(data){ 
	        Notification.error({message: data[0], positionY: 'botton', positionX: 'left'});
		});  

	    $scope.currentPage = 0;
	};

	$scope.voltar = function() {
		$rootScope.cliente=null;
		$location.path('/clientes');
	}
	
	$scope.novo = function() { 
		$rootScope.cliente=null;
	    $scope.cliente = {
	    		id : null,
	    		nome: null,
	    		tipoPessoa : "PF",
	    		cpfCnpj : null,
	    		endereco : null,
	    		numero : null,
	    		bairro : null,
	    		cep :  null,
	    		cidade : null
	    }; 		
		$location.path('/clientes/cadastro');
	};
	
	$scope.salvar = function() {  
	    var data = JSON.stringify($scope.cliente);
		$http.post(SERVER.url+ '/api/clientes',data).success(function(){ 
	        Notification.success({message: 'Dados salvos com Sucesso!', positionY: 'botton', positionX: 'left'});
			$scope.novo();
			$scope.consultar();
		}).error(function(data){ 
			if (data != null){ 
				var erro = "";
				for (var msg in data) {
					erro = erro + data[msg] + "<br/>"
				}

				 Notification.error({message: erro, positionY: 'botton', positionX: 'left'});
			} 
		});
		 
	};

	
	$scope.editar = function(clienteConsulta) {  
		$rootScope.cliente = clone(clienteConsulta);
		$location.path('/clientes/cadastro');
	};
	
	$scope.excluir = function() { 
		if ($scope.cliente != null && $scope.cliente.id != null){ 
			$('#md-exluir').modal('hide');  
		    var data = JSON.stringify($scope.cliente);
			$http.post(SERVER.url+ '/api/clientes/excluir',data).success(function(){
				$scope.mensagem = "Cliente Excluirdo com Sucesso";
				$scope.novo();
				$scope.consultar();
			}).error(function(data){  
		        Notification.error({message: data[0], positionY: 'botton', positionX: 'left'});
			});
	  }
	};
	
	$scope.pesquisar = function(keyEvent) {
		  if (keyEvent.which === 13)
			  $scope.consultar();
	}

	$scope.alterarTipoPessoa = function() { 
		$scope.cliente.cpfCnpj = null;
		if ($scope.cliente.tipoPessoa == "PF"){
			$scope.exibePj = false;
			$scope.exibePf = true;
		} else	if ($scope.cliente.tipoPessoa == "PJ"){
			$scope.exibePj = true;
			$scope.exibePf = false;
		}
	};

	$scope.habilitaEditarPessoa = function() { 
		if ($scope.cliente.id == null){
			return false;
		}
		return true;
	};
	 
	
	// paginacao //
    $scope.currentPage = 0;
    $scope.pageSize = 3; 
    $scope.numberOfPages=function(){
    	if ($scope.clientes !=null){
    		return Math.ceil($scope.clientes.length/$scope.pageSize);
    	}
    }
	
	$scope.consultar(" ");
	$scope.cliente.tipoPessoa = "PF";
	$scope.exibePf = true;
	$('.cpf').mask('999.999.999.99');
	$('.cnpj').mask('99.999.999/9999-99');
	$('.cep').mask('99999-999'); 
});
