/**
 * Created by Lodbrok on 15.07.2017.
 */

(function () {
    cmpCore.registryComponent({
        name: "CProgressBar",
        templateId: "CProgressBarTemplate",
        methods: {
            setValue: function (value) {
                if (value > this.properties.maxValue) {
                    value = this.properties.maxValue;
                }
                if (value < 0) {
                    value = 0;
                }

                var bar = this.containerElm.querySelector("progress");
                var progressBar = this.containerElm.querySelector(".progress-bar");
                var progressValue = this.containerElm.querySelector(".progress-value");
                bar.value = value;
                progressBar.style.width = (+value * 100 / this.properties.maxValue) + "%";
                progressValue.innerHTML = value + " / " + this.properties.maxValue;

                this.properties.value = value;
            },
            setMaxValue: function () {

            }
        }
    });
})();
