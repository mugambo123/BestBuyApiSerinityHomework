package com.bestbuy.api.info;

import com.bestbuy.api.stepsinfo.ServicesSteps;
import com.bestbuy.api.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RunWith(SerenityRunner.class)
public class ServicesTest extends TestBase {

    static String name = "Cola Gola";
    static int serviceId;

    @Steps
    ServicesSteps servicesSteps;
    @Test
    public void test001(){
        servicesSteps.createService(name).statusCode(201);
    }

    @Title("Verify if the service was added to the application")
    @Test
    public void test002() {
        HashMap<String, Object> value = servicesSteps.getServiceInfoByName(name);
        assertThat(value, hasValue(name));
        serviceId = (int) value.get("id");
    }
    @Title("Update the service information by Patch and verify the updated information")
    @Test
    public void test003(){

        name = name+ "_Updated";

        servicesSteps.updateServiceByPatch(serviceId, name).statusCode(200);

        HashMap<String, Object> value = servicesSteps.getServiceInfoByName(name);
        assertThat(value, hasValue(name));

    }

    @Title("Delete the student and verify if the student is deleted!")
    @Test
    public void test004() {
        servicesSteps.deleteService(serviceId).statusCode(204);
        servicesSteps.getServiceByID(serviceId).statusCode(404);
    }

}
