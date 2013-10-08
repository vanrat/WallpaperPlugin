var WallpaperPlugin = {
    set: function(ms, successCallback, errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'WallpaperPlugin', // mapped to our native Java class called "Calendar"
            'setWallPaper', // with this action name
			[ms]
        );
     }
}
module.exports = WallpaperPlugin;