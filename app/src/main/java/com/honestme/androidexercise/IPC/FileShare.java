package com.honestme.androidexercise.IPC;

import com.honestme.androidexercise.HonestItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Created by zhangconglin on 2015/12/27.
 */
public class FileShare {
    private String mDirPath;
    private String mFilePath;

    private HonestItem mItem;

    public void persistToFile(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                File dir = new File(mDirPath);
                if(!dir.exists() || !dir.isDirectory()){
                    dir.mkdirs();
                }
                File file = new File(mFilePath);

                ObjectOutputStream outputStream = null;
                try{
                    outputStream = new ObjectOutputStream(new FileOutputStream(file));

                    outputStream.writeObject(mItem);

                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    //outputStream.close();
                }


            }
        }).run();
    }

    public void recoverFromFile(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = new File(mFilePath);
                if(!file.exists()){
                    return;
                }

                ObjectInputStream inputStream = null;

                try {
                    inputStream = new ObjectInputStream(new FileInputStream(file));

                    mItem = (HonestItem)inputStream.readObject();
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    //inputStream.close();
                }

            }
        }).run();
    }
}
