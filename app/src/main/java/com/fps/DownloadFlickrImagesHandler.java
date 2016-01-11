package com.fps;

import android.graphics.Bitmap;
import android.util.Pair;

import com.fps.model.FlickrImgRef;

public interface DownloadFlickrImagesHandler {

	void onDownloadedFlickrImage(FlickrImgRef imgRef);

	void onDownloadFinish();
}
