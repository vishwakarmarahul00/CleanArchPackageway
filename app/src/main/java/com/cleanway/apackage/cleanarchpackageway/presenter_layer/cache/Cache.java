package com.cleanway.apackage.cleanarchpackageway.presenter_layer.cache;

import com.cleanway.apackage.cleanarchpackageway.presenter_layer.features.Presenter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Cache implements Serializable {

    private static Cache ourInstance;
    private Map<UUID, Presenter> cache;

    public static Cache getInstance() {
        if (ourInstance == null) {
            ourInstance = new Cache();
        }
        return ourInstance;
    }

    private Cache() {
        cache = new HashMap<>();
    }

    public void cachePresenterFor(UUID uuid, Presenter presenter) {
        cache.put(uuid, presenter);
    }

    public Presenter restorePresenterFor(UUID uuid) {
        Presenter presenter = cache.get(uuid);
        cache.remove(presenter);
        return presenter;
    }
}
