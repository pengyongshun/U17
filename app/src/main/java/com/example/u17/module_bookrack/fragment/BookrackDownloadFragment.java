package com.example.u17.module_bookrack.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.app.Fragment;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.u17.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author:king1033
 * @Desc:
 * @Time:2016/9/12
 */
public class BookrackDownloadFragment extends Fragment{
    //进度条
    @BindView(R.id.progressbar_sd_available)
    ProgressBar mProgressBar;
    //使用容量
    @BindView(R.id.bookrack_sd_used_size)
    TextView mSDUsedSize;
    //可用容量
    @BindView(R.id.bookrack_sd_available_size)
    TextView mSDAvailableSize;
    //ListView
    @BindView(R.id.bookrack_list_view_download)
    ListView mDownloadListView;
    //空视图
    @BindView(R.id.bookrack_download_empty)
    RelativeLayout mDownloadEmpty;



    private Context context;

    public static BookrackDownloadFragment newInstance() {
        BookrackDownloadFragment fragment = new BookrackDownloadFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_bookrack_download, container, false);
        ButterKnife.bind(this,view);
        getSdSize();
        //添加空视图
        mDownloadListView.setEmptyView(mDownloadEmpty);
        return view;
    }

    //获取SD卡信息
    private void getSdSize() {
        //获得SD卡空间的信息
        File path= Environment.getExternalStorageDirectory();
        StatFs statFs=new StatFs(path.getPath());
        long blockSize=statFs.getBlockSize();
        long totalBlocks=statFs.getBlockCount();
        long availableBlocks=statFs.getAvailableBlocks();
        long usedBlocks = totalBlocks-availableBlocks;

        //计算SD卡的空间大小
        long totalSize=totalBlocks*blockSize;
        long availableSize=availableBlocks*blockSize;
        long usedSize =usedBlocks*blockSize;
        int progress = (int) (usedSize*100/totalSize);
        //转化为可以显示的字符串
        String usedSizeStr= Formatter.formatFileSize(context, usedSize);
        String totalSizeStr= Formatter.formatFileSize(context, totalSize);
        String availableSizeStr=Formatter.formatFileSize(context, availableSize);
        mSDUsedSize.setText("已占用"+usedSizeStr);
        mSDAvailableSize.setText("剩余空间"+availableSizeStr);
        mProgressBar.setProgress(progress);
    }
}
