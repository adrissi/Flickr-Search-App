package com.fps;

import com.fps.model.FlickrImgRef;

import java.util.List;

/**
 * Created by anissou on 16-01-09.
 */
public interface SearchFlickrResultHandler {
    void onSearchFlickrResult(List<FlickrImgRef> result);
}
