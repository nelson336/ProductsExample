package example.dagger.com.productsexample.injection.injections;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

/**
 * Created by nelson336 on 01/08/16.
 */
public class FragmentInject extends Fragment {

    @Inject Bundle mSave;

    public Bundle getSave() {
        return mSave;
    }

}
