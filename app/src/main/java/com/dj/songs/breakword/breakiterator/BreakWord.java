package com.dj.songs.breakword.breakiterator;

import android.icu.text.BreakIterator;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dengjie09
 * @description:
 * @date：2020/4/13 2:00 PM
 */
public class BreakWord {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<String> breakWord(String breakWord) {

        BreakIterator boundary = BreakIterator.getWordInstance();
        boundary.setText(breakWord);
        return printEachForward(boundary, breakWord);
    }

    public List<String> printEachForward(BreakIterator boundary, String source) {
        List<String> res = new ArrayList<>();
        int start = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            start = boundary.first();
            for (int end = boundary.next();
                 end != BreakIterator.DONE;
                 start = end, end = boundary.next()) {
                res.add(source.substring(start, end));
            }
        }
        return res;
    }
}