/**
 * Created by stvolov-is on 09.03.2017.
 */

(function () {
    cmpCore.registryComponent({
        name: "CStandartFormField",
        templateId: "CStandartFormFieldTemplate",
        methods: {
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

                if (message) {
                    let wrapperAlert = this.containerElm.querySelector(".input_wrapper_alert");
                    wrapperAlert.classList.remove("hidden");
                    wrapperAlert.innerHTML = message;
                }
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
            }
        },
        events: {
/*            click: function (e) {
                var target = e.target;
                //console.log(target.tagName);
                if (target.tagName == "INPUT") {
                    //console.log("Кликнул на текстовое поле.");
                    return;
                }

                if (target.tagName == "LABEL") {
                    var target = e.target;
                    if (target.tagName == "LABEL")  {
                        var wrapper = this.containerElm.querySelector(".text-input-wrapper");
                        if (!wrapper) {
                            console.log("No wrapper");
                            return;
                        }
                        wrapper.classList.add("text-input-wrapper__filled-input");
                        var input = this.containerElm.querySelector("input");
                        input.focus();
                        return;
                    }
                    return;
                }
            },*/
/*            focus: function (e) {
                var target = e.target;
                //console.log(target.tagName);
                if (target.tagName == "INPUT" || target.tagName == "LABEL")  {
                    var wrapper = this.containerElm.querySelector(".text-input-wrapper");
                    if (!wrapper) {
                        console.log("No wrapper");
                        return;
                    }
                    wrapper.classList.add("text-input-wrapper__filled-input");
                    return;
                }
            },
            blur: function (e) {
                var target = e.target;
                //console.log(target.tagName);
                if (target.tagName == "INPUT")  {
                    if (target.value == "") {
                        target.parentNode.classList.remove("text-input-wrapper__filled-input");
                    }
                }
            },*/


            input: function (evt) {

                if (this.properties.regExp) {

                    var input = this.containerElm.querySelector("input");
                    let value = input.value;

                    if (!value.match(this.properties.regExp)) {
                        this.setAlert("Поле заполнено не правильно");
                        return false;
                    }
                }

                /*var alertLabel = this.containerElm.querySelector(".text-input-wrapper-alert");

                if (input.classList.contains("text-input-wrapper_alert")) {
                    input.classList.remove("text-input-wrapper_alert");
                }

                if (alertLabel.style.display == "block") {
                    alertLabel.style.display = "none";
                }

                if (input.focused) {
                    return;
                }

                input.focus();*/
            }
        },
        afterRender: function () {

        }
    });
})();
