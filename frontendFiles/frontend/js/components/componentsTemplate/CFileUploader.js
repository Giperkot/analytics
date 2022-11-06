/**
 * Created by Lodbrok on 06.05.2017.
 */

(function () {
    cmpCore.registryComponent({
        name: "CFileUploader",
        templateId: "CFileUploaderTemplate",
        init: function () {
            let component = this;
            component.uploadedFileTemplate = _.template(document.getElementById("CFileUploaderFileItemTemplate").innerHTML);
        },
        methods: {
            constructor: function () {
                let self = this;

                self.temp.fileMap = {};
                self.properties.maxFilesCount = self.properties.maxFilesCount || 10;
            },
            preAction: function () {
                if (this.temp.lock) {
                    return false;
                }
                return true;
            },
            click: function () {

            },
            setWait: function () {

            },
            stopWait: function () {

            },
            setAlert: function (message) {

            },
            removeAlert: function () {

            },
            setDisabled: function () {

            },
            setEnabled: function () {

            },
            setGrey: function () {

            },
            hide: function () {
                this.containerElm.classList.add("hidden");
            },
            show: function () {
                this.containerElm.classList.remove("hidden");
            },
            getFileMap: function () {
                return this.temp.fileMap;
            },
            getFormData: function () {
                let formData = new FormData();

                let keys = Object.keys(this.temp.fileMap);

                if (!keys || keys.length < 0) {
                    return formData;
                }

                for (let i = 0; i < keys.length; i++) {
                    let file = this.temp.fileMap[keys[i]];
                    formData.append("file", file);
                }

                return formData;
            },
            refreshFileList: function () {
                const html = this.component.uploadedFileTemplate({
                    data: {
                        fileArray: Object.values(this.temp.fileMap)
                    }
                });

                this.containerElm.querySelector(".uploaded_files_info").innerHTML = html;
            }
        },
        events: {
            click: function (evt) {
                let target = evt.target;

                if (!target.closest(".box__input")) {
                    return;
                }

                let fileInput = this.containerElm.querySelector(".box__file");
                let keys = Object.keys(this.temp.fileMap);

                if (this.properties.maxFilesCount <= keys.length) {
                    this.setAlert("Максимальное количество файлов: " + this.properties.maxFilesCount);
                    return;
                }

                fileInput.click();
            },
            change: function (evt) {
                // Получить файлы
                let fileInput = this.containerElm.querySelector(".box__file");
                let files = fileInput.files;
                console.log(files);
                this.temp.fileMap[files[0].name] =  files[0];

                this.refreshFileList();
                if (this.events.onLoadFile) {
                    this.events.onLoadFile();
                }
            },

            dragenter: function (evt) {
                // console.log("dragenter");

                let target = evt.target;
                let boxInput = target.closest(".file_uploader_wrapper");
                if (!boxInput) {
                    return;
                }
                evt.preventDefault();
                this.temp.fileEntered = true;
                boxInput.classList.add("dragenter_style");
            },
            dragover: function (evt) {
                console.log('File(s) in drop zone');

                // Prevent default behavior (Prevent file from being opened)
                evt.preventDefault();
                // console.log("dragover");
            },
            dragleave: function (evt) {
            // mouseleave: function (evt) {
                // console.log("dragleave");
                let target = evt.target;
                let fromElement = evt.relatedTarget;

                // console.log("dragleave: " + target.classList);
                // console.log(evt);
                if (!target.classList.contains("file_uploader_wrapper") || fromElement.closest(".file_uploader_wrapper")) {
                    return;
                }

                this.temp.fileEntered = false;
                target.classList.remove("dragenter_style");
            },
            drop: function (evt) {
                console.log('File(s) dropped');
                evt.preventDefault();

                if (evt.dataTransfer) {
                    let filesCount = Math.min(this.properties.maxFilesCount, evt.dataTransfer.files.length);

                    for (var i = 0; i < filesCount; i++) {
                        console.log('... file[' + i + '].name = ' + evt.dataTransfer.files[i].name);

                        var file = evt.dataTransfer.files[i];

                        console.log(file);
                        this.temp.fileMap[file.name] =  file;
                        console.log('... file[' + i + '].name = ' + file.name);

                    }

                    this.refreshFileList();

                    if (this.events.onLoadFile) {
                        this.events.onLoadFile();
                    }
                }
            }
        },
        afterRender: function () {

        }
    });
})();



