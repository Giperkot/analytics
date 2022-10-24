/**
 * import type: "CSendButton",
 * import type: "CStandartFormField",
 * import type: "CStandartPupupForm",
 * import type: "CEmpty",
 *
 */

let fileTypes = {
    public: "public",
    image: "image",
    video: "video",
    documents: "documents"
};

function getUrlParam( paramName ) {
    var reParam = new RegExp( '(?:[\?&]|&)' + paramName + '=([^&]+)', 'i' );
    var match = window.location.search.match( reParam );

    return ( match && match.length > 1 ) ? match[1] : null;
}

document.addEventListener("DOMContentLoaded", function () {

    let INNER_APP = "innerApp";
    let ROOT_DIR = "rootDir";
    let TYPE = "type";

    function addDirectory() {
        popupForm.showForm();
    }

    function returnFileUrl(fileUrl) {

        let source = getUrlParam("source");

        if (source === INNER_APP) {
            let cmpName = getUrlParam("cmpName");
            window.opener.fileManagerCallbackDispatcher.execCallback(cmpName, fileUrl);
        } else {
            // ckEditor
            let funcNum = getUrlParam( 'CKEditorFuncNum' );
            // var fileUrl = '/path/to/file.txt';
            window.opener.CKEDITOR.tools.callFunction( funcNum, fileUrl);
        }

        window.close();
    }

    let rootDir = getUrlParam(ROOT_DIR);
    let filesType = getUrlParam(TYPE) || fileTypes.image;

    let fileManager = new FileManager({
        formClassName: "file_browse_form",
        linkPrefix: "/api/private/files/downloadByPath?fileLink=",
        rootDir: rootDir,
        filesType: filesType,
        addDirectory: addDirectory,
        returnFileUrl: returnFileUrl
    });

    fileManager.getServerFilesInfo();


    let popupForm = cmpCore.addElement({
        name: "popupForm",
        type: "CStandartPupupForm",
        container: ".form_popup",
        children: [
            {
                name: "wrapper",
                type: "CEmpty",
                templateId: "CFormPopupTemplate",
                container: ".form_wrapper",
                children: [
                    {
                        name: "directoryNameField",
                        type: "CStandartFormField",
                        container: ".directory_name_wrapper",
                        model: {
                            labelText: "Введите название директории",
                            value: ""
                        }
                    }, {
                        name: "directoryCreateBtn",
                        type: "CSendButton",
                        container: ".directory_create_btn",
                        model: {
                            buttonText: "Создать",
                            name: "ok",
                        },
                        events: {
                            click: function (evt) {
                                let directoryNameField = this.findByName("directoryNameField");

                                let dirName = directoryNameField.getValue();

                                if (!dirName) {
                                    return;
                                }

                                fileManager.addNewDirectory(dirName);
                                popupForm.closeEvent.call(popupForm);
                            }
                        }
                    }
                ]
            }
        ]
    });

    cmpCore.drawAll();
});
