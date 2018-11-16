package com.example.dasha_000.shopping.WebParsing;

import android.util.Log;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyThread extends Thread {

    private List<Thread> threadsToJoin;


    public void setThreadsToJoin(List<Thread> threadsToJoin) {
        this.threadsToJoin = threadsToJoin;

    }

    @Override
    public void run() {

        for (Thread threadToJoin : threadsToJoin) {
            try {
                threadToJoin.join();
            } catch (InterruptedException ex) {
                // Exception handling
            }

        }
    }
}
