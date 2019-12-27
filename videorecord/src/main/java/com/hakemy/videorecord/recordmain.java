package com.hakemy.videorecord;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;

public class recordmain extends AppCompatActivity  implements View.OnClickListener {
    private int mCameraid;
    private CameraPreview mPreview;
    private ImageView cameraswitch,record;
    private boolean Front =false;
    private FrameLayout preview;
    private long lastDown;
    private long lastDuration;
    private TextView time;
    private CountDownTimer countDownTimer;
    private ProgressBar progressBar;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.videorecord);
        cameraswitch =findViewById(R.id.cameraswitch);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        record =findViewById(R.id.record);
        time=findViewById(R.id.time);
        cameraswitch.setOnClickListener(this);


        // Create an instance of Camera
        if (Camera.getNumberOfCameras() >= 2) {

            //if you want to use the back facing camera
            mCameraid = Camera.CameraInfo.CAMERA_FACING_BACK;
        }




        mPreview=null;
        mPreview = new CameraPreview(this, mCameraid, recordmain.this);
        preview=null;
        preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);



        record.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        time.setVisibility(View.VISIBLE);
                        lastDown = System.currentTimeMillis();
                        final int[] count = {0};
                        countDownTimer=new CountDownTimer(System.currentTimeMillis()+1000, 1000) {

                            public void onTick(long millisUntilFinished) {

                                count[0]++;
                                progressBar.setVisibility(View.VISIBLE);
                                time.setText(String.valueOf(count[0]));
                            }

                            public void onFinish() {

                            }

                        }.start();
                         cameraswitch.setVisibility(View.INVISIBLE);

                        mPreview.StartRecord();


                        break;

                    case MotionEvent.ACTION_UP:
                        cameraswitch.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                        time.setVisibility(View.INVISIBLE);
                        countDownTimer.cancel();
                        lastDuration = System.currentTimeMillis() - lastDown;
                        if(lastDuration>=2000) {
                            File file = null;
                            try {
                                file = mPreview.StopRecord();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            Log.e("path", file.getAbsolutePath());

                            Intent intent = new Intent();
                            intent.putExtra("DATA", file.getAbsolutePath());
                            setResult(Activity.RESULT_OK,intent);
                            onBackPressed();

                        }
                        else
                        {
                            mPreview.releaseMediaRecorder();
                        }

                        break;
                }

                return true;
            }
        });





    }



    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.cameraswitch) {
            if (Front) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.rotate);

                cameraswitch.setAnimation(animation);
                mCameraid = Camera.CameraInfo.CAMERA_FACING_BACK;
                mPreview.releaseCamera();

                mPreview=null;
                mPreview = new CameraPreview(this, mCameraid, recordmain.this);
                preview=null;
                preview = (FrameLayout) findViewById(R.id.camera_preview);
                preview.addView(mPreview);
                Front = false;
            } else {

                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.rotate);
                cameraswitch.setAnimation(animation);

                mCameraid = Camera.CameraInfo.CAMERA_FACING_FRONT;
                mPreview.releaseCamera();

                mPreview=null;
                mPreview = new CameraPreview(this, mCameraid, recordmain.this);
                preview=null;
                preview = (FrameLayout) findViewById(R.id.camera_preview);
                preview.addView(mPreview);

                Front = true;
            }
        }
    }

    @Override
    public void onBackPressed() {
        mPreview.releaseCamera();
        mPreview.releaseMediaRecorder();
        preview=null;
        progressBar=null;
        countDownTimer=null;
        time=null;
        cameraswitch=null;
        super.onBackPressed();
    }
}
