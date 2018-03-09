app.controller("UpdateProductCtrl", function ($scope, $uibModalInstance, ProductService, toastr, product) {

    $scope.product = product;

    $scope.submit = function (paramProduct) {
        console.log("param")
        console.log(paramProduct);

        ProductService.updateProductById(product.id, paramProduct)
            .then(function success(response) {
                if (response.status === 200) {
                    toastr.success("Produto editado com sucesso!");
                    $uibModalInstance.close(201);
                }
            }, function error(error) {
                toastr.error("Problemas ao tentar atribuir preço ao produto: " + paramProduct.productName);
            });

    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});