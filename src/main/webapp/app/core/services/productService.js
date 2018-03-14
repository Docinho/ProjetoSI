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
        return $http.put("/api/product/" + id + "/" + data.productName, data.price);
    }

    this.sellProducts = (id, quantity) => {
        return $http.post("/api/product/" + id + "/sales/", quantity);
    }

    this.cancelSale = (idProduct, sale) => {
        console.log(idProduct + "ID PRODUCT");
        console.log(idProduct + "ID SALE")
        return $http.put("/api/product/" + idProduct + "/sales/", sale);
    }

})