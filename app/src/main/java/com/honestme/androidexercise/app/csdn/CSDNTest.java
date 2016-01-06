package com.honestme.androidexercise.app.csdn;

import java.util.List;

/**
 * Created by zhangconglin on 2016/1/5.
 */
public class CSDNTest {
    int currentPage = 1;

    public void test(){
        try{
            List<CSDNNewsItem> list = CSDNApiUtil.getDataFromHtml(CSDNApiUtil.NEWS_CHENGXUYUAN,currentPage);

            for (CSDNNewsItem item:list
                 ) {
                System.out.println(item);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
