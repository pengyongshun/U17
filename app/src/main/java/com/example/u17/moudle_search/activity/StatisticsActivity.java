package com.example.u17.moudle_search.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.u17.R;
import com.example.u17.base_ascytask.StatisticsBottomAscytask;
import com.example.u17.base_bean.StatisticsBottomBean;
import com.example.u17.base_http.BaseUrl;
import com.example.u17.base_uitls.log_uitls.LogUtils;
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

public class StatisticsActivity extends AppCompatActivity {
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
    private List<String> urls=new ArrayList<>();
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
    private List<SerachStatisticMoreBean.DataBean.ReturnDataBean.OtherWorksBean> otherWorks;

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
        cover = bundle.getString("cover");
        int comicId = bundle.getInt("comicId");
        if (cover==null){
            return;
        }
        Picasso.with(this).load(cover).into(ivIcon);
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
            public void callBack(StatisticsBottomBean statisticsBottomBean) {
                List<StatisticsBottomBean.DataBean.ReturnDataBean> returnData = statisticsBottomBean.getData().getReturnData();
                int size = returnData.size();
                for (int i = 0; i < size; i++) {
                    StatisticsBottomBean.DataBean.ReturnDataBean returnDataBean = returnData.get(i);
                    String cover = returnDataBean.getCover();
                    urls.add(cover);
                }
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
        public StatisticsViewHodler(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO  根据comic_id来刷新当前界面的head部分即可
                    Toast.makeText(StatisticsActivity.this, "根据comic_id来刷新当前界面的head部分即可", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    //这个类类似于baseAdapter的getView中的ViewHoder
    class StatisticsBottomAdapter extends RecyclerView.Adapter<StatisticsViewHodler>{

        //类似于baseAdapter的getView中的初次  复用
        @Override
        public StatisticsViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
            ImageView imageView=new ImageView(StatisticsActivity.this);
            imageView.setLayoutParams(new RecyclerView.LayoutParams(width,150));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return new StatisticsViewHodler(imageView);
        }

        //类似于baseAdapter的getView中的更新UI
        @Override
        public void onBindViewHolder(StatisticsViewHodler holder, int position) {
            String url = urls.get(position);
            Picasso.with(StatisticsActivity.this).load(url).into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return urls==null?0:urls.size();
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
                SerachStatisticsTicketBean.DataBean.ReturnDataBean returnDataBean = serachStatisticsTicketBean.getData().getReturnData();
                SerachStatisticsTicketBean.DataBean.ReturnDataBean.ComicBean comic = returnDataBean.getComic();
                SerachStatisticsTicketBean.DataBean.ReturnDataBean.CommentBean comment = returnDataBean.getComment();
                favorite_total = comic.getFavorite_total();
                commentCount = comment.getCommentCount();
                 month_ticket = comic.getMonth_ticket();
                 total_click = comic.getTotal_click();
                total_tucao = comic.getTotal_tucao();
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
                SerachStatisticMoreBean.DataBean.ReturnDataBean returnData1 = serachStatisticMoreBean.getData().getReturnData();
                 otherWorks = returnData1.getOtherWorks();

                SerachStatisticMoreBean.DataBean.ReturnDataBean.ComicBean comic = serachStatisticMoreBean.getData().getReturnData().getComic();
                SerachStatisticMoreBean.DataBean.ReturnDataBean.ComicBean.AuthorBean author = comic.getAuthor();
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
                //TODO  返回上一界面
                finish();
                break;
            case R.id.activity_statistics_head_iv_share:
                //TODO  分享
                Toast.makeText(StatisticsActivity.this, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.activity_statistics_head_iv_icon:
                //TODO  点击icon 将其发大，还可以分享到那个平台
                Toast.makeText(StatisticsActivity.this, "点击icon 将其发大，还可以分享到那个平台", Toast.LENGTH_SHORT).show();
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
                    SerachStatisticMoreBean.DataBean.ReturnDataBean.OtherWorksBean otherWorksBean = otherWorks.get(i);
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
                //TODO  点击收藏
                Toast.makeText(StatisticsActivity.this, "点击收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.activity_statistics_head_ll_download:
                //TODO  点击下载
                Toast.makeText(StatisticsActivity.this, "点击下载", Toast.LENGTH_SHORT).show();
                break;
            case R.id.activity_statistics_head_tv_short:
                //TODO  将所有的章节排序
                Toast.makeText(StatisticsActivity.this, "将所有的章节排序", Toast.LENGTH_SHORT).show();
                break;
            case R.id.activity_statistics_middle_ll_open:
                //TODO  打开所有的章节
                Toast.makeText(StatisticsActivity.this, "打开所有的章节", Toast.LENGTH_SHORT).show();
                break;
            case R.id.activity_statistics_bottom_btn_add:
                //TODO  添加收藏
                Toast.makeText(StatisticsActivity.this, "添加收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.activity_statistics_bottom_btn_read:
                //TODO  继续阅读
                Toast.makeText(StatisticsActivity.this, "继续阅读", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
