$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip(); 
}); // TOOLTIP ADJUSTMENT

const app = angular.module("efApp", ["ngRoute", "ngMessages","ngAnimate", "toastr", "ui.bootstrap"]);
