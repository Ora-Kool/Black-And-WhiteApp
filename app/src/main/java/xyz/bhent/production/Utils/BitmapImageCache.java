package xyz.bhent.production.Utils;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

/**
 * Created by etinge mabian on 7/26/16.
 * This class is meant to handle image caches
 */
public class BitmapImageCache extends LruCache<String, Bitmap> implements ImageCache {

    //method that get the default cache size
    public static int getDefaultCacheSize(){
        final int maxMemory = (int)(Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;
        return cacheSize;
    }

    //constructor
    public BitmapImageCache(){
        this(getDefaultCacheSize());
    }

    //constructor that take the default memory size
    public BitmapImageCache(int sizeInKiloB){
        super(sizeInKiloB);
    }

    //return the converted size of a given image
    @Override
    protected  int sizeOf(String key, Bitmap value){
        return value.getRowBytes() * value.getHeight() / 1024;
    }

    @Override
    public Bitmap getBitmap(String url){
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap){
        put(url, bitmap);
    }

}
