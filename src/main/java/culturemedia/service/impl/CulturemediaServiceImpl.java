package culturemedia.service.impl;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;
import culturemedia.service.CulturemediaService;
import culturemedia.exception.CulturemediaException;

import java.util.List;

public class CulturemediaServiceImpl implements CulturemediaService {

    private final VideoRepository videoRepository;
    private final ViewsRepository viewsRepository;

    public CulturemediaServiceImpl(VideoRepository videoRepository, ViewsRepository viewsRepository) {
        this.videoRepository = videoRepository;
        this.viewsRepository = viewsRepository;
    }

    @Override
    public Video saveVideo(Video video) throws CulturemediaException {
        return videoRepository.save(video);
    }

    @Override
    public List<Video> getAllVideos() throws CulturemediaException {
        List<Video> videos = videoRepository.findAll();
        if (videos.isEmpty()) {
            throw new VideoNotFoundException();
        }
        return videos;
    }

    @Override
    public List<Video> findVideosByTitle(String title) throws CulturemediaException {
        List<Video> videos = videoRepository.find(title);
        if (videos.isEmpty()) {
            throw new VideoNotFoundException(title);
        }
        return videos;
    }

    @Override
    public List<Video> findVideosByDurationRange(Double minDuration, Double maxDuration) throws CulturemediaException {
        List<Video> videos = videoRepository.find(minDuration, maxDuration);
        if (videos.isEmpty()) {
            throw new VideoNotFoundException();
        }
        return videos;
    }

    @Override
    public View saveVideoView(View videoView) throws CulturemediaException {
        return viewsRepository.save(videoView);
    }

    @Override
    public List<View> getVideoViews(String videoCode) throws CulturemediaException {
        List<View> views = viewsRepository.findAllByVideo(videoCode);
        if (views.isEmpty()) {
            throw new VideoNotFoundException();
        }
        return views;
    }
}