app.controller("DiscountCtrl", function($scope, $uibModal, BASE_TEMPLATE_PATH, toastr) {

    var initialLoad = () => {
        $scope.discounts = []
        $scope.categories = []
        loadDiscounts();
        loadCategories();
    }

    $scope.openNewDiscountView = () => {
        var newModal = $uibModal.open({
            ariaLabelledBy: 'Criar Desconto',
            ariaDescribedBy: 'Formulário para criar novo desconto em uma categoria',
            templateUrl: BASE_TEMPLATE_PATH + 'newDiscountView.html',
            controller: 'NewDiscountCtrl',
        });

        newModal.result.then(res => {
            if (res === 201) {
                toastr.success("Desconto criado com sucesso");
            }
        }).catch(err => {
            if (err != 'cancel' && err != 'backdrop click') {
                toastr.error("Erro ao criar desconto.");
                console.log(err);
            }
        });
    }

});