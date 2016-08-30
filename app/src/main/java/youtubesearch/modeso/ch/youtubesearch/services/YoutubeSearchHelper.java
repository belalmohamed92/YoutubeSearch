package youtubesearch.modeso.ch.youtubesearch.services;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import youtubesearch.modeso.ch.youtubesearch.models.Video;
import youtubesearch.modeso.ch.youtubesearch.responsies.ApiResponse;
import youtubesearch.modeso.ch.youtubesearch.responsies.ErrorResponse;

/**
 * Created by Belal Mohamed on 8/28/16.
 * www.modeso.ch
 */
public abstract class YoutubeSearchHelper {

    private static final String TAG = YoutubeSearchHelper.class.getName();

    public static void searchVideos(final String searchQuery) {
        YoutubeApiClient.searchYoutube(searchQuery, new YoutubeApiClient.ApiCallBack() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                if (apiResponse != null) {
                    EventBus.getDefault().post(new VideosListEvent(VideosListEvent.EventType.Success
                            , VideoItemWrapper.getVideoList(apiResponse), searchQuery, null));
                }
            }

            @Override
            public void onError(ErrorResponse errorResponse) {
                if (errorResponse != null) {
                    Log.d(TAG, errorResponse.getError().getMessage());
                    EventBus.getDefault().post(new VideosListEvent(VideosListEvent.EventType.Error, null
                            , searchQuery, errorResponse.getError().getMessage()));
                }
            }
        });
    }


    public static class VideosListEvent {

        private EventType eventType;
        private List<Video> videos;
        private String errorMessage;
        private String searchQuery;

        public VideosListEvent(EventType eventType, List<Video> videos, String searchQuery, String errorMessage) {
            this.eventType = eventType;
            this.videos = videos;
            this.searchQuery = searchQuery;
            this.errorMessage = errorMessage;
        }


        public enum EventType {
            Success,
            Error;

            public String getName() {
                switch (this) {
                    case Success:
                        return "Success";
                    case Error:
                        return "Error";
                    default:
                        return null;
                }
            }
        }

        public EventType getEventType() {
            return eventType;
        }

        public List<Video> getVideos() {
            return videos;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public String getSearchQuery() {
            return searchQuery;
        }
    }
}
