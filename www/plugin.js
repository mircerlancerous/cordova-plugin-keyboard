Plugin.keyboardPlugin = {
  onKeyUp: function(callback, onFail){
    cordova.exec(callback, onFail, 'KeyboardPlugin', 'keyUp', []);
  },
  
  onKeyDown: function(callback, onFail){
    cordova.exec(callback, onFail, 'KeyboardPlugin', 'keyDown', []);
  },
  
  stopKeyUp: function(callback, onFail){
  	cordova.exec(callback, onFail, 'KeyboardPlugin', 'stopKeyUp', []);
  },
  
  stopKeyDown: function(callback, onFail){
  	cordova.exec(callback, onFail, 'KeyboardPlugin', 'stopKeyDown', []);
  },
  
  testKeyUp: function(callback, onFail){
    cordova.exec(callback, onFail, 'KeyboardPlugin', 'testKeyUp', []);
  },
  
  testKeyDown: function(callback, onFail){
    cordova.exec(callback, onFail, 'KeyboardPlugin', 'testKeyDown', []);
  },
  
  catchAllKeyEvents: function(callback, onFail){
    cordova.exec(callback, onFail, 'KeyboardPlugin', 'catchAllKeyEvents', []);
  },
  
  getStatus: function(callback, onFail){
    cordova.exec(callback, onFail, 'KeyboardPlugin', 'getStatus', []);
  }
};
