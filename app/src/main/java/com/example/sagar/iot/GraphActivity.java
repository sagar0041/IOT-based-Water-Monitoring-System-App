package com.example.sagar.iot;

import android.app.Activity;
import android.arch.persistence.room.Database;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphActivity extends AppCompatActivity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        WebView containerWebView=(WebView)findViewById(R.id.containerWebView);
        WebView containerWebView2=(WebView)findViewById(R.id.containerWebView2);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);

        containerWebView.setWebViewClient(new GraphActivity.MyWebView());
        String url="https://thingspeak.com/channels/762979/charts/2?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&type=line";
        containerWebView.getSettings().setJavaScriptEnabled(true);
        containerWebView.loadUrl(url);
        containerWebView.getSettings().setBuiltInZoomControls(true);
        containerWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
                setTitle("Loading...");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
                setTitle(view.getTitle());
            }
        });

        containerWebView2.setWebViewClient(new GraphActivity.MyWebView());
        String url2="https://thingspeak.com/channels/762979/charts/1?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=10&timescale=daily&title=WaterLevel&type=line";
        containerWebView2.getSettings().setJavaScriptEnabled(true);
        containerWebView2.loadUrl(url2);
        containerWebView2.getSettings().setBuiltInZoomControls(true);
    }

    private class MyWebView extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view,String url){
            view.loadUrl(url);
            return true;
        }
    }
}





//        btnWaterlevel=(Button)findViewById(R.id.btnWaterlevel);
//        btnph=(Button)findViewById(R.id.btnph);
//
//        btnWaterlevel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(GraphActivity.this, LevelGraphActivity.class);
//                startActivity(i);
//            }
//        });
//
//        btnph.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(GraphActivity.this, PhgraphActivity.class);
//                startActivity(i);
//
//            }
//        });




