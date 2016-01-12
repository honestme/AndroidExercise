package com.honestme.androidexercise.app.csdn;

import android.provider.DocumentsContract;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangconglin on 2016/1/4.
 */
public class CSDNApiUtil {
    public static final int NEWS_YEJIE = 0;
    public static final int NEWS_YIDONG = 1;
    public static final int NEWS_YUNJISUAN = 2;
    public static final int NEWS_RUANJIAN = 3;
    public static final int NEWS_CHENGXUYUAN = 4;
    public static final int NEWS_JIKE = 5;

    public static final String URL_YEJIE = "http://news.csdn.net/news";
    public static final String URL_YIDONG = "http://mobile.csdn.net";
    public static final String URL_YUNJISUAN = "http://cloud.csdn.net/cloud";
    public static final String URL_RUANJIAN = "http://sd.csdn.net/sd";
    public static final String URL_CHENGXUYUAN = "http://programmer.csdn.net/programmer";

    public static String getUrl(int newsType,int page){
        String retUrl = "";

        switch (newsType){
            case NEWS_YEJIE:
                retUrl = URL_YEJIE;
                break;
            case NEWS_JIKE:
                retUrl = "";
                break;
            case NEWS_CHENGXUYUAN:
                retUrl = URL_CHENGXUYUAN;
                break;
            case NEWS_RUANJIAN:
                retUrl = URL_RUANJIAN;
                break;
            case NEWS_YIDONG:
                retUrl = URL_YIDONG;
                break;
            default:break;
        }

        retUrl += '/' + page;

        return retUrl;
    }

    public static String getHtmlFromUrl(String urlStr) throws Exception{
        StringBuffer stringBuffer = new StringBuffer();

        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("get");
            connection.setConnectTimeout(5000);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            if(connection.getResponseCode() == 200){
                InputStream inputStream = connection.getInputStream();
                int length = 0;
                byte[] buffer = new byte[1024];
                while(inputStream.read(buffer) != -1){
                    stringBuffer.append(new String(buffer,0,length,"UTF-8"));
                }
                inputStream.close();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return stringBuffer.toString();
    }

    public static List<CSDNNewsItem> getNewsTitleFromHtml(int newsType, int page){
        List<CSDNNewsItem> newsItemList = new ArrayList<CSDNNewsItem>();
        CSDNNewsItem newsItem = null;

        String urlStr = getUrl(newsType, page);
        String htmlStr = null;
        try {
            htmlStr = getHtmlFromUrl(urlStr);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        org.jsoup.nodes.Document document = Jsoup.parse(htmlStr);
        Elements elements = document.getElementsByClass("unit");

        for(int i = 0;i < elements.size();++i){
            newsItem = new CSDNNewsItem();
            newsItem.setNewsType(newsType);

            org.jsoup.nodes.Element element = elements.get(i);
            org.jsoup.nodes.Element element_h1 = element.getElementsByTag("h1").get(0);
            org.jsoup.nodes.Element element_a = element_h1.child(0);

            newsItem.setTitle(element_a.text());
            newsItem.setContentLink(element_a.attr("href"));

            org.jsoup.nodes.Element element_h4 = element.getElementsByTag("h4").get(0);
            org.jsoup.nodes.Element element_h4_ago = element_h4.getElementsByClass("ago").get(0);
            org.jsoup.nodes.Element element_h4_view_time = element_h4.getElementsByClass("view_time")
                    .get(0);
            org.jsoup.nodes.Element element_h4_num_recom = element_h4.getElementsByClass("num_recom")
                    .get(0);

            String date = element_h4_ago.text();
            String viewTimes = element_h4_view_time.text();
            String recommends = element_h4_num_recom.text();

            newsItem.setDate(date);
            newsItem.setViewTimes(viewTimes);
            newsItem.setRecommends(recommends);

            org.jsoup.nodes.Element element_dl = element.getElementsByTag("dl").get(0);
            org.jsoup.nodes.Element element_dt = element_dl.child(0);
            try {
                org.jsoup.nodes.Element element_imgSrc = element_dt.child(0).getElementsByAttribute("src")
                        .get(0);
                String imageLink = element_imgSrc.text();
                newsItem.setImageLink(imageLink);
            }catch (IndexOutOfBoundsException ex){
                ex.printStackTrace();
            }

            org.jsoup.nodes.Element element_dd = element_dl.child(1);
            String content = element_dd.text();

            newsItem.setContent(content);

            newsItemList.add(newsItem);
        }

        return newsItemList;
    }

    public static void getNewsContent(String urlStr){
        try {
            String htmlStr = getHtmlFromUrl(urlStr);
            org.jsoup.nodes.Document document = Jsoup.parse(htmlStr);

            org.jsoup.nodes.Element element = document.select(".left.detail").get(0);

            org.jsoup.nodes.Element elementTitle = element.select("h1.title").get(0);
            String title = elementTitle.text();


        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
