app.controller("NewDiscountCtrl", function($scope, $uibModalInstance, DiscountsService) {

    var loadCategories = () => {
        $scope.categories = []
        DiscountsService.getDiscounts()
            .then(res => {
                if (res == 201) {
                    $scope.categories = res.data;
                }
            }).catch(err => {
                console.log(err);
            })
    }

    $scope.discounts = [{
            message:"Sem Desconto (0%)", value:0
        }, {
            message:"Bom Desconto (10%)", value:10
        }, {
            message:"Ótimo Desconto (25%)", value:25
        }, {
            message:"Super Desconto (50%)", value:50
    }];

    loadCategories();

    $scope.createDiscount = (category, discount) => {
        DiscountsService.createDiscount(category, discount);
    }


    $scope.cancel = () => {
        $uibModalInstance.dismiss("cancel");
    }

})