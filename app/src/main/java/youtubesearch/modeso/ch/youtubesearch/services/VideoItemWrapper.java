package youtubesearch.modeso.ch.youtubesearch.services;

import java.util.ArrayList;
import java.util.List;

import youtubesearch.modeso.ch.youtubesearch.models.Video;
import youtubesearch.modeso.ch.youtubesearch.responsies.ApiResponse;
import youtubesearch.modeso.ch.youtubesearch.responsies.Item;

/**
 * Created by Belal Mohamed on 8/28/16.
 * www.modeso.ch
 */
public abstract class VideoItemWrapper {
    public static List<Video> getVideoList(ApiResponse apiResponse) {
        List<Video> videos = new ArrayList<>();

        for (Item item : apiResponse.getItems()) {
            Video video = new Video();
            video.setId(item.getId().getVideoId());
            video.setPublishDate(item.getSnippet().getPublishDate());
            video.setChannelTitle(item.getSnippet().getChannelTitle());
            video.setTitle(item.getSnippet().getTitle());
            video.setThumbnail(item.getSnippet().getThumbnails().getHigh().getUrl());
            video.setDescription(item.getSnippet().getDescription());
            videos.add(video);
        }

        return videos;
    }
}
