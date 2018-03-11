app.service("DiscountsService", function($http) {

    this.url = "/api/category/";

    this.getDiscounts = () => {
        return $http.get(this.url);
    }

    this.createDiscount = (category, discount) => {
        return $http.put(this.url + category.id, discount);
    }

})