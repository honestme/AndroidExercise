// IBookManager.aidl
package com.honestme.androidexercise.ipc.aidl;

import com.honestme.androidexercise.ipc.aidl.Book;
// Declare any non-default types here with import statements
import com.honestme.androidexercise.ipc.aidl.INewBookArrivedListener;

interface IBookManager {
   List<Book> getBookList();
   void addBook(in Book book);
   void registerNewBookArrivedListener(in INewBookArrivedListener listener);
   void unRegisterNewBookArrivedListener(in INewBookArrivedListener listener);
}
