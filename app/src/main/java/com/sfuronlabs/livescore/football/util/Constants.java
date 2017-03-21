package com.sfuronlabs.livescore.football.util;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.sfuronlabs.livescore.football.R;

/**
 * @author Ripon
 */

public class Constants {

    public static final String TAG = "ripon";
    public static FirebaseRemoteConfig remoteConfig;

    public static final String ONE_PLUS_TEST_DEVICE = "7D3F3DF2A7214E839DBE70BE2132D5B9";
    //public static final String XIAOMI_TEST_DEVICE = "EE613118FFB77F457D6DBDAC46C3658C";
    public static final String XIAOMI_TEST_DEVICE = "F5C90B562D482744906FD29363CF595C";
    public static boolean showImage = false;
    public static boolean showPopupMessage = false;
    public static String popupMessage = "";
    public static String playStoreUrl = "";
    public static String versions = "bchbhdcd";
    public static String positiveButton = "yes";
    public static String negativeButton = "no";
    public static boolean linkAvailable = false;

    public static FirebaseRemoteConfig getConfig() {
        remoteConfig = FirebaseRemoteConfig.getInstance();

        remoteConfig.setConfigSettings(new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(true)
                .build());

        remoteConfig.setDefaults(R.xml.remote_config_default);


        final Task<Void> fetch = remoteConfig.fetch(0);

        fetch.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                remoteConfig.activateFetched();
                setRemoteConfig();
            }
        });

        setRemoteConfig();

        return remoteConfig;
    }

    private static void setRemoteConfig(){
        //should be network call
        //onSuccess
        showImage = remoteConfig.getBoolean("show_image");
        showPopupMessage = remoteConfig.getBoolean("show_popup");
        popupMessage = remoteConfig.getString("popup_message");
        playStoreUrl = remoteConfig.getString("play_store_url");
        versions = remoteConfig.getString("versions");
        positiveButton = remoteConfig.getString("positive_button");
        negativeButton = remoteConfig.getString("negative_button");
        linkAvailable = remoteConfig.getBoolean("link_available");
    }
}
