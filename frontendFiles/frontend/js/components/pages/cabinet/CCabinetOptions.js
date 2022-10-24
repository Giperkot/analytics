(function () {
    cmpCore.registryComponent({
        name: "CCabinetOptions",
        templateId: "CCabinetOptionsTemplate",
        methods: {
            showChangePassword: function () {
                let newPasswordField = this.findByName("newPassword");
                let newPasswordAgainField = this.findByName("newPasswordAgain");
                let passwordField = this.findByName("password");

                if (newPasswordField.isDrawn) {
                    newPasswordField.show();
                } else {
                    newPasswordField.render(this.containerElm);
                    newPasswordField.bind();
                }

                if (newPasswordAgainField.isDrawn) {
                    newPasswordAgainField.show();
                } else {
                    newPasswordAgainField.render(this.containerElm);
                    newPasswordAgainField.bind();
                }

                passwordField.setDisabled(false);
            },
            hideChangePassword: function () {
                let newPasswordField = this.findByName("newPassword");
                let newPasswordAgainField = this.findByName("newPasswordAgain");
                let passwordField = this.findByName("password");

                newPasswordField.hide();
                newPasswordAgainField.hide();

                passwordField.setDisabled(true);
                passwordField.setValue("****");
            },
            saveUserInfo: function (evt) {
                
                if (evt.target.tagName !== "BUTTON") {
                    return;
                }

                let self = this;

                let lastNameField = this.findByName("lastName");
                let firstNameField = this.findByName("firstName");
                let emailField = this.findByName("email");

                let passwordField = this.findByName("password");
                let newPasswordField = this.findByName("newPassword");
                let newPasswordAgainField = this.findByName("newPasswordAgain");
                let changePasswordBtn = this.findByName("changePasswordBtn");

                let lastName = lastNameField.getValue();
                let firstName = firstNameField.getValue();
                let email = emailField.getValue();
                let password = passwordField.getValue();
                let newPassword = newPasswordField.getValue();
                let newPasswordAgain = newPasswordAgainField.getValue();
                let disablePassword = changePasswordBtn.properties.disablePassword;

                if (!lastName) {
                    lastNameField.setAlert("Нужно ввести Фамилию");
                    return;
                }
                if (!firstName) {
                    firstNameField.setAlert("Нужно ввести Имя");
                    return;
                }

                if (!disablePassword) {
                    if (!password) {
                        passwordField.setAlert("Нужно ввести текущий пароль");
                        return;
                    }

                    if (!newPassword) {
                        passwordField.setAlert("Нужно ввести Новый пароль");
                        return;
                    }

                    if (!newPasswordAgain) {
                        passwordField.setAlert("Нужно ввести Новый пароль ещё раз");
                        return;
                    }

                    if (newPassword !== newPasswordAgain) {
                        passwordField.setAlert("Пароли не совпадают");
                        return;
                    }
                }

                let saveUserBtn = self.findByName("saveUserBtn");
                saveUserBtn.setWait();

                helper.getHttpPromise({
                    method: "POST",
                    url: "/api/private/user/saveUserInfo",
                    contentType: "application/json",
                    jsonData: {
                        surname: lastName,
                        name: firstName,
                        email: email,
                        password: password,
                        newPassword: newPassword,
                        newPasswordAgain: newPasswordAgain,
                        disablePassword: disablePassword
                    }
                }).then(function (response) {
                    saveUserBtn.stopWait();

                    var data = JSON.parse(response);

                    if (data.success) {
                        saveUserBtn.setAlert("Информация успешно сохранена");
                    } else {
                        saveUserBtn.setAlert(data.message);
                    }
                }, function (response) {
                    saveUserBtn.stopWait();

                    var data = JSON.parse(response);
                    saveUserBtn.setAlert(data.message);
                });

            }
        },
        events: {

        }
    });
})();
