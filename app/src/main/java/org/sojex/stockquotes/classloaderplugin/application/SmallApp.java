package org.sojex.stockquotes.classloaderplugin.application;

import android.app.Application;

import net.wequick.small.Small;

/**
 * SmallApp 初始化 Small
 * author:张冠之
 * time: 2017/8/10 9:35
 * e-mail: guanzhi.zhang@sojex.cn
 */

public class SmallApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        Small.preSetUp(this);//Small 初始化
    }
}
