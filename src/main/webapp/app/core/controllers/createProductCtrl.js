app.controller("CreateProductCtrl", function ($scope, $uibModalInstance, toastr, ProductService) {

    $scope.createProduct = function (product) {
        product.price = 0;
        ProductService.createProduct(product).then(function success(response) {
            if (response.status === 201) {
                toastr.success("Produto adicionado com sucesso!");
                $uibModalInstance.close(201);
            }
        }, function error(error) {
            console.log(error);
            toastr.error("Problemas ao tentar adicionar produto.");
        });;
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});