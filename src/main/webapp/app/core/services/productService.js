/**
 * Service created to handle all product requests:
 * Create product and update product
 */
app.service("ProductService", function($http, BASE_SERVER_URL) {

    this.createProduct = (product, category) => {
        return $http.post("/api/product/" + category, JSON.stringify(product));
    }

    this.getAllProducts = () => {
        return $http.get("/api/product/");
    }

    this.updateProductById = (id, data) => {
        return $http.put("/api/product/" + id + "/" + data.name, data.price);
    }

})