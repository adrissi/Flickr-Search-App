package com.fps.model;

import java.io.Serializable;

public class FlickrPersonInfoRoot implements Serializable {

    private static final long serialVersionUID = 1L;

    private FlickrPersonInfoContainer person;

    private String stat;

    public FlickrPersonInfoContainer getPerson() {
        return person;
    }

    public void setPerson(FlickrPersonInfoContainer photo) {
        this.person = photo;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((person == null) ? 0 : person.hashCode());
        result = prime * result + ((stat == null) ? 0 : stat.hashCode());
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
        FlickrPersonInfoRoot other = (FlickrPersonInfoRoot) obj;
        if (person == null) {
            if (other.person != null)
                return false;
        } else if (!person.equals(other.person))
            return false;
        if (stat == null) {
            if (other.stat != null)
                return false;
        } else if (!stat.equals(other.stat))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "FlickrImgInfoRoot [person=" + person + ", stat=" + stat + "]";
    }


}
