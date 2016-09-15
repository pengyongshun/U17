package com.example.u17.module_home.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.example.u17.R;
import com.example.u17.base_uitls.net_uitls.HttpURLUtils;
import com.example.u17.module_home.activity.AnimationActivity;
import com.example.u17.module_home.activity.RankingActivity;
import com.example.u17.module_home.activity.SearchActivity;
import com.example.u17.module_home.activity.SpecialActivity;
import com.example.u17.module_home.adapter.BoutiqueExpandAdapter;
import com.example.u17.module_home.bean.Boutique;
import com.example.u17.module_home.url.HomeUrl;
import com.example.u17.moudle_search.activity.StatisticsActivity;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends Fragment {
    private static final String TAG = "BoutiqueFragment";
    @BindView(R.id.boutique_refresh_expand_listview)
    PullToRefreshExpandableListView mExpandListView;
    private List<Boutique.DataBean.ReturnDataBean.ComicListsBean> comicLists = new ArrayList<>();
    private Map<String, List<Boutique.DataBean.ReturnDataBean.ComicListsBean.ComicsBean>> dataMap = new HashMap<>();
    private Context mContext;
    private BoutiqueExpandAdapter mBoutiqueExpandAdapter;
    private ExpandableListView listView;
    private HeaderViewHolder headerViewHolder;
    private View headerview;
    public Boutique boutique;
    private List<Boutique.DataBean.ReturnDataBean.GalleryItemsBean> galleryItemsBeanList=new ArrayList<>();


    /**
     * list部分的加载
     *
     * */
    private Handler handler=new Handler(){


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Object obj = msg.obj;
            if (obj instanceof Bundle){
                Bundle bundle= (Bundle) obj;
                boutique = (Boutique) bundle.getSerializable("boutique");
                List<Boutique.DataBean.ReturnDataBean.GalleryItemsBean> galleryItems = boutique.getData().getReturnData().getGalleryItems();
                galleryItemsBeanList.addAll(galleryItems);

                initHeadAdapter();
                List<Boutique.DataBean.ReturnDataBean.ComicListsBean> comicLists1 = boutique.getData().getReturnData().getComicLists();
                Map<String, List<Boutique.DataBean.ReturnDataBean.ComicListsBean.ComicsBean>> data = new HashMap<>();
                int size = comicLists1.size();
                for (int i = 0; i < size; i++) {
                    Boutique.DataBean.ReturnDataBean.ComicListsBean comicListsBean = comicLists1.get(i);
                    String itemTitle = comicListsBean.getItemTitle();
                    List<Boutique.DataBean.ReturnDataBean.ComicListsBean.ComicsBean> comics = comicListsBean.getComics();
                    data.put(itemTitle,comics);
                }
                dataMap.putAll(data);
                comicLists.addAll(comicLists1);
                for (int i = 0; i < comicLists.size(); i++) {
                    listView.expandGroup(i);
                }
                mBoutiqueExpandAdapter.notifyDataSetChanged();

            }
        }
    };


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);
        initProgress();
        initListView();
        initHeadView();
        return view;
    }

    private void initHeadView() {
        headerview = LayoutInflater.from(mContext).inflate(R.layout.boutique_header_view, null);
        headerViewHolder = new HeaderViewHolder(headerview);

    }


    private void initHeadAdapter(){
        if (galleryItemsBeanList!=null){
            headerViewHolder.convenientBanner.setPages(new CBViewHolderCreator<MyBannerHolder>() {
                @Override
                public MyBannerHolder createHolder() {
                    return new MyBannerHolder();
                }
            }, galleryItemsBeanList)
                    .setPageIndicator(new int[]{R.drawable.icon_game_dot_unselected, R.drawable.icon_game_dot_selected})
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
            listView.addHeaderView(headerview);
            mBoutiqueExpandAdapter.notifyDataSetChanged();
        }
    }




    private void initListView() {
        listView = mExpandListView.getRefreshableView();
        mExpandListView.setMode(PullToRefreshBase.Mode.BOTH);
        initListAdapter();
        initListData();
    }


    private void initListAdapter() {
        if (comicLists!=null&&dataMap!=null){
            mBoutiqueExpandAdapter = new BoutiqueExpandAdapter(mContext,comicLists,dataMap);
            listView.setAdapter(mBoutiqueExpandAdapter);

        }

    }

    private void initListData() {
        //开启一个线程去加载数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] bytes = HttpURLUtils.getBytesFromNetWorkGetMethod(HomeUrl.HOME_LIST_URL);
                String json=new String(bytes,0,bytes.length);
                Gson gson=new Gson();
                Boutique boutique = gson.fromJson(json, Boutique.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("boutique",boutique);
                Message message = Message.obtain();
                message.obj=bundle;
                handler.sendMessage(message);
            }
        }).start();
    }

    /**
     * 添加为空的进度
     *
     * **/
    private void initProgress() {
        ProgressBar progressBar = new ProgressBar(mContext);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        progressBar.setLayoutParams(params);
        mExpandListView.setEmptyView(progressBar);
    }

    private class MyBannerHolder implements Holder<Boutique.DataBean.ReturnDataBean.GalleryItemsBean> {
        ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, final Boutique.DataBean.ReturnDataBean.GalleryItemsBean data) {
            String coverUrl = data.getCover();
            Picasso.with(context).load(coverUrl).placeholder(R.drawable.main_recycler_image_default).into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<Boutique.DataBean.ReturnDataBean.GalleryItemsBean.ExtBean> ext = data.getExt();
                    int comicId = 0;
                    for (int i = 0; i < ext.size(); i++) {
                        if (ext.get(i).getKey().equals("comicId")) {
                            comicId = Integer.parseInt(ext.get(i).getVal());
                        }
                    }
                    Intent intent = new Intent(mContext, StatisticsActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt("comicId",comicId);
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                }
            });
        }
    }

    class HeaderViewHolder {
        @BindView(R.id.boutique_header_banner)
        public ConvenientBanner convenientBanner;

        public HeaderViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        @OnClick({R.id.tv_boutique_ranking, R.id.tv_boutique_special,
                R.id.tv_boutique_classify, R.id.tv_boutique_animation})
        public void click(View view) {
            Intent intent = new Intent();
            switch (view.getId()) {
                case R.id.tv_boutique_ranking:
                    intent.setClass(mContext, RankingActivity.class);
                    break;
                case R.id.tv_boutique_special:
                    intent.setClass(mContext, SpecialActivity.class);
                    break;
                case R.id.tv_boutique_classify:
                    intent.setClass(mContext, SearchActivity.class);
                    break;
                case R.id.tv_boutique_animation:
                    intent.setClass(mContext, AnimationActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (headerViewHolder!=null){
            headerViewHolder.convenientBanner.startTurning(3000);
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if (headerViewHolder!=null){
            headerViewHolder.convenientBanner.stopTurning();
        }
    }

}
