/**
 * Created by Lodbrok on 15.07.2017.
 */

(function () {
    cmpCore.registryComponent({
        name: "CCheckbox",
        templateId: "CCheckboxTemplate",
        methods: {
            getValue: function () {
                return this.containerElm.querySelector("input").checked;
            },
            setValue: function (value) {
                this.containerElm.querySelector("input").checked = value;
            }
        }
    });
})();
