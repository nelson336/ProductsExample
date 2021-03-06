package example.dagger.com.productsexample.injection.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by nelson336 on 02/08/16.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AppScope {
}
