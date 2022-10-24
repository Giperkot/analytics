(function () {
    cmpCore.registryComponent({
        name: "CCabinetMain",
        templateId: "CCabinetMainTemplate",
        methods: {
            showMenuForAdmin: function (menuItemList) {

                for (let i = 0; i < menuItemList.length; i++) {
                    let dataCmp = menuItemList[i].getAttribute("data-cmp");

                    if (dataCmp === "cabinetUsers" || dataCmp === "cabinetRoles") {
                        menuItemList[i].classList.remove("hidden");
                    }
                }
            },
            showMenuForCourceCreater: function (menuItemList) {

                for (let i = 0; i < menuItemList.length; i++) {
                    let dataCmp = menuItemList[i].getAttribute("data-cmp");

                    if (dataCmp === "cabinetCourses" || dataCmp === "cabinetSections" || dataCmp === "cabinetLessons") {
                        menuItemList[i].classList.remove("hidden");
                    }
                }
            },
        },
        events: {

        },
        afterRender: function () {
            let self = this;

            userHelper.getUserAbilities()
                .then(function () {
                    let isAdmin = userHelper.hasPermission(userHelper.permissions.ADMIN);
                    let canCreateCourse = userHelper.hasPermission(userHelper.permissions.CREATE_COURSE);
                    let menuItemList;

                    if (isAdmin || canCreateCourse) {
                        menuItemList = self.containerElm.querySelectorAll(".menu_item");
                    }

                    if (isAdmin) {
                        self.showMenuForAdmin(menuItemList);
                    }

                    if (canCreateCourse) {
                        self.showMenuForCourceCreater(menuItemList);
                    }
                });
        }
    });
})();
