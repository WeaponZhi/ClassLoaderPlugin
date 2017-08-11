package com.stockboxs.stock.libtestlib;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by admin on 2017/8/10.
 */

public class Util {
    public void printLog(Context ctx){
        Toast.makeText(ctx,"测试成功",Toast.LENGTH_LONG).show();
    }
}
