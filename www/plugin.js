Plugin.keyboardPlugin = {
  onKeyUp: function(callback, onFail){
    cordova.exec(callback, onFail, 'KeyboardPlugin', 'keyUp', []);
  },
  
  onKeyDown: function(callback, onFail){
    cordova.exec(callback, onFail, 'KeyboardPlugin', 'keyDown', []);
  }
};
