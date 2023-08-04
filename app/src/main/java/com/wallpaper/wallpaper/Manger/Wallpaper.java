package com.wallpaper.wallpaper.Manger;

public class Wallpaper {
    private String name;
    private String url;

    public Wallpaper(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
