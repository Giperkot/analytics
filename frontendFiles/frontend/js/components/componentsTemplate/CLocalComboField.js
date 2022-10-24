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
        name: "CLocalComboField",
        templateId: "CLocalComboFieldTemplate",
        init: function () {
            let component = this;
            component.optionListTemplate = _.template(document.getElementById("CLocalComboFieldOptionListTemplate").innerHTML);
        },
        methods: {
            constructor: function () {
                let self = this;

                if (!this.model.optionList) {
                    this.model.optionList = [];
                }

                self.model.filteredOptionList = this.model.optionList;
                self.temp.selection = {};
                self.temp.needToRender = true;
                self.temp.opened = false;
                self.temp.customKeyboardText = false;
                // Выбранный с клавиатуры элемент списка.
                self.temp.activeItem = -1;

                self.idField = self.properties.idField || "id";
                self.valueField = self.properties.valueField || "name";

                document.addEventListener("click", function (evt) {
                    let target;
                    let serviceLine;

                    if (self.temp.customKeyboardText) {
                        target = evt.target;
                        serviceLine = target.closest(self.container);

                        if (!serviceLine && !self.temp.selection[self.idField]) {
                            self.disable();
                        }

                        if (!serviceLine && self.temp.selection[self.idField]) {
                            self.input.value = self.temp.selection[self.valueField];
                        }
                    }

                    if (!self.temp.opened) {
                        return;
                    }

                    target = evt.target;
                    serviceLine = target.closest(self.container);

                    if (!serviceLine ) {
                        self.closeSelect();
                    }
                });
            },
            buildSelect: function (data) {
                var self = this;

                if (self.temp.needToRender) {
                    const html = self.component.optionListTemplate({data: {
                            data: self.model.filteredOptionList,
                            idField: self.idField,
                            valueField: self.valueField
                        }});
                    self.containerElm.querySelector(".selected_field").innerHTML = html;
                    self.temp.needToRender = false;
                }

                self.containerElm.querySelector(".selected_field").classList.add("selected_field__with_data");
                self.temp.opened = true;
            },
            closeSelect: function () {
                var self = this;
                let selectedField = self.containerElm.querySelector(".selected_field");

                if (selectedField && selectedField.classList.contains("selected_field__with_data")) {
                    selectedField.classList.remove("selected_field__with_data");
                }

                self.temp.activeItem = -1;
                this.temp.opened = false;
            },
            returnToSelect: function () {
                var self = this;
                if (!self.temp.selection) {
                    self.disable();
                    return;
                }

                self.select(self.temp.selection.display);
            },
            doFilter: function (searchTxt) {
                var self = this;

                if (searchTxt) {
                    let tmpList = [];
                    for (let i = 0; i < self.model.optionList.length; i++) {
                        let option = self.model.optionList[i];
                        if (option[self.valueField].indexOf(searchTxt) !== -1) {
                            tmpList.push(option);
                        }
                    }

                    self.model.filteredOptionList = tmpList;
                    self.temp.needToRender = true;
                }

                if (self.model.filteredOptionList && self.model.filteredOptionList.length > 0) {
                    self.buildSelect(self.model.filteredOptionList);
                    return;
                }
            },
            findInLocalDataById: function (id) {
                var self = this;

                if (!id || !self.model.filteredOptionList) {
                    return null;
                }

                for (var i = 0; i < self.model.filteredOptionList.length; i++) {
                    if (self.model.filteredOptionList[i][self.idField] == id) {
                        return self.model.filteredOptionList[i];
                    }
                }
            },
            /**
             * Выбор из списка вариантов
             * @param id
             */
            select: function(id, serviceLine) {
                var self = this;

                var newSelection = self.findInLocalDataById((id !== 0) ? id : null);
                self.temp.selection = (newSelection) ? newSelection : self.selection;
                if (!self.temp.selection) {
                    return;
                }

                this.input.value = self.temp.selection[self.valueField];
                this.temp.customKeyboardText = false;
                self.closeSelect();
                self.validate();

                if (!self.onSelect) {
                    return;
                }
                self.onSelect.call(this, self.temp.selection);
            },
            doFocusActiveElement: function () {
                let selectedFieldParent = this.containerElm.querySelector(".selected_field");
                let serviceLineList = selectedFieldParent.querySelectorAll(".service_line");
                let activeLine = selectedFieldParent.querySelector(".service_line__active");

                if (activeLine) {
                    activeLine.classList.remove("service_line__active");
                }

                let activeChildElm = serviceLineList[this.temp.activeItem];
                activeChildElm.classList.add("service_line__active");

                // Здесь же будет прокрутка, если элемент не попал в область видимости.
                let parentCoords = selectedFieldParent.getBoundingClientRect();
                let activeChildCoords = activeChildElm.getBoundingClientRect();

                let shiftBottom = activeChildCoords.bottom - parentCoords.bottom;
                let shiftUp = parentCoords.top - activeChildCoords.top;

                if (shiftBottom > 0) {
                    selectedFieldParent.scrollTop += shiftBottom;
                }

                if (shiftUp > 0) {
                    selectedFieldParent.scrollTop -= shiftUp;
                }
            },
            toPrevItem: function () {
                if (this.temp.activeItem <= 0) {
                    this.temp.activeItem = this.model.filteredOptionList.length - 1;
                } else {
                    this.temp.activeItem--;
                }

                this.doFocusActiveElement();
            },
            toNextItem: function () {
                if (this.temp.activeItem >= this.model.filteredOptionList.length - 1) {
                    this.temp.activeItem = 0;
                } else {
                    this.temp.activeItem++;
                }

                this.doFocusActiveElement();
            },
            validate: function () {
                let input = this.input;
                if (!this.temp.selection) {
                    input.classList.add("alert_standart_input");
                    return false;
                }

                if (input.classList.contains("alert_standart_input")) {
                    input.classList.remove("alert_standart_input")
                }

                return true;
            },
            disable: function(id) {
                var self = this;

                if (!self.isDrawn) {
                    return;
                }

                self.model.filteredOptionList = self.model.optionList;
                self.temp.selection = {};
                self.closeSelect();
                self.input.value = "";
                self.temp.opened = false;
                self.temp.needToRender = true;
                self.temp.customKeyboardText = false;
            },
            setOptionList: function (optionList) {
                this.model.optionList = optionList;
                this.model.filteredOptionList = optionList;
            },
            getValue: function () {
                return this.temp.selection[this.idField];
            },
            getRecord: function () {
                return this.temp.selection;
            },
            /**
             * Ищет по idField
             * @param value
             */
            setValue: function (value) {
                let self = this;

                if (self.temp.selection[self.idField] === value) {
                    self.input.value = self.temp.selection[self.valueField];
                }

                for (let i = 0; i < this.model.optionList.length; i++) {
                    if (this.model.optionList[i][self.idField] === value) {
                        self.temp.selection = this.model.optionList[i];
                        self.model.displayValue = self.temp.selection[self.valueField];

                        if (self.input) {
                            self.input.value = self.temp.selection[self.valueField];
                        }
                        break;
                    }
                }
            },
            setAlert: function (message) {
                var input = this.containerElm.getElementsByTagName("INPUT")[0];
                input.classList.add("alert_standart_input");
            },
            handleEnterButton: function () {
                let aciveElm = this.model.filteredOptionList[this.temp.activeItem];
                if (aciveElm) {
                    this.select(aciveElm[this.idField]);
                }
            }
        },
        events: {
            click: function (e) {
                var target = e.target;
                let serviceLine = target.closest(".service_line");

                if (!serviceLine) {
                    let selectedBtn = target.closest(".selected_container_button");
                    if (selectedBtn) {
                        this.doFilter();
                        return;
                    }

                    return;
                }

                var id = serviceLine.getAttribute("data-id");

                this.select(id, serviceLine);
            },
            // надо true. Capturing
/*            blur: function (e) {
                var target = e.target;

                if (!target || target.closest(this.container)) {
                    // перешли к выбору.
                    return;
                }
                this.closeSelect();
                // this.returnToSelect();
            },*/
            input: function (e) {
                e.preventDefault();
                e.stopPropagation();

                if (this.input.value.length == 0) {
                    this.disable();
                    return;
                }

                /*if (this.input.value.length < self.minChars) {
                    return;
                }*/

                this.temp.customKeyboardText = true;
                this.model.displayValue = this.input.value;
                this.doFilter(this.input.value);
            },
            //keyup
            keydown: function (evt) {
                /**
                 * Служебные клавиши:
                 * вверх и вниз
                 */
                if (!this.temp.opened && evt.keyCode === 40) {
                    // Открыть select.
                    this.doFilter(this.input.value);
                    return;
                }

                if (!this.temp.opened) {
                    return;
                }

                switch (evt.keyCode) {
                    // up
                    case 38:
                        this.toPrevItem();
                        evt.preventDefault();
                        break;
                    // down
                    case 40:
                        this.toNextItem();
                        evt.preventDefault();
                        break;
                    // enter
                    case 13:
                        this.handleEnterButton();
                        return;
                    // esc
                    case 27:
                        this.closeSelect();
                        break;
                }
            }
        },
        afterRender: function () {
            this.input = this.containerElm.querySelector("input");
            this.temp.needToRender = true;
            this.model.filteredOptionList = this.model.optionList;
        }
    });
})();
