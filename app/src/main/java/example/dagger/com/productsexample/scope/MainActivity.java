package example.dagger.com.productsexample.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by nelson336 on 28/07/16.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface MainActivity {
}