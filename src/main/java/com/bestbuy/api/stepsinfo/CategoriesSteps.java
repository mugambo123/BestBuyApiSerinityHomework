package com.bestbuy.api.stepsinfo;

import com.bestbuy.api.constants.EndPoints;
import com.bestbuy.api.model.CategoriesPojo;
import com.bestbuy.api.model.StoresPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class CategoriesSteps {

    @Step("creating category with name: {0}")
    public ValidatableResponse createCategory(String name) {
        CategoriesPojo categoriesPojo = new CategoriesPojo();
        categoriesPojo.setName(name);

        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(categoriesPojo)
                .post(EndPoints.CREATE_CATEGORIES)
                .then();
    }

    @Step("Getting the category information with name: {0}")
    public HashMap<String, Object> getCategoryInfoByName() {
        String p1 = "findAll{it.Gift Ideas=='";
        String p2 = "'}.get(0)";

        HashMap<String, Object> value = SerenityRest.rest().given()
                .when()
                .get(EndPoints.GET_ALL_CATEGORIES)
                .then()
                .extract()
                .path(p1 + p2);
        return value;
    }

    @Step("Updating category information by Patch with categoryID: {0}, name: {1}")
    public ValidatableResponse UpdateCategoryByPatch(String categoryId, String name) {

        CategoriesPojo categoriesPojo = new CategoriesPojo();
        categoriesPojo.setName(name);

        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .pathParam("categoryId", categoryId)
                .log().all()
                .when()
                .body(categoriesPojo)
                .patch(EndPoints.PATCH_UPDATE_CATEGORY_BY_ID)
                .then();

    }

    @Step("Deleting category information with categoryId: {0}")
    public ValidatableResponse deleteCategory(String categoryId) {
        return SerenityRest.rest()
                .given()
                .pathParam("categoryId", categoryId)
                .when()
                .delete(EndPoints.DELETE_CATEGORY_BY_ID)
                .then();
    }

    @Step("Getting information of category with categoryID: {0}")
    public ValidatableResponse getCategoryById(String categoryId) {
        return SerenityRest.rest()
                .given()
                .pathParam("categoryId", categoryId)
                .when()
                .delete(EndPoints.GET_SINGLE_CATEGORY_BY_ID)
                .then();

    }

    @Step("Getting all category information")
    public ValidatableResponse getAllCategories() {
        return SerenityRest.rest()
                .given()
                .when()
                .get(EndPoints.GET_ALL_CATEGORIES)
                .then();
    }
}
