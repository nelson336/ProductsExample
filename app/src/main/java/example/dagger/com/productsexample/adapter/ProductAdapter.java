package example.dagger.com.productsexample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import example.dagger.com.productsexample.R;
import example.dagger.com.productsexample.modell.Product;

/**
 * Created by nelson336 on 26/07/16.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ItemViewHolder> {

    public interface ProductAdapterListerner {
        void onClick(Product product);
    }

    private ProductAdapterListerner mProductAdapterListerner;

    public void setProductAdapterListerner(ProductAdapterListerner productAdapterListerner) {
        this.mProductAdapterListerner = productAdapterListerner;
    }

    List<Product> mProducts;

    public ProductAdapter(List<Product> mProducts) {
        this.mProducts = mProducts;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_product_adapter_item, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        final Product product = mProducts.get(position);
        holder.tvDesc.setText(product.getDesc());
        holder.llContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mProductAdapterListerner != null) {
                    mProductAdapterListerner.onClick(mProducts.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProducts != null ?  mProducts.size() : 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tvDesc) TextView tvDesc;
        @Bind(R.id.llContent) LinearLayout llContent;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
