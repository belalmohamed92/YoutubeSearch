package youtubesearch.modeso.ch.youtubesearch.utils;

/**
 * Created by Belal Mohamed on 8/25/16.
 * www.modeso.ch
 */
public abstract class Constants {

    public static final String API_KEY = "AIzaSyBY_ql7VxnyHDny-4TQ1-tu6gXRhIIdwuY";
    public static final String BASE_URL = "https://www.googleapis.com/youtube/v3/search?part=id,snippet&type=video&maxResults=50&key="+API_KEY+"&q=";
    public static final String ACTION_SEARCH_YOUTUBE = "youtubesearch.modeso.ch.youtubesearch.ACTION_SEARCH_YOUTUBE";
    public static final String SEARCH_QUERY_KEY = "youtubesearch.modeso.ch.youtubesearch.SEARCH_QUERY_KEY";
}
