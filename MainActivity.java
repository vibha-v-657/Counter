package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView count;
    Button start,stop;
    int counter=0;
    boolean running = false;
    String url="https://media1.tenor.com/images/142ffb3aa784ee74e9ce08b57edc89c6/tenor.gif?itemid=5301252";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count=(TextView)findViewById(R.id.count);
        start=(Button)findViewById(R.id.startbtn);
        stop=(Button)findViewById(R.id.stopbtn);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);

    }
    @Override
    public void onClick(View view)
    {
        if(view.equals(start))
        {
            counter=0;
            running=true;
            WebView webView = (WebView) findViewById(R.id.w1);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webView.getSettings().setLoadsImagesAutomatically(true);
            webView.setBackgroundColor(Color.TRANSPARENT);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(url);
            new MyCounter().start();
        }
        else
            running=false;
    }
    Handler handler= new Handler(){
    public void handleMessage(Message counter)
    {
        count.setText(String.valueOf(counter.what));
    }};
    class MyCounter extends Thread
    {
        public void run()
        {
            while(running)
            {

                counter++;
                handler.sendEmptyMessage(counter);
                try{
                    Thread.sleep(1000);
                }
                catch(Exception e){}
            }
        }
    }
}