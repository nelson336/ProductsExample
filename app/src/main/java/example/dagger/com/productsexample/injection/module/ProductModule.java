package example.dagger.com.productsexample.injection.module;

import android.os.Bundle;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import example.dagger.com.productsexample.injection.scope.ActivityScope;
import example.dagger.com.productsexample.modell.Product;

/**
 * Created by nelson336 on 29/07/16.
 */

@ActivityScope
@Module
public class ProductModule {

    public static final String PRODUCTS_INJECT = "products_inject";
    public static final String PRODUCT_INJECT = "product_inject";

    private List<Product> mProducts;

    private Product mProduct;

    public ProductModule(List<Product> products) {
        this.mProducts = products;
    }

    public ProductModule(Product product) {
        this.mProduct = product;
    }

    @ActivityScope
    @Provides
    public Product provideProduct() {
        return mProduct;
    }

    @ActivityScope
    @Provides
    public List<Product> provideProducts() {

        return mProducts;
    }

    @ActivityScope
    @Provides
    public Bundle provideSave() {
        final Bundle save = new Bundle();
        if(mProduct != null){
            save.putString(PRODUCT_INJECT, new Gson().toJson(mProduct));
        }

        if(mProducts != null){
            save.putString(PRODUCTS_INJECT, new Gson().toJson(mProducts));
        }

        return save;
    }

}
