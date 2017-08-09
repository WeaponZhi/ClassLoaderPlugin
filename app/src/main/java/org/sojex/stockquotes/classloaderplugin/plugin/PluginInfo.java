package org.sojex.stockquotes.classloaderplugin.plugin;

import android.content.res.Resources;
import android.content.res.AssetManager;
import dalvik.system.DexClassLoader;

/**
 * Created by admin on 2017/8/9.
 */

public class PluginInfo {

    public DexClassLoader mClassLoader;
    public AssetManager mAsserManager;
    public Resources mResources;
}
