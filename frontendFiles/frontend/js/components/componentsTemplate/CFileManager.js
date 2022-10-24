/**
 * Created by stvolov-is on 09.03.2017.
 */

(function () {
    cmpCore.registryComponent({
        name: "CFileManager",
        templateId: "CFileManagerTemplate",
        init: function () {
            // Создать диспетчер callback для открытых окон.
            let self = this;

            self.callbackStore = {};

            let fileManagerCallbackDispatcher = {
                execCallback: function (cmpName, resultUrl) {
                    let element = cmpCore.findByName(cmpName);
                    self.callbackStore[cmpName].call(element, resultUrl);
                }
            };

            window.fileManagerCallbackDispatcher = fileManagerCallbackDispatcher;
        },
        methods: {
            constructor: function () {
                this.component.callbackStore[this.name] = this.fileCallback;
            },
            /*addCallback: function () {

            },*/
            getValue: function () {
                const input = this.containerElm.getElementsByTagName("INPUT")[0];
                return input && input.value;
            },
            setValue: function (value) {
                this.containerElm.getElementsByTagName("INPUT")[0].value = value;
            },
            setAlert: function (message) {
                var input = this.containerElm.getElementsByTagName("INPUT")[0];
                input.classList.add("alert_standart_input");

                /*if (!message) {
                    return;
                }
                var label = this.containerElm.getElementsByClassName("text-input-wrapper-alert")[0];

                label.innerHTML = message;
                label.style.display = "block";*/

            },
            focus: function () {
                var input = this.containerElm.getElementsByTagName("INPUT")[0];
                input.focus();
            },
            validate: function () {
                var value = this.getValue();

                if (value == "") {
                    this.setAlert("Поле не должно быть пустым");
                    return false;
                }
                if (this.properties.regExp) {
                    if (!value.match(this.properties.regExp)) {
                        this.setAlert("Поле заполнено не правильно");
                        return false;
                    }
                }

                return true;
            },
            hide: function () {
                this.containerElm.classList.add("hidden");
            },
            show: function () {
                this.containerElm.classList.remove("hidden");
            },
            disable: function () {
                if (!this.isDrawn) {
                    return;
                }

                var input = this.containerElm.getElementsByTagName("INPUT")[0];
                input.value = "";
            },
            setDisabled: function (disabled) {
                if (!this.isDrawn) {
                    return;
                }

                var input = this.containerElm.getElementsByTagName("INPUT")[0];
                input.disabled = disabled;

                if (disabled) {
                    input.parentNode.classList.add("disabled");
                } else {
                    input.parentNode.classList.remove("disabled");
                }
            },
            fileCallback: function (fileUrl) {
                let input = this.containerElm.querySelector(".standart_input");
                input.value = fileUrl;

                console.log("file is pucked: " + fileUrl);
                console.log(this);
            }
        },
        events: {
            click: function (e) {
                var target = e.target;

                if (!target.closest(".choose_file")) {
                    //console.log("Кликнул на текстовое поле.");
                    return;
                }

                let self = this;
                let fileWindow = window.open("/frames/fileManager.html?source=innerApp&cmpName=" + self.name + "&rootDir=public", "fileManager",
                    "width=" + (window.innerWidth / 2) + ",height=550");

                fileWindow.addEventListener("afterLoad", function () {
                    console.log("afterLoad on childWindow");
                    fileWindow.setFileCallback(function (fileUrl) {
                        return self.fileCallback(fileUrl);
                    });
                });

                fileWindow.addEventListener("DOMContentLoaded", function () {
                    console.log("DOMContentLoaded on childWindow");
                });

            },

            input: function (evt) {
                if (this.properties.regExp) {

                    var input = this.containerElm.querySelector("input");
                    let value = input.value;

                    if (!value.match(this.properties.regExp)) {
                        this.setAlert("Поле заполнено не правильно");
                        return false;
                    }
                }
            }
        },
        afterRender: function () {

        }
    });
})();
