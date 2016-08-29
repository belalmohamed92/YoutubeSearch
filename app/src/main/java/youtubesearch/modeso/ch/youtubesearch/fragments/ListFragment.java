package youtubesearch.modeso.ch.youtubesearch.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import youtubesearch.modeso.ch.youtubesearch.R;
import youtubesearch.modeso.ch.youtubesearch.services.ListFetchService;
import youtubesearch.modeso.ch.youtubesearch.utils.Constants;

/**
 * Created by Belal Mohamed on 8/25/16.
 * www.modeso.ch
 */
public class ListFragment extends Fragment {

    private RecyclerView mVideosList;
    private EditText mSearchEditText;
    private Button mSearchButton;

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

    private void initSearchButton() {
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchQuery = mSearchEditText.getText().toString();
                if (searchQuery.trim().length() > 0) {
                    Intent intent = new Intent(getContext(), ListFetchService.class);
                    intent.setAction(Constants.ACTION_SEARCH_YOUTUBE);
                    intent.putExtra(Constants.SEARCH_QUERY_KEY, searchQuery);
                    getContext().startService(intent);
                } else {
                    Toast.makeText(getContext(), "Search field is empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
