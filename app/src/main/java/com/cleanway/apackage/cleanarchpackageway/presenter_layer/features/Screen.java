package com.cleanway.apackage.cleanarchpackageway.presenter_layer.features;

import java.util.UUID;


public interface Screen {
    void cachePresenter(Presenter presenter);
    void restorePresenter(UUID uuid);
}
