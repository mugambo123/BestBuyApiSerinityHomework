package com.bestbuy.api.stepsinfo;

import com.bestbuy.api.constants.EndPoints;
import com.bestbuy.api.model.StoresPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class StoresSteps {

    @Step("Creating store with name: {0}, type: {1}, address: {2}, address2 : {3}, city: {4}, state: {5}," +
            "zip: {6}, lat: {7}, lng: {8}, hours: {9}, services: {10}")
    public ValidatableResponse createStore(String name, String type, String address, String address2, String city,
                                           String state, String zip, float lat, float lng, String hours,
                                           HashMap<String, Object> services){
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);
        storesPojo.setServices(services);

        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(storesPojo)
                .post()
                .then();
    }
    @Step("Getting the store information with name: {0} ")
    public HashMap<String, Object>getStoreInfoByName(String name){
        String p1 = "findAll{it.name=='";
        String p2 = "'}.get(0)";

                HashMap<String, Object> value = SerenityRest.rest().given()
                        .get(EndPoints.GET_ALL_STORES)
                        .then()
                        .extract()
                        .path(p1 + name + p2);
                return value;

    }
    @Step("Updating by patch store information with storeId: {0}, name: {1}, services: {11}")
    public ValidatableResponse updateStoreByPatch(int storeId, String name, HashMap<String, Object> services){

        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setServices(services);

        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .pathParam("storeId", storeId)
                .log().all()
                .when()
                .body(storesPojo)
                .patch(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then();
    }
    @Step("Deleting store information with storeId: {0}")
    public ValidatableResponse deleteStudent(int storeId){
        return SerenityRest.rest()
                .given()
                .pathParam("storeId", storeId)
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID)
                .then();
    }
    @Step("Getting information of the store with storeId: {0}")
    public ValidatableResponse getStoreById(int storeId){
        return SerenityRest.rest()
                .given()
                .pathParam("storeId", storeId)
                .when()
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then();
    }
@Step("Getting all students information")
    public ValidatableResponse getAllStores(){
        return SerenityRest.rest()
                .given()
                .when()
                .get(EndPoints.GET_ALL_STORES)
                .then();
}
}
