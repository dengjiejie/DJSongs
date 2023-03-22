package com.dj.songs.aop;


import android.util.Log;
import android.view.View;

import com.dj.songs.SongsApplication;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author dengjie09
 * @description:
 * @date：2020/4/7 7:42 PM
 */
@Aspect
public class AspectClick {
    public final static String TAG = "AspectJAop";

    //切点为OnClickListener.onClick方法的匹配表达式
    public final static String CLICK_POINTCUTS = "execution(* android.view.View.On*Listener.on*Click(..))";

    //切点为lambda表达式的匹配表达式
    public final static String CLICK_IN_LAMBDA_POINTCUTS = "execution(void *..lambda$*(..))";


    @Pointcut(CLICK_POINTCUTS)
    public void onClickPointcuts() {
    }

    @Pointcut(CLICK_IN_LAMBDA_POINTCUTS)
    public void onClickInLambdaPointcuts() {
    }

    @Before("onClickPointcuts() || onClickInLambdaPointcuts()")
    public void onViewClick(JoinPoint joinPoint) throws Throwable {
        Log.d(TAG, "click view:  " + getViewInfoString(joinPoint));
        Log.d(TAG, "StackTrace: \n" + getStackTrace());
    }

    private static String getViewInfoString(JoinPoint joinPoint) {
        String viewInfo = joinPoint.getArgs()[1].getClass().getSimpleName();
        try {
            View view = (View) joinPoint.getArgs()[1];
            String viewName = SongsApplication.mContext.getResources().getResourceName(view.getId());
            viewInfo += "; id = " + viewName;
        } catch (Exception e) {
            viewInfo = "ERROR : " + viewInfo + " is not view";
        }
        return viewInfo;
    }

    private static String getStackTrace() {
        StringBuffer stringBuffer = new StringBuffer();
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        for (StackTraceElement trace1 : trace) {
            stringBuffer.append(trace1.getClassName());
            stringBuffer.append("$");
            stringBuffer.append(trace1.getMethodName());
            stringBuffer.append("\n");
        }
        return stringBuffer.toString();
    }

}
