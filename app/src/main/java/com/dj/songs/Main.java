package com.dj.songs;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * author : dengjiejie
 * date : 2023/2/28 20:13
 * description :
 */
public class Main {


    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {

        PriorityQueue heap = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 >= o2 ? 0 : 1;
            }
        });

     heap.add(1);
     heap.add(2);
     System.out.print(heap.poll());
    }


}
