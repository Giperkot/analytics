/**
 * Created by stvolov-is on 09.03.2017.
 *
 * commitType:
 *      BY_BLUR - editor will disappear after blur event
 *      AUTO_COMMIT - editor not disappear
 */

(function () {
    cmpCore.registryComponent({
        name: "CTextEditor",
        templateId: "CCTextEditorTemplate",
        init: function () {
            let component = this;
            component.sizeHeightMap = {
                default: 200,
                big: 406
            };
        },
        methods: {
            constructor: function () {
                let self = this;

                let height;
                if (self.model.theme && self.model.theme.indexOf("big_size") !== -1) {
                    height = self.component.sizeHeightMap.big;
                } else {
                    height = self.component.sizeHeightMap.default;
                }

                self.temp.height = height;

                self.temp.commitType = (self.properties.commitType) ? self.properties.commitType : "BY_BLUR";
                this.temp.editorActive = false;
                this.temp.editorInstance = null;
            },
            getValue: function () {

                if (this.temp.editorActive) {
                    return this.temp.editorInstance.getData();//this.temp.editorInstance.
                }

                let editor = this.containerElm.querySelector(".editor");
                // this.model.value = editor.value;
                this.model.value = editor.innerHTML;
                return this.model.value;


            },
            setValue: function (value) {
                let editor = this.containerElm.querySelector(".editor");
                // editor.value = value;

                editor.innerHTML = value;
            },
            editorBlurAction: function (evt) {
                if (!this.temp.editorActive) {
                    return;
                }

                if (this.temp.editorInstance) {
                    this.temp.editorInstance.destroy();
                    this.temp.editorInstance = null;
                    this.temp.editorActive = false;
                }
            },
            setAlert: function (message) {
                let editorWrapper = this.containerElm.querySelector(".editor");

                if (editorWrapper) {
                    editorWrapper.classList.add("alert_standart_input");
                }

                if (message) {
                    let inputWrapperAlert = this.containerElm.querySelector(".input_wrapper_alert");
                    inputWrapperAlert.innerHTML = message;
                    inputWrapperAlert.classList.remove("hidden");
                }
            }
        },
        events: {
            click: function (evt) {
                let self = this;

                if (this.temp.editorActive) {
                    return;
                }

                let editorElm = evt.target.closest(".editor");

                if (!editorElm) {
                    return;
                }

                if (self.properties.beforeOpen) {
                    self.properties.beforeOpen();
                }

                let editorInstance = window.CKEDITOR.replace(editorElm, {
                    customConfig: '/ckeditor/config.js',
                    startupFocus: true,
                    extraPlugins: 'mathjax',
                    mathJaxLib: 'https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.4/MathJax.js?config=TeX-AMS_HTML',
                    height: self.temp.height,
                    courseId: self.properties.courseId
                } );


                editorInstance.on('contentDom', function() {

                    editorInstance.on('blur', function(evt) {
                        // editorBuffer = editor.getData();

                        if (self.temp.commitType !== "BY_BLUR") {
                            return;
                        }

                        let wirisWin = document.querySelector(".wrs_modal_dialogContainer:not(.wrs_closed)");

                        if (wirisWin) {
                            return;
                        }

                        self.editorBlurAction(evt);

                        if (self.properties.beforeClose) {
                            self.properties.beforeClose();
                        }

                    });
                    /*editorInstance.on('focus', function(evt){
                        console.log('focus');
                    });*/
                });

                this.temp.editorActive = true;
                this.temp.editorInstance = editorInstance;

                editorInstance.focus();
            },
            /*blur: function (evt) {

            }*/
        },
        afterRender: function () {
            this.temp.editorActive = false;
        }
    });
})();
