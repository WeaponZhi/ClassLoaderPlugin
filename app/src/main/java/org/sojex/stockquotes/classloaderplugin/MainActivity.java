package org.sojex.stockquotes.classloaderplugin;
import com.stockboxs.stock.libtestlib.Util;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.wequick.small.Small;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String apkPath = getExternalCacheDir().getAbsolutePath()+"/bundle-debug.apk";
//        loadApk(apkPath);
    }

    private void loadApk(String apkPath) {
        File optDir = getDir("opt",MODE_PRIVATE);
        //初始化 classLoader
        DexClassLoader classLoader = new DexClassLoader(apkPath,
                optDir.getAbsolutePath(),null,this.getClassLoader());

        try {
            Class cls = classLoader.loadClass("org.sojex.stockquotes.bundle.BundleUtil");
            if (cls!=null){
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
        Small.setUp(this, new Small.OnCompleteListener() {
            @Override
            public void onComplete() {
                Small.openUri("bundle",MainActivity.this);
            }
        });
        Util.printLog(this);
    }
}
