package edu.wuwang.opengl.image.filter;

/**
 * @author:sjy
 * @date: 2020/11/10
 * Description
 */
public enum FilterEnum {

    NONE(0, new float[]{0.0f, 0.0f, 0.0f}),
    GRAY(1, new float[]{0.299f, 0.587f, 0.114f}),
    COOL(2, new float[]{0.0f, 0.0f, 0.1f}),
    WARM(2, new float[]{0.1f, 0.1f, 0.0f}),
    BLUR(3, new float[]{0.006f, 0.004f, 0.002f}),
    MAGN(4, new float[]{0.0f, 0.0f, 0.4f});


    private int vChangeType;
    private float[] data;

    FilterEnum(int vChangeType, float[] data) {
        this.vChangeType = vChangeType;
        this.data = data;
    }

    public int getType() {
        return vChangeType;
    }

    public float[] data() {
        return data;
    }
}
