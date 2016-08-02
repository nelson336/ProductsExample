package example.dagger.com.productsexample.injection.injections;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by nelson336 on 01/08/16.
 */
public class GenericInject {

    @Inject Map<String, GenericObjectInject> mInject;

    public Map<String, GenericObjectInject> getInject(){
            return mInject;
    }


}
