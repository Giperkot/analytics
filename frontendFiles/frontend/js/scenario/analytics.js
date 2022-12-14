// import type: "CSingleMenu",
// import type: "AnalyticsConst",

(function () {
  document.addEventListener("DOMContentLoaded", function() {
    window.onpopstate = function(event) {
      lkPage.loadPageByUrl();
    };

    var popupForm = cmpCore.addElement({
      name: "standartPopup",
      type: "CStandartPupupForm",
      container: ".article_popup",
      properties: {
        isSuccess: false
      },
      methods: {
        /*showConfirm: function(config) {
          this.setActiveComponent("confirmWindow");
          let confirmWindow = this.findByName("confirmWindow");
          confirmWindow.setOkCallback(config.callback);

          let articlePopup = this.containerElm.closest(".article_popup");
          articlePopup.className = "article_popup article_popup__small";
          // articlePopup.classList.add("article_popup__small");

          this.showForm(true);
        }*/
      },
      /*children: [{
          name: "confirmWindow",
          type: "CConfirm",
          container: ".form_wrapper",
          properties: {
            hidden: true
          }
        }]*/
    });

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
            case "import":
              toPage = "import";
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
          name: "import",
          type: "CRealtyImport",
          container: ".page_switcher",
          properties: {
            hidden: true
          },
          children: [
            {
              name: "realtyImportUploader",
              type: "CFileUploader",
              container: ".realty_import_uploader",
              methods: {

              },
              events: {
                onLoadFile: function () {
                  let self = this;
                  let importObj = cmpCore.findByName("import");
                  let fileUploader = cmpCore.findByName("realtyImportUploader");

                  let formData = fileUploader.getFormData();

                  //todo Тут бы ещё loader поставить...
                  helper.getHttpPromise({
                    method: "POST",
                    url: "/api/import/readExcel",
                    multipartData: formData
                  }).then(function (response) {
                    var ans = JSON.parse(response);

                    importObj.temp.requestId = ans.requestId;

                    let uploadXlsFile = fileUploader.parent.containerElm.querySelector(".upload_xls_file");
                    uploadXlsFile.classList.add("hidden");

                    let resultTable = cmpCore.findByName("resultTable");
                    let importPage = cmpCore.findByName("import");

                    for (let i = 0; i < ans.importExcelRealtyDtoList.length; i++) {
                      let row = ans.importExcelRealtyDtoList[i];

                      row.balcon = AnalyticsConst.balcon[row.balcon].text;
                      row.realtySegment = AnalyticsConst.realtySegment[row.realtySegment].text;
                      row.repairType = AnalyticsConst.repairType[row.repairType].text;
                      row.roomsCount = AnalyticsConst.roomsCount[row.roomsCount].text;
                      row.wallMaterial = AnalyticsConst.simpleHouseType[row.wallMaterial].text;
                    }

                    resultTable.setGridData(ans.importExcelRealtyDtoList);
                    resultTable.render(resultTable.rootElm.containerElm);

                    let continueRow = fileUploader.parent.containerElm.querySelector(".continue_row");
                    continueRow.classList.remove("hidden");

                    importPage.temp.requestId = ans.requestId;
                  }, function (response) {
                    let ans = JSON.parse(response);
                    popupForm.showForm(true);
                    popupForm.showResult(ans.message, "small");
                  });
                }
              }
            }, {
              name: "resultTable",
              type: "CTableSelector",
              container: ".realty_result_table",
              properties: {
                multiSelect: true
              },
              model: {
                labelText: "Выберите эталонные объекты",
                columns: [
                  {
                    label: "Адрес",
                    name: "address"
                  }, {
                    label: "Кол. комнат",
                    name: "roomsCount"
                  }, {
                    label: "Сегмент",
                    name: "realtySegment"
                  }, {
                    label: "Кол. этажей",
                    name: "houseFloorsCount"
                  }, {
                    label: "Материал стен",
                    name: "wallMaterial"
                  }, {
                    label: "Этаж",
                    name: "floor"
                  }, {
                    label: "Площадь",
                    name: "totalArea"
                  }, {
                    label: "Кухня",
                    name: "kitchenArea"
                  }, {
                    label: "Балкон",
                    name: "balcon"
                  }, {
                    label: "Расст. до метро",
                    name: "metroDistance"
                  }, {
                    label: "Ремонт",
                    name: "repairType"
                  }
                ],
                controls: {
                  add: false,
                  edit: false,
                  delete: false
                }
              },
              methods: {
                getSelectedFilesMessage: function (x, y) {
                  if ((x % 100 == 11) || (x % 100 == 12) || (x % 100 == 13) || (x % 100 == 14)) {
                    return("Выбрано " + x + " квартир из " + y)
                  } else if ((x % 10 == 2) ||  (x % 10 == 3) || (x % 10 == 4)) {
                    return("Выбрано " + x + " квартиры из " + y)
                  } else { if (x % 10 == 1) {
                    return("Выбрано " + x + " квартира из " + y)
                  } else
                    return("Выбрано " + x + " квартир из " + y)
                  }
                }
              },
              events: {
                onSelect: function (records) {
                  let selectedCount = lkPage.containerElm.querySelector(".selected_count");

                  selectedCount.innerHTML = this.getSelectedFilesMessage(records.length, this.model.gridData.length);
                }
              }
            }, {
              name: "continueBtn",
              type: "CSendButton",
              container: ".continue_btn",
              model: {
                buttonText: "Продолжить"
              },
              events: {
                click: function (evt) {
                  let self = this;
                  let resultTable = cmpCore.findByName("resultTable");
                  let importPage = cmpCore.findByName("import");

                  let standardRecords = resultTable.getValue();

                  let idArr = standardRecords.map(function (elm) {
                    return elm.id;
                  });

                  console.log(standardRecords);

                  let data = {
                    requestId: importPage.temp.requestId,
                    standardRecords: idArr
                  }

                  //todo Тут бы ещё loader поставить...
                  helper.getHttpPromise({
                    method: "POST",
                    url: "/api/realty/selectStandartObjects",
                    jsonData: data
                  }).then(function (response) {
                    var ans = JSON.parse(response);
                    importPage.temp.stObjectList = ans;

                    // Спрятать таблицу с эталонами.
                    let continueRow = importPage.containerElm.querySelector(".continue_row");
                    continueRow.classList.add("hidden");

                    // Отобразить страницу с подбором эталонных объектов.
                    let showingStandartObjectResult = self.parent.findByName("showingStandartObjectResult");
                    // showingStandartObjectResult.properties.hidden = false;
                    showingStandartObjectResult.model.stObjectList = ans;

                    showingStandartObjectResult.show(true);
                  });

                }
              }
            }, {
              name: "showingStandartObjectResult",
              type: "CCorrectStandartObject",
              container: ".showing_standart_object_result",
              properties: {
                hidden: true
              },
              children: [
                {
                  name: "toResultBtn",
                  type: "CSendButton",
                  container: ".to_result_btn",
                  model: {
                    buttonText: "Расчитать"
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
                    click: function(evt) {
                      let self = this;

                      let importPage = cmpCore.findByName("import");
                      let fileUploader = cmpCore.findByName("realtyImportUploader");

                      let data = {
                        requestId: importPage.temp.requestId,
                        evalutionStandartObjDtoList: importPage.temp.stObjectList
                      }

                      //todo Тут бы ещё loader поставить...
                      helper.getHttpPromise({
                        method: "POST",
                        url: "/api/realty/calcResult",
                        jsonData: data
                      }).then(function (response) {
                        var ans = JSON.parse(response);

                        let showingStandartObjectResult = self.rootElm.findByName("showingStandartObjectResult");
                        showingStandartObjectResult.hide();

                        let finalRow = self.rootElm.containerElm.querySelector(".final_row");
                        finalRow.classList.remove("hidden");

                        let calcResultTable = cmpCore.findByName("showingRealtyObjectsPrice");

                        for (let i = 0; i < ans.importExcelRealtyDtoList.length; i++) {
                          let row = ans.importExcelRealtyDtoList[i];

                          row.balcon = AnalyticsConst.balcon[row.balcon].text;
                          row.realtySegment = AnalyticsConst.realtySegment[row.realtySegment].text;
                          row.repairType = AnalyticsConst.repairType[row.repairType].text;
                          row.roomsCount = AnalyticsConst.roomsCount[row.roomsCount].text;
                          row.wallMaterial = AnalyticsConst.simpleHouseType[row.wallMaterial].text;

                          if (row.sum !== "NaN") {
                            row.sum = self.numFormat((row.sum).toFixed(2));
                          }

                        }

                        calcResultTable.setGridData(ans.importExcelRealtyDtoList);
                        calcResultTable.render(calcResultTable.rootElm.containerElm);


                      });
                    }
                  }
                }
              ]
            }, {
              name: "showingRealtyObjectsPrice",
              type: "CTableSelector",
              container: ".final_stage",
              model: {
                labelText: "Цены объектов",
                columns: [
                  {
                    label: "Адрес",
                    name: "address",
                    className: "min_width_160"
                  }, {
                    label: "Кол. комнат",
                    name: "roomsCount"
                  }, /*{
                    label: "Сегмент",
                    name: "realtySegment"
                  }, {
                    label: "Кол. этажей",
                    name: "houseFloorsCount"
                  },*/ {
                    label: "Материал стен",
                    name: "wallMaterial"
                  }, {
                    label: "Этаж",
                    name: "floor"
                  }, {
                    label: "Площадь",
                    name: "totalArea"
                  }, {
                    label: "Кухня",
                    name: "kitchenArea"
                  }, {
                    label: "Балкон",
                    name: "balcon"
                  }, {
                    label: "Расст. до метро",
                    name: "metroDistance"
                  }, {
                    label: "Ремонт",
                    name: "repairType"
                  }, {
                    label: "Сумма, руб.",
                    name: "sum",
                    className: "min_width_90"
                  }
                ],
                controls: {
                  add: false,
                  edit: false,
                  delete: false
                }
              },
              children: [

              ]
            }, {
              name: "export_btn",
              type: "CSendButton",
              container: ".export_btn",
              model: {
                buttonText: "Экспорт в Excel"
              },
              events: {
                click: function (evt) {
                  popupForm.showForm(true);
                  popupForm.showResult("We're so sorry...", "small");
                }
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