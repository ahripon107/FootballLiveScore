package com.sfuronlabs.livescore.football.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.sfuronlabs.livescore.football.R;

import java.lang.ref.WeakReference;

/**
 * @author Ripon
 */

public class DefaultMessageHandler extends Handler {
    private WeakReference<Context> contextWeakReference;
    private boolean finishActivityOnPostError;
    private ProgressDialog progressDialog;


    public DefaultMessageHandler(Context context, boolean anyProgressDialog) {

        this.contextWeakReference = new WeakReference<>(context);
        this.finishActivityOnPostError = finishActivityOnPostError;

        if (anyProgressDialog) {
            progressDialog = new ProgressDialog(contextWeakReference.get(), R.style.Theme_Football_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.show();
            //progressDialog.setContentView must be called after progressDialog.show()
            progressDialog.setContentView(R.layout.loading_spinner);

        }
    }

    @Override
    public void handleMessage(Message msg) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

        if (msg.what == RequestStatus.SUCCESS) {
            onSuccess(msg);
        } else {
            onFailure(msg);
        }
    }

    public void onSuccess(Message msg) {
        //empty implementation
    }

    public void onFailure(Message msg) {

    }
}

