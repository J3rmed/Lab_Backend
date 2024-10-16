package culturemedia.service;

import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.exception.CulturemediaException;

import java.util.List;

public interface CulturemediaService {
    Video saveVideo(Video video) throws CulturemediaException;
    List<Video> getAllVideos() throws CulturemediaException;
    List<Video> findVideosByTitle(String title) throws CulturemediaException;
    List<Video> findVideosByDurationRange(Double minDuration, Double maxDuration) throws CulturemediaException;
    View saveVideoView(View videoView) throws CulturemediaException;
    List<View> getVideoViews(String videoId) throws CulturemediaException;
}