// import type: "CSingleMenu",

// import type: "CAnimatedTextField",
// import type: "CSendButton",
// import type: "CoursesConst",
// import type: "CQuestionList",
// import type: "CLocalComboField",
// import type: "CCheckbox",
// import type: "CTextEditor",
// import type: "CStandartFormField",
// import type: "CRadioButton",

(function () {

    document.addEventListener("DOMContentLoaded", function () {

        window.onpopstate = function(event) {
            pageSwitcher.loadPageByUrl();
        };

        var popupForm = cmpCore.addElement({
            name: "standartPopup",
            type: "CStandartPupupForm",
            container: ".article_popup",
            properties: {
                isSuccess: false
            },
            methods: {
                showConfirm: function (config) {
                    this.setActiveComponent("confirmWindow");
                    let confirmWindow = this.findByName("confirmWindow");
                    confirmWindow.setOkCallback(config.callback);

                    let articlePopup = this.containerElm.closest(".article_popup");
                    articlePopup.className = "article_popup article_popup__small";
                    // articlePopup.classList.add("article_popup__small");

                    this.showForm(true);
                },
                showDistrictEditor: function () {
                    this.setActiveComponent("districtEditor");

                    let articlePopup = this.containerElm.closest(".article_popup");
                    articlePopup.className = "article_popup article_popup__middle_up";

                    this.showForm(true);
                }
            },
            children: [
                {
                    name: "confirmWindow",
                    type: "CConfirm",
                    container: ".form_wrapper",
                    properties: {
                        hidden: true
                    }
                }, {
                    name: "districtEditor",
                    type: "CDistrictEditor",
                    templateId: "CDistrictEditorTemplate",
                    container: ".form_wrapper",
                    properties: {
                        hidden: true
                    },
                    children: [
                        {
                            name: "districtNameField",
                            type: "CStandartFormField",
                            container: ".name_textfield",
                            model: {
                                labelText: "Имя"
                            }
                        }, {
                            name: "districtParentCombo",
                            type: "CLocalComboField",
                            container: ".parent_combobox",
                            model: {
                                labelText: "Входит в состав"
                            }
                        }, {
                            name: "saveDistrictBtn",
                            type: "CSendButton",
                            container: ".save_district_btn",
                            model: {
                                buttonText: "Сохранить"
                            },
                            events: {
                                click: function (evt) {
                                    this.parent.saveOrCreateDistrict(popupForm);
                                }
                            }
                        }
                    ],
                    addAfterRender: function () {
                        helper.loadLibrary("yamaps");
                    }
                }
            ]
        });

        let cabinetMain = cmpCore.addElement({
            name: "cabinetMain",
            type: "CCabinetMain",
            container: ".content",
            children: [
                {
                    name: "pageSwitcher",
                    type: "CPageSwitcher",
                    container: ".content_column",
                    properties: {
                        defaultPage: "cabinetOptions"
                    },
                    model: {
                        theme: ""
                    },

                    methods: {
                        loadPagePageByUrlParams: function (urlParams) {
                            let pathName = urlParams.pathName;

                            if (pathName.endsWith("/")) {
                                pathName = pathName.substring(0, pathName.length - 1);
                            }

                            let toPage;
                            switch (pathName) {
                                case "/cabinet/roles":
                                    toPage = "cabinetRoles";
                                    break;
                                case "/cabinet/users":
                                    toPage = "cabinetUsers";
                                    break;
                                case "/cabinet/districts":
                                    toPage = "cabinetDistricts";
                                    break;

                                case "cabinet/options":
                                default:
                                    toPage = this.properties.defaultPage;
                                    break;
                            }

                            this.changePage({
                                toPage: toPage,
                                url: urlParams.pathName
                            });
                        },
                    },

                    children: [
                        {
                            name: "cabinetOptions",
                            type: "CCabinetOptions",
                            container: ".page_switcher",
                            children: [
                                {
                                    name: "lastName",
                                    type: "CStandartFormField",
                                    container: ".last_name",
                                    model: {
                                        labelText: "Фамилия",
                                        theme: "width_63",
                                        value: ""
                                    }
                                }, {
                                    name: "firstName",
                                    type: "CStandartFormField",
                                    container: ".first_name",
                                    model: {
                                        labelText: "Имя",
                                        theme: "width_63",
                                        value: ""
                                    }
                                }, {
                                    name: "email",
                                    type: "CStandartFormField",
                                    container: ".email",
                                    model: {
                                        labelText: "email",
                                        disabled: true,
                                        theme: "width_63",
                                        value: ""
                                    }
                                }, {
                                    name: "password",
                                    type: "CStandartFormField",
                                    container: ".password",
                                    model: {
                                        labelText: "Пароль",
                                        disabled: true,
                                        value: "****",
                                        type: "password"
                                    }
                                }, {
                                    name: "changePasswordBtn",
                                    type: "CSendButton",
                                    container: ".change_password_btn",
                                    properties: {
                                        disablePassword: true
                                    },
                                    model: {
                                        buttonText: "Сменить пароль",
                                        name: "ok",
                                        elementClass: "max_width_184",
                                        theme: "in_row_style"
                                    },
                                    events: {
                                        click: function (evt) {
                                            let cabinetOptions = this.findByName("cabinetOptions");
                                            this.properties.disablePassword = !this.properties.disablePassword;

                                            if (!this.properties.disablePassword) {
                                                cabinetOptions.showChangePassword();
                                                return;
                                            }

                                            cabinetOptions.hideChangePassword();
                                        }
                                    }
                                }, {
                                    name: "newPassword",
                                    type: "CStandartFormField",
                                    container: ".new_password",
                                    model: {
                                        labelText: "Новый пароль",
                                        theme: "width_63",
                                        value: "",
                                        type: "password"
                                    },
                                    properties: {
                                        hidden: true
                                    }
                                }, {
                                    name: "newPasswordAgain",
                                    type: "CStandartFormField",
                                    container: ".new_password_again",
                                    model: {
                                        labelText: "Новый пароль ещё раз",
                                        // disabled: true,
                                        theme: "width_63",
                                        value: "",
                                        type: "password"
                                    },
                                    properties: {
                                        hidden: true
                                    }
                                }, {
                                    name: "saveUserBtn",
                                    type: "CSendButton",
                                    container: ".save_btn",
                                    model: {
                                        buttonText: "Сохранить",
                                        name: "ok"
                                    },
                                    events: {
                                        click: function (evt) {
                                            let cabinetOptions = this.findByName("cabinetOptions");

                                            cabinetOptions.saveUserInfo(evt);
                                        }
                                    }
                                }
                            ],
                            methods: {
                                getData: function () {
                                    let self = this;

                                    return new Promise(function (resolve, reject) {
                                        helper.getHttpPromise({
                                            method: "POST",
                                            url: "/api/private/user/getUserInfo",
                                            contentType: "application/json"
                                        }).then(function (response) {
                                            var data = JSON.parse(response);

                                            let lastName = self.findByName("lastName");
                                            let firstName = self.findByName("firstName");
                                            let email = self.findByName("email");

                                            lastName.model.value = data.surname;
                                            firstName.model.value = data.name;
                                            // middleName.model.value = middleName.surname;
                                            email.model.value = data.email;

                                            resolve();
                                        });
                                    });


                                }
                            },
                        }, {
                            name: "cabinetDistricts",
                            type: "CCabinetDistricts",
                            container: ".page_switcher",
                            properties: {
                                hidden: true
                            },
                            methods: {
                                getData: function () {
                                    let self = this;

                                    return new Promise(function (resolve, reject) {
                                        helper.getHttpPromise({
                                            url: "/api/dict/realty/getCityList",
                                            method: "GET",
                                            contentType: "application/json"
                                        }).then(function(response) {
                                            let ans = JSON.parse(response);

                                            let cityCombo = cabinetMain.findByName("cityCombo");
                                            cityCombo.setOptionList(ans);

                                            resolve();
                                        });
                                    });
                                }
                            },
                            /*addAfterRender: function () {
                                helper.getHttpPromise({
                                    url: "/api/dict/realty/getCityList",
                                    method: "GET",
                                    contentType: "application/json"
                                }).then(function (response) {
                                    let ans = JSON.parse(response);

                                    let cityCombo = cabinetMain.findByName("cityCombo");
                                    cityCombo.setOptionList(ans);
                                });

                            },*/
                            children: [
                                {
                                    name: "cityCombo",
                                    type: "CLocalComboField",
                                    container: ".city_combo",
                                    properties: {
                                        idField: "id",
                                        valueField: "name"
                                    },
                                    model: {
                                        labelText: "Город",
                                        theme: "text-input-wrapper__light",
                                        placeholder: "Начинайте вводить название города",
                                    },
                                    methods: {
                                        onSelect: function (selectedCity) {
                                            helper.getHttpPromise({
                                                method: "POST",
                                                url: "/api/dict/realty/getMicroDistricts",
                                                contentType: "application/json",
                                                jsonData: {
                                                    cityId: selectedCity.id,
                                                    startLevel: 0
                                                }
                                            }).then(function (response) {
                                                var ans = JSON.parse(response);

                                                let districtCombo = cabinetMain.findByName("districtCombo");
                                                districtCombo.setOptionList(ans);
                                                districtCombo.show(true);
                                            });
                                        }
                                    }
                                }, {
                                    name: "districtCombo",
                                    type: "CLocalComboField",
                                    container: ".district_combo",
                                    properties: {
                                        idField: "id",
                                        valueField: "name",
                                        hidden: true
                                    },
                                    model: {
                                        labelText: "Район",
                                        theme: "text-input-wrapper__light",
                                        placeholder: "Начинайте вводить название района",
                                    }
                                }
                            ]
                        }
                    ]
                }
            ],
            // Переход по ссылкам (пункты меню)
            events: {
                click: function (evt) {

                    let target = evt.target.closest(".menu_item");

                    if (!target) {
                        return;
                    }

                    let self = this;
                    let pageSwitcher = self.findByName("pageSwitcher");

                    let url = target.getAttribute("data-link");
                    let toPage = target.getAttribute("data-cmp");

                    pageSwitcher.changePageAndhistory({
                        url: url,
                        toPage: toPage
                    });
                }
            },
            addAfterRender: function () {

            }
        });

        // Подгрузить страницу по умолчанию
        cmpCore.drawAll();

        let pageSwitcher = cabinetMain.findByName("pageSwitcher");
        pageSwitcher.loadPageByUrl();
    });

})();
