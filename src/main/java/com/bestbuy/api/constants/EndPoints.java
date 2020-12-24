package com.bestbuy.api.constants;

public class EndPoints {

    /*
     * This is Endpoints of products api
     */
    public static final String GET_ALL_PRODUCTS = "/products";
    public static final String CREATE_PRODUCT = "/products";
    public static final String DELETE_PRODUCT_BY_ID = "/products/{productId}";
    public static final String GET_SINGLE_PRODUCT_BY_ID = "/products/{productId}";
    public static final String PATCH_UPDATE_PRODUCT_BY_ID = "/products/{productId}";

    /*
     * This is Endpoints of stores api
     */
    public static final String GET_ALL_STORES = "/stores";
    public static final String CREATE_STORE = "/stores";
    public static final String DELETE_STORE_BY_ID = "/stores/{storeId}";
    public static final String GET_SINGLE_STORE_BY_ID = "/stores/{storeId}";
    public static final String PATCH_UPDATE_STORE_BY_ID = "/stores/{storeId}";


    /*
     * This is Endpoints of services api
     */
    public static final String GET_ALL_SERVICE = "/services";
    public static final String CREATE_SERVICE = "/services";
    public static final String DELETE_SERVICE_BY_ID = "/services/{serviceId}";
    public static final String GET_SINGLE_SERVICE_BY_ID = "/services/{serviceId}";
    public static final String PATCH_UPDATE_SERVICE_BY_ID = "/services/{serviceId}";


    /*
     * This is Endpoints of categories api
     */
    public static final String GET_ALL_CATEGORIES = "/categories";
    public static final String CREATE_CATEGORIES = "/categories";
    public static final String DELETE_CATEGORY_BY_ID = "/categories/{categoryId}";
    public static final String GET_SINGLE_CATEGORY_BY_ID = "/categories/{categoryId}";
    public static final String PATCH_UPDATE_CATEGORY_BY_ID = "/categories/{categoryId}";

}
