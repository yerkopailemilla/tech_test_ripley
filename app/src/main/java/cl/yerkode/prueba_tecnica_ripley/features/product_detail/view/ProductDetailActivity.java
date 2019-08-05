package cl.yerkode.prueba_tecnica_ripley.features.product_detail.view;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cl.yerkode.prueba_tecnica_ripley.R;
import cl.yerkode.prueba_tecnica_ripley.features.product_detail.ProductDetailMVP;
import cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.entity.Attribute;
import cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.entity.Image;
import cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.entity.ProductDetailEntity;
import cl.yerkode.prueba_tecnica_ripley.features.product_detail.presenter.ProductDetailPresenter;
import cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.view.CartDialogFragment;
import cl.yerkode.prueba_tecnica_ripley.features.show_product_catalog.view.CatalogActivity;
import cl.yerkode.prueba_tecnica_ripley.utils.SlideImageAdapter;

public class ProductDetailActivity extends AppCompatActivity implements ProductDetailMVP.View{

    @BindView(R.id.toolbar_product_detail) Toolbar toolbar_product_detail;
    @BindView(R.id.bottom_sheet) LinearLayout lytBottomSheet;
    @BindView(R.id.pager) ViewPager viewPager;
    @BindView(R.id.layout_dots) LinearLayout layout_dots;
    @BindView(R.id.content_row) ViewGroup content_row;
    @BindView(R.id.lyt_card_price_detail) LinearLayout lyt_card_price_detail;
    @BindView(R.id.product_detail_name) TextView product_detail_name;
    @BindView(R.id.product_detail_card_price) TextView product_detail_card_price;
    @BindView(R.id.product_detail_list_price) TextView product_detail_list_price;

    private Unbinder unbinder;
    private ProductDetailMVP.Presenter presenter;
    private ProgressDialog loader;
    private SlideImageAdapter adapterImageSlider;
    private String skuProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        unbinder = ButterKnife.bind(this);
        skuProduct = getIntent().getStringExtra(CatalogActivity.PRODUCT_SKU);
        setSupportActionBar(toolbar_product_detail);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        presenter = new ProductDetailPresenter(this);
        loader = new ProgressDialog(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setProductDetailView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getProductDetail(skuProduct);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.toolbar_shopping_bag:
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment prev = getSupportFragmentManager().findFragmentByTag("showShoppingCart");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);

                DialogFragment dialogFragment = CartDialogFragment.newInstance();
                dialogFragment.show(ft, "showShoppingCart");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showProgress()  {
        if (getBaseContext()!= null && !isFinishing()){
            /*getString(R.string.activity_retailers_title)*/
            loader.setTitle("Detalle de producto");
            loader.setMessage("Mostrando datos del producto");
            loader.setIndeterminate(true);
            loader.setCancelable(false);
            loader.show();
        }
    }

    @Override
    public void hideProgress() {
        if (loader != null && loader.isShowing()){
            loader.dismiss();
        }
    }

    @Override
    public void productDetailSuccess() {
        Toast.makeText(this, "Detalle de producto cargado con éxito", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void productDetailFailed() {
        Toast.makeText(this, "Error al cargar detalle de producto", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProduct(ProductDetailEntity productDetailEntity) {
        if (productDetailEntity != null){

            List<Attribute> attributes = productDetailEntity.getAttributes();
            loadSlideImagesProduct(productDetailEntity.getImages());

            int countRow = 0;
            for (Attribute attribute : attributes) {
                countRow++;
                if (countRow % 2 == 0) {
                    addChild(attribute.getName(), attribute.getValue(), Color.LTGRAY);
                } else {
                    addChild(attribute.getName(), attribute.getValue(), Color.WHITE);
                }
            }

            product_detail_name.setText(productDetailEntity.getName());
            int cardPrice = productDetailEntity.getPrices().getCardPrice();
            if (cardPrice != 0){
                product_detail_list_price.setText("$" + productDetailEntity.getPrices().getListPrice());
                product_detail_list_price.setPaintFlags(product_detail_list_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                product_detail_card_price.setText("$" + cardPrice);
            } else {
                product_detail_list_price.setText("$" + productDetailEntity.getPrices().getListPrice());
                lyt_card_price_detail.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @SuppressLint("InlinedApi")
    private void addChild(String nameAttribute, String valueAttribute, int colorRow) {
        LayoutInflater inflater = LayoutInflater.from(this);
        int id = R.layout.product_attribute_row;

        LinearLayout linearLayout = (LinearLayout) inflater.inflate(id, null, false);

        TextView attributeName = linearLayout.findViewById(R.id.product_attribute_name);
        TextView attributeValue = linearLayout.findViewById(R.id.product_attribute_value);
        attributeName.setText(nameAttribute);
        attributeValue.setText(valueAttribute);

        //layout params
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        linearLayout.setBackgroundColor(colorRow);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(params);

        content_row.addView(linearLayout);
    }

    private void loadSlideImagesProduct(List<String> images) {
        adapterImageSlider = new SlideImageAdapter(this, new ArrayList<Image>());

        List<Image> items = new ArrayList<>();
        for (String i : images) {
            Image obj = new Image();
            obj.setPathImage(i.substring(2));
            items.add(obj);
        }

        adapterImageSlider.setItems(items);
        viewPager.setAdapter(adapterImageSlider);

        // displaying selected image first
        viewPager.setCurrentItem(0);
        addBottomDots(layout_dots, adapterImageSlider.getCount(), 0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int pos, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int pos) {
                addBottomDots(layout_dots, adapterImageSlider.getCount(), pos);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void addBottomDots(LinearLayout layout_dots, int size, int current) {
        ImageView[] dots = new ImageView[size];

        layout_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
            int width_height = 15;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle);
            dots[i].setColorFilter(ContextCompat.getColor(this, R.color.colorBlack), PorterDuff.Mode.SRC_ATOP);
            layout_dots.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current].setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        }
    }
}
