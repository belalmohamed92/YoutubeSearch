package youtubesearch.modeso.ch.youtubesearch.services;

import android.app.IntentService;
import android.content.Intent;

import youtubesearch.modeso.ch.youtubesearch.utils.Constants;

/**
 * Created by Belal Mohamed on 8/25/16.
 * www.modeso.ch
 */
public class ListFetchService extends IntentService {

    public ListFetchService() {
        super("List Loading service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        switch (intent.getAction()){
            case Constants.ACTION_SEARCH_YOUTUBE:
                YoutubeSearchHelper.searchVideos(intent.getStringExtra(Constants.SEARCH_QUERY_KEY));
                break;
        }
    }
}
