package example.dagger.com.productsexample.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by nelson336 on 28/07/16.
 */
public class Utils {

    public static String loadJSONFromAsset(Context ctx, String jsonFile) {
        String json;
        try {
            InputStream is = ctx.getAssets().open(jsonFile);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
