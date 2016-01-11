package com.fps.model;

import java.io.Serializable;

public class FlickrUsernameContainer implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _content;

    public String getContent() {
        return _content;
    }

    public void setId(String content) {
        this._content = content;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((_content == null) ? 0 : _content.hashCode());
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
        FlickrUsernameContainer other = (FlickrUsernameContainer) obj;
        if (_content == null) {
            if (other._content != null)
                return false;
        } else if (!_content.equals(other._content))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "FlickrUsernameContainer [_content=" + _content + "]";
    }


}
