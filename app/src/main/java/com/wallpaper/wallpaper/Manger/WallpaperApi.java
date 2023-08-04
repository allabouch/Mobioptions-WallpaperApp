package com.wallpaper.wallpaper.Manger;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WallpaperApi {
    @GET("mod.json")
    Call<List<Wallpaper>> getWallpapers();


}
