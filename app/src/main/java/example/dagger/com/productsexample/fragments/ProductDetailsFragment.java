package example.dagger.com.productsexample.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import example.dagger.com.productsexample.R;
import example.dagger.com.productsexample.injection.component.ProductComponent;
import example.dagger.com.productsexample.injection.injections.GenericInject;
import example.dagger.com.productsexample.injection.injections.GenericObjectInject;
import example.dagger.com.productsexample.injection.module.ProductModule;
import example.dagger.com.productsexample.modell.Product;

/**
 * Created by nelson336 on 26/07/16.
 */
public class ProductDetailsFragment extends Fragment {

    @Bind(R.id.tvCod) TextView tvCod;
    @Bind(R.id.tvDesc) TextView tvDesc;

    private GenericInject mProductInject;

    public GenericInject getProductInject() {

        if(mProductInject == null){
            mProductInject = new GenericInject();
        }

        return mProductInject;
    }

    public static ProductDetailsFragment newInstance() {
        return new ProductDetailsFragment();
    }

    private Product mProduct;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mProductInject != null && mProductInject.getInject() != null) {
            final GenericObjectInject objectInject = mProductInject.getInject().get(ProductModule.PRODUCT_INJECT);
            mProduct = GenericObjectInject.parse(objectInject);
        }
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
        if (mProduct == null) {
            tvCod.setText("0");
            tvDesc.setText("NULO");
        } else {
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
