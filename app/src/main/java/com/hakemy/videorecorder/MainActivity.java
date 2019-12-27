package com.hakemy.videorecorder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;

import com.hakemy.videorecord.recordmain;

public class MainActivity extends AppCompatActivity  {

    VideoView videoView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView =findViewById(R.id.videoView);
        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),  recordmain.class);
                startActivityForResult(intent,0);

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            // A contact was picked.  Here we will just display it
            // to the user.
            Log.e("data1",data.getStringExtra("DATA"));
            videoView.setVideoPath(data.getStringExtra("DATA"));
            videoView.start();
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);

        }
    }
}
