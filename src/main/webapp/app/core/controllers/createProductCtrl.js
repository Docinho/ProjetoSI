app.controller("CreateProductCtrl", function ($uibModalInstance, toastr, ProductService) {

    this.createProduct = function (product) {
        product.preco = 0.00;
        product.situacao = 0;
        ProductService.createProduct(product).then(function success(response) {
            if (response.status === 201) {
            	product.id = response.id;
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