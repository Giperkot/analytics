(function () {
    cmpCore.registryComponent({
        name: "CCabinetRoles",
        templateId: "CCabinetRolesTemplate",
        methods: {

            createRole: function () {
                let standartPopup = cmpCore.findByName("standartPopup");

                // cabinet.js
                standartPopup.showRoleEditor();
            },
            deleteRole: function () {
                let self = this;
                let standartPopup = cmpCore.findByName("standartPopup");
                let roleCombobox = self.findByName("roleCombobox");

                let roleId = roleCombobox.getValue();

                if (!roleId) {
                    roleCombobox.setAlert("Нужно выбрать роль перед удалением");
                    return;
                }

                // cabinet.js
                standartPopup.showConfirm({
                    callback: function () {
                        // Создаем роль
                        helper.getHttpPromise({
                            method: "POST",
                            url: "/api/private/abilities/deleteRole",
                            contentType: "application/json",
                            jsonData: {
                                id: roleId
                            }
                        }).then(function (response) {
                            var data = JSON.parse(response);

                            if (data.success) {
                                roleCombobox.resetCache();
                                roleCombobox.disable();
                                standartPopup.showResult("Роль успешно удалена!");
                            }
                        });

                    }
                });
            },
            createPermission: function () {
                let standartPopup = cmpCore.findByName("standartPopup");

                // cabinet.js
                standartPopup.showPermissionEditor();
            },
            deletePermission: function () {
                let self = this;
                let standartPopup = cmpCore.findByName("standartPopup");
                let permissionCombobox = self.findByName("permissionCombobox");

                let permissionId = permissionCombobox.getValue();

                if (!permissionId) {
                    permissionCombobox.setAlert("Нужно выбрать привилегию перед удалением");
                    return;
                }

                // cabinet.js
                standartPopup.showConfirm({
                    callback: function () {
                        // Создаем роль
                        helper.getHttpPromise({
                            method: "POST",
                            url: "/api/private/abilities/deletePermission",
                            contentType: "application/json",
                            jsonData: {
                                id: permissionId
                            }
                        }).then(function (response) {
                            var data = JSON.parse(response);

                            if (data.success) {
                                permissionCombobox.resetCache();
                                permissionCombobox.disable();
                                standartPopup.showResult("Привилегия успешно удалена!");
                            }
                        });

                    }
                });
            }
        },
        events: {
            click: function (evt) {
                let target = evt.target;
                let cabRoleBtn = target.closest(".cab_role_btn");

                if (!cabRoleBtn) {
                    return;
                }
                evt.preventDefault();
                evt.stopPropagation();

                let self = this;
                let action = cabRoleBtn.getAttribute("data-action");

                switch (action) {
                    case "createRole":
                        self.createRole();
                        break;
                    case "deleteRole":
                        self.deleteRole();
                        break;
                    case "createPermission":
                        self.createPermission();
                        break;
                    case "deletePermission":
                        self.deletePermission();
                        break;
                }

            }
        }
    });
})();
