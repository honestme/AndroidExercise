package com.honestme.androidexercise.IPC;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;

import com.honestme.androidexercise.Constant;
import com.honestme.androidexercise.util.LogUtil;

/**
 * Created by zhangconglin on 2015/12/28.
 */
public class MessengerServer extends Service {
    private final String TAG = getClass().getSimpleName();

    private class MessengerHandler extends android.os.Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constant.MESSAGE_FROM_CLIENT:
                    Bundle bundle = msg.getData();
                    String message = bundle.getString("msg");
                    LogUtil.d(TAG, message);
                    break;
                default:
                    super.handleMessage(msg);
            }

        }
    }

    private Messenger mMessenger = new Messenger(new MessengerHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
