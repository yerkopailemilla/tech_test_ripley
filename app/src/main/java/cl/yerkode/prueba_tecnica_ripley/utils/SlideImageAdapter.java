package cl.yerkode.prueba_tecnica_ripley.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.util.List;

import cl.yerkode.prueba_tecnica_ripley.R;
import cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.entity.Image;

public class SlideImageAdapter extends PagerAdapter {

    private Activity act;
    private List<Image> items;

    private OnItemClickListener onItemClickListener;

    private interface OnItemClickListener {
        void onItemClick(View view, Image obj);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    // constructor
    public SlideImageAdapter(Activity activity, List<Image> items) {
        this.act = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    public Image getItem(int pos) {
        return items.get(pos);
    }

    public void setItems(List<Image> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final Image o = items.get(position);
        LayoutInflater inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_list_slide_image, container, false);

        ImageView image = v.findViewById(R.id.image);
        LinearLayout lyt_parent = (LinearLayout) v.findViewById(R.id.lyt_parent);
        String pat = o.getPathImage();
        displayImageOriginal(image, pat);
        lyt_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, o);
                }
            }
        });

        ((ViewPager) container).addView(v);

        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);

    }

    public static void displayImageOriginal(ImageView img, String pathImage) {
        try {
            Picasso.get()
                    .load("https://" + pathImage)
                    .into(img);
        } catch (Exception e) {
        }
    }

}
