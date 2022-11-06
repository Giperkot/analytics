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
      FIRST_OR_LAST: {id: 0, title: "Первый или последний этаж"},
      AVERAGE: {id: 1, title: "Средние этажи"}
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
    }
  };



  window.AnalyticsConst = AnalyticsConst;
})();