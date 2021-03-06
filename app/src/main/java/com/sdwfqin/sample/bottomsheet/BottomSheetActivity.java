package com.sdwfqin.sample.bottomsheet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sdwfqin.sample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sdwfqin on 2017/3/11.
 */

public class BottomSheetActivity extends AppCompatActivity {

    @BindView(R.id.nsv)
    NestedScrollView nsv;
    @BindView(R.id.bottom_list)
    ListView bottomList;
    private BottomSheetBehavior mBottomSheetBehavior;
    private static final String TAG = "BottomSheetActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottomsheet);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {

        mBottomSheetBehavior = BottomSheetBehavior.from(nsv);

        String[] strings = new String[]{"BottomSheet", "BottomSheetDialog", "BottomSheetDialogFragment"};
        bottomList.setAdapter(new ArrayAdapter<String>(this, R.layout.item_list, R.id.tv_items, strings));

        bottomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        bottomSheet();
                        break;
                    case 1:
                        bootomSheetDialog();
                        break;
                    case 2:
                        bottomSheetDialogFragment();
                        break;
                }
            }
        });

        //回掉监听
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                Log.e(TAG, "onStateChanged: " + "状态改变" + newState);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.e(TAG, "onSlide: " + slideOffset);
            }
        });

        //mBottomSheetBehavior.setPeekHeight(0); // 设置当关闭时底部的高度
        //app:behavior_peekHeight="50dp"
        //mBottomSheetBehavior.setHideable(false);//设置当拉升到底部是否可以隐藏
        //app:behavior_hideable="true"
        //bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);//设置状态
        //
        //public static final int STATE_DRAGGING = 1;  //拖动
        //public static final int STATE_SETTLING = 2;//沉降中
        //public static final int STATE_EXPANDED = 3;//打开了
        //public static final int STATE_COLLAPSED = 4;//关闭了
        //public static final int STATE_HIDDEN = 5;//隐藏了
    }

    private void bootomSheetDialog() {
        BottomSheetDialog dialog = new BottomSheetDialog(BottomSheetActivity.this);
        View view = getLayoutInflater().inflate(R.layout.dialog_bottom_sheet, null);
        dialog.setContentView(view);
        dialog.show();
    }

    private void bottomSheet() {

        if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

    private void bottomSheetDialogFragment() {
        new MyBottomSheetDialogFragment().show(getSupportFragmentManager(), "dialog");
    }

}
