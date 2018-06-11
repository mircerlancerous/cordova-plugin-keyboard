# cordova-plugin-keyboard
Reads external keyboard input into a mobile device

Note: still in development. Currently for android only.

Note 2: it appears as though this is impossible with Cordova (as of Jan 2018). The appropriate objects are not exposed via Cordova to the plugin code. An alternative method of reading the USB device directly also does not work, as Android takes full control of these devices, and does not permit access via its USB classes.
