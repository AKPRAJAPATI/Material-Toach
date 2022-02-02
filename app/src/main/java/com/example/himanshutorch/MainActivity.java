package com.example.himanshutorch;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView bulbImage, switch_off;
    boolean state;
    TextView bulbOff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        bulbOff = findViewById(R.id.bulboff);
        bulbImage = findViewById(R.id.off_image);
        switch_off = findViewById(R.id.off_switch);
        switch_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!state){
                    CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
                    try {
                        String cameraId = cameraManager.getCameraIdList()[0];
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            cameraManager.setTorchMode(cameraId,true);
                        }
                        state = true;
                        bulbOff.setText("ON");
                        bulbImage.setImageResource(R.drawable.on);
                        switch_off.setImageResource(R.drawable.on_img);

                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }else{
                    //This is code for off light
                    CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
                    try {
                        String cameraId = cameraManager.getCameraIdList()[0];
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            cameraManager.setTorchMode(cameraId,false);
                        }
                        state = false;
bulbOff.setText("OFF");
                        bulbImage.setImageResource(R.drawable.off);
                        switch_off.setImageResource(R.drawable.off_img);

                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }
}