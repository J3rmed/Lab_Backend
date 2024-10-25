package culturemedia.service.impl;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;
import culturemedia.repository.impl.VideoRepositoryImpl;
import culturemedia.repository.impl.ViewsRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class CulturemediaServiceTest {

    private CulturemediaServiceImpl cultureMediaService;
    private VideoRepository videoRepository;

    @BeforeEach
    void setUp() {
        videoRepository = new VideoRepositoryImpl();
        ViewsRepository viewsRepository = new ViewsRepositoryImpl();
        cultureMediaService = new CulturemediaServiceImpl(videoRepository, viewsRepository);
    }

    @Test
    void when_FindAll_all_videos_should_be_returned_successfully() {
        // Arrange
        Video video1 = new Video("1", "Test Video 1", "Description 1", 10.0);
        Video video2 = new Video("2", "Test Video 2", "Description 2", 15.0);
        videoRepository.save(video1);
        videoRepository.save(video2);

        // Act
        List<Video> result = cultureMediaService.getAllVideos();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(video1));
        assertTrue(result.contains(video2));
    }

    @Test
    void when_FindAll_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        // Arrange - no videos added to repository

        // Act & Assert
        assertThrows(VideoNotFoundException.class, () -> cultureMediaService.getAllVideos());
    }
}