package com.example.mangaramu.tumobilenews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class FragHolderApp extends AppCompatActivity implements NewsTableFragment.newsshow, NewsTableFragment.filterbutton, FilterMenuFrag.selectorinterface{

    static NewsTableFragment tunewslist; //instNCE OF THE NEWS TABLE FRAGMENT.
    static NewsFragment tunews;
    static FilterMenuFrag tufilter;

    FragmentManager manage ;//since only one fragment will not use the backstack. Should probally not use the back
    //stack in the real application as well. Should have an array of fragments we can programatically go between.
    FragmentTransaction transact;
    //TODO android.R.id.content is the view above all the other views



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_holder_app);

        if(tunewslist == null)
        {
             tunews= new NewsFragment();
             tunewslist = new NewsTableFragment();

        }
        manage=getSupportFragmentManager();
        if(manage.findFragmentById(R.id.FragmentContainer) == null) {
            transact = manage.beginTransaction();
            transact.replace(R.id.FragmentContainer, tunewslist).commit();
            manage.executePendingTransactions();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    protected void getnews()
    {

    }

    @Override
    public void shownews(Newsitem t) {// part of the shownews interface. Takes in a newsitem. Uses the newscontent from the news item
        //as input for the constructor of the show news fragment
        String newshtml = t.newscontent;
        String newurl = t.newsurl;
        tunews.news=newshtml;
        tunews.newsurl= newurl;
        manage = getSupportFragmentManager();
        transact = manage.beginTransaction();
        transact.replace(R.id.FragmentContainer,tunews).commit();
        manage.executePendingTransactions();
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().findFragmentById(R.id.FragmentContainer) instanceof  NewsFragment)
        {
            manage=getSupportFragmentManager();
            transact=manage.beginTransaction();
            transact.replace(R.id.FragmentContainer,tunewslist).commit();
            manage.executePendingTransactions();

        }
        else if(getSupportFragmentManager().findFragmentById(android.R.id.content) instanceof FilterMenuFrag)
        {
            manage=getSupportFragmentManager();
            transact=manage.beginTransaction();
            transact.remove(tufilter).commit();
            manage.executePendingTransactions();
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public void filterbuttonpresseed() { //part of the filterbutton interface. Takes in nothing and returns nothing. Adds a new tufilter fragment in an area on top of where fragments normally are put.(Allowing for an overlay type effect)
        manage= getSupportFragmentManager();
        transact = manage.beginTransaction();
        tufilter= new FilterMenuFrag();//new filter because the checkboxes keep procting with state changes if it is the same menufrrag
        transact.add(android.R.id.content,tufilter);
        transact.commit();
        manage.executePendingTransactions();
    }

    @Override
    public void newslink(String x) {//part of the newsshow interface. Takes in a string that should be the link to be loaded in the NewsFragment. Removes the filter fragment and uses a public method of the NewsFragment to load news based on the string (link) provided.

        tunewslist.finallink=x;
        manage=getSupportFragmentManager();
        transact = manage.beginTransaction();
        transact.remove(tufilter).commit();
        manage.executePendingTransactions();
        tunewslist.loadnews();
    }
}
