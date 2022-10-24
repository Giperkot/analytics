// import type: "CSingleMenu",
// import type: "AnalyticsConst",

(function () {
  document.addEventListener("DOMContentLoaded", function() {
    window.onpopstate = function(event) {
      debugger;
      lkPage.loadPageByUrl();
    };

    let lkPage = cmpCore.addElement({
      name: "pageSwitcher",
      type: "CPageSwitcher",
      container: ".the_content",
      properties: {
        defaultPage: "mainMenu"
      },
      methods: {
        loadValuesToComboFromConsts: function (constObj, cmpName) {
          let roomsArr = [];
          for (let key in constObj) {
            roomsArr.push(constObj[key]);
          }

          let comboField = lkPage.findByName(cmpName);
          comboField.setOptionList(roomsArr);
        },

        loadPagePageByUrlParams: function(urlParams) {

          let pageName = "";

          for (let urlParam in urlParams) {
            // Планируется 1 параметр
            pageName = urlParam;

            break;
          }

          let toPage;
          switch (urlParams.pathName.replace(/\//gi, "")) {
            case "realtySearch":
              toPage = "realtySearch";
              break;
            case "reports":
              toPage = "reports";
              break;
            case "analytics":
            default:
              toPage = this.properties.defaultPage;
              break;
          }

          this.changePage({
            toPage: toPage,
            url: urlParams.pathName
          })
        }
      },
      children: [
        {
          name: "mainMenu",
          type: "CMainMenu",
          container: ".page_switcher"
        }, {
          name: "realtySearch",
          type: "CRealtySearch",
          container: ".page_switcher",
          properties: {
            hidden: true
          },
          addAfterRender: function () {
            let self = this;
            // Подгрузить данные в комбобокс.
            helper.getHttpPromise({
              method: "POST",
              url: "/api/dict/realty/getPrimaryDistricts",
              contentType: "application/json",
              jsonData: {
                cityId: 1
              }
            }).then(function (response) {
              var ans = JSON.parse(response);

              let regionCmp = lkPage.findByName("districtSearchField");
              regionCmp.setOptionList(ans);
            });

            helper.getHttpPromise({
              method: "POST",
              url: "/api/dict/realty/getMicroDistricts",
              contentType: "application/json",
              jsonData: {
                cityId: 1
              }
            }).then(function (response) {
              var ans = JSON.parse(response);

              let regionCmp = lkPage.findByName("microdistrictSearchField");
              regionCmp.setOptionList(ans);
            });

            self.parent.loadValuesToComboFromConsts(AnalyticsConst.roomsCount, "roomsCountSearchField");
            self.parent.loadValuesToComboFromConsts(AnalyticsConst.houseFloor, "houseFloorSearchField");
            self.parent.loadValuesToComboFromConsts(AnalyticsConst.houseBuildYear, "buildYearSearchField");
            // self.loadValuesToComboFromConsts(AnalyticsConst.roomsCount, "houseTypeSearchField");
            self.parent.loadValuesToComboFromConsts(AnalyticsConst.floor, "floorSearchField");
          },
          children: [
            {
              name: "districtSearchField",
              type: "CLocalComboField",
              container: ".district_search_field",
              properties: {
                idField: "id",
                valueField: "name",
                hidden: true
              },
              model: {
                labelText: "Район",
                name: "districtSearchField",
                theme: "text-input-wrapper__light",
                placeholder: "Начинайте вводить название района",
              }
            }, {
              name: "microdistrictSearchField",
              type: "CMultiCombeField",
              container: ".microdistrict_search_field",
              properties: {
                idField: "id",
                valueField: "name"
              },
              model: {
                labelText: "Микрорайон",
                name: "microdistrictSearchField",
                theme: "text-input-wrapper__light",
                placeholder: "Начинайте вводить название района",
              }
            }, {
              name: "roomsCountSearchField",
              type: "CLocalComboField",
              container: ".rooms_count_search_field",
              properties: {
                idField: "id",
                valueField: "title"
              },
              model: {
                labelText: "Количество комнат",
                name: "roomsCountSearchField",
                theme: "text-input-wrapper__light",
                placeholder: "Начинайте вводить название района",
              }
            }, {
              name: "houseFloorSearchField",
              type: "CLocalComboField",
              container: ".house_floors_search_field",
              properties: {
                idField: "id",
                valueField: "title"
              },
              model: {
                labelText: "Этажность дома",
                name: "houseFloorSearchField",
                theme: "text-input-wrapper__light",
                placeholder: "Начинайте вводить название района",
              }
            }, {
              name: "floorSearchField",
              type: "CLocalComboField",
              container: ".floor_search_field",
              properties: {
                idField: "id",
                valueField: "title"
              },
              model: {
                labelText: "Этаж",
                name: "floorSearchField",
                theme: "text-input-wrapper__light",
                placeholder: "Начинайте вводить название района",
              }
            }, {
              name: "houseTypeSearchField",
              type: "CLocalComboField",
              container: ".house_type_search_field",
              properties: {
                idField: "id",
                valueField: "title",
                hidden: true
              },
              model: {
                labelText: "Тип дома",
                name: "houseTypeSearchField",
                theme: "text-input-wrapper__light",
                placeholder: "Начинайте вводить название района",
              }
            }, {
              name: "buildYearSearchField",
              type: "CLocalComboField",
              container: ".build_year_search_field",
              properties: {
                idField: "id",
                valueField: "title",
                hidden: true
              },
              model: {
                labelText: "Год постройки",
                name: "buildYearSearchField",
                theme: "text-input-wrapper__light",
                placeholder: "Начинайте вводить название района",
              }
            },{
              name: "searchSendBtn",
              type: "CSendButton",
              container: ".search_send_button",
              model: {
                buttonText: "Найти"
              },
              methods: {
                numFormat: function (value) {
                  let str = "" + value;

                  let dotPos = str.indexOf(".");

                  if (dotPos < 0) {
                    dotPos = str.length;
                  }

                  for (let i = dotPos - 3; i > 0; i -= 3) {
                    str = str.substring(0, i) + " " + str.substring(i, str.length);
                  }

                  return str;
                }
              },

              events: {
                click: function (evt) {
                  // Начнем поиск...
                  let self = this;

                  let microdistrictSearchField = lkPage.findByName("microdistrictSearchField");
                  let roomsCountSearchField = lkPage.findByName("roomsCountSearchField");
                  let houseFloorSearchField = lkPage.findByName("houseFloorSearchField");
                  let floorSearchField = lkPage.findByName("floorSearchField");
                  let houseTypeSearchField = lkPage.findByName("houseTypeSearchField");
                  let buildYearSearchField = lkPage.findByName("buildYearSearchField");

                  let reqObj = {
                    districtIdArr: Object.keys(microdistrictSearchField.getValue()),
                    roomsCount: roomsCountSearchField.getValue(),
                    floor: floorSearchField.getValue(),
                    houseFloor: houseFloorSearchField.getValue(),
                    houseType: houseTypeSearchField.getValue(),
                    houseBuildYear: buildYearSearchField.getValue(),
                    // balcon: ,
                  };

                  helper.getHttpPromise({
                    method: "POST",
                    url: "/api/realty/getNoticesByFilter",
                    contentType: "application/json",
                    jsonData: reqObj
                  }).then(function (response) {
                    let data = JSON.parse(response);

                    console.log(data);

                    // let tableData = [];

                    for (let i = 0; i < data.length; i++) {
                      data[i].squareSum = data[i].sum / data[i].squareValue;
                      data[i].squareSumFront = self.numFormat(data[i].squareSum.toFixed(2)),
                      data[i].averageSumFront = self.numFormat(data[i].averageSum.toFixed(2)),
                      data[i].sum = self.numFormat(data[i].sum);
                      data[i].address = data[i].street + " " + data[i].houseNum;
                      data[i].link = "<a href=\"https://avito.ru" + data[i].url + "\">Перейти</a>";

                      data[i].className = (data[i].averageSum >= data[i].squareSum) ? "green" : "red";
                    }

                    let noticeTable = lkPage.findByName("noticeTable");
                    // noticeTable.setData(data);

                    noticeTable.show();
                    noticeTable.setGridData(data);
                    noticeTable.reDraw();
                  });



                }
              }
            }, {
              name: "noticeTable",
              type: "CTableSelector",
              container: ".search_result",
              properties: {
                hidden: true
              },
              model: {
                labelText: "Список объявлений",
                columns: [
                  {
                    label: "Микрорайон",
                    name: "name"
                  }, {
                    label: "Адрес",
                    name: "address"
                  }, {
                    label: "Название",
                    name: "title"
                  }, {
                    label: "Цена",
                    name: "sum"
                  }, {
                    label: "Цена за квадрат",
                    name: "squareSumFront"
                  }, {
                    label: "ср. цена за квадрат",
                    name: "averageSumFront"
                  }, {
                    label: "",
                    name: "link"
                  }
                ],
                controls: {
                  add: false,
                  edit: false,
                  delete: false
                }
              },
              methods: {

              }
            }
          ]
        }, {
          name: "reports",
          type: "CRealtyReports",
          container: ".page_switcher",
          properties: {
            hidden: true
          }
        }

      ]
    });

    cmpCore.drawAll();
    lkPage.loadPageByUrl();
  });
})();