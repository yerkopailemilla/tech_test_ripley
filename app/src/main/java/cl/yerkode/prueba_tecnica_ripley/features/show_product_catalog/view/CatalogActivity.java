package cl.yerkode.prueba_tecnica_ripley.features.show_product_catalog.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cl.yerkode.prueba_tecnica_ripley.R;
import cl.yerkode.prueba_tecnica_ripley.adapters.ProductCatalogListener;
import cl.yerkode.prueba_tecnica_ripley.adapters.ShowProductsCatalogAdapter;
import cl.yerkode.prueba_tecnica_ripley.features.product_detail.view.ProductDetailActivity;
import cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.view.CartDialogFragment;
import cl.yerkode.prueba_tecnica_ripley.features.show_product_catalog.ShowProductCatalogMVP;
import cl.yerkode.prueba_tecnica_ripley.features.show_product_catalog.model.entity.CatalogEntity;
import cl.yerkode.prueba_tecnica_ripley.features.show_product_catalog.presenter.ShowProductsCatalogPresenter;

public class CatalogActivity extends AppCompatActivity implements View.OnClickListener,
        NavigationView.OnNavigationItemSelectedListener, ShowProductCatalogMVP.View, ProductCatalogListener {

    @BindView(R.id.toolbar_main) Toolbar toolbar_main;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView nav_drawer;
    @BindView(R.id.catalog_main_recycler_view) RecyclerView catalog_main_recycler_view;

    public static final String PRODUCT_SKU = "cl.yerkode.prueba_tecnica_ripley.features.show_product_catalog.view.KEY.PRODUCT_SKU";

    private Unbinder unbinder;
    private ProgressDialog loader;
    private ShowProductsCatalogPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
        unbinder = ButterKnife.bind(this);
        setSupportActionBar(toolbar_main);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        loader = new ProgressDialog(this);
        presenter = new ShowProductsCatalogPresenter(this);

        nav_drawer.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar_main, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.mipmap.ic_filter_category);
        toggle.syncState();

        toggle.setToolbarNavigationClickListener(this);

        catalog_main_recycler_view.setLayoutManager(new GridLayoutManager(CatalogActivity.this,2));
        catalog_main_recycler_view.setHasFixedSize(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setCatalogView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getProductsCatalog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
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
        if (item.getItemId() == R.id.toolbar_shopping_bag) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Fragment prev = getSupportFragmentManager().findFragmentByTag("showShoppingCart");
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);

            DialogFragment dialogFragment = CartDialogFragment.newInstance();
            dialogFragment.show(ft, "showShoppingCart");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int drawerOptions = menuItem.getItemId();
        switch (drawerOptions){
            case R.id.nav_tecno:
                Toast.makeText(CatalogActivity.this, "Tecno", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.nav_electro:
                Toast.makeText(CatalogActivity.this, "Electro", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.nav_muebles_hogar:
                Toast.makeText(CatalogActivity.this, "Muebles y hogar", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.nav_moda_mujer:
                Toast.makeText(CatalogActivity.this, "Moda y mujer", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.nav_deporte_aventura:
                Toast.makeText(CatalogActivity.this, "Deporte y aventura", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void showProgress()  {
        if (getBaseContext()!= null && !isFinishing()){
            /*getString(R.string.activity_retailers_title)*/
            loader.setTitle("Catálogo de productos");
            loader.setMessage("Cargando lista de productos...");
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
    public void productsCatalogSuccess() {
        Toast.makeText(this, "Catálogo cargado con éxito", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void productsCatalogFailed() {
        Toast.makeText(this, "Error al cargar catálogo", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCatalog(List<CatalogEntity> catalogEntities) {
        ShowProductsCatalogAdapter adapter = new ShowProductsCatalogAdapter(catalogEntities, this);
        catalog_main_recycler_view.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void toProductDetail(String sku) {
        Intent toProductDetail = new Intent(this, ProductDetailActivity.class);
        toProductDetail.putExtra(PRODUCT_SKU, sku);
        startActivity(toProductDetail);
    }

}
