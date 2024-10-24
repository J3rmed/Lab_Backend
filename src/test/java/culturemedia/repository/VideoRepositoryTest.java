package culturemedia.repository;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import culturemedia.model.Video;
import culturemedia.repository.impl.VideoRepositoryImpl;

import static org.junit.jupiter.api.Assertions.*;

class VideoRepositoryTest {

    private VideoRepository videoRepository;

    // Inicializa el repositorio y agrega videos de prueba.
    @BeforeEach
    void init() {
        videoRepository = new VideoRepositoryImpl();

        List<Video> videos = List.of(
                new Video("01", "Título 1", "Descripción 1", 4.5),
                new Video("02", "Título 2", "Descripción 2", 5.5),
                new Video("03", "Título 3", "Descripción 3", 4.4),
                new Video("04", "Título 4", "Descripción 4", 3.5),
                new Video("05", "Clic 5", "Descripción 5", 5.7),
                new Video("06", "Clic 6", "Descripción 6", 5.1)
        );

        for (Video video : videos) {
            videoRepository.save(video);
        }
    }

    // Prueba: Todos los videos deben ser devueltos.
    @Test
    void when_FindAll_all_videos_should_be_returned_successfully() {
        List<Video> videos = videoRepository.findAll();
        assertEquals(6, videos.size(), "Se esperaban 6 videos en total.");
    }

    // Prueba: Buscar videos que coincidan exactamente con el título.
    @Test
    void when_FindByTitle_only_videos_which_match_the_exact_title_should_be_returned_successfully() {
        List<Video> videos = videoRepository.find("Clic 5");
        assertEquals(1, videos.size(), "Se esperaba 1 video con el título exacto 'Clic 5'.");

        videos = videoRepository.find("Clic 6");
        assertEquals(1, videos.size(), "Se esperaba 1 video con el título exacto 'Clic 6'.");
    }

    @Test
    void when_FindByDuration_only_videos_between_the_range_should_be_returned_successfully() {
        // Ajustamos el rango para respetar los límites estrictos.
        List<Video> videos = videoRepository.find(4.4, 5.4);
        assertEquals(2, videos.size(), "Se esperaban 2 videos en el rango estricto 4.4 < duración < 5.4.");
    }

    @Test
    void when_FindByTitle_does_not_match_any_video_an_empty_list_should_be_returned_successfully() {
        // Ahora esperamos una lista vacía en lugar de null.
        List<Video> videos = videoRepository.find("Inexistente");
        assertTrue(videos.isEmpty(), "Se esperaba una lista vacía.");
    }

    // Prueba: Buscar videos en un rango donde no haya coincidencias.
    @Test
    void when_FindByDuration_does_not_match_any_video_an_empty_list_should_be_returned_successfully() {
        List<Video> videos = videoRepository.find(6.0, 7.0);
        assertTrue(videos.isEmpty(), "Se esperaba una lista vacía.");
    }
}
