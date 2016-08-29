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
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        // init code for view event listening
        
        this.currentView = webView.getView();
        this.currentView.setOnKeyListener(this);
        
        // init code for activity event listening
        /*
        Activity act = cordova.getActivity();
        act.onKeyUp = this.onKeyUp;
        act.onKeyDown = this.onKeyDown;
        */
    }
    
    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
        PluginResult result = new PluginResult(PluginResult.Status.NO_RESULT);
        result.setKeepCallback(true);
        
        if (action.equalsIgnoreCase("keyUp")) {
            this.keyup_callback = callbackContext;
        }
        else if(action.equalsIgnoreCase("stopKeyUp")){
        	this.keyup_callback = null;
        }
        else if(action.equalsIgnoreCase("testKeyUp")){
            result = new PluginResult(PluginResult.Status.OK);
            this.onKeyUp(85,null);
        }
        else if(action.equalsIgnoreCase("keyDown")){
            this.keydown_callback = callbackContext;
        }
        else if(action.equalsIgnoreCase("stopKeyDown")){
        	this.keydown_callback = null;
        }
        else if(action.equalsIgnoreCase("testKeyDown")){
            result = new PluginResult(PluginResult.Status.OK);
            this.onKeyDown(68,null);
        }
        else {
            // invalid action
            return false;
        }
        
        callbackContext.sendPluginResult(result);

        return true;
    }
    
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP) {
            return this.onKeyUp(keyCode, event);
        }
        else if (event.getAction() == KeyEvent.ACTION_DOWN) {
            return this.onKeyDown(keyCode, event);
        }
        return false;
    }
    
    //@Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
    	if(this.keydown_callback == null){
    		return false;
    	}
        PluginResult result = new PluginResult(PluginResult.Status.OK, Integer.toString(keyCode));
        result.setKeepCallback(true);
        this.keydown_callback.sendPluginResult(result);
        return true;
    }
    
    //@Override
    public boolean onKeyUp(int keyCode, KeyEvent event){
    	if(this.keyup_callback == null){
    		return false;
    	}
        PluginResult result = new PluginResult(PluginResult.Status.OK, Integer.toString(keyCode));
        result.setKeepCallback(true);
        this.keyup_callback.sendPluginResult(result);
        return true;
    }
}
