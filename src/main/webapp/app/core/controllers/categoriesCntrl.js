app.controller("categoriaCtrl", function ($scope, $http, toastr, ProductService) {
    $scope.categories = [];


    var listCategories = function() {

        $http({method:'GET', url:'/api/category/'})
            .then(function(answer){
                $scope.categories = answer.data;
            }, function(answer){
                console.log("Fez erroneamente o GET das categorias");
            });         
    }

    $scope.updateDiscount = function(category, option) {

        var discount = parseInt(option);

        
        if(isNaN(discount)) {
           toastr.error("Selecione um desconto antes de alterar!");

        } else {
            $http({method:'GET', url:"/api/category/"+ category.id +"/" + discount})
            .then(function(answer){
                    category.discount = answer.data.discount;
                    toastr.success("Desconto alterado com sucesso!");
            }, function(answer){
             console.log("Falha " + answer);
               }); 
        }
    }
    
    listCategories();
});