package com.dalong.galleryrecycleview.demo2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dalong.galleryrecycleview.R;
import com.dalong.galleryrecycleview.demo.Item;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>, DiscreteScrollView.ScrollStateChangeListener<RecyclerView.ViewHolder> {
    private List<Item> mlist = new ArrayList<>();
    private int[] mImgs = {R.mipmap.test1, R.mipmap.test2, R.mipmap.test3, R.mipmap.test4, R.mipmap.test5
            , R.mipmap.test6, R.mipmap.test7
            , R.mipmap.test8, R.mipmap.test9, R.mipmap.test10, R.mipmap.test11, R.mipmap.test12, R.mipmap.test13, R.mipmap.test14, R.mipmap.test15
            , R.mipmap.test16, R.mipmap.test17, R.mipmap.test18, R.mipmap.test19, R.mipmap.test20, R.mipmap.test21, R.mipmap.test22, R.mipmap.test23};

    private DiscreteScrollView mGalleryRecyclerView;
    private TextView mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initData();
        initView();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        for (int i = 0; i < mImgs.length; i++) {
            Item item = new Item();
            item.setImg(mImgs[i]);
            item.setName(i + "km");
            mlist.add(item);
        }

    }

    private void initView() {
        mGalleryRecyclerView = (DiscreteScrollView) findViewById(R.id.picker2);
        mGalleryRecyclerView.setSlideOnFling(true);
        mGalleryRecyclerView.addOnItemChangedListener(this);
        mGalleryRecyclerView.addScrollStateChangeListener(this);
        mGalleryRecyclerView.setItemTransitionTimeMillis(150);
        mGalleryRecyclerView.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.7f)
                .build());

        mPosition = (TextView) findViewById(R.id.position);

        mGalleryRecyclerView.setAdapter(new CommonAdapter<Item>(this, R.layout.item_gallery, mlist) {
            @Override
            public void convert(ViewHolder holder, final Item s, final int position) {
                holder.setText(R.id.name, s.getName());
                holder.setImageResource(R.id.profile_image, s.getImg());
                holder.getView(R.id.item_layout).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position == mGalleryRecyclerView.getCurrentItem()) {
                            Toast.makeText(mContext, s.getName(), Toast.LENGTH_SHORT).show();
                        } else {
                            mGalleryRecyclerView.smoothScrollToPosition(position);
                        }

                    }
                });
            }
        });
        mGalleryRecyclerView.scrollToPosition(mImgs.length / 2);
    }

    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {

    }

    @Override
    public void onScrollStart(@NonNull RecyclerView.ViewHolder currentItemHolder, int adapterPosition) {

    }

    @Override
    public void onScrollEnd(@NonNull RecyclerView.ViewHolder currentItemHolder, int adapterPosition) {

    }

    @Override
    public void onScroll(float scrollPosition, int currentPosition, int newPosition, @Nullable RecyclerView.ViewHolder currentHolder, @Nullable RecyclerView.ViewHolder newCurrent) {

    }
}
