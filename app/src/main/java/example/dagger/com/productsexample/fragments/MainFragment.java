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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
    }

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
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
    }

    private void initComponents() {

        if (mClient != null){
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
                fragment.inject(component);

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

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void inject(ProductComponent dagger){
        GenericInject inject = new GenericInject();
        dagger.inject(inject);
        GenericObjectInject objectInject = inject.getInject().get(ProductModule.PRODUCTS_INJECT);
        mProducts = GenericObjectInject.parse(objectInject);
    }

    public void inject(ClientComponent dagger){
        GenericInject inject = new GenericInject();
        dagger.inject(inject);
        GenericObjectInject objectInject = inject.getInject().get(ClientModule.CLIENT_INJECT);
        mClient = GenericObjectInject.parse(objectInject);
    }

}