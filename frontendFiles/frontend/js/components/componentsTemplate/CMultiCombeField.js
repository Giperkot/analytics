/**
 * Created by stvolov-is on 09.03.2017.
 *
 * Отличие от CLocalComboField, в том что self.selection содержит несколько значений.
 */

(function () {
  cmpCore.registryComponent({
    name: "CMultiCombeField",
    templateId: "CLocalComboFieldTemplate",
    extends: "CLocalComboField",
    init: function () {
      let component = this;
      component.optionListTemplate = _.template(document.getElementById("CLocalComboFieldOptionListTemplate").innerHTML);
    },
    methods: {
      select: function(id, serviceLine) {
        var self = this;

        var newSelection = self.findInLocalDataById((id !== 0) ? id : null);

        if (!newSelection) {
          return;
        }

        if (!self.temp.selection[newSelection[self.idField]]) {
          // Добавить
          self.temp.selection[newSelection[self.idField]] = newSelection;
          serviceLine.classList.add("service_line__selected");
        } else {
          // Удалить его
          delete self.temp.selection[newSelection[self.idField]];
          serviceLine.classList.remove("service_line__selected");
        }

        let keys = Object.keys(self.temp.selection);
        this.input.value = keys.join(", ");

        // this.containerElm.querySelector();
        // selected_field selected_field__with_data
        // service_line__active

        // self.closeSelect();
        self.validate();

        if (!self.onSelect) {
          return;
        }
        self.onSelect.call(this, self.temp.selection);
      },
      handleEnterButton: function () {
        let selectedFieldParent = this.containerElm.querySelector(".selected_field");
        let serviceLineList = selectedFieldParent.querySelectorAll(".service_line");
        let activeChildElm = serviceLineList[this.temp.activeItem];

        let aciveElm = this.model.filteredOptionList[this.temp.activeItem];
        if (aciveElm) {
          this.select(aciveElm[this.idField], activeChildElm);
        }
      },
      getValue: function () {
        return this.temp.selection;
      }
    }
  });
})();
