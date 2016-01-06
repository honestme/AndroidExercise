// IMyBookManager.aidl
package com.honestme.androidexercise;

// Declare any non-default types here with import statements
import com.honestme.androidexercise.Book;
interface IMyBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    List<Book> getBookList();
    void addBook();
}
