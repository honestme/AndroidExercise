package com.honestme.androidexercise.IPC;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;

import com.honestme.androidexercise.Constant;

/**
 * Created by zhangconglin on 2015/12/28.
 */
public class MessengerClient extends Activity {
    private final String TAG = getClass().getSimpleName();

    private Messenger mMessenger;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMessenger = new Messenger(service);
            Message message = Message.obtain(null,Constant.MESSAGE_FROM_CLIENT);
            Bundle bundle = new Bundle();
            bundle.putString("msg", "This is a greeting from client");
            try{
                message.setData(bundle);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this,MessengerClient.class);
        bindService(intent,mServiceConnection,0);
    }

    @Override
    protected void onDestroy() {
        unbindService(mServiceConnection);
        super.onDestroy();
    }
}
