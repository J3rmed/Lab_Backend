package culturemedia.repository;

import java.util.List;

import culturemedia.model.View;

public interface ViewsRepository {
    View save(View View);
    List<View> findAllByVideo(String videoId);
}
