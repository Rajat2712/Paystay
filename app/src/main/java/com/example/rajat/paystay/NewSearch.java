package com.example.rajat.paystay;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class NewSearch extends AppCompatActivity implements ViewSwitcher.ViewFactory {
    Button button;

    int imgs[] =
            {
                    R.drawable.home1,
                    R.drawable.homee2,
                    R.drawable.homee3,
                    R.drawable.homee4,
                    R.drawable.homee5,
                    R.drawable.homee6,
                    R.drawable.homee7,
                    R.drawable.homee8,
                    R.drawable.homee9,
                    R.drawable.homee10
            };

    ImageSwitcher imgSwitcher;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_search);
        imgSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher1);
        imgSwitcher.setFactory(this);
        imgSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in));
        imgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out));

        Gallery gallery = (Gallery) findViewById(R.id.gallery1);
        gallery.setAdapter(new ImageAdapter(this));

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                imgSwitcher.setImageResource(imgs[arg2]);
            }
        });
    }

    public class ImageAdapter extends BaseAdapter {

        private Context ctx;

        public ImageAdapter(Context c) {
            ctx = c;
        }

        public int getCount() {

            return imgs.length;
        }

        public Object getItem(int arg0) {

            return arg0;
        }

        public long getItemId(int arg0) {

            return arg0;
        }

        public View getView(int arg0, View arg1, ViewGroup arg2) {

            ImageView iView = new ImageView(ctx);
            iView.setImageResource(imgs[arg0]);
            iView.setScaleType(ImageView.ScaleType.FIT_XY);
            iView.setLayoutParams(new Gallery.LayoutParams(200, 150));
            return iView;
        }
    }

    public View makeView() {
        ImageView iView = new ImageView(this);
        iView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        iView.setLayoutParams(new ImageSwitcher.LayoutParams
                (RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.MATCH_PARENT));
        iView.setBackgroundColor(0xFF000000);
        return iView;
    }
    public  void onclick(View v){
        Intent intent=new Intent(this,Searchdata.class);
        startActivity(intent);
    }
}
