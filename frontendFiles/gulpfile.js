"use strict";

var gulp = require('gulp');
const less = require("gulp-less");
const concat = require("gulp-concat");
const debug = require("gulp-debug");
const sourcemaps = require("gulp-sourcemaps");
const del = require("del");
const newer = require("gulp-newer");
// const uglify = require("gulp-uglify");
const uglify = require('gulp-uglify-es').default;
const cleanCSS = require('gulp-clean-css');
// const concatUtil = require('gulp-concat-util');
const fs = require("fs");

/*const destinationPath = "../analytics-web/src/main/webapp/style";
const destinationHTML = "../analytics-web/src/main/webapp/frames";
const templateDestPath = "../analytics-web/src/main/webapp/template";*/
const destinationPath = "../analytics-web/target/analytics-web/style";
const destinationHTML = "../analytics-web/target/analytics-web/frames";
const templateDestPath = "../analytics-web/target/analytics-web/template";
const templateAddPath = "frontend/template/";
const scenarioDirPath = "frontend/js/scenario/";
const slashesRegexp = /[\\\/]/gi;
const quoteRegexp = /"/g;

//NODE_ENV=prod gulp buildStyles
const isDevelopment = (process.env.NODE_ENV != "prod");


let templateCmpCache = {};

/**
 * Шаблоны
 */

function readFile (filePath) {
    return new Promise(function (resolve, reject) {
        if (templateCmpCache) {
            resolve(templateCmpCache);
            return;
        }

        fs.readFile(filePath, function (err, buf) {
            let fileStr = buf.toString("utf-8");
            let config = JSON.parse(fileStr);

            resolve(config);
        })
    });
}

function concatTemplates(pageName, templatePathList) {

    let pointPos = pageName.indexOf(".");
    let tmpName = (pointPos !== -1) ? pageName.substring(0, pointPos) : pageName;
    tmpName = tmpName + ".jstmpl";

    if (templatePathList.length < 1) {
        //templateDestPath + "/frontend/" + tmpName
        // D:/С диска E/Програмки/Мои проекты/java/rosReestrStatementBot/rosReestrWeb/src/main/webapp/template/frontend
        setTimeout(function () {
            fs.writeFile("./../rosReestrWeb/src/main/webapp/template/frontend/" + tmpName, "<div></div>", function(error){
                if (error) {
                    console.log("ERROR: -> -> " + error);
                }

                console.log("empty " + tmpName + " is created");
            });
        }, 1000);
        return;
    }

    gulp.src(templatePathList)
        .pipe(concat(tmpName))
        .pipe(gulp.dest(templateDestPath + "/frontend"));
}

function getTemplateName (fileContent) {
    let templateProp = "templateId:";

    let startPlace = fileContent.indexOf(templateProp);
    let startTamplatePlace = startPlace + templateProp.length;

    let endTamplatePlace = fileContent.indexOf(",", startTamplatePlace);
    let strBreakCharInd = fileContent.indexOf("\n", startTamplatePlace);
    let endObjectCharInd = fileContent.indexOf("}", startTamplatePlace);

    endTamplatePlace = (endTamplatePlace > 0) ? endTamplatePlace : fileContent.length;
    strBreakCharInd = (strBreakCharInd > 0) ? strBreakCharInd : fileContent.length;
    endObjectCharInd = (endObjectCharInd > 0) ? endObjectCharInd : fileContent.length;

    endTamplatePlace = Math.min(endTamplatePlace, strBreakCharInd, endObjectCharInd);

    let templateName = fileContent.substring(startTamplatePlace, endTamplatePlace);

    // console.log(templateName);
    return new Promise(function (resolve, reject) {
        return resolve(templateName.trim().replace(quoteRegexp, ""));
    });

}

function getComponentListFromScenario (fileContent) {
    let typeProp = "type:";

    let cmpExists = true;
    let startIndex = 0;

    let cmpList = [];

    while (cmpExists) {

        let startPlace = fileContent.indexOf(typeProp, startIndex);

        if (startPlace === -1) {
            break;
        }

        startPlace = startPlace + typeProp.length;
        let endPlace = fileContent.indexOf(",", startPlace);
        let templateName = fileContent.substring(startPlace, endPlace);
        // console.log(templateName);
        let cmpName = templateName.trim().replace(quoteRegexp, "");
        // console.log(cmpList.indexOf("C"));
        if (/*cmpName[0] === "C" && */cmpList.indexOf(cmpName) === -1) {
            cmpList.push(cmpName);
        }

        startIndex = startPlace + 1;
    }

    return cmpList;
}


function generateFiles(data) {

    let pageName = (data.fileName.lastIndexOf(".js") !== -1) ? data.fileName : data.fileName + ".js";
    let cmpList = data.cmps;

    let destFilePath = destinationPath + "/js/pagesCmp/";

    if (data.directory) {
        destFilePath = destFilePath + "/" + data.directory;
    }

    // let destFileName = destinationPath + "/js/pagesCmp/" + pageName;

    // console.log(destFileName);

    let filePathList = [];
    let templatePathList = [];

    for (let i = 0; i < cmpList.length; i++) {
        let cmpFileName = (cmpList[i].lastIndexOf(".js") !== -1) ? cmpList[i] : cmpList[i] + ".js";

        filePathList.push("frontend/js/components/**/" + cmpFileName);
    }

    console.log("generateFiles: " + data.fileName);

    console.log(filePathList);

    if (filePathList.length < 1) {
        // создать пустой файл.
        concatTemplates(pageName, templatePathList);
        return;
    }

    let vinyl = gulp.src(filePathList)
        .on("data", function (file) {
            let fileStr = file.contents.toString("utf-8");
            getTemplateName(fileStr)
                .then(function (templateName) {
                    // console.log("templateName");
                    // console.log(templateName);

                    templatePathList.push("frontend/template/jsTemplate/**/" + templateName + ".html");
                });

        });

    if (isDevelopment) {
        vinyl = vinyl.pipe(sourcemaps.init())
    }

    vinyl = vinyl.pipe(concat(pageName));

    if (isDevelopment) {
        vinyl = vinyl.pipe(sourcemaps.write())
    }

    vinyl.pipe(gulp.dest(destFilePath))
        .on("end", function () {
            concatTemplates(pageName, templatePathList);
        });
}

/**
 * Создание scriptList.json
 */
gulp.task("scriptListGenerate", function () {
    templateCmpCache = {};

    return gulp.src("frontend/js/scenario/**/*.js", {since: gulp.lastRun("scriptListGenerate")})
        .on("data", function (file) {
            let fileName = file.basename.substring(0, file.basename.lastIndexOf("."));
            templateCmpCache[fileName] = {};

            console.log(file.basename);
            // console.log(file);

            let dirPath = file.dirname.replace(/\\/g, "/");
            console.log(dirPath);
            let idx = dirPath.indexOf(scenarioDirPath);

            let fileStr = file.contents.toString("utf-8");
            let cmpList = getComponentListFromScenario(fileStr);

            templateCmpCache[fileName].fileName = fileName;
            templateCmpCache[fileName].cmps = cmpList;

            if (idx > 0 && idx + scenarioDirPath.length < dirPath.length - 1) {
                templateCmpCache[fileName].directory = dirPath.substring(idx + scenarioDirPath.length);
            }
        }).on("end", function () {
            fs.writeFile("frontend/scriptList.json", JSON.stringify(templateCmpCache), function(error){
                console.log("scriptList.json is created");
            });
        });
});


/**
 * Копирование templates для бакенда
 */
gulp.task("copyTemplates", function () {

    //templateAddPath + "backendTemplate/**/*.ftl"
    return gulp.src("frontend/template/bakendTemplate/**/*.ftl", {since: gulp.lastRun("copyTemplates")})
        .pipe(newer(templateDestPath + "/backend"))
        .pipe(debug({title: "copyTemplates"}))
        .pipe(gulp.dest(templateDestPath + "/backend"));
});

/**
 * Объединениие templates для бакенда и компонентов js.
 */
gulp.task("generateTemplates", function (cb) {

    return readFile("frontend/scriptList.json").then(function (data) {
        // console.log(data);

        let keys = Object.keys(data);

        for (let i = 0; i < keys.length; i++) {
            let key = keys[i];
            let pageData = data[key];

            generateFiles(pageData);
        }


        return cb;
    });
});

/**
 * Работа с js
 */

gulp.task("copyOuterLibs", function () {
    return gulp.src("frontend/js/outerLib/**/*.js", {since: gulp.lastRun("copyOuterLibs")})
        .pipe(newer(destinationPath + "/js"))
        .pipe(debug({title: "copyOuterLibs"}))
        .pipe(gulp.dest(destinationPath + "/js"));
});

gulp.task("minJs", function () {
    // Копируем всё кроме initLevels, они пакуются в отдельный файл.
    var vinyl = gulp.src(["frontend/js/innerLib/**/*.js", "!frontend/js/innerLib/initLevels/*.js", ])
        .pipe(newer(destinationPath + "/js"));

    if (!isDevelopment) {
        vinyl = vinyl.pipe(uglify());
    }
    return vinyl.pipe(gulp.dest(destinationPath + "/js"));
});

gulp.task("minIninLevels", function () {
    var vinyl = gulp.src("frontend/js/innerLib/initLevels/*.js")
        .pipe(newer(destinationPath + "/js"));

    if (isDevelopment) {
        vinyl = vinyl.pipe(sourcemaps.init())
    }

    if (!isDevelopment) {
        vinyl = vinyl.pipe(uglify());
    }

    vinyl = vinyl.pipe(concat("stageInit.min.js"));

    if (isDevelopment) {
        vinyl = vinyl.pipe(sourcemaps.write())
    }

    return vinyl.pipe(gulp.dest(destinationPath + "/js"));
});

gulp.task("minScenarios", function () {
    var vinyl = gulp.src("frontend/js/scenario/**/*.js")
        .pipe(newer(destinationPath + "/js"));

    if (!isDevelopment) {
        vinyl = vinyl.pipe(uglify());
    }
    return vinyl.pipe(gulp.dest(destinationPath + "/js/scenario"));
});

/*gulp.task("minJsComponents", function () {
    var vinyl = gulp.src("frontend/js/components/!*.js")
        .pipe(newer(destinationPath + "/js"));

    if (isDevelopment) {
        vinyl = vinyl.pipe(sourcemaps.init())
    }

    if (!isDevelopment) {
        vinyl = vinyl.pipe(uglify());
    }
    vinyl = vinyl.pipe(concat("components.min.js"));

    if (isDevelopment) {
        vinyl = vinyl.pipe(sourcemaps.write())
    }

    return vinyl.pipe(gulp.dest(destinationPath + "/js"));
});*/

gulp.task("minJsPhysicsEngine", function () {
    var vinyl = gulp.src("frontend/js/physicsEngine/*.js")
        .pipe(newer(destinationPath + "/js"));

    if (isDevelopment) {
        vinyl = vinyl.pipe(sourcemaps.init())
    }

    if (!isDevelopment) {
        vinyl = vinyl.pipe(uglify());
    }
    vinyl = vinyl.pipe(concat("physicsEngine.min.js"));

    if (isDevelopment) {
        vinyl = vinyl.pipe(sourcemaps.write())
    }

    return vinyl.pipe(gulp.dest(destinationPath + "/js"));
});

gulp.task("copyImages", function () {
    return gulp.src("frontend/images/**/*.{jpg,jpeg,png,ico,svg}", {since: gulp.lastRun("copyImages"), base: "frontend"})
        .pipe(gulp.dest(destinationPath));
});

gulp.task("copyCss", function () {
    var vinyl = gulp.src(["frontend/less/*.css", "frontend/less/frames/**/*.css", "frontend/less/outer/**/*.css"], {since: gulp.lastRun("copyCss"), base: "frontend/less"})
        .pipe(newer(destinationPath));

    if (!isDevelopment) {
        vinyl = vinyl.pipe(cleanCSS({compatibility: 'ie8'}));
    }
    return vinyl.pipe(debug({title: "copyCss"}))
        .pipe(gulp.dest(destinationPath));
});

/*gulp.task("copyOtherCss", function () {
    var vinyl = gulp.src("frontend/less/frames/!**!/!*.css", {since: gulp.lastRun("copyOtherCss"), base: "less"})
        .pipe(newer(destinationPath));

    if (!isDevelopment) {
        vinyl = vinyl.pipe(cleanCSS({compatibility: 'ie8'}));
    }
    return vinyl.pipe(debug({title: "copyOtherCss"}))
        .pipe(gulp.dest(destinationPath));
});*/

gulp.task("styles", function (completeCallback) {

    const styleToAssembleArr = [
        {
            sourcePath: "frontend/less/auth.less",
            destName: "auth.css",
            destPath: ""
        }, {
            sourcePath: "frontend/less/style.less",
            destName: "main.css",
            destPath: ""
        }, /*{
            sourcePath: "frontend/less/frames/fileManager/fileManager.less",
            destName: "fileManager.css",
            destPath: "/frames/fileManager"
        }*/
    ];

    let vinyl;
    for (let i = 0; i < styleToAssembleArr.length; i++) {
        let element = styleToAssembleArr[i];

        vinyl = gulp.src(element.sourcePath);
        if (isDevelopment) {
            vinyl = vinyl.pipe(sourcemaps.init());
        }
        vinyl = vinyl.pipe(less())
            .pipe(concat(element.destName));

        if (isDevelopment) {
            vinyl = vinyl.pipe(sourcemaps.write());
        }
        vinyl.pipe(gulp.dest(destinationPath + element.destPath));
    }

    if (vinyl) {
        return vinyl;
    } else {
        completeCallback();
    }

});

gulp.task("clean", function () {

    del(templateDestPath, {
        force: true
    });
    return del(destinationPath, {
        force: true
    });
});

gulp.task("copyHtml", function () {
    return gulp.src("frontend/frames/**/*.{html,js}", {since: gulp.lastRun("copyHtml"), base: "frontend/frames"})
        .pipe(gulp.dest(destinationHTML));
});


//"generateTemplates"
// Главная задача.
gulp.task("buildStyles", gulp.series("clean", "scriptListGenerate",
    gulp.parallel("styles", "copyCss", "copyImages", "copyOuterLibs", "minJs", "minScenarios",
        /*"minJsComponents",*/ "minJsPhysicsEngine", "copyTemplates", "generateTemplates", "minIninLevels", "copyHtml")));

if (isDevelopment) {
    gulp.watch("frontend/less/**/*.less*", gulp.series("styles"));
    gulp.watch("frontend/less/*.css*", gulp.series("copyCss"));
    gulp.watch("frontend/images/!**/!*.*", gulp.series("copyImages"));
    gulp.watch("frontend/js/outerLib/**/*.js", gulp.series("copyOuterLibs"));
    gulp.watch("frontend/js/innerLib/**/*.js", gulp.series("minJs"));
    gulp.watch("frontend/js/scenario/**/*.js", gulp.series("scriptListGenerate", "minScenarios"));
// gulp.watch("frontend/js/components/!*.js", gulp.series("minJsComponents"));
    gulp.watch("frontend/js/physicsEngine/!*.js", gulp.series("minJsPhysicsEngine"));
    gulp.watch("frontend/js/innerLib/initLevels/*.js", gulp.series("minIninLevels"));
    gulp.watch("frontend/frames/**/*.{html,js}", gulp.series("copyHtml"));

    gulp.watch(["frontend/js/components/**/*.js", "frontend/template/jsTemplate/**/*.html"], gulp.series("generateTemplates"));
    gulp.watch("frontend/template/bakendTemplate/**/*.ftl", gulp.series("copyTemplates"));
}

