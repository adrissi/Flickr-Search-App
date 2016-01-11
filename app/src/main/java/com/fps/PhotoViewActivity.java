package com.fps;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.fps.common.Enums;
import com.fps.model.FlickrImgRef;
import com.fps.view.ExtendedViewPager;

import javax.inject.Inject;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

/**
 * Created by anissou on 16-01-09.
 */
public class PhotoViewActivity extends RoboActivity {

    @InjectView(R.id.pager)
    private ExtendedViewPager viewPager;
    @Inject
    ICurrentAppData currentAppData;

    public DownloadFlickrImagesTask downloadMediumImagesTask;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        setNewAdapter();

        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_photo, menu);
        return true;
    }

    /**
     * Let's the user tap the activity icon to go 'home'.
     * Requires setHomeButtonEnabled() in onCreate().
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.delete:
                int currItem = viewPager.getCurrentItem();
                currItem = currItem % currentAppData.getImageInfos().size();
                currentAppData.getImageInfos().remove(currItem);
                currentAppData.setCurrentPosition(currItem);
                setNewAdapter();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    void setNewAdapter() {
        PhotoViewAdapter mediumViewAdapter = new PhotoViewAdapter(PhotoViewActivity.this);
        viewPager.setAdapter(mediumViewAdapter);
        viewPager.setCurrentItem(currentAppData.getCurrentPosition());
    }


}
