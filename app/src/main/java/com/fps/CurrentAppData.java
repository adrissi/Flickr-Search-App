package com.fps;

import android.graphics.Bitmap;

import com.fps.model.FlickrImgRef;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.inject.Singleton;

/**
 * Created by anissou on 16-01-09.
 */
@Singleton
public class CurrentAppData implements ICurrentAppData {
    List<FlickrImgRef> imageInfos;
    int currentPosition;
    List<Bitmap> mediumBitmaps;

    public int getPosition(String imgRefId) {
        int pos = -1;
        boolean bFound = false;
        Iterator<FlickrImgRef> iter = imageInfos.iterator();
        while(iter.hasNext() && !bFound) {
            FlickrImgRef imgRef = iter.next();
            bFound = imgRef.getId().startsWith(imgRefId);
            pos++;
        }

        if (bFound)
            return pos;

        return -1;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Bitmap getMediumImage(int position) {
        return mediumBitmaps.get(position);
    }

    public void setMediumImage(int position, Bitmap bitmap) {
        mediumBitmaps.set(position,bitmap);
    }

    @Override
    public List getImageInfos() {
        return imageInfos;
    }

    @Override
    public void setImageInfos(List imageInfos) {
        this.imageInfos = imageInfos;
        mediumBitmaps = new ArrayList(Collections.nCopies(imageInfos.size(), (Bitmap) null));
    }
}


