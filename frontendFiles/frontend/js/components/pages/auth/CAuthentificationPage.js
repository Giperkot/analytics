/**
 * Created by stvolov-is on 09.03.2017.
 */

(function () {
    cmpCore.registryComponent({
        name: "CAuthentificationPage",
        templateId: "CAuthentificationPageTemplate",
        methods: {
            handleChangeTab: function (tabMapElement) {
                var self = this;
                var toTab = null,
                    hilighterCls = null;

                toTab = tabMapElement.cmpName;
                hilighterCls = tabMapElement.hilighterCls;
                /*if (target.classList.contains(tabMap.login.className)) {
                    toTab = tabMap.login.cmpName;
                    hilighterCls = tabMap.login.hilighterCls;
                }
                if (target.classList.contains(tabMap.registration.className)) {
                    toTab = tabMap.registration.cmpName;
                    hilighterCls = tabMap.registration.hilighterCls;
                }*/

                if (!toTab || toTab === self.temp.activeTab) {
                    return;
                }

                var currentCmp = self.findByName(self.temp.activeTab);
                currentCmp.properties.hidden = true;
                currentCmp.finalize();

                var hilighterElm = self.containerElm.querySelector(".underline_hilight");
                hilighterElm.className = "underline_hilight";
                hilighterElm.classList.add(hilighterCls);

                // Ищем компонент с табом
                var tabCmp = self.findByName(toTab);
                tabCmp.properties.hidden = false;


                self.temp.activeTab = toTab;
                tabCmp.draw(self.containerElm);
                tabCmp.bind();
            }
        },
        events: {
            click: function (evt) {
                var target = evt.target;

                if (target.closest(".login_tabs")) {
                    var tabMap = this.temp.tabMap;
                    if (target.classList.contains(tabMap.login.className)) {
                        this.handleChangeTab(tabMap.login);
                        return
                    }
                    if (target.classList.contains(tabMap.registration.className)) {
                        this.handleChangeTab(tabMap.registration);
                        return;
                    }
                    return;
                }

                if (target.closest(".to_registration")) {
                    tabMap = this.temp.tabMap;
                    this.handleChangeTab(tabMap.registration);
                    evt.preventDefault();
                    evt.stopPropagation();
                    return;
                }

                if (target.closest(".show_restore_password")) {
                    // Показать восстановление пароля.

                    this.properties.restorePasswordFrom.showForm();
                }
            }
        },
        afterRender: function () {
            var self = this;

            self.temp.activeTab = "loginForm";

            if (!self.temp.tabMap) {
                self.temp.tabMap = {
                    login: {
                        cmpName: "loginForm",
                        className: "login_tab_act",
                        hilighterCls: "enter"
                    },
                    registration: {
                        cmpName: "registrationForm",
                        className: "register_tab_act",
                        hilighterCls: "registration"
                    }
                };
            }
        }
    });
})();
