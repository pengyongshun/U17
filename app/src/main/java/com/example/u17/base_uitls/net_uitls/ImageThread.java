package com.example.u17.base_uitls.net_uitls;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by yangjw on 2016/8/5.
 */
public class ImageThread implements Runnable {

    private String path;
    private ImageView mImageView;

    public ImageThread(String path, ImageView mImageView) {
        this.path = path;
        this.mImageView = mImageView;
        this.mImageView.setTag(path);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Object obj = msg.obj;
            if (obj instanceof Bitmap) {
                Bitmap bitmap = (Bitmap) obj;
                if (path.equals(mImageView.getTag())) {
                    mImageView.setImageBitmap(bitmap);
                }
            }
        }
    };

    @Override
    public void run() {
        //读取磁盘缓存
        Bitmap diskBitmap = DiskLruCacheTool.readBitmapDiskCache(path);
        if (diskBitmap != null) {
            MemoryCacheTool.saveMemoryCache(path,diskBitmap);
            Message message = mHandler.obtainMessage();
            message.obj = diskBitmap;
            mHandler.sendMessage(message);
            return;
        }

        //网络请求加载图片
        InputStream inputStream = null;
        ByteArrayOutputStream baos = null;
        try {
            URL url = new URL(path);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                int len = 0;
                byte[] buffer = new byte[1024];
                baos = new ByteArrayOutputStream();
                while((len = inputStream.read(buffer)) != -1) {
                    baos.write(buffer,0,len);
                }
                baos.flush();

                //流转换成Bitmap对象
                byte[] bytes = baos.toByteArray();

                //------------二次采样-------------
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);
                int outHeight = options.outHeight;
                int outWidth = options.outWidth;
                int ratio = outHeight/100;
                if (ratio >= 1) {
                    options.inSampleSize = ratio;
                }
                //----------------------
                options.inJustDecodeBounds = false;
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length,options);
                DiskLruCacheTool.saveBitmapDiskCache(path,bitmap);
                Message message = mHandler.obtainMessage();
                message.obj = bitmap;
                mHandler.sendMessage(message);

            }

            close(inputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(inputStream);
            close(baos);
        }
    }

    private void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
