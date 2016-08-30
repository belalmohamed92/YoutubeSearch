package youtubesearch.modeso.ch.youtubesearch.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import youtubesearch.modeso.ch.youtubesearch.listeners.VideosListListener;
import youtubesearch.modeso.ch.youtubesearch.services.ListFetchService;
import youtubesearch.modeso.ch.youtubesearch.services.YoutubeSearchHelper;
import youtubesearch.modeso.ch.youtubesearch.utils.Constants;

/**
 * Created by Belal Mohamed on 8/30/16.
 * www.modeso.ch
 */
public class VideosListViewModel {

    private VideosListListener mListener;

    private Context mContext;

    private static String mSearchQuery;

    public VideosListViewModel(@NonNull VideosListListener videosListListener, @NonNull Context context) {
        mListener = videosListListener;
        mContext = context;
    }

    public void onCreate() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    public void onResume() {

    }

    public void onPause() {

    }

    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    public void requestVideoList(String searchQuery) {
        mSearchQuery = searchQuery;
        Intent intent = new Intent(mContext, ListFetchService.class);
        intent.setAction(Constants.ACTION_SEARCH_YOUTUBE);
        intent.putExtra(Constants.SEARCH_QUERY_KEY, searchQuery);
        mContext.startService(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onVideosListEvent(YoutubeSearchHelper.VideosListEvent videosListEvent) {
        if (mSearchQuery.equals(videosListEvent.getSearchQuery())) {
            notifyRegisteredListener(videosListEvent);
        }
    }

    private void notifyRegisteredListener(YoutubeSearchHelper.VideosListEvent videosListEvent) {
        switch (videosListEvent.getEventType()) {
            case Success:
                mListener.onListReady(videosListEvent.getVideos());
                break;
            case Error:
                mListener.onError(videosListEvent.getErrorMessage());
                break;
        }
    }
}
