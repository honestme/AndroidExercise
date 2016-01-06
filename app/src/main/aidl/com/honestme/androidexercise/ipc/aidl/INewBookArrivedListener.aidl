// INewBookArrivedListener.aidl
package com.honestme.androidexercise.ipc.aidl;

import com.honestme.androidexercise.ipc.aidl.Book;
// Declare any non-default types here with import statements

interface INewBookArrivedListener {
   void onNewBookArrived(in Book book);
}
