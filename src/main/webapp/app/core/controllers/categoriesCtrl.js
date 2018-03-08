app.controller("categoriesCtrl", function ($scope, $uibModal, DiscountsService, toastr, BASE_TEMPLATE_PATH) {
    $scope.categories = [];

    var listCategories = function() {
        $scope.categories = [];
        DiscountsService.getDiscounts()
            .then(res => {

            }).catch(err => {

            })
    }

    $scope.openNewDiscountView = () => {
        var modalInstance = $uibModal.open({
            ariaLabelledBy: 'Criar Desconto',
            ariaDescribedBy: 'Formulario para criar um desconto',
            templateUrl: BASE_TEMPLATE_PATH + 'newDiscountView.html',
            controller: 'NewDiscountCtrl',
        });
        
        modalInstance.result.then(res => {
            if (res == 201) {
                toastr.success("Desconto criado com sucesso");

            }}).catch(err => {
                if (err != "cancel" && err != "backdrop click") {
                    toastr.error(err);
                    console.log(err);
                }
            })

    }

    $scope.removeDiscount = (category) => {
        DiscountsService.createDiscount(category, 0);
    }
    
    listCategories();
});