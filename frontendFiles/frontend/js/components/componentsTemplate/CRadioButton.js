/**
 * Created by Lodbrok on 06.05.2017.
 */

(function () {
    cmpCore.registryComponent({
        name: "CRadioButton",
        templateId: "CRadioButtonTemplate",
        methods: {
            getValue: function () {
                return this.containerElm.querySelector("input").checked;
            }
        },
        events: {
            /*change: function (evt) {
                this.model.value = evt.target.checked;
            }*/
        }
    });
})();
