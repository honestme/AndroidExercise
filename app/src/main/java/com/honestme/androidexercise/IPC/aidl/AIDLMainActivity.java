package com.honestme.androidexercise.ipc.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.honestme.androidexercise.R;
import com.honestme.androidexercise.util.LogUtil;

import java.util.List;

public class AIDLMainActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private Context mContext = AIDLMainActivity.this;

    private final int NEW_BOOK_ARRIVED_NOTICE = 0;
    private IBookManager mBookManager;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case NEW_BOOK_ARRIVED_NOTICE:
                    LogUtil.d(TAG,"new book received:"+msg.obj.toString());
                    break;
                default:
                    super.handleMessage(msg);
            }

        }
    };

    private INewBookArrivedListener mListener = new INewBookArrivedListener() {
        @Override
        public void onNewBookArrived(Book book) throws RemoteException {
            mHandler.obtainMessage(NEW_BOOK_ARRIVED_NOTICE,book).sendToTarget();
        }

        @Override
        public IBinder asBinder() {
            return null;
        }
    };

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IBookManager bookManager = IBookManager.Stub.asInterface(service);
            mBookManager = bookManager;

            try {
                List<Book> bookList = bookManager.getBookList();
                LogUtil.d(TAG,"booklist,type is :" + bookList.getClass().getCanonicalName());
                LogUtil.d(TAG, "booklist:" + bookList.toString());

                Book book = new Book(1,"c program");
                bookManager.addBook(book);

                List<Book> newBookList = bookManager.getBookList();
                LogUtil.d(TAG,"newbooklist:"+newBookList.toString());


            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidlmain);
        Intent intent = new Intent(mContext,BookManagerService.class);
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);

        try {
            mBookManager.registerNewBookArrivedListener(mListener);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        try {
            mBookManager.unRegisterNewBookArrivedListener(mListener);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        super.onDestroy();
    }
}
