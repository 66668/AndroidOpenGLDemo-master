package edu.wuwang.opengl.image.filter;

import android.content.Context;
import android.opengl.GLES20;
import android.util.Log;

/**
 * Created by wuwang on 2016/10/22
 */
public class ColorFilter extends AFilter {

    private static final String  TAG = "AFilter";
    private FilterEnum filter;

    private int hChangeType;
    private int hChangeColor;

    public ColorFilter(Context context, FilterEnum filter) {
        super(context, "filter/half_color_vertex.sh", "filter/half_color_fragment.sh");
        Log.e(TAG,"ColorFilter-ColorFilter");
        this.filter=filter;
    }

    @Override
    public void onDrawFrame() {
        Log.e(TAG,"ColorFilter-onDrawFrame");
        GLES20.glUniform1i(hChangeType,filter.getType());
        GLES20.glUniform3fv(hChangeColor,1,filter.data(),0);
    }

    @Override
    public void onSurfaceCreated(int mProgram) {
        Log.e(TAG,"ColorFilter-onSurfaceCreated");
        hChangeType=GLES20.glGetUniformLocation(mProgram,"vChangeType");
        hChangeColor=GLES20.glGetUniformLocation(mProgram,"vChangeColor");
    }

}
