package example.dagger.com.productsexample.injection.module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dagger.Module;
import dagger.Provides;
import example.dagger.com.productsexample.injection.injections.GenericObjectInject;
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

    @Provides
    public Product provideProduct() {
        return mProduct;
    }

    @Provides
    public List<Product> provideProducts() {

        return mProducts;
    }

    @Provides
    public Map<String, GenericObjectInject> provideSave() {
        final  Map<String, GenericObjectInject> map = new HashMap<String, GenericObjectInject>();
        if(mProduct != null){
            GenericObjectInject<Product> productInject = new GenericObjectInject<Product>();
            productInject.set(mProduct);
            map.put(PRODUCT_INJECT, productInject);
        }

        if(mProducts != null){
            GenericObjectInject<List<Product>> productsInject = new GenericObjectInject<List<Product>>();
            productsInject.set(mProducts);
            map.put(PRODUCTS_INJECT, productsInject);
        }

        return map;
    }

}
