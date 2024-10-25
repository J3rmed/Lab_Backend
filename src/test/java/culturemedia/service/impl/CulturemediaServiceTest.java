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
        // Act & Assert
        assertThrows(VideoNotFoundException.class, () -> cultureMediaService.getAllVideos());
    }

    // Nuevos casos de prueba para findVideosByTitle
    @Test
    void when_FindByTitle_with_existing_title_videos_should_be_returned_successfully() {
        // Arrange
        Video video1 = new Video("1", "Cultural Video", "Description 1", 10.0);
        Video video2 = new Video("2", "Cultural Heritage", "Description 2", 15.0);
        videoRepository.save(video1);
        videoRepository.save(video2);

        // Act
        List<Video> result = cultureMediaService.findVideosByTitle("Cultural");

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(v -> v.title().contains("Cultural")));
    }

    @Test
    void when_FindByTitle_with_non_existing_title_VideoNotFoundException_should_be_thrown() {
        // Arrange
        Video video = new Video("1", "Test Video", "Description", 10.0);
        videoRepository.save(video);

        // Act & Assert
        assertThrows(VideoNotFoundException.class,
                () -> cultureMediaService.findVideosByTitle("NonExistent"));
    }

    // Nuevos casos de prueba para findVideosByDurationRange
    @Test
    void when_FindByDurationRange_with_valid_range_videos_should_be_returned_successfully() {
        // Arrange
        Video video1 = new Video("1", "Short Video", "Description 1", 5.0);
        Video video2 = new Video("2", "Medium Video", "Description 2", 10.0);
        Video video3 = new Video("3", "Long Video", "Description 3", 15.0);
        videoRepository.save(video1);
        videoRepository.save(video2);
        videoRepository.save(video3);

        // Act
        List<Video> result = cultureMediaService.findVideosByDurationRange(8.0, 12.0);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Medium Video", result.get(0).title());
    }

    @Test
    void when_FindByDurationRange_with_no_videos_in_range_VideoNotFoundException_should_be_thrown() {
        // Arrange
        Video video1 = new Video("1", "Short Video", "Description 1", 5.0);
        Video video2 = new Video("2", "Medium Video", "Description 2", 10.0);
        videoRepository.save(video1);
        videoRepository.save(video2);

        // Act & Assert
        assertThrows(VideoNotFoundException.class,
                () -> cultureMediaService.findVideosByDurationRange(20.0, 25.0));
    }
}