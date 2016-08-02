package example.dagger.com.productsexample.injection.module;

import java.util.HashMap;
import java.util.Map;

import dagger.Module;
import dagger.Provides;
import example.dagger.com.productsexample.injection.injections.GenericObjectInject;
import example.dagger.com.productsexample.injection.scope.ActivityScope;
import example.dagger.com.productsexample.modell.Client;

/**
 * Created by nelson336 on 02/08/16.
 */

@ActivityScope
@Module
public class ClientModule {

    public final static  String CLIENT_INJECT = "client_inject";

    private Client mClient;

    public ClientModule(Client client) {
        mClient = client;
    }

    @Provides Client provideClient() {
        return mClient;
    }

    @Provides Map<String, GenericObjectInject> provideClientInject() {
        Map<String, GenericObjectInject> map  = new HashMap<String, GenericObjectInject>();

        if(mClient != null){
            GenericObjectInject<Client> inject = new GenericObjectInject<Client>();
            inject.set(mClient);
            map.put(CLIENT_INJECT, inject);
        }

        return map;
    }

}
