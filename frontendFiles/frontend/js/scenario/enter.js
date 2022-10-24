
// import type: "CUseStrict",

(function () {

    document.addEventListener("DOMContentLoaded", function () {

        var restorePasswordForm = cmpCore.addElement({
            name: "standartPopup",
            type: "CStandartPupupForm",
            container: ".form_popup",
            children: [
                {
                    name: "restorePasswordForm",
                    type: "CRestorePasswordForm",
                    container: ".form_wrapper",
                    children: [
                        {
                            name: "emailField",
                            type: "CAnimatedTextField",
                            container: ".email_field",
                            properties: {
                                regExp: /.+@.+\..+/i
                            },
                            model: {
                                labelText: "Ваш email",
                                name: "email",
                                theme: "text-input-wrapper__light",
                                type: "text"
                            }
                        },
                        {
                            name: "register_password_again",
                            type: "CSendButton",
                            container: ".restore_btn",
                            model: {
                                buttonText: "Отправить",
                                name: "ok",
                                elementClass: "max_width_148"
                            },
                            events: {
                                click: function (evt) {
                                    var self = this;

                                    var emailField = self.rootElm.findByName("emailField");

                                    if (!emailField.validate()) {
                                        return;
                                    }

                                    self.setWait();
                                    var data = {
                                        email: emailField.getValue(),
                                    };
                                    helper.getHttpPromise({
                                        method: "POST",
                                        url: "/api/auth/restorePassword",
                                        contentType: "application/json",
                                        jsonData: data
                                    }).then(function (response) {
                                        self.stopWait();
                                        var ans = JSON.parse(response);

                                        restorePasswordForm.showResult(ans.message);
                                    }, function (response) {
                                        self.stopWait();
                                        var ans = JSON.parse(response);
                                        self.setAlert(ans.errorMessage);
                                    });
                                }
                            }
                        }
                    ]
                }
            ]
        });

        var authPage = cmpCore.addElement({
            name: "authPage",
            type: "CAuthentificationPage",
            container: ".login_container",
            properties: {
                restorePasswordFrom: restorePasswordForm
            },
            children: [
                {
                    name: "loginForm",
                    type: "CLogin",
                    container: ".auth_tabs",
                    properties: {
                        hidden: false
                    },
                    model: {
                        isRegisterSuccess: helper.getParameterByName("register")
                    },
                    children: [
                        {
                            name: "loginField",
                            type: "CAnimatedTextField",
                            container: ".email_field",
                            properties: {
                                regExp: /.+@.+\..+/i
                            },
                            model: {
                                labelText: "Email",
                                name: "name",
                                theme: "text-input-wrapper__light",
                                type: "text"
                            },
                            events: {
                                keypress: function (evt) {
                                    if (evt.keyCode !== 13) {
                                        return;
                                    }

                                    authPage.findByName("passwordField").focus();
                                }
                            }
                        },
                        {
                            name: "passwordField",
                            type: "CAnimatedTextField",
                            container: ".password_field",
                            model: {
                                labelText: "Пароль",
                                name: "name",
                                theme: "text-input-wrapper__light",
                                type: "password"
                            },
                            events: {
                                keypress: function (evt) {
                                    if (evt.keyCode !== 13) {
                                        return;
                                    }

                                    authPage.findByName("enterBtn").click();
                                }
                            }
                        },
                        {
                            name: "enterBtn",
                            type: "CSendButton",
                            container: ".enter_btn",
                            model: {
                                buttonText: "Войти",
                                name: "ok",
                                elementClass: "max_width_148"
                            },
                            events: {
                                click: function (evt) {
                                    var enterBtn = this;
                                    var email = enterBtn.rootElm.findByName("loginField");
                                    var password = enterBtn.rootElm.findByName("passwordField");


                                    var emailVal = email.getValue();
                                    var passVal = password.getValue();

                                    if (!emailVal) {
                                        email.setAlert();
                                        return;
                                    }
                                    if (!passVal) {
                                        password.setAlert();
                                        return;
                                    }

                                    enterBtn.setWait();
                                    var data = {
                                        email: emailVal,
                                        password: passVal,
                                    };
                                    helper.getHttpPromise({
                                        method: "POST",
                                        url: "/api/auth/login",
                                        contentType: "application/json",
                                        jsonData: data
                                    }).then(function (response) {
                                        enterBtn.stopWait();
                                        var ans = JSON.parse(response);
                                        if (ans.email && ans.name) {
                                            if (document.location.href.indexOf("enter") !== -1) {
                                                document.location.href = "/analytics";
                                            } else {
                                                document.location.reload();
                                            }

                                            return;
                                        }
                                    }, function (response) {
                                        enterBtn.stopWait();
                                        var ans = JSON.parse(response);
                                        enterBtn.setAlert(ans.message);
                                    });
                                }
                            }
                        }
                    ]
                },
                {
                    name: "registrationForm",
                    type: "CRegistration",
                    container: ".auth_tabs",
                    properties: {
                        hidden: true
                    },
                    children: [
                        {
                            name: "register_name",
                            type: "CAnimatedTextField",
                            container: ".register_name",
                            events: {
                                keypress: function (evt) {
                                    if (evt.keyCode !== 13) {
                                        return;
                                    }

                                    this.rootElm.findByName("register_surname").focus();
                                }
                            },
                            model: {
                                labelText: "Ваше имя",
                                name: "name",
                                theme: "text-input-wrapper__light",
                                type: "text"
                            }
                        },
                        {
                            name: "register_surname",
                            type: "CAnimatedTextField",
                            container: ".register_surname",
                            events: {
                                keypress: function (evt) {
                                    if (evt.keyCode !== 13) {
                                        return;
                                    }
                                    this.rootElm.findByName("register_email").focus();
                                }
                            },
                            model: {
                                labelText: "Ваша фамилия",
                                name: "surname",
                                theme: "text-input-wrapper__light",
                                type: "text"
                            }
                        },
                        {
                            name: "register_email",
                            type: "CAnimatedTextField",
                            container: ".register_email",
                            properties: {
                                regExp: /.+@.+\..+/i
                            },
                            events: {
                                keypress: function (evt) {
                                    if (evt.keyCode !== 13) {
                                        return;
                                    }
                                    this.rootElm.findByName("register_password").focus();
                                }
                            },
                            model: {
                                labelText: "Ваш email",
                                name: "surname",
                                theme: "text-input-wrapper__light",
                                type: "text"
                            }
                        },
                        {
                            name: "register_password",
                            type: "CAnimatedTextField",
                            container: ".register_password",
                            events: {
                                keypress: function (evt) {
                                    if (evt.keyCode !== 13) {
                                        return;
                                    }
                                    this.rootElm.findByName("register_password_again").focus();
                                }
                            },
                            model: {
                                labelText: "Пароль",
                                name: "surname",
                                theme: "text-input-wrapper__light",
                                type: "password"
                            }
                        },
                        {
                            name: "register_password_again",
                            type: "CAnimatedTextField",
                            container: ".register_password_again",
                            events: {
                                keypress: function (evt) {
                                    if (evt.keyCode !== 13) {
                                        return;
                                    }
                                    this.rootElm.findByName("register_btn").click();
                                }
                            },
                            model: {
                                labelText: "Повтор пароля",
                                name: "name",
                                theme: "text-input-wrapper__light",
                                type: "password"
                            }
                        },
                        {
                            name: "register_btn",
                            type: "CSendButton",
                            container: ".register_btn",
                            model: {
                                buttonText: "Регистрация",
                                name: "ok",
                                elementClass: "max_width_148"
                            },
                            events: {
                                click: function (evt) {

                                    var registerName = this.rootElm.findByName("register_name");
                                    var registerSurname = this.rootElm.findByName("register_surname");
                                    var registerEmail = this.rootElm.findByName("register_email");
                                    var registerPassword = this.rootElm.findByName("register_password");
                                    var registerPasswordAgain = this.rootElm.findByName("register_password_again");
                                    var registerBtn = this.rootElm.findByName("register_btn");

                                    if (!registerName.validate() || !registerSurname.validate() || !registerEmail.validate()
                                        || !registerPassword.validate() || !registerPasswordAgain.validate()) {
                                        return;
                                    }

                                    if (registerPassword.getValue() != registerPasswordAgain.getValue()) {
                                        registerBtn.setAlert("Пароли не совпадают");
                                        return;
                                    }

                                    registerBtn.setWait();
                                    var data = {
                                        name: registerName.getValue(),
                                        surname: registerSurname.getValue(),
                                        email: registerEmail.getValue(),
                                        password: registerPassword.getValue(),
                                        confirmPassword: registerPasswordAgain.getValue()
                                    };
                                    helper.getHttpPromise({
                                        method: "POST",
                                        url: "/api/auth/registration",
                                        contentType: "application/json",
                                        jsonData: data
                                    }).then(function (response) {
                                        registerBtn.stopWait();
                                        var ans = JSON.parse(response);

                                        document.location.href = "enter?register=success";
                                    }, function (response) {
                                        registerBtn.stopWait();
                                        var ans = JSON.parse(response);
                                        registerBtn.setAlert(ans.message);
                                    });
                                }
                            }
                        }
                    ]
                }
            ]
        });

    cmpCore.drawAll();

    });

})();
