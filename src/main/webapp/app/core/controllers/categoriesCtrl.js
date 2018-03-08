app.controller("categoriesCtrl", function ($scope, $uibModal, DiscountsService, toastr, BASE_TEMPLATE_PATH) {

    var loadCategories = function() {
        $scope.categories = [];
        DiscountsService.getDiscounts()
            .then(res => {
                $scope.categories = res.data;
            }).catch(err => {
                console.log(err);
            })
    }

    $scope.openNewDiscountView = () => {
        var modalInstance = $uibModal.open({
            ariaLabelledBy: 'Criar Desconto',
            ariaDescribedBy: 'Formulario para criar um desconto',
            templateUrl: BASE_TEMPLATE_PATH + 'newDiscountView.html',
            controller: 'NewDiscountCtrl',
            resolve: {
                categories: function() {
                    return $scope.categories;
                }
            }
        });
        
        modalInstance.result.then(res => {
            if (res == 201) {
                loadCategories();
                toastr.success("Desconto criado com sucesso");
            }}).catch(err => {
                if (err != "cancel" && err != "backdrop click") {
                    toastr.error(err);
                    console.log(err);
                }
            })

    }

    $scope.removeDiscount = (category) => {
        DiscountsService.createDiscount(category, 0)
            .then(res => {
                if (res.status == 201) {
                    loadCategories();
                    toastr.success("Desconto Removido com sucesso")
                }
            }).catch(err => {
                toastr.error(err);
            });
    }
    
    loadCategories();
});