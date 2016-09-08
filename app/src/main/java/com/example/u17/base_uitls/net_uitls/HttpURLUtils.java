package com.example.u17.base_uitls.net_uitls;

import android.support.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 彭永顺 on 2016/8/6.
 */
public class HttpURLUtils {
    /***
     * get请求
     * **/
    @Nullable
    public static byte[] getBytesFromNetWorkGetMethod(String urlPath) {
        InputStream is=null;
        ByteArrayOutputStream baos=null;
        URL url= null;
        try {
            url = new URL(urlPath);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(3000);
            urlConnection.setReadTimeout(3000);
            urlConnection.connect();
            if (urlConnection.getResponseCode()==HttpURLConnection.HTTP_OK){
                is=urlConnection.getInputStream();
                baos=new ByteArrayOutputStream();
                byte[] buffer=new byte[1024];
                int leng=0;
                while ((leng=is.read(buffer))!=-1){
                    baos.write(buffer,0,leng);
                    baos.flush();
                }

                is.close();
                byte[] byteArray = baos.toByteArray();
                return byteArray;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeStream(is);
            closeStream(baos);
        }

        return null;
    }
    /***
     * post请求
     *
     * **/
    @Nullable
    public static byte[] getBytesFromNetWorkPostMethod(String urlPath, String key, String value){
        InputStream is=null;
        ByteArrayOutputStream baos=null;
        try {
            URL url=new URL(urlPath);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(3000);
            urlConnection.setReadTimeout(3000);
            urlConnection.setDoInput(true);
            urlConnection.setRequestMethod("POST");
            //写入参数
            urlConnection.getOutputStream().write((key+value).getBytes());
            urlConnection.connect();
            if (urlConnection.getResponseCode()==HttpURLConnection.HTTP_OK){
                is = urlConnection.getInputStream();
                baos=new ByteArrayOutputStream();
                byte[] buffer=new byte[1024];
                int len=0;
                while ((len=is.read(buffer))!=-1){
                    baos.write(buffer,0,len);
                }
                baos.flush();
                byte[] byteArray = baos.toByteArray();
                return byteArray;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeStream(is);
            closeStream(baos);
        }
        return null;
    }


    /***
     *
     * 关闭流
     * **/
    private static void closeStream(Closeable closeable){
        if (closeable!=null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
