package com.wallpaper.wallpaper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DetailActivity extends AppCompatActivity implements MaxAdListener {

    private String url; // Declare url as class member
    private MaxInterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageView = findViewById(R.id.detail_image_view);
        Button setAsWallpaperButton = findViewById(R.id.set_as_wallpaper_button);
        Button downloadButton = findViewById(R.id.download_button);

        // Initialize url from the intent extras
        url = getIntent().getStringExtra("url");

        // تحقق من وجود إذن WRITE_EXTERNAL_STORAGE
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }

        // تحميل الإعلان التفاعلي (Interstitial ad)
        String interstitialAdUnitId = getString(R.string.interstitial_ad_unit_id);
        interstitialAd = new MaxInterstitialAd(interstitialAdUnitId, this);
        interstitialAd.setListener(this);
        interstitialAd.loadAd();

        Glide.with(this)
                .load(url)
                .into(imageView);

        setAsWallpaperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWallpaper();
            }
        });

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadImage();
            }
        });
    }

    private void setWallpaper() {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        Glide.with(this)
                .asBitmap()
                .load(url)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        try {
                            wallpaperManager.setBitmap(resource);
                            // عرض رسالة توست لإعلام المستخدم أن الخلفية تم تعيينها بنجاح
                            Toast.makeText(DetailActivity.this, "Wallpaper set", Toast.LENGTH_SHORT).show();
                            showInterstitialAd();
                        } catch (IOException e) {
                            // عرض رسالة توست في حالة حدوث خطأ أثناء تعيين الخلفية
                            Toast.makeText(DetailActivity.this, "Error setting wallpaper", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }

    private void downloadImage() {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, url.substring(url.lastIndexOf("/") + 1));

        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);

        // عرض رسالة توست لإعلام المستخدم ببدء عملية التنزيل
        Toast.makeText(DetailActivity.this, "Downloading...", Toast.LENGTH_SHORT).show();
        showInterstitialAd();
    }

    private void showInterstitialAd() {
        if (interstitialAd.isReady()) {
            interstitialAd.showAd();
        } else {
            interstitialAd.loadAd();
        }
    }

    // واجهة MAX Ad Listener
    @Override
    public void onAdLoaded(final MaxAd maxAd) {
        // الإعلان التفاعلي جاهز للعرض. سيُعاد الآن maxAd.isReady() 'صحيح'
    }

    @Override
    public void onAdLoadFailed(final String adUnitId, final MaxError error) {
        // فشل تحميل الإعلان التفاعلي
        // قم بتنفيذ منطق إعادة المحاولة أو التعامل مع الفشل
    }

    @Override
    public void onAdDisplayFailed(final MaxAd maxAd, final MaxError error) {
        // فشل عرض الإعلان التفاعلي. قم بتنفيذ منطق إعادة المحاولة أو تحميل الإعلان التالي
    }

    @Override
    public void onAdDisplayed(final MaxAd maxAd) {
        // الإعلان التفاعلي تم عرضه. يمكنك تنفيذ أي منطق بعد عرض الإعلان
    }

    @Override
    public void onAdClicked(final MaxAd maxAd) {
        // الإعلان التفاعلي تم النقر عليه. يمكنك تنفيذ أي منطق بعد النقر على الإعلان
    }

    @Override
    public void onAdHidden(final MaxAd maxAd) {
        // الإعلان التفاعلي مخفي. قم بتحميل الإعلان التالي مسبقًا لتحسين تجربة المستخدم
        interstitialAd.loadAd();
    }
}
