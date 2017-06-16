package cn.aorise.petition.common;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Yx on 11:36 2016/12/28 12.
 */

public class PublicFunction {
    /**
     * 保存文件
     *
     * @param bm
     * @param fileName
     */
    public static File bmToFile(Bitmap bm, String fileName, Context context) {
        File file = null;
        File dir = context.getCacheDir();
        if (dir != null) {
            file = new File(dir, fileName);
            try {
                FileOutputStream fos = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                bm.compress(Bitmap.CompressFormat.JPEG, 50, bos);
                bos.flush();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }


}
