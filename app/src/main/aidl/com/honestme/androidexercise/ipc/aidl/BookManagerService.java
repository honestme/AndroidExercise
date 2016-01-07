package com.honestme.androidexercise.ipc.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import com.honestme.androidexercise.util.LogUtil;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by zhangconglin on 2016/1/6.
 */
public class BookManagerService extends Service {
    private final String TAG = getClass().getSimpleName();

    private CopyOnWriteArrayList<Book> mArrayList = new CopyOnWriteArrayList<Book>();

    private CopyOnWriteArrayList<INewBookArrivedListener> mListeners = new CopyOnWriteArrayList
            <INewBookArrivedListener>();

    private RemoteCallbackList<INewBookArrivedListener> mListenerList = new RemoteCallbackList<INewBookArrivedListener>();

    private Binder mBinder = new IBookManager.Stub(){
        @Override
        public List<Book> getBookList() throws RemoteException {
            SystemClock.sleep(5000);
            return mArrayList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mArrayList.add(book);
        }

        @Override
        public void registerNewBookArrivedListener(INewBookArrivedListener listener) throws RemoteException {
            if(!mListeners.contains(listener)){
                mListeners.add(listener);
                mListenerList.register(listener);

            }else {
                LogUtil.d(TAG,"Already registered before,can't register again");
            }

            LogUtil.d(TAG,"Already registered,now the list size is:"+mListeners.size());
        }

        @Override
        public void unRegisterNewBookArrivedListener(INewBookArrivedListener listener) throws RemoteException {
            if(mListeners.contains(listener)){
                mListeners.remove(listener);
                mListenerList.unregister(listener);
            }else {
                LogUtil.d(TAG,"Not registered,can't find the mark");
            }

            LogUtil.d(TAG,"Unregistered success,now the list size is:"+mListeners.size());
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mTimer.schedule(mTimerTask,5000,5000);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void onNewBookArrived(Book book){

        mArrayList.add(book);

        int length = mListenerList.beginBroadcast();

        try {
             for(int i = 0;i < length;++i){
                 INewBookArrivedListener listener = mListenerList.getBroadcastItem(i);

                 if(listener != null){
                    listener.onNewBookArrived(book);
                 }
                 mListenerList.finishBroadcast();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }


    }

    private Timer mTimer = new Timer();

    private TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {
            int bookId = mArrayList.size() + 1;
            Book book = new Book(bookId,"new book#"+bookId);
            onNewBookArrived(book);
        }
    };
}
