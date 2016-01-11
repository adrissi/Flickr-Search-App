package com.fps;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import com.fps.common.Config;
import com.fps.common.Enums;
import com.fps.common.Helpers;
import com.fps.model.FlickrImgRef;
import com.fps.model.FlickrPersonInfoRoot;

public class DownloadFlickrImagesTask
        extends AsyncTask<FlickrImgRef, FlickrImgRef, Void> {

	private static final String LOG_TAG = DownloadFlickrImagesTask.class.getSimpleName();

	private final DownloadFlickrImagesHandler handler;
    private final Enums.ImageSize thumbnailImgSize;

	public DownloadFlickrImagesTask(DownloadFlickrImagesHandler handler, Enums.ImageSize imageSize) {

        this.handler = handler;
        this.thumbnailImgSize = imageSize;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Void doInBackground(FlickrImgRef... imgRefs) {
        download(imgRefs, 0, (imgRefs.length/2));
        download(imgRefs, (imgRefs.length/2), imgRefs.length);
        return null;
    }

    private void download(FlickrImgRef[] imgRefs, int startIdx, int endIdx) {
		for (int i = startIdx; i < endIdx; i++) {
                String thumbnailImgUrl = Helpers.constructFlickrImgUrl(imgRefs[i], thumbnailImgSize);
                Bitmap thumbnailBmp = this.downloadBitmap(thumbnailImgUrl);
                imgRefs[i].setThumbnailBitmap(thumbnailBmp);

                publishProgress(imgRefs[i]);
                String username = this.downloadUserInfo(imgRefs[i].getOwner());
                imgRefs[i].setUsername(username);
		}

        for (int i = startIdx; i < endIdx; i++) {
            String mediumImgUrl = Helpers.constructFlickrImgUrl(imgRefs[i], Enums.ImageSize.MEDIUM);
            Bitmap mediumBmp = this.downloadBitmap(mediumImgUrl);
            imgRefs[i].setMediumBitmap(mediumBmp);
        }
    }

	private Bitmap downloadBitmap(String url) {
		Log.d(LOG_TAG, "Downloading image from url: " + url);
		byte[] response = Helpers.doGet(url);
		if (response == null || response.length == 0) {
			return null;
		}
		Options opts = new Options();
		// opts.inSampleSize = 2;
		Bitmap bitmap = BitmapFactory.decodeByteArray(response, 0, response.length, opts);
		return bitmap;
	}

    private String downloadUserInfo(String userId) {
        Log.d(LOG_TAG, "Getting user info: " + userId);
        String url = String.format(Config.GET_PERSON_INFO_API_URL, userId);
        byte[] response = Helpers.doGet(url);
        if (response == null || response.length == 0) {
            return null;
        }
        String jsonString = new String(response);
        Log.d(LOG_TAG, "Response: " + jsonString);

        FlickrPersonInfoRoot personRoot = Helpers.fromJson(jsonString, FlickrPersonInfoRoot.class);
        Log.d(LOG_TAG, "Object Response: " + personRoot);
        if (personRoot != null
                && personRoot.getPerson() != null
                && personRoot.getPerson().getUsername() != null) {
            return personRoot.getPerson().getUsername().getContent();
        }
        return null;
    }

	@Override
	protected void onProgressUpdate(FlickrImgRef... imgRefs) {
		super.onProgressUpdate(imgRefs);
		if (imgRefs != null) {
			for (FlickrImgRef imgRef : imgRefs) {
				handler.onDownloadedFlickrImage(imgRef);
			}
		}
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		handler.onDownloadFinish();
	}
}
