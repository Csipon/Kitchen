package com.csipon.kitchen.ad;

import java.util.ArrayList;
import java.util.List;

class AdvertisementStorage {
    private static AdvertisementStorage instance = new AdvertisementStorage();
    private final List<Advertisement> videos = new ArrayList<>();

    private AdvertisementStorage() {
        Object someContent = new Object();
        add(new Advertisement(someContent, "First Video", 5000, 100, 3 * 60));
        add(new Advertisement(someContent, "Second Video", 100, 10, 15 * 60));
        add(new Advertisement(someContent, "Third Video", 300, 13, 13 * 60));
        add(new Advertisement(someContent, "Forth Video", 200, 14, 12 * 60));
        add(new Advertisement(someContent, "Fifth Video", 500, 15, 15 * 60));
        add(new Advertisement(someContent, "Sixth Video", 600, 16, 16 * 60));
    }

    public static AdvertisementStorage getInstance() {
        return instance;
    }

    public List<Advertisement> list() {
        return videos;
    }

    public void add(Advertisement advertisement) {
        videos.add(advertisement);
    }
}
