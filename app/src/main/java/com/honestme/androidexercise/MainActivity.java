package com.honestme.androidexercise;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.honestme.androidexercise.ipc.MessengerClient;
import com.honestme.androidexercise.app.csdn.CSDNMainActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private HonestAdapter<HonestItem> mAdapter;
    private Context mContext;
    private List<HonestItem> mList;

    private GridView mGridView;

    private String[] mItems = {"Messenger test","Csdn app"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        mGridView = (GridView)findViewById(R.id.main_base_grid);

        mList = new ArrayList<HonestItem>();
        getData();

        mAdapter = new HonestAdapter<HonestItem>(mContext,mList,R.layout.item_honest){
            @Override
            public void populate(BaseViewHolder viewHolder, HonestItem item) {
                viewHolder.setTextView(item.getContent(),R.id.list_item_content);
            }
        };

        mGridView.setAdapter(mAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (mList.get(position).getContent()){
                    case "Messenger test":
                        Intent intent = new Intent(mContext, MessengerClient.class);
                        startActivity(intent);
                        break;
                    case "Csdn app":
                        Intent intent_csdn = new Intent(mContext, CSDNMainActivity.class);
                        startActivity(intent_csdn);

                }
            }
        });
    }


    public void getData(){
        for(int i = 0;i < mItems.length;++i){
            HonestItem honestItem = new HonestItem(mItems[i]);
            mList.add(honestItem);
        }
    }


}
