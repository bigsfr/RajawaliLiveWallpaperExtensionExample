/**
 * Copyright 2013 Alexis Lecanu
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package fr.ravenfeld.livewallpaper.library.example.renderer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import rajawali.Camera2D;
import rajawali.materials.textures.ATexture.TextureException;
import rajawali.renderer.RajawaliRenderer;
import rajawali.util.RajLog;
import rajawali.wallpaper.Wallpaper;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.MotionEvent;
import fr.ravenfeld.livewallpaper.library.example.R;
import fr.ravenfeld.livewallpaper.library.objects.simple.BackgroundGIFFixed;

public class RendererBackgroundGIFFixed extends RajawaliRenderer implements
		SharedPreferences.OnSharedPreferenceChangeListener {
	private final SharedPreferences mSharedPreferences;

	private BackgroundGIFFixed mBackgroundGIFFixed;

	public RendererBackgroundGIFFixed(Context context) {
		super(context);

		mSharedPreferences = context.getSharedPreferences(
				Wallpaper.SHARED_PREFS_NAME, 0);
		mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	protected void initScene() {
		RajLog.systemInformation();

		Camera2D cam = new Camera2D();
		this.replaceAndSwitchCamera(getCurrentCamera(), cam);
		getCurrentScene().setBackgroundColor(Color.RED);
		getCurrentCamera().setLookAt(0, 0, 0);

		try {
			mBackgroundGIFFixed = new BackgroundGIFFixed("bg", R.drawable.bob);
			mBackgroundGIFFixed.rewind();
		} catch (TextureException e) {
			e.printStackTrace();
		}
		addChild(mBackgroundGIFFixed.getObject3D());

	}

	@Override
	public void onDrawFrame(GL10 glUnused) {
		super.onDrawFrame(glUnused);
		if (mBackgroundGIFFixed != null) {
			try {
				mBackgroundGIFFixed.update();
			} catch (TextureException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		super.onSurfaceCreated(gl, config);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		super.onSurfaceChanged(gl, width, height);
		if (mBackgroundGIFFixed != null) {
			mBackgroundGIFFixed.surfaceChanged(width, height);
		}
	}

	@Override
	public void onVisibilityChanged(boolean visible) {
		super.onVisibilityChanged(visible);

	}

	@Override
	public void onSurfaceDestroyed() {
		super.onSurfaceDestroyed();
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
	}

	@Override
	public void onOffsetsChanged(float xOffset, float yOffset,
			float xOffsetStep, float yOffsetStep, int xPixelOffset,
			int yPixelOffset) {
	}
}
