/*
 *
 * SGLRender.java
 *
 * Created by Wuwang on 2016/10/15
 */
package edu.wuwang.opengl.image;

import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.View;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import edu.wuwang.opengl.image.filter.AFilter;
import edu.wuwang.opengl.image.filter.ColorFilter;
import edu.wuwang.opengl.image.filter.FilterEnum;

/**
 * Description:绘图基类，设置新图形后，在这里初始化新图行的绘制。
 */
public class SGLRender implements GLSurfaceView.Renderer {

    private static final String TAG = "SGLRender";
    private AFilter mFilter;
    private Bitmap bitmap;
    private int width, height;
    private boolean refreshFlag = false;
    private EGLConfig config;

    public SGLRender(View mView) {
        mFilter = new ColorFilter(mView.getContext(), FilterEnum.NONE);
    }

    public void setFilter(AFilter filter) {
        refreshFlag = true;
        mFilter = filter;
        if (bitmap != null) {
            mFilter.setBitmap(bitmap);
        }
    }

    public void setImageBuffer(int[] buffer, int width, int height) {
        bitmap = Bitmap.createBitmap(buffer, width, height, Bitmap.Config.RGB_565);
        mFilter.setBitmap(bitmap);
    }

    public void refresh() {
        refreshFlag = true;
    }

    public AFilter getFilter() {
        return mFilter;
    }

    public void setImage(Bitmap bitmap) {
        this.bitmap = bitmap;
        mFilter.setBitmap(bitmap);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Log.e(TAG, "onSurfaceCreated");
        this.config = config;
        mFilter.onSurfaceCreated(gl, config);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Log.e(TAG, "onSurfaceChanged");
        this.width = width;
        this.height = height;
        mFilter.onSurfaceChanged(gl, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        Log.e(TAG, "onDrawFrame");
        if (refreshFlag && width != 0 && height != 0) {
            mFilter.onSurfaceCreated(gl, config);
            mFilter.onSurfaceChanged(gl, width, height);
            refreshFlag = false;
        }
        mFilter.onDrawFrame(gl);
    }
}
