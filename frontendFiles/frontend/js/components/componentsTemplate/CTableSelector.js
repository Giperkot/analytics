/**
 * Created by stvolov-is on 09.03.2017.
 *
 * init - this это Компонент
 *  model: {
 *      optionList
 *
 *  }
 */

(function () {
    cmpCore.registryComponent({
        name: "CTableSelector",
        templateId: "CTableSelectorTemplate",
/*        init: function () {
            let component = this;
            component.optionListTemplate = _.template(document.getElementById("CLocalComboFieldOptionListTemplate").innerHTML);
        },*/
        methods: {
            constructor: function () {
                let self = this;

                if (!self.model.gridData) {
                    self.model.gridData = [];
                }

                if (!self.model.columns) {
                    self.model.columns = [];
                }

                self.temp.selection = {};
                self.idField = self.properties.idField || "id";

                self.model.idField = self.idField;
            },
            deleteRecordById: function (id) {
                for (let i = 0; i < this.model.gridData.length; i++) {
                    if (this.model.gridData[i].id === id) {
                        this.model.gridData.splice(i, 1);
                        return;
                    }
                }
            },
            setRecordById: function (id, record) {
                for (let i = 0; i < this.model.gridData.length; i++) {
                    if (this.model.gridData[i].id === id) {
                        this.model.gridData[i] = record;
                        return;
                    }
                }
            },
            checkControlButtons: function (evt) {
                let target = evt.target;
                let controlBtn = target.closest(".ctrl_btn");

                if (!controlBtn) {
                    return;
                }

                evt.preventDefault();
                evt.stopPropagation();

                let action = controlBtn.getAttribute("data-action");

                switch (action) {
                    case "add":
                        this.onAdd();
                        break;
                    case "edit":
                        if (!this.temp.selection) {
                            return;
                        }

                        this.onEdit(this.temp.selection);
                        break;
                    case "delete":
                        if (!this.temp.selection) {
                            return;
                        }

                        this.onDelete(this.temp.selection);
                        break;
                }

            },
            addRecord: function (record) {
                this.model.gridData.push(record);
            },
            getValue: function () {
                if (!this.temp.selection && this.model.gridData && this.model.gridData.length === 1) {
                    this.temp.selection = this.model.gridData[0];
                }

                return this.temp.selection;
            },
            setGridData: function (gridData) {
                this.model.gridData = gridData;
            },
            onAdd: function () {
                throw new Error('onAdd не реализован');
            },
            onEdit: function (record) {
                throw new Error('onEdit не реализован');
            },
            onDelete: function (record) {
                throw new Error('onDelete не реализован');
            }
        },
        events: {
            click: function (e) {
                var target = e.target;
                let tableLine = target.closest("tr");

                if (!tableLine || tableLine.classList.contains("header_row")) {
                    // Проверить кнопки управления таблицей
                    this.checkControlButtons(e);
                    return;
                }

                let selectedRowList = this.containerElm.querySelectorAll(".selected");

                for(let i = 0; i < selectedRowList.length; i++) {
                    if (selectedRowList[i] !== tableLine) {
                        selectedRowList[i].classList.remove("selected");
                    }
                }

                let selectedId = tableLine.getAttribute("data-id");

                for (let i = 0; i < this.model.gridData.length; i++) {
                    if (selectedId == this.model.gridData[i][this.idField]) {
                        this.temp.selection = this.model.gridData[i];
                        break;
                    }
                }

                tableLine.classList.add("selected");

                // Активировать кнопки
                if (this.model.controls.edit || this.model.controls.delete) {
                    let controlButtons = this.containerElm.querySelectorAll(".icon_line_wrapper");

                    for (let i = 0; i < controlButtons.length; i++) {
                        let button = controlButtons[i];
                        let buttonAction = button.getAttribute("data-action");

                        if (buttonAction === "edit" || buttonAction === "delete") {
                            button.classList.remove("disabled");
                        }
                    }
                }

            }
    }});
})();
