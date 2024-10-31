package culturemedia.service.impl;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CulturemediaServiceTestmock {

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private ViewsRepository viewsRepository;

    private CulturemediaServiceImpl cultureMediaService;

    @BeforeEach
    void setUp() {
        cultureMediaService = new CulturemediaServiceImpl(videoRepository, viewsRepository);
    }

    @Test
    void when_FindAll_all_videos_should_be_returned_successfully() {
        // Arrange
        Video video1 = new Video("1", "Test Video 1", "Description 1", 10.0);
        Video video2 = new Video("2", "Test Video 2", "Description 2", 15.0);
        when(videoRepository.findAll()).thenReturn(Arrays.asList(video1, video2));

        // Act
        List<Video> result = cultureMediaService.getAllVideos();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(video1));
        assertTrue(result.contains(video2));
        verify(videoRepository, times(1)).findAll();
    }

    @Test
    void when_FindAll_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        // Arrange
        when(videoRepository.findAll()).thenReturn(Collections.emptyList());

        // Act & Assert
        assertThrows(VideoNotFoundException.class, () -> cultureMediaService.getAllVideos());
        verify(videoRepository, times(1)).findAll();
    }

    @Test
    void when_FindByTitle_with_existing_title_videos_should_be_returned_successfully() {
        // Arrange
        Video video1 = new Video("1", "Cultural Video", "Description 1", 10.0);
        Video video2 = new Video("2", "Cultural Heritage", "Description 2", 15.0);
        when(videoRepository.find("Cultural")).thenReturn(Arrays.asList(video1, video2));

        // Act
        List<Video> result = cultureMediaService.findVideosByTitle("Cultural");

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(v -> v.title().contains("Cultural")));
        verify(videoRepository, times(1)).find("Cultural");
    }

    @Test
    void when_FindByTitle_with_non_existing_title_VideoNotFoundException_should_be_thrown() {
        // Arrange
        when(videoRepository.find("NonExistent")).thenReturn(Collections.emptyList());

        // Act & Assert
        assertThrows(VideoNotFoundException.class,
                () -> cultureMediaService.findVideosByTitle("NonExistent"));
        verify(videoRepository, times(1)).find("NonExistent");
    }

    @Test
    void when_FindByDurationRange_with_valid_range_videos_should_be_returned_successfully() {
        // Arrange
        Video video = new Video("2", "Medium Video", "Description 2", 10.0);
        when(videoRepository.find(8.0, 12.0)).thenReturn(Collections.singletonList(video));

        // Act
        List<Video> result = cultureMediaService.findVideosByDurationRange(8.0, 12.0);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Medium Video", result.get(0).title());
        verify(videoRepository, times(1)).find(8.0, 12.0);
    }

    @Test
    void when_FindByDurationRange_with_no_videos_in_range_VideoNotFoundException_should_be_thrown() {
        // Arrange
        when(videoRepository.find(20.0, 25.0)).thenReturn(Collections.emptyList());

        // Act & Assert
        assertThrows(VideoNotFoundException.class,
                () -> cultureMediaService.findVideosByDurationRange(20.0, 25.0));
        verify(videoRepository, times(1)).find(20.0, 25.0);
    }
}