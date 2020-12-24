package com.bestbuy.api.info;

import com.bestbuy.api.constants.EndPoints;
import com.bestbuy.api.model.CategoriesPojo;
import com.bestbuy.api.stepsinfo.CategoriesSteps;
import com.bestbuy.api.testbase.TestBase;
import com.bestbuy.api.utils.TestUtils;
import cucumber.api.java.eo.Se;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
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
public class CategoriesTest extends TestBase {

    static String name = "Gift" + TestUtils.getRandomValue();
    static String categoryId;

    @Steps
    CategoriesSteps categoriesSteps;

    @Title("This will create new category")
    @Test
    public void test001(){
      categoriesSteps.createCategory(name);
    }

    @Title("Verify if the category was added to the application")
    @Test
    public void test002() {
        HashMap<String, Object> value = categoriesSteps.getCategoryInfoByName();
        assertThat(value, hasValue("Gift Ideas"));
        categoryId = (String) value.get("id");
    }

    @Title("Update the category information and verify the updated information")
    @Test
    public void test003(){

        name =  "Gift Ideas" + "_Updated";


        categoriesSteps.UpdateCategoryByPatch("abcat0010000", "Gift Ideas")

        HashMap<String, Object> value = categoriesSteps.getCategoryInfoByName();
        assertThat(value, hasValue("Gift Ideas"));

    }

    @Title("Delete the student and verify if the student is deleted!")
    @Test
    public void test004() {

        categoriesSteps.deleteCategory("abcat0010000").statusCode(204);
        categoriesSteps.getCategoryById("abcat0010000").statusCode(404);

    }
}
