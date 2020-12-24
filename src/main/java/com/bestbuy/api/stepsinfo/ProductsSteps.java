package com.bestbuy.api.stepsinfo;

import com.bestbuy.api.constants.EndPoints;
import com.bestbuy.api.model.ProductsPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class ProductsSteps {


    @Step("Creating product with name: {0}, type: {1} ,price:{2}, shipping{3} upc: {4} , description: {5}, manufacturer{6}," +
            " model{7}, url{8}, image{9}  ")
    public ValidatableResponse createProduct(String name, String type, float price, float shipping, String upc,
                                             String description, String manufacturer, String model, String url, String image) {
        ProductsPojo productsPojo = new ProductsPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setPrice(price);
        productsPojo.setShipping(shipping);
        productsPojo.setUpc(upc);
        productsPojo.setDescription(description);
        productsPojo.setManufacturer(manufacturer);
        productsPojo.setModel(model);
        productsPojo.setUrl(url);
        productsPojo.setImage(image);


        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(productsPojo)
                .post(EndPoints.CREATE_PRODUCT)
                .then();
    }

    @Step("Getting the product information with name: {0}")
    public HashMap<String, Object> getProductInfoByName(String name) {
        String p1 = "data.findAll{it.name=='";
        String p2 = "'}.get(1)";

        HashMap<String, Object> value = SerenityRest.rest().given()
                .when()
                .get(EndPoints.GET_ALL_PRODUCTS)
                .then()
                .extract()
                .path(p1 + name + p2);

        return value;
    }
    @Step("Updating product information wth " +
            "productID; {0}, name: {1}, type: {2} ,price:{3}, upc: {4}, shipping{5} , description: {6}, manufacturer{7}, " +
            " model{8}, url{9}, image{10}")
    public ValidatableResponse updateProductByPatch(int productID ,String name, String type, float price,  String upc, float shipping,
                                                    String description, String manufacturer, String model, String url, String image){
        ProductsPojo productsPojo = new ProductsPojo();
        productsPojo.setName(name);


        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .pathParam("productID",9999704)
                .log().all()
                .when()
                .body(productsPojo)
                .patch(EndPoints.PATCH_UPDATE_PRODUCT_BY_ID)
                .then();

    }
    @Step("Deleting product information with productId: {0}")
    public ValidatableResponse deleteProduct(int productId) {
        return SerenityRest.rest()
                .given()
                .pathParam("productId", productId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then();
    }

    @Step("Getting product information of the product with productId: {0}")
    public ValidatableResponse getProductByID(int productId) {
        return SerenityRest.rest()
                .given()
                .pathParam("productId", productId)
                .log().all()
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then();
    }

    @Step("Getting all products information")
    public ValidatableResponse getAllProducts(){
        return SerenityRest.rest()
                .given()
                .when()
                .get(EndPoints.GET_ALL_PRODUCTS)
                .then();
    }
}
