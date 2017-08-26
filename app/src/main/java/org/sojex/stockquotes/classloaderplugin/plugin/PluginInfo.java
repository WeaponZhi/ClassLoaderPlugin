package org.sojex.stockquotes.classloaderplugin.plugin;

import android.content.res.Resources;
import android.content.res.AssetManager;
import dalvik.system.DexClassLoader;

/**
 * PluginInfo 插件实体类
 * author:张冠之
 * time: 2017/8/26 下午7:52
 * e-mail: guanzhi.zhang@sojex.cn
 */

public class PluginInfo {
    //插件实体需要管理的资源，也可以管理很多其他的资源
    public DexClassLoader mClassLoader;
    public AssetManager mAsserManager;
    public Resources mResources;
}
