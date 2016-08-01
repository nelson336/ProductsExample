package example.dagger.com.productsexample.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;
import example.dagger.com.productsexample.MainActivity;
import example.dagger.com.productsexample.R;
import example.dagger.com.productsexample.injection.injections.FragmentInject;
import example.dagger.com.productsexample.injection.module.ProductModule;
import example.dagger.com.productsexample.modell.Product;

/**
 * Created by nelson336 on 26/07/16.
 */
public class ProductDetailsFragment extends FragmentInject {

    @Bind(R.id.tvCod) TextView tvCod;
    @Bind(R.id.tvDesc) TextView tvDesc;

    public static ProductDetailsFragment newInstance() {

        Bundle args = new Bundle();

        ProductDetailsFragment fragment = new ProductDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private Product mProduct;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProduct = new Gson().fromJson(getSave().getString(ProductModule.PRODUCT_INJECT), Product.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_details_products, container, false);
        ButterKnife.bind(this, view);
        initComponents();
        return view;
    }

    private void initComponents() {
        if(mProduct == null){
            tvCod.setText("0");
            tvDesc.setText("NULO");
        }else {
            tvCod.setText(mProduct.getId());
            tvDesc.setText(mProduct.getDesc());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
