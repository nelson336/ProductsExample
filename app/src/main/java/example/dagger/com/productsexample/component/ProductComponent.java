package example.dagger.com.productsexample.component;

import java.util.List;

import dagger.Component;
import example.dagger.com.productsexample.fragments.MainFragment;
import example.dagger.com.productsexample.fragments.ProductDetailsFragment;
import example.dagger.com.productsexample.modell.Product;
import example.dagger.com.productsexample.module.ProductModule;
import example.dagger.com.productsexample.scope.MainActivity;

/**
 * Created by nelson on 29/07/16.
 */

@MainActivity
@Component(modules = {ProductModule.class})
public interface ProductComponent {

    Product provideProduct();
    List<Product> provideProducts();

    void inject(MainFragment fragment);

    void inject(ProductDetailsFragment fragment);

}
