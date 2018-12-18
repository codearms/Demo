package com.codearms.maoqiqi.skin;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {

    private static String getCacheDir(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File cacheDir = context.getExternalCacheDir();
            if (cacheDir != null && (cacheDir.exists() || cacheDir.mkdirs())) {
                return cacheDir.getAbsolutePath();
            }
        }
        return context.getCacheDir().getAbsolutePath();
    }

    public static String copySkinFromAssets(Context context, String assetsFilePath) {
        String cacheDir = getCacheDir(context);
        String skinCachePath = new File(cacheDir, File.separator + assetsFilePath).getAbsolutePath();

        int index = assetsFilePath.lastIndexOf(File.separator);
        if (index != -1) {
            String skinCacheDir = cacheDir + File.separator + assetsFilePath.substring(0, index);
            File skinCacheDirFile = new File(skinCacheDir);
            if (!skinCacheDirFile.exists()) {
                boolean flag = skinCacheDirFile.mkdirs();
                if (!flag) return null;
            }
        }

        try {
            InputStream is = context.getAssets().open(assetsFilePath);
            OutputStream os = new FileOutputStream(skinCachePath);
            int byteCount;
            byte[] bytes = new byte[1024];
            while ((byteCount = is.read(bytes)) != -1) {
                os.write(bytes, 0, byteCount);
            }
            os.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return skinCachePath;
    }
}