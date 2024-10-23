package culturemedia.service.impl;

import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.exception.CulturemediaException;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;
import culturemedia.service.CulturemediaService;

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
        // Implementación
        return videoRepository.save(video);
    }

    @Override
    public List<Video> getAllVideos() throws CulturemediaException {
        // Implementación
        return videoRepository.findAll();
    }

    @Override
    public List<Video> findVideosByTitle(String title) throws CulturemediaException {
        // Implementación
        return videoRepository.find(title);
    }

    @Override
    public List<Video> findVideosByDurationRange(Double minDuration, Double maxDuration) throws CulturemediaException {
        // Implementación
        return videoRepository.find(minDuration, maxDuration);
    }

    @Override
    public View saveVideoView(View videoView) throws CulturemediaException {
        // Implementación
        return viewsRepository.save(videoView);
    }

    @Override
    public List<View> getVideoViews(String videoId) throws CulturemediaException {
        // Implementación
        return viewsRepository.findAllByVideo(videoId);
    }
}