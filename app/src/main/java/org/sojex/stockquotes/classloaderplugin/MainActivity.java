package org.sojex.stockquotes.classloaderplugin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.sojex.stockquotes.classloaderplugin.view.RaiseNumberAnimTextView;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {
    private RaiseNumberAnimTextView mRaiseNumberAnimTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //APK 存放的位置
        String apkPath = getExternalCacheDir().getAbsolutePath()+"/bundle-debug.apk";
        loadApk(apkPath);

//        mRaiseNumberAnimTextView = (RaiseNumberAnimTextView) findViewById(R.id.tv);
//        mRaiseNumberAnimTextView.setDuration(2000);
//        mRaiseNumberAnimTextView.setAnimatorText(1000);
        //Android 应用最少需要两个 ClassLoader
        ClassLoader classLoader = getClassLoader();
        if (classLoader != null) {
            Log.e("weaponzhi", "classLoader: " + classLoader.toString());

            while (classLoader.getParent() != null) {
                classLoader = classLoader.getParent();
                Log.e("weaponzhi","classLoader: "+classLoader.toString());
            }
        }
    }

    private void loadApk(String apkPath) {
        //应用内部目录，MODE_PRIVATE 代表只有自己应用可以访问这个路径。
        File optDir = getDir("opt", MODE_PRIVATE);
        //初始化 classLoader，通过 DexClassLoader 来加载指定目录下的插件中的类
        DexClassLoader classLoader = new DexClassLoader(apkPath,
                optDir.getAbsolutePath(), null, this.getClassLoader());

        try {
            //获取指定路径插件的 class 字节码文件
            Class cls = classLoader.loadClass("org.sojex.stockquotes.bundle.BundleUtil");
            if (cls != null) {
                Object instance = cls.newInstance();
                Method method = cls.getMethod("printLog");
                method.invoke(instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        //Small 插件跳转
//        Small.setUp(this, new Small.OnCompleteListener() {
//            @Override
//            public void onComplete() {
//                Small.openUri("bundle",MainActivity.this);
//            }
//        });
    }
}
