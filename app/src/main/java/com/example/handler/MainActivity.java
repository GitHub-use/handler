package com.example.handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int backColor;

    private final int THREAD_FINISH = 19;

    Handler hen = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==THREAD_FINISH){
                Button button = findViewById(R.id.button);
                Log.d("Handler", "handleMessage:background ");
                button.setBackgroundColor(backColor);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        StringBuilder sb = new StringBuilder();
                        List<String> select = Arrays.asList("0","1","2","3","4","5","6","7","8","9","0","A","B","C","D","E","F");
                        while (sb.length()<6){
                            Random random = new Random();
                            int a = random.nextInt();
                            a=a%17;
                            if(a>0||a==0){
                                sb.append(select.get(a));
                            }else{
                                continue;
                            }
                        }
                        System.out.println(sb.toString());
                        backColor = Integer.valueOf("7F"+sb.toString(),16);
                        Message message = new Message();
                        message.what=THREAD_FINISH;
                        hen.sendMessage(message);
                    }
                }.start();
            }
        });
    }
}
