package com.qsoft.OnlineDio.Util;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 11/7/13
 * Time: 10:24 AM
 * To change this template use File | Settings | File Templates.
 */

import java.io.InputStream;
import java.io.OutputStream;

public class Utils
{
    public static void CopyStream(InputStream is, OutputStream os)
    {
        final int buffer_size = 1024;
        try
        {
            byte[] bytes = new byte[buffer_size];
            for (; ; )
            {
                int count = is.read(bytes, 0, buffer_size);
                if (count == -1)
                {
                    break;
                }
                os.write(bytes, 0, count);
            }
        }
        catch (Exception ex)
        {
        }
    }
}