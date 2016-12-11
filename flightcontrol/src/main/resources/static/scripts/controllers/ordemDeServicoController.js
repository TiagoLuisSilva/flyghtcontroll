'use strict';

app.controller('ordemDeServicoController', function($scope, $rootScope, $http, SERVER, $location, Notification, $filter) { 

	
	// paginacao //
    $scope.currentPage = 0;
    $scope.pageSize = 3; 
    $scope.numberOfPages=function(){
    	if ($scope.ordemDeServicos !=null){
    		return Math.ceil($scope.ordemDeServicos.length/$scope.pageSize);
    	}
    	if ($scope.clientes !=null){
    		return Math.ceil($scope.clientes.length/$scope.pageSize);
    	}
    }
    
    $scope.ordemDeServico = {  
    	   id : null, 
	       cliente : {
	    		id : null,
	    		nome: null,
	    		tipoPessoa : "PF",
	    		cpfCnpj : null,
	    		endereco : null,
	    		numero : null,
	    		bairro : null,
	    		cep :  null,
	    		cidade : null
	       },
	       modelo : null, 
	       cardNumber : null,
	       dataEmissao : new Date(),
	       prefixo : null, 
	       descricao : null,  
		   usuario : {
		  		id : null,
				nome: null,
				userName: null,
				admin: false,
				senha: null
			}
     }; 	
	 if ($rootScope.ordemDeServico !=null){
		 $scope.ordemDeServico = clone($rootScope.ordemDeServico);
	 } 

	 $scope.consultar = function() {  
	    $scope.ordemDeServicoConsulta =  {  
	     	   dataInicio: new Date(),
	     	   dataFinal: new Date(), 
	    	   idCliente :  null,  
	     };
	    if ($scope.ordemDeServicoPesquisa == null){
	    	$scope.ordemDeServicoPesquisa = "";
	    }
	    var diaInicio  = parseInt($scope.dataInicio.substring(0,2));
	    var mesInicio  = parseInt($scope.dataInicio.substring(3,5));
	    var anoInicio  = parseInt($scope.dataInicio.substring(6,10));
	    var diaFinal   = parseInt($scope.dataFinal.substring(0,2));
	    var mesFinal   = parseInt($scope.dataFinal.substring(3,5));
	    var anoFinal   = parseInt($scope.dataFinal.substring(6,10));
	    
	    $scope.ordemDeServicoConsulta.dataInicio = new Date(anoInicio, mesInicio-1, diaInicio); 
	    $scope.ordemDeServicoConsulta.dataFInal =  new Date(anoFinal, mesFinal-1, diaFinal);
	    $scope.ordemDeServicoConsulta.idCliente = $scope.clienteSelecionado.id;
	    var data = JSON.stringify($scope.ordemDeServicoConsulta);
		$http.post(SERVER.url+ '/api/ordemDeServicos/consultar',data).success(function(data){ 
			$scope.ordemDeServicos = data;
		}).error(function(data){ 
	        Notification.error({message: data[0], positionY: 'botton', positionX: 'left'});
		});  

	    $scope.currentPage = 0;
	};

	$scope.voltar = function() {
		$rootScope.ordemDeServico=null;
		$location.path('/ordemdeservico/');
	}
	
	$scope.novo = function() { 
		
		$rootScope.ordemDeServico=null;
		$scope.clienteSelecionado = {
				id: null,
				nome: null
		}
	    $scope.ordemDeServico =   {  
		     	   id : null, 
			       cliente : {
			    		id : null,
			    		nome: null,
			    		tipoPessoa : "PF",
			    		cpfCnpj : null,
			    		endereco : null,
			    		numero : null,
			    		bairro : null,
			    		cep :  null,
			    		cidade : null
			       },
			       modelo : null, 
			       cardNumber : null,
			       dataEmissao : $filter('date')( new Date(), 'dd/MM/yyyy'),
			       prefixo : null, 
			       descricao : null,  
				   usuario : {
				  		id : null,
						nome: null,
						userName: null,
						admin: false,
						senha: null
					}
		     }; 	
		$scope.dataEmissao = $filter('date')( new Date(), 'dd/MM/yyyy');
		$location.path('/ordemdeservico/cadastro');
	};
	
	$scope.salvar = function() {  

	    var dia    = parseInt($scope.dataEmissao.substring(0,2));
	    var mes    = parseInt($scope.dataEmissao.substring(3,5));
	    var ano    = parseInt($scope.dataEmissao.substring(6,10));
	    
	    $scope.ordemDeServico.dataEmissao = new Date(ano, mes-1, dia); 
		$scope.ordemDeServico.cliente = $scope.clienteSelecionado;
	    var data = JSON.stringify($scope.ordemDeServico);
		$http.post(SERVER.url+ '/api/ordemDeServicos',data).success(function(){ 
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

	
	$scope.editar = function(ordemDeServicoConsulta) {  
		$rootScope.ordemDeServico = clone(ordemDeServicoConsulta);
		$location.path('/ordemDeServicos/cadastro');
	};
	
	$scope.excluir = function() { 
		if ($scope.ordemDeServico != null && $scope.ordemDeServico.id != null){ 
			$('#md-exluir').modal('hide');  
		    var data = JSON.stringify($scope.ordemDeServico);
			$http.post(SERVER.url+ '/api/ordemDeServicos/excluir',data).success(function(){ 
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
  
	// ************ CONSULTA DE CLIENTES  **************
	$scope.pesquisarCliente = function(keyEvent) {
		  if (keyEvent.which === 13)
			  $scope.consultarCliente();
	}
	 $scope.consultarCliente = function() {  
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

	    $scope.currentPagePesquisa = 0;
	};
	 $scope.selecionarCliente = function(clienteSelecionado) {  
		 $scope.clienteSelecionado = clone(clienteSelecionado);
		 $('#md-consultaCliente').modal('hide');  
	 }
	
	// ************ CONSULTA DE CLIENTES FIM **************
	$scope.clienteSelecionado = {
			id: null,
			nome: null
	}
    var data = new Date();
    $scope.dataInicio =  $filter('date')(data, 'dd/MM/yyyy');
    $scope.dataFinal = $filter('date')(data, 'dd/MM/yyyy');
    
	$scope.consultar(" ");  
	$('.data').datepicker({ 
		defaultDate: new Date(),
		autoclose: true,
        todayBtn: true, 
    }); 
	$('.data').mask('99/99/9999'); 
	
	
	$scope.dataEmissao = $filter('date')( new Date(), 'dd/MM/yyyy');
	
});
