package com.wallpaper.wallpaper;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;

import java.util.concurrent.TimeUnit;

public class Buttons_Data extends AppCompatActivity implements MaxAdListener {

    private MaxInterstitialAd interstitialAd;
    private int retryAttempt;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons_data);

        // تهيئة SDK لـ AppLovin
        AppLovinSdk.getInstance(this).setMediationProvider("max");
        AppLovinSdk.initializeSdk(this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                // بعد تهيئة SDK بنجاح، قم بإنشاء الإعلان الطبيعي (Native Ad)
                createNativeAd();
            }
        });

        // الحصول على مراجع للأزرار وتعيين مستمعي النقر لها
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ابدأ نشاط التفاصيل مع الصورة والنص المرتبط بالزر رقم 1
                // Get the string value from strings.xml
                String cardTextButton1 = getString(R.string.card_text_button1);
                Intent intent = new Intent(Buttons_Data.this, DetailsData.class);
                intent.putExtra("imageResId", R.drawable.tip1);
                intent.putExtra("cardText", cardTextButton1);
                startActivity(intent);

                // اعرض الإعلان التفاعلي إن توفر
                if (interstitialAd.isReady()) {
                    interstitialAd.showAd();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ابدأ نشاط التفاصيل مع الصورة والنص المرتبط بالزر رقم 1
                String cardTextButton1 = getString(R.string.card_text_button2);
                Intent intent = new Intent(Buttons_Data.this, DetailsData.class);
                intent.putExtra("imageResId", R.drawable.tip2);
                intent.putExtra("cardText", cardTextButton1);
                startActivity(intent);

                startActivity(intent);

                // اعرض الإعلان التفاعلي إن توفر
                if (interstitialAd.isReady()) {
                    interstitialAd.showAd();
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ابدأ نشاط التفاصيل مع الصورة والنص المرتبط بالزر رقم 1
                String cardTextButton1 = getString(R.string.card_text_button3);
                Intent intent = new Intent(Buttons_Data.this, DetailsData.class);
                intent.putExtra("imageResId", R.drawable.tip3);
                intent.putExtra("cardText", cardTextButton1);
                startActivity(intent);

                // اعرض الإعلان التفاعلي إن توفر
                if (interstitialAd.isReady()) {
                    interstitialAd.showAd();
                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ابدأ نشاط التفاصيل مع الصورة والنص المرتبط بالزر رقم 1
                String cardTextButton1 = getString(R.string.card_text_button4);
                Intent intent = new Intent(Buttons_Data.this, DetailsData.class);
                intent.putExtra("imageResId", R.drawable.tip4);
                intent.putExtra("cardText", cardTextButton1);
                startActivity(intent);

                // اعرض الإعلان التفاعلي إن توفر
                if (interstitialAd.isReady()) {
                    interstitialAd.showAd();
                }
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ابدأ نشاط التفاصيل مع الصورة والنص المرتبط بالزر رقم 1
                String cardTextButton1 = getString(R.string.card_text_button5);
                Intent intent = new Intent(Buttons_Data.this, DetailsData.class);
                intent.putExtra("imageResId", R.drawable.tip5);
                intent.putExtra("cardText", cardTextButton1);
                startActivity(intent);

                // اعرض الإعلان التفاعلي إن توفر
                if (interstitialAd.isReady()) {
                    interstitialAd.showAd();
                }
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ابدأ نشاط التفاصيل مع الصورة والنص المرتبط بالزر رقم 1
                String cardTextButton1 = getString(R.string.card_text_button6);
                Intent intent = new Intent(Buttons_Data.this, DetailsData.class);
                intent.putExtra("imageResId", R.drawable.tip6);
                intent.putExtra("cardText", cardTextButton1);
                startActivity(intent);

                // اعرض الإعلان التفاعلي إن توفر
                if (interstitialAd.isReady()) {
                    interstitialAd.showAd();
                }
            }
        });

        // 555
    }

    // طريقة إنشاء الإعلان الطبيعي (Native Ad)
    private void createNativeAd() {
        FrameLayout nativeAdContainer = findViewById(R.id.nativeAdContainer);

        // قم بتحميل الإعلان الطبيعي (Native Ad)
        String nativeAdUnitId = getString(R.string.native_ad_unit_id);
        nativeAdLoader = new MaxNativeAdLoader(nativeAdUnitId, this);
        nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
            @Override
            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                // تنظيف أي إعلان طبيعي موجود بالفعل لتجنب تسرب الذاكرة
                if (nativeAd != null) {
                    nativeAdLoader.destroy(nativeAd);
                }

                // حفظ الإعلان لتنظيفه لاحقًا
                nativeAd = ad;

                // إضافة عرض الإعلان للواجهة الرسومية
                nativeAdContainer.removeAllViews();
                nativeAdContainer.addView(nativeAdView);
            }

            @Override
            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {
                // نوصي بإعادة المحاولة مع تأخيرات تزايدية حتى وقت تأخير أقصى
            }
        });

        nativeAdLoader.loadAd();

        // قم بتحميل الإعلان التفاعلي (Interstitial Ad)
        String interstitialAdUnitId = getString(R.string.interstitial_ad_unit_id);
        interstitialAd = new MaxInterstitialAd(interstitialAdUnitId, this);
        interstitialAd.setListener(this);

        // قم بتحميل أول إعلان تفاعلي
        interstitialAd.loadAd();
    }

    // طرق واجهة MAX Ad Listener
    @Override
    public void onAdLoaded(final MaxAd maxAd) {
        // الإعلان التفاعلي مستعد للعرض، وسيُعود الآن interstitialAd.isReady() بقيمة 'صحيح'

        // إعادة تعيين عدد محاولات إعادة التحميل
        retryAttempt = 0;
    }

    @Override
    public void onAdLoadFailed(final String adUnitId, final MaxError error) {
        // فشل تحميل الإعلان التفاعلي
        // يُوصى بإعادة المحاولة بتأخيرات تزايدية حتى وقت تأخير أقصى (في هذه الحالة 64 ثانية)

        retryAttempt++;
        long delayMillis = TimeUnit.SECONDS.toMillis((long) Math.pow(2, Math.min(6, retryAttempt)));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // إعادة تحميل الإعلان التفاعلي
                interstitialAd.loadAd();
            }
        }, delayMillis);
    }

    @Override
    public void onAdDisplayFailed(final MaxAd maxAd, final MaxError error) {
        // فشل عرض الإعلان التفاعلي. يُوصى بتحميل الإعلان التالي.
        interstitialAd.loadAd();
    }

    @Override
    public void onAdDisplayed(final MaxAd maxAd) {
        // تم عرض الإعلان التفاعلي
    }

    @Override
    public void onAdClicked(final MaxAd maxAd) {
        // الإعلان التفاعلي تم النقر عليه
    }

    @Override
    public void onAdHidden(final MaxAd maxAd) {
        // الإعلان التفاعلي مخفي. قم بتحميل الإعلان التالي مسبقًا.
        interstitialAd.loadAd();
    }
}
