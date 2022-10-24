/**
 * Created by Lodbrok on 06.05.2017.
 */

(function () {
    cmpCore.registryComponent({
        name: "CSendButton",
        templateId: "CSendButtonTemplate",
        methods: {
            preAction: function (evt) {
                if (evt.target.tagName !== "BUTTON") {
                    return false;
                }

                return true;
            },
            click: function () {
                var button = this.containerElm.getElementsByTagName("BUTTON")[0];
                button.click();
            },
            setWait: function () {
                var tmpl = this.containerElm.getElementsByClassName("sent_button_wrapper")[0];

                tmpl.classList.toggle("sent_button_wrapper__hover");
                this.temp.timer = setInterval(function () {
                    tmpl.classList.toggle("sent_button_wrapper__animate");
                }, 1500);
                this.setDisabled();
            },
            stopWait: function () {
                var tmpl = this.containerElm.getElementsByClassName("sent_button_wrapper")[0];

                clearInterval(this.temp.timer);
                tmpl.classList.toggle("sent_button_wrapper__hover");
                if (tmpl.classList.contains("sent_button_wrapper__animate")) {
                    tmpl.classList.remove("sent_button_wrapper__animate");
                }
                this.setEnabled();
            },
            setAlert: function (message) {
                var label = this.containerElm.getElementsByClassName("sent_button_wrapper__alert")[0];

                label.innerHTML = message;
                label.style.display = "block";
            },
            removeAlert: function () {
                var label = this.containerElm.getElementsByClassName("sent_button_wrapper__alert")[0];
                label.style.display = "none";
            },
            setDisabled: function () {
                this.temp.lock = true;
                this.containerElm.removeEventListener("click", this.events.click);
            },
            setEnabled: function () {
                this.temp.lock = false;

                var tmpl = this.containerElm.querySelector(".sent_button_wrapper");
                if (tmpl.classList.contains("sent_button_wrapper__disabled")) {
                    tmpl.classList.remove("sent_button_wrapper__disabled");
                }
            },
            setGrey: function () {
                this.setDisabled();
                var tmpl = this.containerElm.querySelector(".sent_button_wrapper");
                tmpl.classList.add("sent_button_wrapper__disabled");
            },
            hide: function () {
                this.containerElm.classList.add("hidden");
            },
            show: function () {
                this.containerElm.classList.remove("hidden");
            }
        },
        events: {

        }
    });
})();
