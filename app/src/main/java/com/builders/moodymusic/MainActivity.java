package com.builders.moodymusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.PictureResult;
import com.otaliastudios.cameraview.controls.Facing;
import com.otaliastudios.cameraview.controls.Mode;
import com.otaliastudios.cameraview.frame.Frame;
import com.otaliastudios.cameraview.frame.FrameProcessor;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    int CAMERA_PERMISSION = 1004;
    boolean isCameraFacingFront = true;

    private CameraView cameraView;
    private RelativeLayout clickImageRL;
    private RelativeLayout changeCameraFaceRL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        checkForPermission();
    }

    private void checkForPermission() {
        if (hasPermission()) {
            setUpCamera();
            setOnClicks();
        } else {
            String permission[] = {Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
            requestPermissions(permission, CAMERA_PERMISSION);
        }
    }

    private boolean hasPermission() {
        boolean isPermissionGranted = false;
        if (
                checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                        checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
        ) {
            isPermissionGranted = true;
        }
        return isPermissionGranted;
    }

    private void setUpCamera() {
        cameraView = findViewById(R.id.camera_view);
        cameraView.setMode(Mode.PICTURE);
        cameraView.setLifecycleOwner(this);
        cameraView.setFacing(Facing.FRONT);
        cameraView.addCameraListener(new CameraListener() {
            @Override
            public void onPictureTaken(@NonNull @NotNull PictureResult result) {
                super.onPictureTaken(result);
            }
        });
    }

    private void setOnClicks() {
        changeCameraFaceRL = findViewById(R.id.change_camera_face_rl);
        clickImageRL = findViewById(R.id.click_image_rl);

        changeCameraFaceRL.setOnClickListener(this);
        clickImageRL.setOnClickListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION) {
            if (hasPermission()) {
                setUpCamera();
            } else {
                AppController.showToast(getResources().getString(R.string.permission_not_allowed));
                finish();
            }
        }
    }

    private void changeCameraFacing() {
        isCameraFacingFront = !isCameraFacingFront;
        cameraView.setFacing(isCameraFacingFront ? Facing.FRONT : Facing.BACK);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_camera_face_rl: {
                changeCameraFacing();
            }break;
            case R.id.click_image_rl: {
                cameraView.takePicture();
            }break;
        }
    }
}