(function () {
    cmpCore.registryComponent({
        name: "CRestorePasswordForm",
        templateId: "CRestorePasswordFormTemplate",
        methods: {
            showForm: function () {

                if (this.temp.completed) {
                    this.containerElm.querySelector(".form_wrapper").style.display = "block";
                    this.containerElm.querySelector(".result_message").style.display = "none";
                }

                this.containerElm.style.display = "block";

                let overlay = this.containerElm.nextElementSibling;
                overlay.style.display = "block";

                overlay.addEventListener("click", this.closeEvent);
            },
            closeEvent: function (evt) {
                this.containerElm.style.display = "none";

                let overlay = this.containerElm.nextElementSibling;
                overlay.style.display = "none";
            },
            showResult: function (message) {
                this.containerElm.querySelector(".form_wrapper").style.display = "none";
                let resultMessage = this.containerElm.querySelector(".result_message");
                resultMessage.innerHTML = message;
                resultMessage.style.display = "block";

                this.temp.completed = true;
            }
        }
    });
})();