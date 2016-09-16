package com.example.u17.module_login.dao;



/**
 * @Author:king1033
 * @Desc:
 * @Time:2016/9/16
 */
public abstract class BaseListener<T> {
    public abstract void  onError(Exception e);
    public abstract void  onSuccess(T result);
    public abstract void  onStringResult(String result);
    public abstract void  onDownloadFinish(String path);//下载完成
    public abstract void  onDownloadProgress(int progress);//下载进度
}
