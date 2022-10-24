(function () {
    cmpCore.registryComponent({
        name: "CConfirm",
        templateId: "CConfirmTemplate",
        methods: {
            constructor: function () {
                let self = this;

                this.addChildren([{
                    name: "okButton",
                    type: "CSendButton",
                    container: ".ok_button",
                    model: {
                        buttonText: "Да"
                    },
                    events: {
                        click: function (evt) {
                            if (!self.temp.callback) {
                                console.log("okCallback не установлен.");
                                return;
                            }
                            self.temp.callback();
                        }
                    }
                }, {
                    name: "cancelButton",
                    type: "CSendButton",
                    container: ".cancel_button",
                    model: {
                        buttonText: "Отмена"
                    },
                    events: {
                        click: function (evt) {
                            self.parent.closeEvent.call(self.parent);
                        }
                    }
                }])
            },
            setOkCallback: function (callback) {
                this.temp.callback = callback;
            }
        },
        events: {

        }
    });
})();
