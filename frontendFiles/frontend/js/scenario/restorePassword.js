(function () {

    document.addEventListener("DOMContentLoaded", function () {


        var popupForm = cmpCore.addElement({
            name: "standartPopup",
            type: "CStandartPupupForm",
            container: ".form_popup",
            properties: {
                isSuccess: false
            },
            methods: {
                setSuccess: function () {
                    this.properties.isSuccess = true;
                },
                onClose: function () {
                    // Нужно редиректить на Страницу логина.
                    if (this.properties.isSuccess) {
                        window.location.href = "/enter";
                    }
                }
            }
        });

        var restorePasswordPage = cmpCore.addElement({
            name: "restorePasswordPage",
            type: "CRestorePasswordPage",
            container: ".login_container",
            children: [
                {
                    name: "register_password",
                    type: "CAnimatedTextField",
                    container: ".register_password",
                    model: {
                        labelText: "Новый пароль",
                        name: "email",
                        theme: "text-input-wrapper__light",
                        type: "password"
                    }
                },
                {
                    name: "register_password_again",
                    type: "CAnimatedTextField",
                    container: ".register_password_again",
                    model: {
                        labelText: "Подтверждение пароля",
                        name: "email",
                        theme: "text-input-wrapper__light",
                        type: "password"
                    }
                },
                {
                    name: "changePasswordBtn",
                    type: "CSendButton",
                    container: ".change_password_btn",
                    model: {
                        buttonText: "Сменить пароль",
                        name: "ok",
                        elementClass: "max_width_184"
                    },
                    events: {
                        click: function (evt) {
                            var self = this;

                            var passwordField = self.rootElm.findByName("register_password");
                            var passwordAgainField = self.rootElm.findByName("register_password_again");

                            let password = passwordField.getValue();
                            let passwordAgain = passwordAgainField.getValue();

                            if (password !== passwordAgain) {
                                passwordAgain.setAlert("Введёные пароли не совпадают.");
                                return;
                            }

                            let key = helper.getParameterByName("key");

                            self.setWait();
                            var data = {
                                restoreLinkKey: key,
                                newPassword: password,
                                confirmPassword: passwordAgain
                            };
                            helper.getHttpPromise({
                                method: "POST",
                                url: "/api/auth/changePassword",
                                contentType: "application/json",
                                jsonData: data
                            }).then(function (response) {
                                self.stopWait();
                                var ans = JSON.parse(response);

                                popupForm.properties.isSuccess = true;
                                popupForm.showResult(ans.message);
                            }, function (response) {
                                self.stopWait();
                                var ans = JSON.parse(response);
                                self.setAlert(ans.errorMessage);
                            });
                        }
                    }
                }
            ]
        });

        cmpCore.drawAll();

    });

})();
