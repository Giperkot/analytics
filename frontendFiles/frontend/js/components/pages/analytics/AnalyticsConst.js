/**
 * (.*)\((\d+), (.*)\)
 * $1: {id: $2, title: $3}
 */

(function () {
  const AnalyticsConst = {
    roomsCount: {
      ONE: {id: 0, title: "1-к квартира"},
      TWO: {id: 1, title: "2-к квартира"},
      THREE: {id: 2, title: "3-к квартира"},
      FOUR: {id: 3, title: "4-к квартира"},
      FIVE: {id: 4, title: "5-к квартира"},
      MORE_THAN_FIVE: {id: 5, title: "более 5-к квартира"},
      FREE_LAYOUT: {id: 6, title: "Свободная планировка"},
      STUDIO: {id: 7, title: "Студия"},
      TEN_AND_MORE: {id: 8, title: "10-к и больше"}
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
      FALSE: {id: 0, title: "Отсутствует"},
      TRUE: {id: 1, title: "Присутствует"}
    },
    houseBuildYear: {
      FIFTY_AND_EARLIER: {id: 0, title: "FIFTY_AND_EARLIER"},
      FIFTY_EIGHTY: {id: 1, title: "FIFTY_EIGHTY"},
      EIGHTY_ZEROS: {id: 2, title: "EIGHTY_ZEROS"},
      ZEROS_TENTH: {id: 3, title: "ZEROS_TENTH"},
      TENTH_TWENTY: {id: 4, title: "TENTH_TWENTY"},
      TWENTY_AND_LATER: {id: 5, title: "TWENTY_AND_LATER"}
    }
  };



  window.AnalyticsConst = AnalyticsConst;
})();