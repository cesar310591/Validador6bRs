package clases;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class MyReceiver extends BroadcastReceiver {

    DownloadManager my_DowloadManager;
    long tam;
    IntentFilter my_intentfilter;
    private Context my_context;
    private Activity my_activity;

    public MyReceiver(Activity activity){

    }

    @Override
    public void onReceive(Context context, Intent intent) {

    }
}
