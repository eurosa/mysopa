package net.appsfreeforall.sopadeletras.delabiblia.util;

import android.app.Activity;
import android.util.Log;

import com.google.ads.consent.ConsentForm;
import com.google.ads.consent.ConsentFormListener;
import com.google.ads.consent.ConsentInfoUpdateListener;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;

import net.appsfreeforall.sopadeletras.delabiblia.R;

import java.net.MalformedURLException;
import java.net.URL;

public class Consent {

    public static int consentPreference = 0;

    private static ConsentForm form;

    public static void checkContentStatus(final Activity context){
        //ConsentInformation.getInstance(context).addTestDevice("1F474434B1229D7A3963E1B2DEB4E609");
        //ConsentInformation.getInstance(context).setDebugGeography(DebugGeography.DEBUG_GEOGRAPHY_EEA);

        ConsentInformation consentInformation = ConsentInformation.getInstance(context);
        String[] publisherIds = {context.getResources().getString(R.string.admob_publisher_id)};//https://support.google.com/admob/answer/2784578
        consentInformation.requestConsentInfoUpdate(publisherIds, new ConsentInfoUpdateListener() {
            @Override
            public void onConsentInfoUpdated(ConsentStatus consentStatus) {
                // User's consent status successfully updated.
                if(ConsentInformation.getInstance(context).isRequestLocationInEeaOrUnknown()){
                    if(consentStatus == ConsentStatus.UNKNOWN){
                        showConsentForm(context);
                    }else if(consentStatus == ConsentStatus.NON_PERSONALIZED){
                        consentPreference = 1;
                    }else if(consentStatus == ConsentStatus.PERSONALIZED){
                        consentPreference = 0;
                    }
                }
            }

            @Override
            public void onFailedToUpdateConsentInfo(String errorDescription) {
                // User's consent status failed to update.
            }
        });
    }


    public static void showConsentForm(Activity context){

        URL privacyUrl = null;

        try {
            // TODO: Replace with your app's privacy policy URL.
            privacyUrl = new URL("http://umitgoren.github.io/privacy_policy.html");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            // Handle error.
        }



        form = new ConsentForm.Builder(context, privacyUrl)
                .withListener(new ConsentFormListener() {
                    @Override
                    public void onConsentFormLoaded() {
                        // Consent form loaded successfully.
                        form.show();
                    }

                    @Override
                    public void onConsentFormOpened() {
                        // Consent form was displayed.
                    }

                    @Override
                    public void onConsentFormClosed(ConsentStatus consentStatus, Boolean userPrefersAdFree) {
                        // Consent form was closed.
                        if(consentStatus == ConsentStatus.NON_PERSONALIZED){
                            consentPreference = 1;
                        }
                    }

                    @Override
                    public void onConsentFormError(String errorDescription) {
                        // Consent form error.
                        Log.d("consent", errorDescription);
                    }
                })
                .withPersonalizedAdsOption()
                .withNonPersonalizedAdsOption()
                //.withAdFreeOption()
                .build();


                form.load();

    }
}
