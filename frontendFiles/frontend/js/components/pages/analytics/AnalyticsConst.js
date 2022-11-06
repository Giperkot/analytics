/**
 * (.*)\((\d+}, (.*)\)
 * $1: {id: $2, title: $3}
 */

(function () {
  const AnalyticsConst = {
    roomsCount: {
      ONE: {id: 0, title: "1-к квартира", text: "1"},
      TWO: {id: 1, title: "2-к квартира", text: "2"},
      THREE: {id: 2, title: "3-к квартира", text: "3"},
      FOUR: {id: 3, title: "4-к квартира", text: "4"},
      FIVE: {id: 4, title: "5-к квартира", text: "5"},
      MORE_THAN_FIVE: {id: 5, title: "более 5-к квартира", text: ">5"},
      FREE_LAYOUT: {id: 6, title: "Свободная планировка", text: "свободная"},
      STUDIO: {id: 7, title: "Студия", text: "студия"},
      TEN_AND_MORE: {id: 8, title: "10-к и больше", text: ">10"}
    },
    houseFloor: {
      LOW_FLOORS: {id: 0, title: "Малоэтажные дома"},
      FIVE_OR_LESS: {id: 1, title: "Пять этажей или меньше"},
      MORE_THAN_FIVE: {id: 2, title: "Более пяти этажей"}
    },
    floor: {
      FIRST: {id: 0, title: "Первый"},
      AVERAGE: {id: 1, title: "Средний"},
      LAST: {id: 2, title: "Последний"}
    },
    balcon: {
      FALSE: {id: 0, title: "Отсутствует", text: "нет"},
      TRUE: {id: 1, title: "Присутствует", text: "да"}
    },
    houseBuildYear: {
      FIFTY_AND_EARLIER: {id: 0, title: "FIFTY_AND_EARLIER"},
      FIFTY_EIGHTY: {id: 1, title: "FIFTY_EIGHTY"},
      EIGHTY_ZEROS: {id: 2, title: "EIGHTY_ZEROS"},
      ZEROS_TENTH: {id: 3, title: "ZEROS_TENTH"},
      TENTH_TWENTY: {id: 4, title: "TENTH_TWENTY"},
      TWENTY_AND_LATER: {id: 5, title: "TWENTY_AND_LATER"}
    },
    realtySegment: {
      UNKNOWN: {id: 0, name: "UNKNOWN",  text: "Неизвестно"},
      NEW_BUILDING: {id: 1, name: "NEW_BUILDING",  text: "Новостройка"},
      MODERN_HOUSE: {id: 2, name: "MODERN_HOUSE",  text: "Современное жилье"},
      OLD_HOUSE: {id: 3, name: "OLD_HOUSE",  text: "Старый жилой фонд"}
    },
    simpleHouseType: {
      BRICK: {id: 1, name: "BRICK", text:  "Кирпич"},
        MONOLIT: {id: 2, name: "MONOLIT", text:  "Монолит"},
        PANEL: {id: 3, name: "PANEL", text:  "Панель"}
    },
    repairType: {
      NONE: {id: 1, name: "NONE", text: "Без отделки"},
        BAD: {id: 2, name: "BAD", text:  "Муниципальный ремонт"},
        GOOD: {id: 3, name: "GOOD", text:  "Современная отделка"}
    },
    totalArea: {
      UNKNOWN: {id:0, name: "UNKNOWN", text: ""},
      LESS30: {id:1, name: "LESS30", text: "<30"},
      FROM30TO50: {id:2, name: "FROM30TO50", text: "30-50"},
      FROM50TO65: {id:3, name: "FROM50TO65", text: "50-65"},
      FROM65TO90: {id:4, name: "FROM65TO90", text: "65-90"},
      FROM90TO120: {id:5, name: "FROM90TO120", text: "90-120"},
      MORE120: {id:6, name: "MORE120", text: ">120"}
    },
    kitchenArea: {
      UNKNOWN: {id:0, name: "UNKNOWN", text: ""},
      LESS7: {id:1, name: "LESS7", text: "<7"},
      FROM7TO10: {id:2, name: "FROM7TO10", text: "7-10"},
      FROM10TO15: {id:3, name: "FROM10TO15", text: "10-15"},
      MORE15: {id:6, name: "MORE15", text: ">15"}
    },
    metroDistance: {
      UNKNOWN: {id:0, defaultValue: -1, name: "UNKNOWN", text: ""},
      LESS5: {id:1, defaultValue: 5, name: "LESS7", text: "<5"},
      FROM5TO10: {id:2, defaultValue: 10, name: "FROM5TO10", text: "5-10"},
      FROM10TO15: {id:3, defaultValue: 15, name: "FROM10TO15", text: "10-15"},
      FROM15TO30: {id:4, defaultValue: 30, name: "FROM15TO30", text: "15-30"},
      FROM30TO60: {id:5, defaultValue: 60, name: "FROM30TO60", text: "30-60"},
      FROM60TO90: {id:6, defaultValue: 90, name: "FROM60TO90", text: "60-90"}
    },
    coefficientType: {
      FLOOR: {id:1, text: "Корректировка на этаж", getClassVal: function (name) {
        return AnalyticsConst.floor[name].text;
      }},
      TOTAL_SQUARE: {id:2, text: "Корректировка на площадь", getClassVal: function (name) {
          return AnalyticsConst.totalArea[name].text;
        }},
      BALCON: {id:3, text: "Корректировка на балкон", getClassVal: function (name) {
          return AnalyticsConst.balcon[name].text;
        }},
      REPAIR_TYPE: {id:4, text: "Корректировка на состояние", getClassVal: function (name) {
          return AnalyticsConst.repairType[name].text;
        }},
      KITCHEN_SQUARE: {id:5, text: "Корректировка на пл. кухни", getClassVal: function (name) {
          return AnalyticsConst.kitchenArea[name].text;
        }},
      METRO_DISTANCE: {id:6, text: "Корректировка на дистанцию до метро", getClassVal: function (name) {
          return AnalyticsConst.metroDistance[name].text;
        }},
      TRADE: {id:7, text: "Корректировка на торг", getClassVal: function (name) {
          return "";
        }},
    }
  };



  window.AnalyticsConst = AnalyticsConst;
})();