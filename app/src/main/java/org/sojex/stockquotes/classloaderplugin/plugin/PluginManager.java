package org.sojex.stockquotes.classloaderplugin.plugin;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;

import dalvik.system.DexClassLoader;

/**
 * Created by admin on 2017/8/9.
 */

public class PluginManager {

    private static volatile PluginManager mInstance;
    private static Context context;
    private static File mOptFile;
    private static HashMap<String, PluginInfo> mPluginMap;

    private PluginManager(Context context) {
        this.context = context;
        mOptFile = context.getDir("opt", context.MODE_PRIVATE);
        mPluginMap = new HashMap<>();
    }

    //获取单例对象
    public static PluginManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (PluginManager.class) {
                if (mInstance == null) {
                    mInstance = new PluginManager(context);
                }
            }
        }
        return mInstance;
    }

    //为插件 apk 创建对应的 classLoader
    private static DexClassLoader createPluginDexClassLoader(String apkPath) {
        DexClassLoader classLoader = new DexClassLoader(apkPath,
                mOptFile.getAbsolutePath(), null, null);
        return classLoader;
    }

    //为对应的插件创建AssetManager
    private static AssetManager createPluginAssetManager(String apkPath) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, apkPath);
            return assetManager;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PluginInfo loadApk(String apkPath) {
        if (mPluginMap.get(apkPath) != null) {
            return mPluginMap.get(apkPath);
        }

        PluginInfo pluginInfo = new PluginInfo();
        pluginInfo.mClassLoader = createPluginDexClassLoader(apkPath);
        pluginInfo.mAsserManager = createPluginAssetManager(apkPath);
        pluginInfo.mResources = createPluginResources(apkPath);

        mPluginMap.put(apkPath,pluginInfo);
        return pluginInfo;
    }

    //为对应的插件创建 Resources 类
    private static Resources createPluginResources(String apkPath) {
        AssetManager assetManager = createPluginAssetManager(apkPath);

        Resources superResources = context.getResources();//宿主应用的 Resources 类
        Resources pluginResources = new Resources(assetManager,
                superResources.getDisplayMetrics(), superResources.getConfiguration());
        return pluginResources;

    }
}
