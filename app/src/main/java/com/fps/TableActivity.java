package com.fps;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.util.Pair;
import android.view.MenuItem;
import android.widget.ListView;

import com.fps.common.Config;
import com.fps.common.Enums;
import com.fps.common.Helpers;
import com.fps.model.FlickrImgRoot;
import com.fps.model.FlickrImgRef;

import java.util.Collections;
import java.util.List;

import roboguice.activity.RoboActivity;


/**
 * Created by anissou on 16-01-08.
 */
public class TableActivity extends RoboActivity
        implements DownloadFlickrImagesHandler, SearchFlickrResultHandler {

    private final String apiKey = "8bd85526368174664b645402f5810a1c";
    private final String secretKey = "25e8476109271f61";
    private final String callBackUrl = "flickrj-android-sample-oauth";

    private SearchResultsListAdapter listAdapter;
    private SearchPhotosFromFlickrTask searchPhotosAsyncTask;
    private DownloadFlickrImagesTask downloadImagesTask;
    private ListView imageContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("search", "search triggered");
        setContentView(R.layout.activity_table);

        handleIntent(getIntent());

        listAdapter = new SearchResultsListAdapter(this);
        imageContainer = (ListView) findViewById(R.id.imageContainer);
        imageContainer.setAdapter(listAdapter);

        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (listAdapter != null) {
            listAdapter.checkDataSetChanged();
        }
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d("search", "search triggered");
        setIntent(intent);
        handleIntent(intent);
    }
    private void handleIntent(Intent intent)
    {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.d("search", query);
            searchPhotosAsyncTask = new SearchPhotosFromFlickrTask(this,
                                                                   Enums.SortOrder.INTERESTINGNESS_DESC);
            searchPhotosAsyncTask.execute(query);

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (searchPhotosAsyncTask != null)
            searchPhotosAsyncTask.cancel(true);
        if (downloadImagesTask != null)
            downloadImagesTask.cancel(true);
    }

    @Override
    public boolean onSearchRequested() {

        Log.d("search", "search triggered");

        return false;  // don't go ahead and show the search box
    }

    @Override
    public void onSearchFlickrResult(List<FlickrImgRef> result) {
        FlickrImgRef[] input = new FlickrImgRef[result.size()];
        input = result.toArray(input);
        downloadImagesTask = new DownloadFlickrImagesTask(this, Enums.ImageSize.LARGE_SQUARE);
        downloadImagesTask.execute(input);
    }

    @Override
    public void onDownloadedFlickrImage(FlickrImgRef imgRef) {
        listAdapter.addItem(imgRef);
    }


    @Override
    public void onDownloadFinish() {
        // progressBar.setVisibility(View.GONE);
    }


    /**
     * Created by anissou on 16-01-08.
     */
    class SearchPhotosFromFlickrTask extends AsyncTask<String, Void, List<FlickrImgRef>> {

        private final String LOG_TAG = SearchPhotosFromFlickrTask.class.getSimpleName();

        private final SearchFlickrResultHandler handler;

        private final Enums.SortOrder sortOrder;

        public SearchPhotosFromFlickrTask(SearchFlickrResultHandler handler, Enums.SortOrder sortOrder) {
            this.handler = handler;
            this.sortOrder = sortOrder;
        }

        @Override
        protected List<FlickrImgRef> doInBackground(String... words) {
            Log.d(LOG_TAG, "Do a search for " + words[0]);

            String encodedSearch = Helpers.encodeUrlString(words[0]);
            String searchURL = String.format(Config.SEARCH_API_URL, sortOrder, encodedSearch);
            byte[] response = Helpers.doGet(searchURL);
            if (response == null || response.length == 0) {
                return Collections.emptyList();
            }

            String jsonString = new String(response);
            Log.d(LOG_TAG, "Response: " + jsonString);

            FlickrImgRoot imgRoot = Helpers.fromJson(jsonString, FlickrImgRoot.class);
            Log.d(LOG_TAG, "Object Response: " + imgRoot);
            if (imgRoot != null && imgRoot.getPhotos() != null && imgRoot.getPhotos().getPhoto() != null) {
                return imgRoot.getPhotos().getPhoto();
            }

            return Collections.emptyList();
        }


        @Override
        protected void onPostExecute(List<FlickrImgRef> result) {
            super.onPostExecute(result);
            handler.onSearchFlickrResult(result);

        }

    }


}
