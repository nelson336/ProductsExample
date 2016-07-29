package example.dagger.com.productsexample.module;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import example.dagger.com.productsexample.modell.Product;
import example.dagger.com.productsexample.scope.MainActivity;

/**
 * Created by nelson336 on 29/07/16.
 */

@MainActivity
@Module
public class ProductModule {

    private List<Product> mProducts;

    private Product mProduct;

    public ProductModule(List<Product> products) {
        this.mProducts = products;
    }

    public ProductModule(Product product) {
        this.mProduct = product;
    }

    @MainActivity
    @Provides
    public Product provideProduct() {

        if(mProduct == null){
            mProduct= new Product();
        }

        return mProduct;
    }

    @MainActivity
    @Provides
    public  List<Product>  provideProducts() {

        if(mProducts ==null){
            mProducts = new ArrayList<Product>();
        }

        return mProducts;
    }

}
