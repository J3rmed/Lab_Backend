package culturemedia.controller;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.service.CulturemediaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
public class CulturemediaController {
    private final CulturemediaService cultureMediaService;

    public CulturemediaController(CulturemediaService cultureMediaService) {
        this.cultureMediaService = cultureMediaService;
    }

    @GetMapping
    public ResponseEntity<List<Video>> findAllVideos() throws VideoNotFoundException {
        return ResponseEntity.ok(cultureMediaService.getAllVideos());
    }

    @PostMapping
    public ResponseEntity<Video> saveVideo(@RequestBody Video video) throws VideoNotFoundException {
        return ResponseEntity.ok(cultureMediaService.saveVideo(video));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Video>> findByTitle(@RequestParam String title) throws VideoNotFoundException {
        return ResponseEntity.ok(cultureMediaService.findVideosByTitle(title));
    }

    @GetMapping("/duration")
    public ResponseEntity<List<Video>> findByDuration(
            @RequestParam Double minDuration,
            @RequestParam Double maxDuration) throws VideoNotFoundException {
        return ResponseEntity.ok(cultureMediaService.findVideosByDurationRange(minDuration, maxDuration));
    }

    @PostMapping("/views")
    public ResponseEntity<View> saveView(@RequestBody View view) throws VideoNotFoundException {
        return ResponseEntity.ok(cultureMediaService.saveVideoView(view));
    }

    @GetMapping("/{videoId}/views")
    public ResponseEntity<List<View>> getVideoViews(@PathVariable String videoId) throws VideoNotFoundException {
        return ResponseEntity.ok(cultureMediaService.getVideoViews(videoId));
    }
}
