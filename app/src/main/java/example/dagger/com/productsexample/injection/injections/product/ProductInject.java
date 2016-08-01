package example.dagger.com.productsexample.injection.injections.product;

import java.util.List;

import javax.inject.Inject;

import example.dagger.com.productsexample.injection.component.DaggerProductComponent;
import example.dagger.com.productsexample.injection.component.ProductComponent;
import example.dagger.com.productsexample.modell.Product;

/**
 * Created by nelson336 on 01/08/16.
 */
public class ProductInject {

    @Inject List<Product> mProducts;
    @Inject Product mProduct;


    public List<Product> getProducts() {
        return mProducts;
    }

    public Product getProduct() {
        return mProduct;
    }
}
