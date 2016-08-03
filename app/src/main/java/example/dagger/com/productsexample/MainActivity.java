package example.dagger.com.productsexample;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import example.dagger.com.productsexample.injection.component.ClientComponent;
import example.dagger.com.productsexample.injection.component.DaggerClientComponent;
import example.dagger.com.productsexample.injection.component.DaggerProductComponent;
import example.dagger.com.productsexample.injection.component.ProductComponent;
import example.dagger.com.productsexample.fragments.MainFragment;
import example.dagger.com.productsexample.injection.injections.GenericInject;
import example.dagger.com.productsexample.injection.module.ClientModule;
import example.dagger.com.productsexample.modell.Client;
import example.dagger.com.productsexample.modell.Product;
import example.dagger.com.productsexample.injection.module.ProductModule;
import example.dagger.com.productsexample.utils.Utils;

public class MainActivity extends AppCompatActivity {

    public static final String MAIN_FRAGMENT_TAG = "main_fragment_tag";
    public static final String PRODUCT_DETAILS_FRAGMENT_TAG = "product_details_fragment_tag";

    private List<Product> mProducts;
    private Client mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {

            mProducts = new ArrayList<Product>(Arrays.asList(new Gson().fromJson(Utils.loadJSONFromAsset(MainActivity.this, "jsons/products.json"), Product[].class)));
            mClient = new Gson().fromJson(Utils.loadJSONFromAsset(MainActivity.this, "jsons/client.json"), Client.class);

            ProductComponent productComponent = DaggerProductComponent
                    .builder()
                    .productModule(new ProductModule(mProducts))
                    .build();


            ClientComponent clientComponent = DaggerClientComponent
                    .builder()
                    .clientModule(new ClientModule(mClient))
                    .build();

            MainFragment fragment = MainFragment.newInstance();

            clientComponent.inject(fragment.getClientInject());
            productComponent.inject(fragment.getProductInject());

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager
                    .beginTransaction().
                    replace(R.id.FragmentContent, fragment, MAIN_FRAGMENT_TAG)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
