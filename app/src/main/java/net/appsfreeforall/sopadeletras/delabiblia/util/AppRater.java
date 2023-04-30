package net.appsfreeforall.sopadeletras.delabiblia.util;

import android.app.Activity;

import net.appsfreeforall.sopadeletras.delabiblia.Config;
import net.appsfreeforall.sopadeletras.delabiblia.dialog.RateDialog;
import net.appsfreeforall.sopadeletras.delabiblia.data.Constants;
import net.appsfreeforall.sopadeletras.delabiblia.data.Settings;

public class AppRater {


    public static void app_launched(Activity mContext) {

        if (Settings.getBooleanValue(mContext, Constants.DONT_SHOW_RATE_DIALOG_AGAIN, false)) { return ; }

        // Increment launch counter
        long launch_count = Settings.getLongValue(mContext, Constants.APP_LAUNCH_COUNT, 0) + 1;
        Settings.saveLongValue(mContext, Constants.APP_LAUNCH_COUNT, launch_count);

        // Get date of first launch
        Long date_firstLaunch = Settings.getLongValue(mContext, Constants.APP_FIRST_LAUNCH, 0);
        if (date_firstLaunch == 0) {
            date_firstLaunch = System.currentTimeMillis();
            Settings.saveLongValue(mContext, Constants.APP_FIRST_LAUNCH, date_firstLaunch);
        }

        // Wait at least n days before opening
        if (launch_count >= Config.numberOfTimesAppShouldOpenBeforeAppRate) {
            if (System.currentTimeMillis() >= date_firstLaunch + (Config.daysBeforeAskingForAppRate * 24 * 60 * 60 * 1000)) {
                RateDialog rd = new RateDialog(mContext);
                if(!mContext.isFinishing())rd.show();
            }
        }

    }

}