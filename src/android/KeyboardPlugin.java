package com.otb.cordova.keyboard;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.KeyEvent;
import android.view.*;

public class KeyboardPlugin extends CordovaPlugin implements OnKeyListener{
    private CallbackContext keyup_callback = null;
    private CallbackContext keydown_callback = null;
    private View currentView = null;
    
    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
        if(this.currentView == null){
        	currentView = this.webView.getView();
        	currentView.setOnKeyListener(this);
        }
        
        if (action.equalsIgnoreCase("keyUp")) {
            keyup_callback = callbackContext;
        }
        else if(action.equalsIgnoreCase("stopKeyUp")){
        	keyup_callback = null;
        }
        else if(action.equalsIgnoreCase("keyDown")){
            keydown_callback = callbackContext;
        }
        else if(action.equalsIgnoreCase("stopKeyDown")){
        	keydown_callback = null;
        }
        else {
            // invalid action
            return false;
        }
        
        PluginResult result = new PluginResult(PluginResult.Status.NO_RESULT);
        result.setKeepCallback(true);
        callbackContext.sendPluginResult(result);

        return true;
    }
    
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP) {
            KeyUp(keyCode, event);
        }
        else if (event.getAction() == KeyEvent.ACTION_DOWN) {
        	KeyDown(keyCode, event);
        }
        return false;
    }
    
    private boolean KeyDown(int keyCode, KeyEvent event){
    	if(keydown_callback == null){
    		return true;
    	}
        PluginResult result = new PluginResult(PluginResult.Status.OK, Integer.toString(keyCode));
        result.setKeepCallback(true);
        keydown_callback.sendPluginResult(result);
        return false;
    }
    
    private boolean KeyUp(int keyCode, KeyEvent event){
    	if(keyup_callback == null){
    		return true;
    	}
        PluginResult result = new PluginResult(PluginResult.Status.OK, Integer.toString(keyCode));
        result.setKeepCallback(true);
        keyup_callback.sendPluginResult(result);
        return false;
    }
}
