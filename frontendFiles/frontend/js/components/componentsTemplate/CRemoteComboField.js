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
        name: "CRemoteComboField",
        extends: "CLocalComboField",
        templateId: "CLocalComboFieldTemplate",
        init: function () {
            let component = this;
            component.optionListTemplate = _.template(document.getElementById("CRemoteComboFieldOptionListTemplate").innerHTML);
        },
        events: {
            // @Override
            click: function (e) {
                let self = this;
                var target = e.target;
                let serviceLine = target.closest(".service_line");

                if (target.classList.contains("next_page") && !target.classList.contains("disabled")) {
                    this.doFilter(self.temp.lastSearch, self.temp.page + 1);
                    return;
                }

                if (target.classList.contains("prev_page") && !target.classList.contains("disabled")) {
                    this.doFilter(self.temp.lastSearch, self.temp.page - 1);
                    return;
                }

                if (!serviceLine) {
                    let selectedBtn = target.closest(".selected_container_button");
                    if (selectedBtn) {
                        this.doFilter();
                        return;
                    }

                    return;
                }

                var id = serviceLine.getAttribute("data-id");

                this.select(id);
            },

            // @Override
            input: function (e) {
                e.preventDefault();
                e.stopPropagation();
                let self = this;

                if (this.input.value.length == 0) {
                    this.disable();
                    return;
                }

                if (self.input.value.length < self.minChars) {
                    return;
                }

                self.lastInputDate = Date.now();
                setTimeout(function () {
                    if (Date.now() - self.lastInputDate >= 1000) {
                        self.model.displayValue = self.input.value;
                        self.doFilter(self.input.value);
                    }
                }, 1100)


            },
        },
        methods: {

            doFilterLocal: function (searchTxt) {
                var self = this;

               /* if (searchTxt) {
                    let tmpList = [];
                    for (let i = 0; i < self.model.optionList.length; i++) {
                        let option = self.model.optionList[i];
                        if (option[self.valueField].indexOf(searchTxt) !== -1) {
                            tmpList.push(option);
                        }
                    }

                    self.model.filteredOptionList = tmpList;
                    self.temp.needToRender = true;
                }*/

                if (self.model.filteredOptionList && self.model.filteredOptionList.length > 0) {
                    self.buildSelect(self.model.filteredOptionList);
                    return;
                }
            },

            // @Override
            buildSelect: function (data) {
                var self = this;

                if (self.temp.needToRender) {
                    const html = self.component.optionListTemplate({
                        data: {
                            data: self.model.filteredOptionList,
                            idField: self.idField,
                            valueField: self.valueField,
                            remote: {
                                page: self.temp.page,
                                isEnd: (self.temp.pageSize > self.model.optionList.length)
                            }
                        }
                    });
                    self.containerElm.querySelector(".selected_field").innerHTML = html;
                    self.temp.needToRender = false;
                }

                self.containerElm.querySelector(".selected_field").classList.add("selected_field__with_data");
                self.temp.opened = true;
            },

            resetCache: function () {
                this.temp.lastSearch = null;
                this.temp.page = null;
            },

            // @Override
            doFilter: function (searchTxt, page) {
                var self = this;

                if (!page) {
                    page = 1;
                }

                if (self.temp.storeLoaded && (self.temp.lastSearch === searchTxt && self.temp.page === page)) {
                    self.temp.lastSearch = searchTxt;
                    self.doFilterLocal(searchTxt);
                    return;
                }

                self.temp.page = page;

                let preloaderWait = self.containerElm.querySelector(".preloader_wait");
                preloaderWait.style.display = "flex";

                // Получить данные с бэка.
                helper.getHttpPromise({
                    method: self.model.remoteConfig.method,
                    url: self.model.remoteConfig.url,
                    contentType: self.model.remoteConfig.contentType,
                    jsonData: {
                        query: searchTxt || null,
                        pageSize: 100,
                        page: page
                    }
                }).then(function (response) {
                    preloaderWait.style.display = "none";
                    var data = JSON.parse(response);

                    self.model.optionList = data.result;
                    self.model.filteredOptionList = data.result;
                    self.temp.needToRender = true;
                    self.temp.pageSize = data.pageSize;
                    self.temp.storeLoaded = true;

                    self.doFilterLocal(searchTxt);

                });

            },
/*            select: function(id) {
                var self = this;

                // Получить данные с бэка.
                helper.getHttpPromise({
                    method: "POST",
                    url: "/api/private/abilities/getRoleList",
                    contentType: "application/json",
                    /!*jsonData: {
                        id: practiceId
                    }*!/
                }).then(function (response) {

                    var data = JSON.parse(response);
                    console.log(data);

                    self.model = data;
                    resolve();
                });
            },*/
        }
    });
})();
