package com.cdv.databindingdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.cdv.databindingdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);

        mMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        BookBean book = new BookBean();
        book.setBookName(" android Book");
        book.setBookAuthor("cdv");
        book.setRating(3);
       mMainBinding.setVariable(BR.book,book);
       mMainBinding.mAuthor.setText(book.bookAuthor);
       mMainBinding.mTitle.setText(book.bookName);

    }
}