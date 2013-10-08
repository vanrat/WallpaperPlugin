package com.happy.wallpaperplugin;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class WallpaperPlugin extends CordovaPlugin {
    public final String ACTION_SET_WALLPAPER = "setWallPaper";
    @Override
    public boolean execute(String action, JSONArray arg1, CallbackContext callbackContext) {
        PluginResult result = new PluginResult(Status.INVALID_ACTION);
        if (action.equals(ACTION_SET_WALLPAPER)) {
            Context ctx = this.cordova.getActivity().getApplicationContext();
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(ctx);
            try {
                InputStream bitmap=null;
                try {
                    bitmap=this.cordova.getActivity().getAssets().open(/*"www/img/" + */arg1.getString(0));
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } //reference to image folder

                Bitmap bit=BitmapFactory.decodeStream(bitmap);
                wallpaperManager.setBitmap(bit);
                result = new PluginResult(Status.OK);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                result = new PluginResult(Status.ERROR, e.getMessage());
            }
        }
        callbackContext.sendPluginResult(result);
        return true;
    }
}