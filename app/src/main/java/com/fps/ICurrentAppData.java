package com.fps;

import com.fps.model.FlickrImgRef;

import java.util.List;

/**
 * Created by anissou on 16-01-09.
 */
public interface ICurrentAppData {
    List<FlickrImgRef> getImageInfos();

    void setImageInfos(List<FlickrImgRef> imageInfos);

    int getPosition(String imageInfoId);

    int getCurrentPosition();

    void setCurrentPosition(int currentPosition);
}
