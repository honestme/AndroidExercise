package com.honestme.androidexercise.app.animetasteextra;

import android.net.Uri;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangconglin on 2016/1/11.
 */
public class ATEApiUtil {
    public static final int ATE_MAIN = 0;
    public static final int ATE_INTERVIEW = 1;
    public static final int ATE_WEEKLY_INSPIRATIONS = 2;
    public static final int ATE_AIMOZHEN = 3;
    public static final int ATE_SUGER = 4;
    public static final int ATE_ATTENTION = 5;

    public static final String ATE_MAIN_URL = "http://animetaste.net";
    public static final String ATE_INTERVIEW_URL = "http://animetaste.net/category/interview";
    public static final String ATE_WEEKLY_INSPIRATIONS_URL = "http://animetaste.net/category/weekly-inspirations";
    public static final String ATE_AIMOZHEN_URL = "http://aimozhen.com";
    public static final String ATE_SUGER_URL = "http://bingtanghuluer.com/";
    public static final String ATE_ATTENTION_URL = "";

    public static String getFormatUri(int type,int page){
        StringBuffer stringBuffer = new StringBuffer();

        switch (type){
            case ATE_MAIN:
                stringBuffer.append(ATE_MAIN_URL);
                break;
            case ATE_INTERVIEW:
                stringBuffer.append(ATE_INTERVIEW_URL);
                break;
            case ATE_WEEKLY_INSPIRATIONS:
                stringBuffer.append(ATE_WEEKLY_INSPIRATIONS_URL);
                break;
            case ATE_AIMOZHEN:
                stringBuffer.append(ATE_AIMOZHEN_URL);
                break;
            case ATE_SUGER:
                stringBuffer.append(ATE_SUGER_URL);
                break;
            case ATE_ATTENTION:
                stringBuffer.append(ATE_ATTENTION_URL);
                break;
        }

        stringBuffer.append("/page/" + page);

        return stringBuffer.toString();
    }

    public static List<ATEItem> getDataFromUri(String uriStr,int type,int page){
        List<ATEItem> itemList = new ArrayList<ATEItem>();

        String formatUrl = getFormatUri(type, page);

        Uri uri = Uri.parse(formatUrl);
        try {
            Document document = Jsoup.connect(formatUrl).get();


        }catch (Exception ex){
            ex.printStackTrace();
        }

        return itemList;
    }

}
