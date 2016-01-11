package com.fps.common;

import android.os.Build;

/**
 * Static configuration constants for the app.
 */
public class Config {

	/**
	 * No instances of this class
	 */
	private Config() {
	}

	/**
	 * HTTP user agent string of the app.
	 */
	public static final String HTTP_USER_AGENT = "MyFlickrSearch/1.0 Android " + Build.VERSION.RELEASE;

	/**
	 * The Api key goes in here, if you do not have a key you can get a temp key from flicker API
	 * explorer:
	 * <p/>
	 * http://www.flickr.com/services/api/explore/flickr.photos.search
	 */
	public static final String API_KEY = "d6c8a1ba28a86aa96b9dc32e0256912c";

	/** The maximum number of images per result pege. */
	public static final int PER_PAGE = 20;

	/**
	 * flickr.photos.search url with two place holders one for the sorting order and one for the search term.
	 * <p/>
	 * For more details see:
	 * http://www.flickr.com/services/api/flickr.photos.search.html
	 */
	public static final String SEARCH_API_URL = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key="
			+ API_KEY + "&format=json&nojsoncallback=1&sort=%s&per_page=" + PER_PAGE + "&text=%s";

    /**
     * flickr.people.getInfo url with one place holder for the user_id.
     * <p/>
     * For more details see:
     * http://www.flickr.com/services/api/flickr.people.getInfo.html
     */
    public static final String GET_PERSON_INFO_API_URL = "https://api.flickr.com/services/rest/?method=flickr.people.getInfo&api_key="
            + API_KEY + "&format=json&nojsoncallback=1&user_id=%s";


	/**
	 * Constant used to pass extra parameters from the MainActivity to the next one.
	 */
	public static final String EXTRA_KEY_SEARCH_TERM = "extraKey.searchTerm";

	/**
	 * Constant used to pass extra parameters from the MainActivity to the next one.
	 */
	public static final String EXTRA_KEY_SORT_ORDER = "extraKey.sortOrder";

	/**
	 * Constant used to pass extra parameters from the MainActivity to the next one.
	 */
	public static final String EXTRA_KEY_FLICKR_REF = "extraKey.flickrRef";

}
