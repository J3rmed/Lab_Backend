package culturemedia.repository.impl;

import java.util.ArrayList;
import java.util.List;

import culturemedia.model.View;
import culturemedia.repository.ViewsRepository;

public class ViewsRepositoryImpl implements ViewsRepository {

    private final List<View> views;

    public ViewsRepositoryImpl() {
        this.views = new ArrayList<>();
    }

    @Override
    public View save(View view) {
        this.views.add(view);
        return view;
    }

    @Override
    public List<View> findAllByVideo(String videoCode) {
        List<View> videoViews = new ArrayList<>();
        for (View view : views) {
            if (view.video().code().equals(videoCode)) {
                videoViews.add(view);
            }
        }
        return videoViews;
    }
}