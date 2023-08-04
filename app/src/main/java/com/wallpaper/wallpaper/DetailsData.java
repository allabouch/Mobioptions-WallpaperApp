package com.wallpaper.wallpaper;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;

public class DetailsData extends AppCompatActivity {

    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_data);

        // الحصول على الصورة والنص من البيانات الإضافية للنشاط
        int imageResId = getIntent().getIntExtra("imageResId", 0);
        String cardText = getIntent().getStringExtra("cardText");

        // تعيين الصورة والنص في تخطيط النشاط activity_detail
        ImageView detailImageView = findViewById(R.id.detailImageView);
        TextView detailTextView = findViewById(R.id.detailTextView);

        detailImageView.setImageResource(imageResId);
        detailTextView.setText(cardText);

        // تحميل الإعلان الطبيعي (Native ad)
        createNativeAd();
    }

    private void createNativeAd() {
        FrameLayout nativeAdContainer = findViewById(R.id.nativeAdContainer);

        String nativeAdUnitId = getString(R.string.native_ad_unit_id);
        nativeAdLoader = new MaxNativeAdLoader(nativeAdUnitId, this);

        nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
            @Override
            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                // تنظيف أي إعلان طبيعي موجود مسبقًا لتجنب تسرب الذاكرة.
                if (nativeAd != null) {
                    nativeAdLoader.destroy(nativeAd);
                }

                // حفظ الإعلان للتنظيف لاحقًا.
                nativeAd = ad;

                // إضافة عرض الإعلان للواجهة.
                nativeAdContainer.removeAllViews();
                nativeAdContainer.addView(nativeAdView);
            }

            @Override
            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {
                // نوصي بإعادة المحاولة مع تأخيرات زمنية تزايدية حتى الوصول إلى تأخير أقصى.
            }
        });

        nativeAdLoader.loadAd();
    }
}
