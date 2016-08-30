package youtubesearch.modeso.ch.youtubesearch.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import youtubesearch.modeso.ch.youtubesearch.R;
import youtubesearch.modeso.ch.youtubesearch.listeners.VideosListListener;
import youtubesearch.modeso.ch.youtubesearch.models.Video;
import youtubesearch.modeso.ch.youtubesearch.services.ListFetchService;
import youtubesearch.modeso.ch.youtubesearch.utils.Constants;
import youtubesearch.modeso.ch.youtubesearch.viewmodels.VideosListViewModel;

/**
 * Created by Belal Mohamed on 8/25/16.
 * www.modeso.ch
 */
public class ListFragment extends Fragment implements VideosListListener {

    private RecyclerView mVideosList;
    private EditText mSearchEditText;
    private Button mSearchButton;
    private VideosListViewModel mViewModel;
    private static final String TAG = ListFragment.class.getName();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mViewModel = new VideosListViewModel(this, context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.onCreate();
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        mVideosList = (RecyclerView) rootView.findViewById(R.id.videos_list);
        mSearchButton = (Button) rootView.findViewById(R.id.search_button);
        mSearchEditText = (EditText) rootView.findViewById(R.id.search_query_edittext);

        initSearchButton();

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        mViewModel.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewModel.onDestroy();
    }

    private void initSearchButton() {
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchQuery = mSearchEditText.getText().toString();
                if (searchQuery.trim().length() > 0) {
                    mViewModel.requestVideoList(searchQuery);
                } else {
                    Toast.makeText(getContext(), "Search field is empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onListReady(List<Video> videoList) {
        Log.d(TAG, "List size: " + videoList.size());
    }

    @Override
    public void onError(String errorMessage) {
        Log.d(TAG, errorMessage);
    }
}
