(function () {
    cmpCore.registryComponent({
        name: "CCabinetUsers",
        templateId: "CCabinetUsersTemplate",
        methods: {
            updatePermissionTable: function (userId) {
                let self = this;
                let userCombobox = self.findByName("userCombobox");

                helper.getHttpPromise({
                    method: "POST",
                    url: "/api/private/abilities/getPermissionListByUserId",
                    contentType: userCombobox.model.remoteConfig.contentType,
                    jsonData: {
                        id: userId
                    }
                }).then(function (response) {
                    var data = JSON.parse(response);

                    let permissionTable = self.findByName("userPermissionsTable");
                    permissionTable.properties.hidden = false;

                    permissionTable.model.gridData = data.result;
                    permissionTable.reDraw();
                });
            },
            updateRoleTable: function (userId) {
                let self = this;
                let userCombobox = self.findByName("userCombobox");

                helper.getHttpPromise({
                    method: "POST",
                    url: "/api/private/abilities/getRoleListByUserId",
                    contentType: userCombobox.model.remoteConfig.contentType,
                    jsonData: {
                        id: userId
                    }
                }).then(function (response) {
                    var data = JSON.parse(response);

                    let userRolesTable = self.findByName("userRolesTable");
                    userRolesTable.properties.hidden = false;

                    userRolesTable.model.gridData = data.result;
                    userRolesTable.reDraw();
                });
            }
        },
        events: {

        }
    });
})();
