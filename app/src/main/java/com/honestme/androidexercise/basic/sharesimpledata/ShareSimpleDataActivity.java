package com.honestme.androidexercise.basic.sharesimpledata;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.honestme.androidexercise.R;

public class ShareSimpleDataActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButtonText;
    private Button mButtonImage;
    private Button mButtonMutiImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_share_simple_data_send_activity);

        mButtonText = (Button)findViewById(R.id.basic_share_simple_data_send_text);
        mButtonImage = (Button)findViewById(R.id.basic_share_simple_data_send_image);
        mButtonMutiImage = (Button)findViewById(R.id.basic_share_simple_data_send_muti_image);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.basic_share_simple_data_send_text:
                Intent intentText = new Intent(Intent.ACTION_SEND);
                intentText.putExtra(Intent.EXTRA_TEXT, "This is a text I sended.");
                intentText.setType("text/plain");
                startActivity(Intent.createChooser(intentText, "please choose a app to accept" +
                        " the text"));
                break;
            case R.id.basic_share_simple_data_send_image:
                Intent intentImage = new Intent(Intent.ACTION_SEND);
                intentImage.setType("image/*");
                intentImage.putExtra(Intent.EXTRA_STREAM, Uri.parse("something"));
                startActivity(Intent.createChooser(intentImage,"please choose a app to accept the image."));
                break;
            case R.id.basic_share_simple_data_send_muti_image:
                Intent intentMultiImage = new Intent(Intent.ACTION_SEND_MULTIPLE);
                intentMultiImage.setType("image/*");
                intentMultiImage.putExtra(Intent.EXTRA_STREAM, Uri.parse("multi image"));
                startActivity(Intent.createChooser(intentMultiImage, "please choose a app to accept the multi images"));
        }
    }
}
