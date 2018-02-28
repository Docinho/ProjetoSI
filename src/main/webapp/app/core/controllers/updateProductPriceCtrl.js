app.controller("UpdateProductPriceCtrl", function ($scope, $uibModalInstance, ProductService, toastr, produto) {

    $scope.produto = produto;

    $scope.submit = function (product) {

        console.log(product);

        ProductService.updateProductById(product.id)
            .then(function success(response) {
                if (response.status === 200) {
                    toastr.success("Produto editado com sucesso!");
                    $uibModalInstance.close({
                        status: 200,
                        newProduct: response.data
                    });
                }
            }, function error(error) {
                console.log(error);
                toastr.error("Problemas ao tentar atribuir preço ao produto: " + product.id);
            });

    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});