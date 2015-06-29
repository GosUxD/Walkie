package com.example.buddy;

import java.util.List;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

public class CameraFragment extends Fragment {

	private Camera mCamera;
	private SurfaceView mSurface;

	@SuppressWarnings("deprecation")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.surface_fragment, container, false);

		mSurface = (SurfaceView) v.findViewById(R.id.camera_Surface);
		SurfaceHolder holder = mSurface.getHolder();
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		holder.addCallback(new SurfaceHolder.Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				if (mCamera != null) {
					mCamera.stopPreview();
				}

			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				try {
					if (mCamera != null)
						mCamera.setPreviewDisplay(holder);
				} catch (Exception e) {
					Log.d("CameraFragment", "Cannot start preview", e);
				}
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				// TODO Auto-generated method stub
				if (mCamera == null)
					return;
				Camera.Parameters parameters = mCamera.getParameters();
				Size s = getBestSize(parameters.getSupportedPreviewSizes(),
						width, height);
				mCamera.setParameters(parameters);
				try {
					mCamera.setDisplayOrientation(90);
					mCamera.startPreview();
				} catch (Exception e) {
					Log.d("CameraFragment", "Cannot start preview", e);
					mCamera.release();
					mCamera = null;
				}

			}
		});

		return v;
	}

	private Size getBestSize(List<Size> sizes, int width, int height) {
		Size bestSize = sizes.get(0);
		int LargestArea = bestSize.width * bestSize.height;
		for (Size s : sizes) {
			int area = s.width * s.height;
			if (area > LargestArea) {
				bestSize = s;
				LargestArea = area;
			}
		}
		return bestSize;
	}

	@TargetApi(9)
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			mCamera = Camera.open(0);
		} else {
			mCamera = Camera.open();
		}
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (mCamera != null) {
			mCamera.release();
			mCamera = null;
		}
	}
	

}
