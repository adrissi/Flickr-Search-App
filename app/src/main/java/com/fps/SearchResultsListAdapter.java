package com.fps;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fps.model.FlickrImgRef;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import roboguice.RoboGuice;

public class SearchResultsListAdapter extends BaseAdapter {

	private List<FlickrImgRef> data;
    private int currentSize;

    @Inject
    ICurrentAppData currentAppData;
	private Activity parentActivity;

	public SearchResultsListAdapter(Activity parentActivity) {
        RoboGuice.getInjector(parentActivity).injectMembers(this);
		this.data = new ArrayList<FlickrImgRef>();
		this.parentActivity = parentActivity;
        this.currentSize = 0;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int index) {
		return this.data.get(index);
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	public void addItem(FlickrImgRef item) {
		this.data.add(item);
		this.notifyDataSetChanged();
        this.currentSize = this.data.size();
	}

    public void removeItem(FlickrImgRef item) {
        this.data.remove(item);
        this.notifyDataSetChanged();
        this.currentSize = this.data.size();
    }

    public void checkDataSetChanged() {
        if (currentSize != data.size()) {
            currentSize = data.size();
            this.notifyDataSetChanged();
        }
    }

	/**
	 * A reference holder class so we do not call findViewById that often
	 */
	private class ViewHolder {
		TextView flickrTitle;
        TextView flickrUsername;
		ImageView imageView;
	}

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater layoutInflater = parentActivity.getLayoutInflater();
			convertView = layoutInflater.inflate(R.layout.flickr_list_item, null);

			ViewHolder holder = new ViewHolder();
            holder.imageView = ((ImageView) convertView.findViewById(R.id.flickrImage));
            holder.flickrTitle = ((TextView) convertView.findViewById(R.id.flickrTitle));
            holder.flickrUsername = ((TextView) convertView.findViewById(R.id.flickrUsername));
			convertView.setTag(holder);
            convertView.setClickable(true);
            convertView.setOnClickListener(new ListViewCellOnClickListener());
        }

        FlickrImgRef imgRef = data.get(position);
        int id = first9Digits(imgRef.getId());
        convertView.setId(id);
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.imageView.setImageBitmap(imgRef.getThumbnailBitmap());
		holder.flickrTitle.setText(imgRef.getTitle());
        holder.flickrUsername.setText(imgRef.getUsername());
		return convertView;

	}

    public static int first9Digits(String s) {
        return Integer.parseInt(s.substring(0, Math.min(s.length(), 9)));
    }

    class ListViewCellOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            currentAppData.setImageInfos(data);
            int newPos = currentAppData.getPosition(String.valueOf(v.getId()));
            currentAppData.setCurrentPosition(newPos);
            Intent intent = new Intent(parentActivity, PhotoViewActivity.class);
            parentActivity.startActivity(intent);
        }
    }
}
