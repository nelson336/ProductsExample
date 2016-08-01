package example.dagger.com.productsexample.injection.component;

import java.util.List;

import dagger.Component;
import example.dagger.com.productsexample.injection.injections.FragmentInject;
import example.dagger.com.productsexample.injection.module.ProductModule;
import example.dagger.com.productsexample.injection.scope.ActivityScope;
import example.dagger.com.productsexample.modell.Product;

/**
 * Created by nelson on 29/07/16.
 */

@ActivityScope
@Component(modules = {ProductModule.class})
public interface ProductComponent {

    Product provideProduct();
    List<Product> provideProducts();

    void inject(FragmentInject inject);
}
