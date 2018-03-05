app.controller("LoginCtrl", function($scope, $uibModal, toastr, UserService, BASE_TEMPLATE_PATH) {
    $scope.loginUser = (userParams) => {
        UserService.login(userParams)
            .then(function successCallback(result) {
                toastr.success("Login feito com sucesso");
            }).catch(function errorCallback(error) {
                toastr.error("Erro ao fazer login");
                console.log(error);
            })
    }

    $scope.openSignUpDialog = () => {
        var newModal = $uibModal.open({
            ariaLabelledBy: 'Cadastro de usuário',
            ariaDescribedBy: 'Formulario para cadastro de clientes',
            templateUrl: BASE_TEMPLATE_PATH + 'signUpView.html',
            controller: 'SignUpCtrl',
        })

        newModal.result
            .then(res => {
                if (res === 201) {
                    toastr.success("Usuário cadastrado com sucesso");
                }
            }).catch(err => {
                if (err != 'cancel') {
                    toastr.error("Erro ao cadastrar usuário");
                }
            });
    }

})