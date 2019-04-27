package com.muse.h.muse;

import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.ar.core.ArCoreApk;
import com.google.ar.core.Session;
import com.google.ar.core.exceptions.UnavailableApkTooOldException;
import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException;
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException;
import com.google.ar.core.exceptions.UnavailableSdkTooOldException;
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException;
import android.view.GestureDetector;
import android.view.MotionEvent;
import com.google.ar.sceneform.ArSceneView;
import com.google.ar.sceneform.HitTestResult;
import com.google.ar.sceneform.Node;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.core.Frame;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.core.Session;
import com.google.ar.core.Trackable;
import com.google.ar.core.TrackingState;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.Color;
import com.google.ar.sceneform.rendering.MaterialFactory;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ShapeFactory;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.ar.core.exceptions.CameraNotAvailableException;
import com.google.ar.core.exceptions.UnavailableException;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import com.muse.h.muse.common.CameraPermission;
import com.muse.h.muse.ui.ar.ArFragment;
import com.muse.h.muse.ui.ar.DemoUtils;

import android.util.Log;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Context;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.Sensor;
import android.location.LocationManager;
import android.location.LocationListener;
import android.location.Location;
import org.w3c.dom.Text;



public class ARActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = ARActivity.class.getSimpleName();

    // ========================================================================
    // Primitive Declarations
    // ========================================================================

    // ========================================================================
    // AR Declarations
    // ========================================================================

    BaseArFragment baseArFragment;
    ArSceneView arSceneView;
    private ModelRenderable navLine1Renderable;
    private ModelRenderable navLine2Renderable;
    private ModelRenderable navLine3Renderable;
    private ModelRenderable navLine4Renderable;

    // ========================================================================
    // Camera Declarations
    // ========================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ar_activity);

        // Identify AR environments
        baseArFragment = (BaseArFragment)getSupportFragmentManager().findFragmentById(R.id.sceneforem_ux_fragment);
        arSceneView = findViewById(R.id.sceneform_ar_scene_view);

        setupModel();

        baseArFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
                Anchor anchor = hitResult.createAnchor();
                AnchorNode anchorNode = new AnchorNode(anchor);

                anchorNode.setParent(baseArFragment.getArSceneView().getScene());
                createModel(anchorNode);
            }
        });

    }


    private void setupModel() {
        ModelRenderable.builder()
                .setSource(this, R.raw.line)
                .build().thenAccept(renderable -> navLine1Renderable = renderable)
                .exceptionally(throwable -> {
                            Log.e(TAG, "Unable to load line one.", throwable);
                            return null;
                        }
                );
    }

    private void createModel(AnchorNode anchorNode) {
        TransformableNode navLine1 = new TransformableNode(baseArFragment.getTransformationSystem());
        navLine1.setParent(anchorNode);
        navLine1.setRenderable(navLine1Renderable);
        navLine1.select();
    }

    @Override
    public void onClick(View v) {

    }
}
