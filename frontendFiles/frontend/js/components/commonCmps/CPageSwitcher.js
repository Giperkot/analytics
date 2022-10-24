/**
 * temp: {
 *     currentPage: "...",
 *
 * }
 *
 * children
 *  должен обладать методом
 *  getData: function () {
 *      return new Promise();
 *  }
 *
 */

(function () {
    cmpCore.registryComponent({
        name: "CPageSwitcher",
        templateId: "CPageSwitcherTemplate",
        methods: {
            changePageAndhistory: function (config) {
                let url = config.url;
                if (url) {
                    // document.location.hash = url;
                    history.pushState(null, null, url);
                    // dataParams = this.getParamerersForRequest(url);
                }

                return this.changePage(config);
            },
            changePage: function (config) {
                let self = this;
                let url = config.url;

                let toPage = config.toPage;

                let dataParams = {};

                if (url) {
                    // document.location.hash = url;
                    dataParams = this.getParamerersForRequest(url);
                }

                let currentPage = self.temp.currentPage;

                if (currentPage && currentPage.name === toPage) {
                    // Никуда не переходим.
                    return true;
                }

                if (currentPage) {
                    currentPage.properties.hidden = true;
                    currentPage.finalize(true);
                }

                let nextElm = self.findByName(toPage);

                if (!nextElm) {
                    return false;
                }

                self.temp.currentPage = nextElm;

                nextElm.properties.hidden = false;
                nextElm.properties.pageSwitch = dataParams;

                if (nextElm.getData) {
                    nextElm.getData(dataParams)
                        .then(function () {
                            self.rerenderCmp(nextElm);
                        });
                } else {
                    self.rerenderCmp(nextElm);
                }

                return true;
            },
            getParamerersForRequest: function (url) {
                let paremsArray = url.split("/");
                let result = {};
                let i = 0;

                while (i < paremsArray.length) {
                // for (let i = 0; i < paremsArray.length; i++) {
                    if (!paremsArray[i]) {
                        i++;
                        continue;
                    }

                    result[paremsArray[i]] = paremsArray[i + 1];
                    i = i + 2;
                }

                return result;
            },
            rerenderCmp: function (nextElm) {
                nextElm.draw(this.containerElm);
                nextElm.bind();
            },
            loadPageByUrl: function () {

                let pathName = window.location.pathname;

                if (pathName === "" || pathName === "/") {
                    this.changePage({
                        toPage: this.properties.defaultPage
                    });

                    return;
                }

                let pageParams = this.getParamerersForRequest(pathName);
                pageParams.pathName = pathName;

                this.loadPagePageByUrlParams(pageParams);
            }

        }
    });
})();