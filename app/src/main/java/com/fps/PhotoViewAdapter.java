package com.fps;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import javax.inject.Inject;

import roboguice.RoboGuice;
import android.support.v4.view.PagerAdapter;

import com.fps.common.Enums;
import com.fps.model.FlickrImgRef;
import com.fps.view.ExtendedViewPager;


/**
 * Created by anissou on 16-01-09.
 */
public class PhotoViewAdapter extends PagerAdapter {

    private static final int PAGER_PAGES = 10000;
    private static final int PAGER_PAGES_MIDDLE = PAGER_PAGES / 2;

    @Inject
    ICurrentAppData currentAppData;

    private Activity activity;
    private ImageView imageView;
    private TextView pagenumber;
    private int currCount = 0;

    public PhotoViewAdapter(Activity activity) {
        RoboGuice.getInjector(activity).injectMembers(this);
        this.activity = activity;
    }

    //The following implementation of getCount has been designed
    //to support cyclical swiping
    @Override
    public int getCount() {
        int count;
        int trueCount = getTrueCount();

        if (trueCount != currCount) {
            currCount = trueCount;
            this.notifyDataSetChanged();
        }

        if (trueCount == 0) {
            count = 0;
        }
        else if (trueCount == 1) {
            count = 1;
        }
        else {
            count = PAGER_PAGES;
        }
        return count;
    }

    private int getTrueCount() {
        return currentAppData.getImageInfos().size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == (RelativeLayout)o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int pos) {

        View layout = null;

        //The following has been introduced to support cyclical swiping
        int imageNumber = pos % getTrueCount();

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.layout_photo, container, false);

        imageView = (ImageView) viewLayout.findViewById(R.id.imageView);
        FlickrImgRef imgRef = currentAppData.getImageInfos().get(imageNumber);
        Bitmap bmp = imgRef.getMediumBitmap();
        imageView.setImageBitmap(bmp);

        pagenumber = (TextView) viewLayout.findViewById(R.id.pagenumber);
        int pagenumberId=imageNumber + 1;
        pagenumber.setId(pagenumberId);
        pagenumber.setText(pagenumberId + " Of " + getTrueCount());

        ((ExtendedViewPager) container).addView(viewLayout);
        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ExtendedViewPager)container).removeView((RelativeLayout) object);
    }

}


