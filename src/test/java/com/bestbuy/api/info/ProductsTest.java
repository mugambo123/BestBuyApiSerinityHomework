package com.bestbuy.api.info;

import com.bestbuy.api.stepsinfo.ProductsSteps;
import com.bestbuy.api.testbase.TestBase;
import com.bestbuy.api.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasValue;
//For some reasons TestRunner is not Working
//for end to end testing please run this test from @RunWith otherwise from individual method
//please keep increasing id number by +1 in test003 after every test you run
@RunWith(SerenityRunner.class)
public class ProductsTest extends TestBase {

    static String name = "Apple Airpod" + TestUtils.getRandomValue();
    static String type = "Electronics";
    static float price = 159.99f;
    static float shipping = 12.99f;
    static String upc = "99999999";
    static String description = "Bluetooth";
    static String manufacturer = "Apple inc.";
    static String model = "Pro5";
    static String url = "apple.co.uk";
    static String image = "California";
    static int productId;

    @Steps
    ProductsSteps productsSteps;

    @Title("This will create product")
    @Test
    public void test001() {
        productsSteps.createProduct(name, type, price, shipping, upc, description, manufacturer,
                model, url, image).log().all();

    }


    @Title("This will verify created product added to the products")
    @Test
    public void test002() {
        HashMap<String, Object> value = productsSteps.getProductInfoByName("Nokia Finland");
        assertThat(value, hasValue("Nokia Finland"));
    }

    @Title("This will verify created product deleted successfully")
    @Test
    public void test003() {

        productsSteps.deleteProduct(9999715).statusCode(200).log().all();
        productsSteps.getProductByID(9999715).statusCode(404).log().all();

    }

}

