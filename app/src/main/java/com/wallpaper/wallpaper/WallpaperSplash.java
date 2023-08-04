package com.wallpaper.wallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class WallpaperSplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper_splash);

        ImageView splashIcon = findViewById(R.id.splashIcon);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_animation);
        splashIcon.startAnimation(animation);

        // قم بمحاكاة عملية التحميل أو أي عملية أخرى هنا (مثل التحقق من مصادقة المستخدم)
        // لأغراض العرض التوضيحي، سنستخدم postDelayed لتأخير الانتقال إلى النشاط الرئيسي
        splashIcon.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WallpaperSplash.this, GuideActivity.class));
                finish();
            }
        }, 3000); // قم بتحديد وقت التأخير بالميلي ثانية (مثال: 3000 ميلي ثانية = 3 ثواني)
    }
}
