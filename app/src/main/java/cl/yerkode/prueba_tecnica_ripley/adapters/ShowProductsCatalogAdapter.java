package cl.yerkode.prueba_tecnica_ripley.adapters;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cl.yerkode.prueba_tecnica_ripley.R;
import cl.yerkode.prueba_tecnica_ripley.features.show_product_catalog.model.entity.CatalogEntity;

public class ShowProductsCatalogAdapter extends RecyclerView.Adapter<ShowProductsCatalogAdapter.CatalogViewHolder> {

    private List<CatalogEntity> catalogEntityList;
    private ProductCatalogListener catalogListener;

    public ShowProductsCatalogAdapter(List<CatalogEntity> catalogEntityList, ProductCatalogListener catalogListener) {
        this.catalogEntityList = catalogEntityList;
        this.catalogListener = catalogListener;
    }

    @NonNull
    @Override
    public CatalogViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_list_catalog_main, viewGroup, false);
        return new CatalogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CatalogViewHolder catalogViewHolder, int position) {
        CatalogEntity catalog = catalogEntityList.get(position);

        String imagePath = catalog.getThumbnailImage().substring(2);
        Picasso.get()
                .load("https://" + imagePath)
                .into(catalogViewHolder.item_list_catalog_image);

        catalogViewHolder.item_list_catalog_name.setText(catalog.getName());

        int cardPrice = catalog.getPrices().getCardPrice();

        if (cardPrice != 0){
            catalogViewHolder.item_list_catalog_price.setText("$" + catalog.getPrices().getListPrice());
            catalogViewHolder.item_list_catalog_price.setPaintFlags(catalogViewHolder.item_list_catalog_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            catalogViewHolder.item_list_catalog_card_price.setText("$" + catalog.getPrices().getCardPrice());
        } else {
            catalogViewHolder.item_list_catalog_price.setText("$" + catalog.getPrices().getListPrice());
            catalogViewHolder.lyt_card_price.setVisibility(View.GONE);
        }

        catalogViewHolder.item_list_catalog_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int auxPosition = catalogViewHolder.getAdapterPosition();
                CatalogEntity auxCatalog = catalogEntityList.get(auxPosition);
                catalogListener.toProductDetail(auxCatalog.getSku());
            }
        });
    }

    @Override
    public int getItemCount() {
        return catalogEntityList.size();
    }

    public void setData(List<CatalogEntity> newList){
        this.catalogEntityList = newList;
        notifyDataSetChanged();
    }

    static class CatalogViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_list_catalog_image) ImageView item_list_catalog_image;
        @BindView(R.id.item_list_catalog_name) TextView item_list_catalog_name;
        @BindView(R.id.lyt_card_price) LinearLayout lyt_card_price;
        @BindView(R.id.item_list_catalog_price) TextView item_list_catalog_price;
        @BindView(R.id.item_list_catalog_card_price) TextView item_list_catalog_card_price;

        public CatalogViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
