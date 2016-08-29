package youtubesearch.modeso.ch.youtubesearch.services;

import android.util.Log;

import youtubesearch.modeso.ch.youtubesearch.responsies.ApiResponse;
import youtubesearch.modeso.ch.youtubesearch.responsies.ErrorResponse;

/**
 * Created by Belal Mohamed on 8/28/16.
 * www.modeso.ch
 */
public abstract class YoutubeSearchHelper {

    private static final String TAG = YoutubeSearchHelper.class.getName();

    public static void searchVideos(String searchQuery) {
        YoutubeApiClient.searchYoutube(searchQuery, new YoutubeApiClient.ApiCallBack() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                if (apiResponse != null) {
                    Log.d(TAG, "Items Size: " + VideoItemWrapper.getVideoList(apiResponse).size());
                }
            }

            @Override
            public void onError(ErrorResponse errorResponse) {
                if (errorResponse != null) {
                    Log.d(TAG, errorResponse.getError().getMessage());
                }
            }
        });
    }
}
