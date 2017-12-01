package com.example.rajat.paystay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import DatabaseHelpers.PgContact;
import DatabaseHelpers.PgDatabaseHelper;

/**
 * Created by Rajat on 11/1/2016.
 */
public class PgList extends AppCompatActivity {

    TextView PgCount;
    int count;
    List<PgContact> pgContactList=new ArrayList<>();
    public static PgDatabaseHelper pgDatabaseHelper;
    private RecyclerView recyclerView;
    private PgAdapter pgAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pg_list);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        pgDatabaseHelper=new PgDatabaseHelper(getApplicationContext());

        pgContactList=pgDatabaseHelper.getAllPg();
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        /*try{pgContactList=pgDatabaseHelper.getAllPg();}catch (Exception e) {
            e.printStackTrace();
        }
        */

        if(pgContactList!=null){
        pgAdapter = new PgAdapter(pgContactList);
        recyclerView.setAdapter(pgAdapter);}


        /*if(pgContactList!=null) {
            pgAdapter = new PgAdapter(pgContactList);
            recyclerView.setAdapter(pgAdapter);
        }*/
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent i=new Intent(getApplicationContext(),PgInfo.class);
                i.putExtra("id",pgContactList.get(position).getID());
                startActivity(i);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

               // prepareCourseData();


    }


    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private PgList.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final PgList.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),AdminSection.class);
        startActivity(i);
    }


}
