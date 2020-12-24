package com.bestbuy.api.info;

import com.bestbuy.api.stepsinfo.StoresSteps;
import com.bestbuy.api.testbase.TestBase;
import com.bestbuy.api.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;

@RunWith(SerenityRunner.class)
public class StoresTest extends TestBase {

    static String name = "Bargain Kingdom" + TestUtils.getRandomValue();
    static String type = "Food, Wine and Grocery";
    static String address = "1569, Kensington Road";
    static String address2 = "Downtown";
    static String city = "Los Angeles";
    static String state = "California";
    static String zip = "3459";
    static float lat = 34.0522f;
    static float lng = 118.2437f;
    static String hours = "Monday to Saturday: 8.00 a.m. to 11.00 p.m.";
    static int storeId;

    @Steps
    StoresSteps storesSteps;

    @Title("This will create a new store")
    @Test
    public void test001() {
        HashMap<String, Object> services = new HashMap<>();
        services.put("Pharmacy", "Prescription");

        storesSteps.createStore(name, type, address, address2, city, state, zip, lat, lng, hours, services)
               .log().all(); //.statusCode(201);
    }

    @Title("Verify if the store was added to the application")
    @Test
    public void test002() {
        HashMap<String, Object> value = storesSteps.getStoreInfoByName(name);
        assertThat(value, hasValue(name));
        storeId = (int) value.get("id");
    }

    @Title("Update the store information by patch and verify the updated information")
    @Test
    public void test003() {

        name = name + "_Updated";

        HashMap<String, Object> services = new HashMap<>();
        services.put("Pharmacy", "Flue JAb");
        storesSteps.updateStoreByPatch(storeId, name, services).statusCode(200);

        HashMap<String, Object> value = storesSteps.getStoreInfoByName(name);
        assertThat(value, hasValue(name));

    }

    @Title("Delete the student and verify if the student is deleted!")
    @Test
    public void test004() {
        storesSteps.deleteStudent(storeId).statusCode(204);
        storesSteps.getStoreById(storeId).statusCode(404);
    }
}




