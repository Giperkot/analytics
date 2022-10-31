(function () {
  cmpCore.registryComponent({
    name: "CDistrictEditor",
    templateId: "CDistrictEditorTemplate",
    methods: {
      constructor:function () {
        this.temp.map = null;
        this.temp.district = null;
      },
      init: function (standartPopup, districtInfo, cityInfo) {
        let self = this;
        let nameTextfield = standartPopup.findByName("districtNameField");
        let districtParentCombo = standartPopup.findByName("districtParentCombo");

        if (districtInfo.name) {
          nameTextfield.setValue(districtInfo.name);
        }

        let districtCombo = cmpCore.findByName("districtCombo");

        districtParentCombo.setOptionList(districtCombo.model.optionList);

        if (districtInfo.parentId) {
          districtParentCombo.setValue(districtInfo.parentId);
        }

        self.temp.district = districtInfo;

        let timeout = 0;
        if (!window.ymaps) {
          timeout = 2000;
        }

        setTimeout(function () {
          self.loadMap(districtInfo.coords, cityInfo.latitude, cityInfo.longitude);
        }, timeout);
      },
      mappingPoligon: function(arrayOfPoints) {
        let x = 0;
        let y = 0;
        let plosky = arrayOfPoints

        let east = plosky[0][1], west = plosky[0][1], south = plosky[0][0], north = plosky[0][0];

        let mapped = plosky.map(function (elm) {

          x += elm[1];
          y += elm[0];

          if (east < elm[1]) {
            east = elm[1];
          }
          if (west > elm[1]) {
            west = elm[1];
          }
          if (south > elm[0]) {
            south = elm[0];
          }
          if (north < elm[0]) {
            north = elm[0];
          }

          return {
            lat: elm[0],
            lng: elm[1]
          };
        });

        let resObj = {
          poligonBounds: mapped,
          center: {
            lng: x / plosky.length,
            lat: y / plosky.length
          },
          bound: {
            low: {
              lng: east,
              lat: south
            },
            up: {
              lng: west,
              lat: north
            }
          }
        };

        return resObj;
      },

      mappingMultiPoligon: function (arrayOfArrayOfPoints) {
        let result = [];

        for (let i = 0; i < arrayOfArrayOfPoints.length; i++) {
          result.push(this.mappingPoligon(arrayOfArrayOfPoints[i]));
        }

        return result;
      },
      getCoordinatesFromMap: function () {
        let coords = [];

        let geoObjects = this.temp.map.geoObjects;

        for (let i = 0; i < geoObjects.getLength(); i++) {
          let polyCoords = geoObjects.get(i).geometry.getCoordinates();

          for (let j = 0; j < polyCoords.length; j++) {
            coords.push(polyCoords[j]);
          }

        }

        return this.mappingMultiPoligon(coords);
      },
      loadMap: function (coords, latitude, longitude) {

        if (this.temp.map) {
          this.temp.map.container.fitToViewport()
        }

        let districtMap = document.getElementById("district_map");

        var myMap = new ymaps.Map(districtMap, {
          center: [latitude, longitude],
          // center: [58.011929, 56.242579],
          zoom: 10,
          controls: ['default', 'routeButtonControl']
        });

        let myPolygon;
        let myPolygonArr = [];
        if (coords) {
          for (let i = 0; i < coords.length; i++) {
            let poligonPoints = [];
            for (let j = 0; j < coords[i].poligonBounds.length; j++) {
              poligonPoints.push([coords[i].poligonBounds[j].lat, coords[i].poligonBounds[j].lng]);
            }

            myPolygonArr.push(poligonPoints);
          }

          // Создаем многоугольник
          myPolygon = new ymaps.Polygon(myPolygonArr);

          myMap.geoObjects
               .add(myPolygon);

          let stateMonitor = new ymaps.Monitor(myPolygon.editor.state);
          stateMonitor.add("drawing", function (newValue) {
            myPolygon.options.set("strokeColor", newValue ? '#FF0000' : '#0000FF');
          });

          myPolygon.editor.startEditing();
        } else {
          myPolygon = new ymaps.Polygon([]);
        }

        this.temp.map = myMap;
      },
      clearMap: function() {
        this.temp.map.geoObjects.removeAll();
      },
      addPolygon: function() {
        let myPolygon = new ymaps.Polygon([]);

        /*let stateMonitor = new ymaps.Monitor(myPolygon.editor.state);
        stateMonitor.add("drawing", function (newValue) {
          myPolygon.options.set("strokeColor", newValue ? '#FF0000' : '#0000FF');
        });*/

        this.temp.map.geoObjects.add(myPolygon);

        myPolygon.editor.startDrawing();
      },
      saveOrCreateDistrict: function (popupForm) {
        let districtNameField = popupForm.findByName("districtNameField");
        let districtParentCombo = popupForm.findByName("districtParentCombo");

        let coords = this.getCoordinatesFromMap();

        let data = {
          id: this.temp.district.id,
          name: districtNameField.getValue(),
          cityId: this.temp.district.cityId,
          parentId: districtParentCombo.getValue(),
          coords: coords
        };

        helper.getHttpPromise({
          method: "POST",
          url: "/api/realty/saveDistrict",
          contentType: "application/json",
          jsonData: data
        }).then(function (response) {
          var data = JSON.parse(response);

          if (data.success) {
            popupForm.showResult("Успех", "small");
          } else {
            popupForm.showResult("Произошла ошибка", "small");
          }

        });
      },
    },
    events: {
      click: function (evt) {
        let target = evt.target;

        if (target.className === "add_polygon") {
          this.addPolygon();
          return;
        }

        if (target.className === "clear_map") {
          this.clearMap();
          return;
        }

      }
    }
  });
})();
