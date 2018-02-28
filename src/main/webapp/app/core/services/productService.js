/**
 * Service created to handle all product requests:
 * Create product and update product
 */
app.service("ProductService", function($http, BASE_SERVER_URL) {

    this.createProduct = (product) => {
        return $http.post("/api/produto/", JSON.stringify(product));
    }

    this.getAllProducts = () => {
        return $http.get("/api/produto/");
    }

    this.updateProductById = (data) => {
        var id = data.id;
        return $http.put("/api/produto/" + id, data);
    }

})