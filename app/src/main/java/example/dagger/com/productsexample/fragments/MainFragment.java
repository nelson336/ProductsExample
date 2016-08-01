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

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import example.dagger.com.productsexample.MainActivity;
import example.dagger.com.productsexample.R;
import example.dagger.com.productsexample.adapter.ProductAdapter;
import example.dagger.com.productsexample.injection.component.DaggerProductComponent;
import example.dagger.com.productsexample.injection.component.ProductComponent;
import example.dagger.com.productsexample.injection.injections.FragmentInject;
import example.dagger.com.productsexample.injection.module.ProductModule;
import example.dagger.com.productsexample.modell.Product;

/**
 * Created by nelson336 on 28/07/16.
 */
public class MainFragment extends FragmentInject {

    @Bind(R.id.rvProducts) RecyclerView rvProducts;
    @Bind(R.id.llFragmentContent) LinearLayout llFragmentContent;

    private MainActivity mActivity;
    private List<Product> mProducts;

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
           mProducts = new ArrayList<Product>(Arrays.asList(new Gson().fromJson(getSave().getString(ProductModule.PRODUCTS_INJECT), Product[].class)));
    }

    private void initComponents() {
        rvProducts.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        rvProducts.setHasFixedSize(true);
        ProductAdapter adapter = new ProductAdapter(mProducts);
        adapter.setProductAdapterListerner(new ProductAdapter.ProductAdapterListerner() {
            @Override
            public void onClick(Product product) {

                ProductComponent component = DaggerProductComponent
                        .builder()
                        .productModule(new ProductModule(product))
                        .build();

                ProductDetailsFragment fragment = ProductDetailsFragment.newInstance();
                component.inject(fragment);

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





}