package com.honestme.androidexercise.basic.sharesimpledata;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.honestme.androidexercise.R;

public class AcceptSimpleDataActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private Context mContext = AcceptSimpleDataActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_share_simple_data_accept_activity);


    }

    private class HandlerDataTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... params) {
            Intent intentAccept = getIntent();
            Uri uri = intentAccept.getData();
            String type = intentAccept.getType();
            String scheme = intentAccept.getScheme();

            if(intentAccept.getAction().equals(Intent.ACTION_SEND) && type != null){
                if(type.equals("text/plain")){
                    handleText(intentAccept);
                }else if(type.startsWith("image/")){
                    if(scheme.equals("http")){
                        handleHttpImage(intentAccept);
                    }else {
                        handleImage(intentAccept);
                    }

                }


            }else if(intentAccept.getAction().equals(Intent.ACTION_SEND_MULTIPLE) && type != null){
                if(type.startsWith("image/")){
                    handleMultiImage(intentAccept);
                }
            }else {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

    }

    private void handleText(Intent intent){
        Toast.makeText(mContext, intent.getStringExtra(Intent.EXTRA_TEXT),Toast.LENGTH_SHORT).show();
    }

    private void handleImage(Intent intent){
        Toast.makeText(mContext,intent.getParcelableExtra(Intent.EXTRA_STREAM).toString(),Toast.LENGTH_SHORT).show();
    }

    private void handleMultiImage(Intent intent){
        Toast.makeText(mContext, intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM).toString(),Toast.LENGTH_SHORT).show();
    }

    private void handleHttpImage(Intent intent){
        Toast.makeText(mContext,intent.getData().toString(),Toast.LENGTH_SHORT).show();
    }
}
