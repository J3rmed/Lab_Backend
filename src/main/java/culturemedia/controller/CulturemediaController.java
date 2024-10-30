package culturemedia.controller;

import java.util.List;
import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.service.CulturemediaService;


public class CulturemediaController {

    private final CulturemediaService cultureMediaService;

    public CulturemediaController(CulturemediaService cultureMediaService) {
        this.cultureMediaService = cultureMediaService;
    }

    public List<Video> findAllVideos() throws VideoNotFoundException {
        return cultureMediaService.getAllVideos();
    }
}