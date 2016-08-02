package example.dagger.com.productsexample.injection.component;

import dagger.Component;
import example.dagger.com.productsexample.injection.injections.GenericInject;
import example.dagger.com.productsexample.injection.module.ClientModule;
import example.dagger.com.productsexample.injection.scope.ActivityScope;
import example.dagger.com.productsexample.modell.Client;

/**
 * Created by nelson336 on 02/08/16.
 */

@ActivityScope
@Component(modules = {ClientModule.class})
public interface ClientComponent extends GenericComponent {
    Client provideClient();
}
