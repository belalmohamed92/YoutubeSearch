package youtubesearch.modeso.ch.youtubesearch.listeners;

import java.util.List;

import youtubesearch.modeso.ch.youtubesearch.models.Video;

/**
 * Created by Belal Mohamed on 8/30/16.
 * www.modeso.ch
 */
public interface VideosListListener {
    void onListReady(List<Video> videoList);

    void onError(String errorMessage);
}
