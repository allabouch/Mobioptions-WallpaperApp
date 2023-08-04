package com.wallpaper.wallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class GuideActivity extends AppCompatActivity implements MaxAdListener {

    private MaxInterstitialAd interstitialAd;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;

    private boolean isInterstitialAdLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        // تهيئة SDK لـ AppLovin
        AppLovinSdk.getInstance(this).setMediationProvider("max");
        AppLovinSdk.initializeSdk(this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                createNativeAd();
                loadInterstitialAd();
            }
        });
    }

    public void next(View view) {
        // التحقق مما إذا كان الإعلان التفاعلي مستعدًا للعرض
        if (isInterstitialAdLoaded) {
            // عرض الإعلان التفاعلي قبل الانتقال إلى النشاط التالي
            interstitialAd.showAd();
        } else {
            // إذا لم يكن الإعلان التفاعلي مستعدًا، قم بالانتقال إلى النشاط التالي بدون عرض الإعلان
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void how(View view) {
        // التحقق مما إذا كان الإعلان التفاعلي مستعدًا للعرض
        if (isInterstitialAdLoaded) {
            // عرض الإعلان التفاعلي قبل الانتقال إلى النشاط Buttons_Data
            interstitialAd.showAd();
        } else {
            // إذا لم يكن الإعلان التفاعلي مستعدًا، قم بالانتقال إلى النشاط Buttons_Data بدون عرض الإعلان
            Intent intent = new Intent(this, Buttons_Data.class);
            startActivity(intent);
        }
    }

    void createNativeAd() {
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

            @Override
            public void onNativeAdClicked(final MaxAd ad) {
                // يمكن تنفيذ دالة النقر اختياريًا.
            }
        });

        nativeAdLoader.loadAd();
    }

    void loadInterstitialAd() {
        String interstitialAdUnitId = getString(R.string.interstitial_ad_unit_id);
        interstitialAd = new MaxInterstitialAd(interstitialAdUnitId, this);
        interstitialAd.setListener(this);
        interstitialAd.loadAd();
    }

    // طرق واجهة MAX Ad Listener
    @Override
    public void onAdLoaded(final MaxAd maxAd) {
        // الإعلان التفاعلي مستعد للعرض. ستُعيد maxAd.isReady() الآن قيمة 'true'
        isInterstitialAdLoaded = true;
    }

    @Override
    public void onAdLoadFailed(final String adUnitId, final MaxError error) {
        // فشل تحميل الإعلان التفاعلي
        // يمكن تنفيذ معالجة خطأ أو إعادة المحاولة
        isInterstitialAdLoaded = false;
    }

    @Override
    public void onAdDisplayFailed(final MaxAd maxAd, final MaxError error) {
        // فشل عرض الإعلان التفاعلي. يمكن تنفيذ معالجة خطأ أو تحميل الإعلان التالي
        isInterstitialAdLoaded = false;
    }

    @Override
    public void onAdDisplayed(final MaxAd maxAd) {
        // تم عرض الإعلان التفاعلي. يمكن تنفيذ أي منطق بعد عرض الإعلان.
    }

    @Override
    public void onAdClicked(final MaxAd maxAd) {
        // الإعلان التفاعلي تم النقر عليه. يمكن تنفيذ أي منطق بعد نقر الإعلان.
    }

    @Override
    public void onAdHidden(final MaxAd maxAd) {
        // الإعلان التفاعلي مخفي. قم بالانتقال إلى النشاط المطلوب بعد إغلاق الإعلان.
        if (isInterstitialAdLoaded) {
            // إذا تم عرض الإعلان التفاعلي، قم بالانتقال إلى النشاط المقابل
            Intent intent = new Intent(this, Buttons_Data.class);
            startActivity(intent);
        } else {
            // إذا لم يتم عرض الإعلان التفاعلي، يعني أن المستخدم انتقل إلى النشاط مباشرةً دون رؤية الإعلان.
            // قم بتنفيذ التحقق نفسه للزر "How" إذا لزم الأمر.
        }
    }
}
