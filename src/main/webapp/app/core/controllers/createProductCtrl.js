app.controller("CreateProductCtrl", function ($uibModalInstance, toastr, ProductService) {

    this.createProduct = function (product) {
        product.preco = 0;
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

    this.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});