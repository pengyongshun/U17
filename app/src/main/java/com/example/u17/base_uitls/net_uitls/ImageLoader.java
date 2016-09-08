package com.example.u17.base_uitls.net_uitls;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageLoader {

    private static ImageLoader imageLoader;
    //线程池
    private static ExecutorService executorService;
    public static ImageLoader init(Context context) {
        executorService = Executors.newFixedThreadPool(4);
        DiskLruCacheTool.init(context);
        if (imageLoader == null) {
            imageLoader = new ImageLoader();
        }
        return imageLoader;
    }
    //入口:开始加载图片
    public void load(String url,ImageView imageView) {
        //加载内存缓存
        Bitmap memoryCahche = MemoryCacheTool.readMemoryCahche(url);
        if (memoryCahche != null) {
            imageView.setImageBitmap(memoryCahche);
        } else {
            //开启线程
            executorService.execute(new ImageThread(url,imageView));
        }
        DiskLruCacheTool.flush();
    }

}
