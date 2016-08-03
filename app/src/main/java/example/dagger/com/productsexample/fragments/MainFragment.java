package example.dagger.com.productsexample.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import example.dagger.com.productsexample.MainActivity;
import example.dagger.com.productsexample.R;
import example.dagger.com.productsexample.adapter.ProductAdapter;
import example.dagger.com.productsexample.injection.component.ClientComponent;
import example.dagger.com.productsexample.injection.component.DaggerProductComponent;
import example.dagger.com.productsexample.injection.component.ProductComponent;
import example.dagger.com.productsexample.injection.injections.GenericInject;
import example.dagger.com.productsexample.injection.injections.GenericObjectInject;
import example.dagger.com.productsexample.injection.module.ClientModule;
import example.dagger.com.productsexample.injection.module.ProductModule;
import example.dagger.com.productsexample.modell.Client;
import example.dagger.com.productsexample.modell.Product;

/**
 * Created by nelson336 on 28/07/16.
 */
public class MainFragment extends Fragment {

    @Bind(R.id.rvProducts) RecyclerView rvProducts;
    @Bind(R.id.llFragmentContent) LinearLayout llFragmentContent;
    @Bind(R.id.tvClientCPF) TextView tvClientCPF;
    @Bind(R.id.tvClientName) TextView tvClientName;

    private MainActivity mActivity;
    private List<Product> mProducts;
    private Client mClient;

    private GenericInject mProductInject;
    private GenericInject mClientInject;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
    }

    public GenericInject getProductInject() {

        if(mProductInject == null){
            mProductInject = new GenericInject();
        }

        return mProductInject;
    }

    public GenericInject getClientInject() {
        if(mClientInject == null){
            mClientInject = new GenericInject();
        }

        return mClientInject;
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        initComponents();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mClientInject != null && mClientInject.getInject() != null) {
            final GenericObjectInject objectInject = mClientInject.getInject().get(ClientModule.CLIENT_INJECT);
            mClient = GenericObjectInject.parse(objectInject);
        }

        if (mProductInject != null && mProductInject.getInject() != null) {
            final GenericObjectInject objectInject = mProductInject.getInject().get(ProductModule.PRODUCTS_INJECT);
            mProducts = GenericObjectInject.parse(objectInject);
        }
    }

    private void initComponents() {

        if (mClient != null) {
            tvClientCPF.setText(mClient.getCpf());
            tvClientName.setText(mClient.getName());
        }

        rvProducts.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        rvProducts.setHasFixedSize(true);
        final ProductAdapter adapter = new ProductAdapter(mProducts);
        adapter.setProductAdapterListerner(new ProductAdapter.ProductAdapterListerner() {
            @Override
            public void onClick(Product product) {

                ProductComponent component = DaggerProductComponent
                        .builder()
                        .productModule(new ProductModule(product))
                        .build();

                ProductDetailsFragment fragment = ProductDetailsFragment.newInstance();
                 component.inject(fragment.getProductInject());

                FragmentManager fragmentManager = mActivity.getSupportFragmentManager();
                fragmentManager
                        .beginTransaction().
                        replace(R.id.FragmentContent, fragment, MainActivity.PRODUCT_DETAILS_FRAGMENT_TAG)
                        .addToBackStack(null)
                        .commit();


            }
        });
        rvProducts.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}