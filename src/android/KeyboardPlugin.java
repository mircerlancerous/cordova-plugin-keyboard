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

public class KeyboardPlugin extends CordovaPlugin{// implements OnKeyListener{
    private CallbackContext keyup_callback = null;
    private CallbackContext keydown_callback = null;
    private CallbackContext allkeys_callback = null;
    private View currentView = null;
    
    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        // init code for view event listening
        
        this.currentView = webView.getView();
        this.currentView.setOnKeyListener(
                new View.OnKeyListener(){
                    @Override
                    public boolean onKeyUp(int keyCode, KeyEvent event){
                        return doKeyUp(keyCode, event);
                    }
                    @Override
                    public boolean onKeyDown(int keyCode, KeyEvent event){
                        return doKeyDown(keyCode, event);
                    }
                }
            );
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
            if(this.doKeyUp(85,null)){
                result = new PluginResult(PluginResult.Status.OK, "key up test success");
            }
            else{
                result = new PluginResult(PluginResult.Status.ERROR, "key up test failed");
            }
        }
        else if(action.equalsIgnoreCase("keyDown")){
            this.keydown_callback = callbackContext;
        }
        else if(action.equalsIgnoreCase("stopKeyDown")){
        	this.keydown_callback = null;
        }
        else if(action.equalsIgnoreCase("testKeyDown")){
            if(this.doKeyDown(68,null)){
                result = new PluginResult(PluginResult.Status.OK, "key down test success");
            }
            else{
                result = new PluginResult(PluginResult.Status.ERROR, "key down test failed");
            }
        }
    /*    else if(action.equalsIgnoreCase("catchAllKeyEvents")){
            this.allkeys_callback = callbackContext;
        }*/
        else if(action.equalsIgnoreCase("getStatus")){
            String msg = "status:";
            if(this.keyup_callback != null){
                msg += "keyup active - ";
            }
            else{
                msg += "keyup inactive - ";
            }
            if(this.keydown_callback != null){
                msg += "keydown active";
            }
            else{
                msg += "keydown inactive";
            }
            result = new PluginResult(PluginResult.Status.OK,msg);
        }
        else {
            // invalid action
            return false;
        }
        
        callbackContext.sendPluginResult(result);

        return true;
    }
    /*
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP) {
            return this.onKeyUp(keyCode, event);
        }
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            return this.onKeyDown(keyCode, event);
        }
        return this.onCatchAllKeyEvents(keyCode, event);
    }
    *//*
    public boolean onCatchAllKeyEvents(int keyCode, KeyEvent event){
        if(this.allkeys_callback != null){
            return false;
        }
        try{
            String str = String.valueOf((char)event.getUnicodeChar());
            PluginResult result = new PluginResult(PluginResult.Status.OK, str);
            result.setKeepCallback(true);
            this.allkeys_callback.sendPluginResult(result);
        }
        catch(Exception e){
            PluginResult result = new PluginResult(PluginResult.Status.ERROR, "error in all key event handling");
            this.allkeys_callback.sendPluginResult(result);
        }
        return true;
    }
    */
    public boolean doKeyDown(int keyCode, KeyEvent event){
    	if(this.keydown_callback == null){
    		return false;
    	}
    	try{
            String str = "";
            if(event != null){
                str = String.valueOf((char)event.getUnicodeChar());
            }
            else{
                str = String.valueOf(Character.toChars(keyCode)[0]);
            }
            PluginResult result = new PluginResult(PluginResult.Status.OK, str);
            result.setKeepCallback(true);
            this.keydown_callback.sendPluginResult(result);
    	}
    	catch(Exception e){
    	    PluginResult result = new PluginResult(PluginResult.Status.ERROR, "error in keydown handling");
            this.keydown_callback.sendPluginResult(result);
            return false;
    	}
        return true;
    }
    
    public boolean doKeyUp(int keyCode, KeyEvent event){
    	if(this.keyup_callback == null){
    		return false;
    	}
    	try{
            String str = "";
            if(event != null){
                str = String.valueOf((char)event.getUnicodeChar());
            }
            else{
                str = String.valueOf(Character.toChars(keyCode)[0]);
            }
            PluginResult result = new PluginResult(PluginResult.Status.OK, str);
            result.setKeepCallback(true);
            this.keyup_callback.sendPluginResult(result);
    	}
    	catch(Exception e){
    	    PluginResult result = new PluginResult(PluginResult.Status.ERROR, "error in keyup handling");
            this.keyup_callback.sendPluginResult(result);
            return false;
    	}
        return true;
    }
}
