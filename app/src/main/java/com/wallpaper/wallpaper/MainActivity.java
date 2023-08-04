package com.wallpaper.wallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.wallpaper.wallpaper.Manger.Wallpaper;
import com.wallpaper.wallpaper.Manger.WallpaperAdapter;
import com.wallpaper.wallpaper.Manger.WallpaperApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // تهيئة Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // إنشاء خدمة WallpaperApi
        WallpaperApi wallpaperApi = retrofit.create(WallpaperApi.class);

        // إجراء الاتصال بالشبكة للحصول على قائمة الخلفيات
        Call<List<Wallpaper>> call = wallpaperApi.getWallpapers();

        call.enqueue(new Callback<List<Wallpaper>>() {
            @Override
            public void onResponse(Call<List<Wallpaper>> call, Response<List<Wallpaper>> response) {
                if (!response.isSuccessful()) {
                    // معالجة الخطأ هنا
                    return;
                }

                // استرجاع قائمة الخلفيات من الاستجابة
                List<Wallpaper> wallpapers = response.body();

                // ملء RecyclerView بالخلفيات
                RecyclerView recyclerView = findViewById(R.id.recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(new WallpaperAdapter(wallpapers));
            }

            @Override
            public void onFailure(Call<List<Wallpaper>> call, Throwable t) {
                // معالجة الفشل هنا
            }
        });
    }
}
