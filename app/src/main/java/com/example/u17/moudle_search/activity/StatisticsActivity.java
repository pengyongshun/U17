package com.example.u17.moudle_search.activity;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.u17.R;
import com.example.u17.base_ascytask.StatisticsBottomAscytask;
import com.example.u17.base_bean.StatisticsBottomBean;
import com.example.u17.base_http.BaseUrl;
import com.example.u17.base_uitls.log_uitls.LogUtils;
import com.example.u17.module_home.activity.AllCateLogActivity;
import com.example.u17.module_login.activity.LoginActivity;
import com.example.u17.moudle_search.ascytask.SerachStatisticMoreAscytask;
import com.example.u17.moudle_search.ascytask.SerachStatisticTicketAscytask;
import com.example.u17.moudle_search.bean.SerachStatisticMoreBean;
import com.example.u17.moudle_search.bean.SerachStatisticsTicketBean;
import com.example.u17.moudle_search.bean.StatisticsBean;
import com.example.u17.moudle_search.url.SerachUrl;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatisticsActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.activity_statistics_head_iv_back)
    public ImageView ivBack;
    @BindView(R.id.activity_statistics_head_iv_icon)
    public ImageView ivIcon;
    @BindView(R.id.activity_statistics_tv_four)
    public TextView tvToalTicket;
    @BindView(R.id.activity_statistics_tv_two)
    public TextView tvTicketTapy;
    @BindView(R.id.activity_statistics_head_tv_ticke)
    public TextView tvMothTicket;
    @BindView(R.id.activity_statistics_head_tv_comment)
    public TextView tvComment;
    @BindView(R.id.activity_statistics_tv_one)
    public TextView tvTicketName;
    @BindView(R.id.activity_statistics_tv_three)
    public TextView tvAuthorName;
    @BindView(R.id.activity_statistics_tv_six)
    public TextView tvMore;
    @BindView(R.id.activity_statistics_tv_five)
    public TextView tvType;
    @BindView(R.id.activity_statistics_bottom_rv_like)
    public RecyclerView mRecyclerView;
    private String cover;
    private List<StatisticsBottomBean> returnDataBeens=new ArrayList<>();
    private StatisticsBottomAdapter mStatisticsBottomAdapter;
    private int width;
    private String commentCount;
    private String month_ticket;
    private double total_click;
    private int total_tucao;
    private String authorName;
    private String short_description;
    private List<String> theme_ids;
    private String avatar;
    private String cate_id;
    private String last_update_week;
    private String description;
    private int favorite_total;
    private List<SerachStatisticMoreBean.OtherWorksBean> otherWorks;
    private String url;
    private String mComicId;
    private PopupWindow popupWindow;
    private PopupWindow sharePopupWindow;
    private int comicId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        ButterKnife.bind(this);
        //平分屏幕
        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth() / 3;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        //从serachFragment
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
         comicId = bundle.getInt("comicId");
        loadHeadData(comicId);
        initBotom();
    }

    private void initBotom() {
        //加载RecyclerView
        loadBottomData();
    }

    private void loadBottomData() {
        new StatisticsBottomAscytask(new StatisticsBottomAscytask.BaseSBCallBack() {
            @Override
            public void callBack(List<StatisticsBottomBean> statisticsBottomBean) {
                if (statisticsBottomBean==null){
                    return;
                }
                returnDataBeens.addAll(statisticsBottomBean);
                mStatisticsBottomAdapter = new StatisticsBottomAdapter();
                mRecyclerView.setAdapter(mStatisticsBottomAdapter);
            }
        }).execute(BaseUrl.STATISTICS_LIKE_URL);
    }

    /**
     * 创建一个RecyclerView的适配器
     * **/
    class StatisticsViewHodler extends RecyclerView.ViewHolder{
           ImageView imageView;
           TextView textView;
        public StatisticsViewHodler(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.item_fragment_statistics_more_detial_gridview_iv_icon);
            textView= (TextView) itemView.findViewById(R.id.item_fragment_statistics_more_detial_gridview_tv_name);

        }
    }
    //这个类类似于baseAdapter的getView中的ViewHoder
    class StatisticsBottomAdapter extends RecyclerView.Adapter<StatisticsViewHodler>{

        //类似于baseAdapter的getView中的初次  复用
        @Override
        public StatisticsViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(StatisticsActivity.this).inflate(R.layout.item_fragment_statistics_more_detial_gridview,parent,false);
            view.setLayoutParams(new RecyclerView.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new StatisticsViewHodler(view);
        }

        //类似于baseAdapter的getView中的更新UI
        @Override
        public void onBindViewHolder(final StatisticsViewHodler holder, int position) {
            final StatisticsBottomBean returnDataBean = returnDataBeens.get(position);
            holder.imageView.setTag(returnDataBean);
            String cover = returnDataBean.getCover();
            String name = returnDataBean.getName();
            String comic_id = returnDataBean.getComic_id();
            holder.imageView.setTag(comic_id);
            holder.textView.setText(name);
            Picasso.with(StatisticsActivity.this).load(cover).into(holder.imageView);
            //对每一条目进行监听
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tag = (String) v.getTag();
                    int id = Integer.parseInt(tag);
                    loadHeadData(id);
                }
            });
        }

        @Override
        public int getItemCount() {
            return returnDataBeens==null?0:returnDataBeens.size();
        }
    }
//...........................................................................
    private void loadHeadData(int comicId) {
        if (comicId==0){
            return;
        }
       //加载ticket部分
        new SerachStatisticTicketAscytask(new SerachStatisticTicketAscytask.SSCallBack() {


            @Override
            public void callBack(SerachStatisticsTicketBean serachStatisticsTicketBean) {
                //更新UI
                if (serachStatisticsTicketBean==null){
                    return;
                }
                favorite_total = serachStatisticsTicketBean.getFavorite_total();
                commentCount = serachStatisticsTicketBean.getCommentCount();
                 month_ticket = serachStatisticsTicketBean.getMonth_ticket();
                 total_click = serachStatisticsTicketBean.getTotal_click();
                total_tucao = serachStatisticsTicketBean.getTotal_tucao();
                tvToalTicket.setText("点击："+(total_click/100000000)+"亿");
                tvTicketTapy.setText("首发");
                tvMothTicket.setText("月票"+"("+month_ticket+")");
                tvComment.setText("评论"+"("+commentCount+")");
            }
        }).execute(BaseUrl.STATISTICS_BASE_URL+comicId+BaseUrl.STATISTICS_BOTTOM_URL);
        //加载更多的详情
        new SerachStatisticMoreAscytask(new SerachStatisticMoreAscytask.SSMCallBack() {

            @Override
            public void callBack(SerachStatisticMoreBean serachStatisticMoreBean) {
                if (serachStatisticMoreBean==null){
                    return;
                }
                 otherWorks = serachStatisticMoreBean.getOtherWorks();

                SerachStatisticMoreBean.ComicBean comic = serachStatisticMoreBean.getComic();
                SerachStatisticMoreBean.ComicBean.AuthorBean author = comic.getAuthor();
                 url = comic.getCover();
                Picasso.with(StatisticsActivity.this).load(url).into(ivIcon);
                mComicId = comic.getComic_id();
                authorName = author.getName();
                avatar = author.getAvatar();
                short_description = comic.getShort_description();
                theme_ids = comic.getTheme_ids();
                int size = theme_ids.size();
                StringBuffer buffer=new StringBuffer();
                for (int i = 0; i < size; i++) {
                    String type = theme_ids.get(i);
                    buffer.append(type).append("\t");
                }
                String name = comic.getName();
                description = comic.getDescription();
                last_update_week = comic.getLast_update_week();
                cate_id = comic.getCate_id();
                tvTicketName.setText(name);
                tvAuthorName.setText(authorName);
                tvMore.setText(short_description);
                tvType.setText(buffer.toString());
            }
        }).execute(BaseUrl.STATISTICS_BASE_MORE_URL+comicId+BaseUrl.STATISTICS_BOTTOM_MORE_URL);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_statistics_head_iv_back:
                finish();
                break;
            case R.id.activity_statistics_head_iv_share:
                CreateSharePupawindow();
                break;
            case R.id.activity_statistics_head_iv_icon:
                if (url==null){
                    return;
                }
                Intent intent2=new Intent(StatisticsActivity.this,StatisticsHeadPhotoActivity.class);
                Bundle bundle2=new Bundle();
                bundle2.putString("url",url);
                intent2.putExtra("bundle",bundle2);
                startActivity(intent2);
                break;
            case R.id.activity_statistics_tv_six:
                if (otherWorks==null){
                    return;
                }

                StatisticsBean statisticsBean=new StatisticsBean();
                List<StatisticsBean.OtherWorksBean> newOtherWorks = new ArrayList<>();
                int size = otherWorks.size();
                LogUtils.log(StatisticsActivity.class,otherWorks.size()+"");
                for (int i = 0; i < size; i++) {
                    StatisticsBean.OtherWorksBean newOtherWorksBean=new StatisticsBean.OtherWorksBean();
                    SerachStatisticMoreBean.OtherWorksBean otherWorksBean = otherWorks.get(i);
                    String comicId = otherWorksBean.getComicId();
                    String coverUrl = otherWorksBean.getCoverUrl();
                    String name = otherWorksBean.getName();
                    String passChapterNum = otherWorksBean.getPassChapterNum();
                    newOtherWorksBean.setComicId(comicId);
                    newOtherWorksBean.setCoverUrl(coverUrl);
                    newOtherWorksBean.setName(name);
                    newOtherWorksBean.setPassChapterNum(passChapterNum);
                    newOtherWorks.add(newOtherWorksBean);
                }
                LogUtils.log(StatisticsActivity.class,newOtherWorks.size()+"");
                statisticsBean.setOtherWorks(newOtherWorks);
                statisticsBean.setAvatar(avatar);
                statisticsBean.setName(authorName);
                statisticsBean.setDescription(description);
                statisticsBean.setCate_id(cate_id);
                statisticsBean.setLast_update_week(last_update_week);
                statisticsBean.setTheme_ids(theme_ids);
                statisticsBean.setTotal_ticket(total_click);
                statisticsBean.setTotal_tucao(total_tucao);
                statisticsBean.setMonth_ticket(month_ticket);
                statisticsBean.setFavorite_total(favorite_total);
                Intent intent=new Intent(StatisticsActivity.this,StatisticsMoreActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("StatisticsBean",statisticsBean);
                intent.putExtra("bundle",bundle);
                startActivity(intent);
                break;
            case R.id.activity_statistics_head_ll_ticke:
                Intent intent1=new Intent(StatisticsActivity.this, LoginActivity.class);
                startActivity(intent1);
                break;
            case R.id.activity_statistics_head_ll_comment:
                if (mComicId==null){
                    return;
                }
                Intent intent3=new Intent(StatisticsActivity.this,ProgramMainActivity.class);
                Bundle bundle3=new Bundle();
                bundle3.putString("id",mComicId);
                intent3.putExtra("bundle",bundle3);
                startActivity(intent3);
                break;
            case R.id.activity_statistics_head_ll_download:
                //TODO  点击下载
                Toast.makeText(StatisticsActivity.this, "点击下载", Toast.LENGTH_SHORT).show();
                break;
            case R.id.activity_statistics_head_tv_short:
                //TODO  将所有的章节排序
                Toast.makeText(StatisticsActivity.this, "将所有的章节排序", Toast.LENGTH_SHORT).show();
                break;
            case R.id.activity_statistics_middle_tv_open:
                if (comicId==0){
                    return;
                }
                Intent intent6=new Intent(StatisticsActivity.this, AllCateLogActivity.class);
                intent6.putExtra("comicId",comicId);
                startActivity(intent6);
                break;
            case R.id.activity_statistics_bottom_btn_add:
                CreatePupawindow();
                break;
            case R.id.activity_statistics_bottom_btn_read:
                Intent intent4=new Intent(StatisticsActivity.this,ReadingActivity.class);
                startActivity(intent4);
                break;
            case R.id.statisticsdetial_popuwindow_btn_chance:
                popupWindow.dismiss();
                break;
            case R.id.statisticsdetial_popuwindow_btn_login:
                popupWindow.dismiss();
                Intent rIntent=new Intent(StatisticsActivity.this,LoginActivity.class);
                startActivity(rIntent);
                break;
            case R.id.iv_share_friends:
                sharePopupWindow.dismiss();
                Toast.makeText(StatisticsActivity.this, "分享到朋友圈", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_share_qq:
                sharePopupWindow.dismiss();
                Toast.makeText(StatisticsActivity.this, "分享到qq", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_share_weibo:
                sharePopupWindow.dismiss();
                Toast.makeText(StatisticsActivity.this, "分享到微博", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_share_weixin:
                sharePopupWindow.dismiss();
                Toast.makeText(StatisticsActivity.this, "分享到微信", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_share_qq_zone:
                sharePopupWindow.dismiss();
                Toast.makeText(StatisticsActivity.this, "分享到qq空间", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private void CreatePupawindow(){
        View view= LayoutInflater.from(this).inflate(R.layout.statisticdetial_popuwindow,null);
        Button btnNicke = (Button) view.findViewById(R.id.statisticsdetial_popuwindow_btn_chance);
        Button btnLogin = (Button) view.findViewById(R.id.statisticsdetial_popuwindow_btn_login);
        popupWindow=new PopupWindow(view, 3*width,400);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        btnLogin.setOnClickListener(this);
        btnNicke.setOnClickListener(this);
    }
    private void CreateSharePupawindow(){
        View view=LayoutInflater.from(this).inflate(R.layout.share_popup_window_view,null);
        ImageView imagewx = (ImageView) view.findViewById(R.id.iv_share_weixin);
        ImageView imageqq = (ImageView) view.findViewById(R.id.iv_share_qq);
        ImageView imagefriends = (ImageView) view.findViewById(R.id.iv_share_friends);
        ImageView imagewb = (ImageView) view.findViewById(R.id.iv_share_weibo);
        ImageView imageqqz = (ImageView) view.findViewById(R.id.iv_share_qq_zone);
        sharePopupWindow=new PopupWindow(view,3*width,400);
        sharePopupWindow.showAtLocation(view,Gravity.BOTTOM,0,0);
        imagefriends.setOnClickListener(this);
        imagewx.setOnClickListener(this);
        imageqq.setOnClickListener(this);
        imagewb.setOnClickListener(this);
        imageqqz.setOnClickListener(this);
    }
}
