package com.fps.model;

import java.io.Serializable;

public class FlickrPersonInfoContainer implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private FlickrUsernameContainer username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public FlickrUsernameContainer getUsername() {
        return username;
    }

    public void setUsername(FlickrUsernameContainer username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
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
        FlickrPersonInfoContainer other = (FlickrPersonInfoContainer) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "FlickrPersonInfoContainer [id=" + id + ", username="
                + username + "]";
    }


}
