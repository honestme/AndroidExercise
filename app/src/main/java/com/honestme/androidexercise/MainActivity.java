package com.honestme.androidexercise;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.honestme.androidexercise.basic.sharesimpledata.ShareSimpleDataActivity;
import com.honestme.androidexercise.ipc.MessengerClient;
import com.honestme.androidexercise.app.csdn.CSDNMainActivity;
import com.honestme.androidexercise.tools.myimageloader.MyImageLoaderActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private HonestAdapter<HonestItem> mAdapterBasic;
    private HonestAdapter<HonestItem> mAdapterApp;
    private HonestAdapter<HonestItem> mAdapterTools;

    private Context mContext;
    private List<HonestItem> mListBasic = new ArrayList<HonestItem>();
    private List<HonestItem> mListApp = new ArrayList<HonestItem>();
    private List<HonestItem> mListTools = new ArrayList<HonestItem>();

    private GridView mGridViewBasic;
    private GridView mGridViewApp;
    private GridView mGridViewTools;

    private String[] mItemsBasic = {"Share simple data","Messenger test"};
    private String[] mItemsApp = {"Csdn app"};
    private String[] mItemsTools = {"My image loader"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        mGridViewBasic = (GridView)findViewById(R.id.main_base_grid);
        getData();

        mAdapterBasic = new HonestAdapter<HonestItem>(mContext, mListBasic,R.layout.item_honest){
            @Override
            public void populate(BaseViewHolder viewHolder, HonestItem item) {
                viewHolder.setTextView(item.getContent(),R.id.list_item_content);
            }
        };

        mGridViewBasic.setAdapter(mAdapterBasic);

        mGridViewBasic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (mListBasic.get(position).getContent()) {
                    case "Share simple data":
                        Intent intentShareSimpleData = new Intent(mContext, ShareSimpleDataActivity.class);
                        startActivity(intentShareSimpleData);

                    case "Messenger test":
                        Intent intentMessenger = new Intent(mContext, MessengerClient.class);
                        startActivity(intentMessenger);
                        break;


                }
            }
        });


        mGridViewApp = (GridView)findViewById(R.id.main_app_grid);
        mAdapterApp = new HonestAdapter<HonestItem>(mContext,mListApp,R.layout.item_honest) {
            @Override
            public void populate(BaseViewHolder viewHolder, HonestItem item) {

            }
        };
        mGridViewApp.setAdapter(mAdapterApp);
        mGridViewApp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (mListApp.get(position).getContent()) {
                    case "Csdn app":
                        Intent intent_csdn = new Intent(mContext, CSDNMainActivity.class);
                        startActivity(intent_csdn);

                }
            }
        });

        mGridViewTools = (GridView)findViewById(R.id.main_tools_grid);
        mAdapterTools = new HonestAdapter<HonestItem>(mContext,mListTools,R.layout.item_honest) {
            @Override
            public void populate(BaseViewHolder viewHolder, HonestItem item) {

            }
        };
        mGridViewTools.setAdapter(mAdapterTools);
        mGridViewTools.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (mListTools.get(position).getContent()){
                    case "My image loader":
                        Intent intent = new Intent(mContext, MyImageLoaderActivity.class);
                        startActivity(intent);
                }
            }
        });
    }


    public void getData(){
        for(int i = 0;i < mItemsBasic.length;++i){
            HonestItem honestItem = new HonestItem(mItemsBasic[i]);
            mListBasic.add(honestItem);
        }

        for(int i = 0;i < mItemsApp.length;++i){
            HonestItem honestItem = new HonestItem(mItemsBasic[i]);
            mListApp.add(honestItem);
        }

        for(int i = 0;i < mItemsTools.length;++i){
            HonestItem honestItem = new HonestItem(mItemsTools[i]);
            mListTools.add(honestItem);
        }
    }


}
