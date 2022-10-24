(function () {
    cmpCore.registryComponent({
        name: "CStandartPupupForm",
        templateId: "CStandartPupupFormTemplate",
        methods: {
            constructor: function () {
                this.temp.activeComponent = null;
            },
            setActiveComponent: function (cmpName) {
                let child = this.findByName(cmpName);

                if (this.temp.activeComponent === cmpName) {
                    return;
                }

                if (this.temp.activeComponent) {
                    let activeChild = this.findByName(this.temp.activeComponent);
                    activeChild.hide();
                }

                this.temp.activeComponent = cmpName;

                if (!this.isDrawn) {
                    // Ещё не отрисован popupWindow, отрендерятся вместе.
                    child.properties.hidden = false;
                    return;
                }

                child.show();
            },
            showForm: function (rerender) {
                let self = this;

                if (rerender) {
                    self.finalize();
                    self.draw(self.containerElm);
                    self.bind();
                }

                if (this.temp.completed) {
                    this.containerElm.querySelector(".form_wrapper").style.display = "block";
                    this.containerElm.querySelector(".result_message").style.display = "none";
                }

                this.containerElm.style.display = "block";

                let overlay = this.containerElm.nextElementSibling;
                overlay.style.display = "block";

                overlay.addEventListener("click", function () {
                    return self.closeEvent.call(self);
                });
            },
            closeEvent: function (evt) {
                this.containerElm.style.display = "none";

                let overlay = this.containerElm.nextElementSibling;
                overlay.style.display = "none";
            },
            showResult: function (message, sizeMode) {
                this.containerElm.querySelector(".form_wrapper").style.display = "none";

                if (sizeMode === "small") {
                    // Сбросить все классы с контейнера.
                    this.containerElm.className = this.container.substring(1) + " article_popup__small";
                }

                let resultMessage = this.containerElm.querySelector(".result_message");
                resultMessage.innerHTML = message;
                resultMessage.style.display = "block";

                this.temp.completed = true;
            },
            setWait: function () {
                this.containerElm.querySelector(".loader_overlay").style.display = "block";
                this.containerElm.querySelector(".loader").style.display = "block";
            },
            stopWait: function () {
                this.containerElm.querySelector(".loader_overlay").style.display = "none";
                this.containerElm.querySelector(".loader").style.display = "none";
            }
        }
    });
})();
