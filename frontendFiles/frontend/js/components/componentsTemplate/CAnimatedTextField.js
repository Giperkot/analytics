/**
 * Created by stvolov-is on 09.03.2017.
 */

(function () {
    cmpCore.registryComponent({
        name: "CAnimatedTextField",
        templateId: "CAnimatedTextFieldTemplate",
        methods: {
            /*constructor: function () {
                let self = this;

                if (self.model && self.model.disabled) {
                    self.temp.disabled = true;
                }
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
                input.classList.add("text-input-wrapper_alert");

                if (!message) {
                    return;
                }
                var label = this.containerElm.getElementsByClassName("text-input-wrapper-alert")[0];

                label.innerHTML = message;
                label.style.display = "block";

            },
            focus: function () {
                if (this.model.disabled) {
                    return;
                }
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
            setEnabled: function () {
                this.model.disabled = false;

                let wrapper = this.containerElm.querySelector(".text-input-wrapper");
                wrapper.classList.add("text-input-wrapper__disabled");
                wrapper.querySelector("input").disabled = false;
            },
            setDisabled: function () {
                this.model.disabled = true;

                let wrapper = this.containerElm.querySelector(".text-input-wrapper");
                wrapper.classList.remove("text-input-wrapper__disabled");
                wrapper.querySelector("input").disabled = true;
            }
        },
        events: {
            click: function (e) {
                var target = e.target;
                //console.log(target.tagName);
                if (target.tagName === "INPUT") {
                    //console.log("Кликнул на текстовое поле.");
                    return;
                }

                if (target.tagName === "LABEL" && !this.model.disabled) {
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
            },
            focus: function (e) {
                var target = e.target;
                //console.log(target.tagName);
                if (target.tagName === "INPUT" || target.tagName === "LABEL")  {
                    var wrapper = this.containerElm.querySelector(".text-input-wrapper");
                    if (!wrapper) {
                        console.log("No wrapper");
                        return;
                    }
                    if (this.model.disabled) {
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
            },


            input: function (evt) {

                var input = this.containerElm.querySelector("input");
                var alertLabel = this.containerElm.querySelector(".text-input-wrapper-alert");

                if (input.classList.contains("text-input-wrapper_alert")) {
                    input.classList.remove("text-input-wrapper_alert");
                }

                if (alertLabel.style.display == "block") {
                    alertLabel.style.display = "none";
                }

                if (input.focused) {
                    return;
                }

                input.focus();
            }
        },
        afterRender: function () {
            var self = this;

            setTimeout(function () {
                var autofilledInput = self.containerElm.querySelector("input:-internal-autofill-selected");
                if (autofilledInput) {
                    var wrapper = self.containerElm.querySelector(".text-input-wrapper");
                    if (!wrapper) {
                        return;
                    }
                    wrapper.classList.add("text-input-wrapper__filled-input");
                }

            }, 100);
        }
    });
})();
