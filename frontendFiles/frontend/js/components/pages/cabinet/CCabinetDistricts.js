(function () {
  cmpCore.registryComponent({
    name: "CCabinetDistricts",
    templateId: "CCabinetDistrictsTemplate",
    methods: {
      createDistrict: function () {
        let self = this;
        let standartPopup = cmpCore.findByName("standartPopup");
        let cityCombo = cmpCore.findByName("cityCombo");

        let districtEditor = standartPopup.findByName("districtEditor");

        districtEditor.init(standartPopup, {
          id: -1,
          cityId: cityCombo.getValue()
        });

        // cabinet.js
        standartPopup.showDistrictEditor();
      },
      editDistrict: function () {
        let self = this;
        let standartPopup = cmpCore.findByName("standartPopup");

        let districtCombo = cmpCore.findByName("districtCombo");

        let districtId = districtCombo.getValue();

        if (!districtId) {
          // нужно показать ошибку.
          districtCombo.setAlert();
          return;
        }

        // Получить district с сервера...
        helper.getHttpPromise({
          method: "GET",
          url: "/api/dict/realty/getDistrictById?id=" + districtId,
          contentType: "application/json",
          // jsonData: data
        }).then(function (response) {
          var ans = JSON.parse(response);

          let districtEditor = standartPopup.findByName("districtEditor");

          districtEditor.init(standartPopup, ans);
        });

        standartPopup.showDistrictEditor();
      },
      deleteDistrictById: function (districtId) {
        helper.getHttpPromise({
          method: "POST",
          url: "/api/realty/deleteDistrictById?id=" + districtId,
          contentType: "application/json",
          // jsonData: data
        }).then(function (response) {
          var ans = JSON.parse(response);

          let standartPopup = cmpCore.findByName("standartPopup");
          if (ans.success) {
            standartPopup.showResult("Район успешно удалён", "small");
          } else {
            standartPopup.showResult("Произошла ошибка при удалении", "small");
          }
        });
      },
      deleteDistrict: function () {
        let self = this;
        let districtCombo = cmpCore.findByName("districtCombo");

        let districtId = districtCombo.getValue();

        if (!districtId) {
          // нужно показать ошибку.
          districtCombo.setAlert();
          return;
        }

        let standartPopup = cmpCore.findByName("standartPopup");

        standartPopup.showConfirm({
          callback: function () {
            self.deleteDistrictById.call(self, districtId);
          }
        });

      }
    },
    events: {
      click: function (evt) {
        let target = evt.target;
        let cabRoleBtn = target.closest(".cab_distr_btn");

        if (!cabRoleBtn) {
          return;
        }
        evt.preventDefault();
        evt.stopPropagation();

        let self = this;
        let action = cabRoleBtn.getAttribute("data-action");

        switch (action) {
          case "createDistrict":
            self.createDistrict();
            break;
          case "editDistrict":
            self.editDistrict();
            break;
          case "deleteDistrict":
            self.deleteDistrict();
            break;

        }
      }
    }
  });
})();
