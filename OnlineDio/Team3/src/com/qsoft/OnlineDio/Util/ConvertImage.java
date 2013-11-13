package com.qsoft.OnlineDio.Util;

import android.graphics.*;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 11/8/13
 * Time: 10:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class ConvertImage
{
    public Bitmap resizeBitMap(Bitmap bitmap)
    {
        Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setShader(shader);
        Canvas c = new Canvas(circleBitmap);
        c.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint);
        return circleBitmap;
    }

}
