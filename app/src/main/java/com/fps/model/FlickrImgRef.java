package com.fps.model;

import android.graphics.Bitmap;

import java.io.Serializable;

public class FlickrImgRef implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String farm;
	private String server;
	private String secret;
	private String title;
    private String owner;
    private String username;

    private Bitmap thumbnail;
    private Bitmap medium;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFarm() {
		return farm;
	}

	public void setFarm(String farm) {
		this.farm = farm;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

    public String getOwner() {
        return owner;
    }

    public void setOwner(String ownerId) {
        this.owner = ownerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Bitmap getThumbnailBitmap() {
        return thumbnail;
    }

    public void setThumbnailBitmap(Bitmap bitmap) {
        this.thumbnail = bitmap;
    }

    public Bitmap getMediumBitmap() {
        return medium;
    }

    public void setMediumBitmap(Bitmap bitmap) {
        this.medium = bitmap;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((farm == null) ? 0 : farm.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((secret == null) ? 0 : secret.hashCode());
		result = prime * result + ((server == null) ? 0 : server.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((owner == null) ? 0 : owner.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((thumbnail == null) ? 0 : thumbnail.hashCode());
        result = prime * result + ((medium == null) ? 0 : medium.hashCode());
        return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlickrImgRef other = (FlickrImgRef) obj;
		if (farm == null) {
			if (other.farm != null)
				return false;
		} else if (!farm.equals(other.farm))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (secret == null) {
			if (other.secret != null)
				return false;
		} else if (!secret.equals(other.secret))
			return false;
		if (server == null) {
			if (other.server != null)
				return false;
		} else if (!server.equals(other.server))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
        if (owner == null) {
            if (other.owner != null)
                return false;
        } else if (!owner.equals(other.owner))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (thumbnail == null) {
            if (other.thumbnail != null)
                return false;
        }
        if (medium == null) {
            if (other.medium != null)
                return false;
        }
        return true;
	}

	@Override
	public String toString() {
		return "FlickrImgRef [id=" + id + ", farm=" + farm + ", server="
				+ server + ", secret=" + secret + ", title=" + title + ", owner=" + owner
                + ", username=" + username +"]";
	}

}
