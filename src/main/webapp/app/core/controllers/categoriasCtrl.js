app.controller("categoriaCtrl", function ($scope, $http, toastr, ProductService) {
    $scope.categorias = [];

    var listarCategorias = function() {

        $http({method:'GET', url:'http://localhost:8080/api/categorias/'})
            .then(function(resposta){
                $scope.categorias = resposta.data;
                console.log("Fez corretamente o GET das categorias");
                
            }, function(resposta){
                console.log("Fez erroneamente o GET das categorias");
            });         
    }

    $scope.atualizarDesconto = function(Categoria) {
        var form = document.getElementById("opcaoDesconto");
         var desconto = form.options[form.selectedIndex].value;
         console.log(Categoria);


          $http({method:'GET', url:"http://localhost:8080/api/categoria/"+ Categoria.id +"/" + desconto})
            .then(function(resposta){
                     Categoria.desconto = resposta.data.desconto;
                    console.log("Mudou o desconto com sucesso!")
                
            }, function(resposta){
             console.log("Falha " + resposta);          

               });


    } 

    listarCategorias();
});