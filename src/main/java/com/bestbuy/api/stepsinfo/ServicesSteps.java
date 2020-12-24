package com.bestbuy.api.stepsinfo;

import com.bestbuy.api.constants.EndPoints;
import com.bestbuy.api.model.ServicesPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class ServicesSteps {

    @Step("Creating services with name: {0}")
    public ValidatableResponse createService(String name) {
        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName(name);

        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(servicesPojo)
                .post(EndPoints.CREATE_SERVICE)
                .then();
    }

    @Step("Getting the service information with name: {0}")
    public HashMap<String, Object> getServiceInfoByName(String name) {
        String p1 = "findAll{it.name=='";
        String p2 = "'}.get(0)";

        HashMap<String, Object> value = SerenityRest.rest().given()
                .when()
                .get(EndPoints.GET_ALL_SERVICE)
                .then()
                .extract()
                .path(p1 + name + p2);
        return value;
    }

    @Step("Updating service information with serviceId: {0}, name: {1}")
    public ValidatableResponse updateServiceByPatch(int serviceId, String name) {
        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName(name);

        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .pathParam("serviceId", serviceId)
                .when()
                .body(servicesPojo)
                .patch(EndPoints.PATCH_UPDATE_SERVICE_BY_ID)
                .then();
    }

    @Step("Deleting service information with servicetId: {0}")
    public ValidatableResponse deleteService(int serviceId) {
        return SerenityRest.rest()
                .given().pathParam("serviceId", serviceId)
                .when()
                .delete(EndPoints.DELETE_SERVICE_BY_ID)
                .then();
    }

    @Step("Getting information of the service with serviceId: {0}")
    public ValidatableResponse getServiceByID(int serviceId) {
        return SerenityRest.rest()
                .given()
                .pathParam("serviceId", serviceId)
                .when()
                .get(EndPoints.GET_SINGLE_SERVICE_BY_ID)
                .then();
    }

    @Step("Getting all service information")
    public ValidatableResponse getAllStudents() {
        return SerenityRest.rest()
                .given()
                .when()
                .get(EndPoints.GET_ALL_SERVICE)
                .then();
    }
}