package com.otb.cordova.keyboard;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.view.KeyEvent;

public class KeyboardPlugin extends CordovaPlugin  {
    private CallbackContext keyup_callback = null;
    private CallbackContext keydown_callback = null;
    
    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
        
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
    
    OnKeyListener keyListener = new OnKeyListener() {
	    @Override
	    public boolean onKey(View v, int keyCode, KeyEvent event) {
	        if (event.getAction() == KeyEvent.ACTION_UP) {
	            onKeyUp(keyCode, event);
	        }
	        else if (event.getAction() == KeyEvent.ACTION_DOWN) {
	        	onKeyDown(keyCode, event);
	        }
	        return false;
	    }
	};
    
    public boolean onKeyDown(int keyCode, KeyEvent event){
    	if(keydown_callback == null){
    		return true;
    	}
        PluginResult result = new PluginResult(PluginResult.Status.OK, Integer.toString(keyCode));
        result.setKeepCallback(true);
        keydown_callback.sendPluginResult(result);
        return false;
    }
    
    public boolean onKeyUp(int keyCode, KeyEvent event){
    	if(keyup_callback == null){
    		return true;
    	}
        PluginResult result = new PluginResult(PluginResult.Status.OK, Integer.toString(keyCode));
        result.setKeepCallback(true);
        keyup_callback.sendPluginResult(result);
        return false;
    }
}
