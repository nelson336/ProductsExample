package example.dagger.com.productsexample.injection.injections;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import dagger.Lazy;

/**
 * Created by nelson336 on 01/08/16.
 */
public class FragmentInject extends Fragment {

    @Inject Lazy<Bundle>  mSave; // injecão preguiçosa

    public Bundle getSave() {
        return mSave.get();
    }

}
