(function () {
    let helper = {};
    let userHelper = {
        userInfo: {},
        userAbilities: null,
        permissions: {
            ADMIN: 2,
            SUPER_ADMIN: 3,
            LOAD_FILE: -1,
            DELETE_FILE: -2,
            CREATE_COURSE: -3
        }
    };

    const scriptInfo = {
        editor: "/ckeditor/ckeditor.js",
        yamaps: "https://api-maps.yandex.ru/2.1/?lang=ru_RU"
    };

    const scriptLoadedInfo = {
        editor: false
    };

    helper.getHttpPromise = function (config) {
        return new Promise(function(resolve, reject) {

            let xhr = new XMLHttpRequest();
            xhr.open(config.method, config.url, true);

            if (config.contentType) {
                xhr.setRequestHeader("Content-type", config.contentType);
            }

            xhr.onload = function() {
                if (this.status == 200) {
                    resolve(this.response);
                } else {
                    let error = new Error(this.statusText);
                    error.code = this.status;
                    reject(this.response, error);
                }
            };

            xhr.onerror = function() {
                reject(new Error("Network Error"));
            };

            if (config.jsonData) {
                xhr.send(JSON.stringify(config.jsonData));
                return;
            }

            if (config.multipartData) {
                xhr.send(config.multipartData);
                return;
            }

            xhr.send();
        });
    };

    helper.getParameterByName = function (name, url) {
        if (!url) url = window.location.href;
        name = name.replace(/[\[\]]/g, "\\$&");
        let regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, " "));
    };

    helper.deepClone = function (object) {
        let objectType = typeof object;
        let isAray = Array.isArray(object);

        if (objectType  === "object" && !isAray) {
            let result = {};

            for (let propName in object) {
                result[propName] = helper.deepClone(object[propName]);
            }

            return result;
        }

        if (objectType  === "object" && isAray) {
            let result = [];

            for (let i = 0; i < object.length; i++) {
                result[i] = helper.deepClone(object[i])
            }

            return result;
        }

        return object;
    };

    userHelper.getUserInfo = function () {

    };

    userHelper.getUserAbilities = function () {
        return new Promise(function(resolve, reject) {

            if (userHelper.userAbilities) {
                resolve(userHelper.userAbilities);
                return;
            }

            helper.getHttpPromise({
                method: "POST",
                url: "/api/private/user/getUserAbilities",
                contentType: "application/json"
            }).then(function (response) {
                var data = JSON.parse(response);

                userHelper.userAbilities = data;

                resolve(userHelper.userAbilities);
            });
        });
    };

    userHelper.hasPermission = function (permissionId) {
        return userHelper.userAbilities.indexOf(permissionId) !== -1;
    };

    helper.loadLibrary = function (libName) {

        if (scriptLoadedInfo[libName]) {
            return;
        }

        scriptLoadedInfo[libName] = true;

        let script = document.createElement("script");
        script.type = "text/javascript";
        script.src = scriptInfo[libName];

        document.body.appendChild(script);
    };

    window.CKEDITOR_BASEPATH = '/ckeditor/';
    helper.scriptInfo = scriptInfo;
    window.userHelper = userHelper;
    window.helper = helper;
})();
