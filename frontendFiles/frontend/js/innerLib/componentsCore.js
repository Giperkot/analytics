/**
 * version 1.0.1
 */
(function () {
    function CmpCore () {
        this.componemtsClass = "component";
        this.cmps = {};
        this.elements = [];
        this.dynChildrenIndex = 0;
    }

    CmpCore.prototype.registryComponent = function (config) {
        var cmp = new Component (config);
        this.cmps[config.name] = cmp;
    };

    CmpCore.prototype.addElement = function (config) {
        if (!config.name || !config.type || !config.container) {
            console.log("Нужео имя, тип и контейнер.");
            return;
        }

        if (!this.cmps[config.type]) {
            console.log("Не найден компонент " + config.type);
            return;
        }

        var element = new Element(config);

        //config.component = this.cmps[config.type];

        this.elements.push(element);
        return element;
    };

    CmpCore.prototype.drawAll = function (force) {
        for (var i = 0; i < this.elements.length; i++) {
            if (!this.elements[i].isDrawn || force) {
                this.elements[i].draw();
                this.elements[i].bind();
            }
        }
    };

    CmpCore.prototype.findByName = function (name) {
        let result = null;

        for (var i = 0; i < this.elements.length; i++) {
            if (this.elements[i].name === name) {
                return this.elements[i];
            }

            result = this.elements[i].findByName(name);

            if (result) {
                return result;
            }
        }
    };
    /*    CmpCore.prototype.getRootElement = function (name, container) {
            for (var i = 0; i < this.elements.length; i++) {
                var containerClear = this.elements[i].container.replace(".", "");
                if (this.elements[i].name === name && (!container || containerClear === container)) {
                    return this.elements[i];
                }
            }

            return null;
        };*/

    /*
     Component
     * config
     * name - тип компонента
     * templateId - id шаблона в html
     * events - События по дефолту
     * methods - методы по дефолту.
     * runtimeTemplate - true: Задается в Элементе, false: Задаётся в компоненте.
     *
     * */
    function Component (parameters) {
        if (!parameters.name || (!parameters.templateId && !parameters.runtimeTemplate)) {
            console.log("Ошибка при создании компонента.");
            return;
        }
        this.name = parameters.name;
        this.events = parameters.events || {};
        this.methods = parameters.methods || {};
        this.afterRender = parameters.afterRender;
        this.extends = parameters.extends;
        this.runtimeTemplate = parameters.runtimeTemplate;
        this.init = parameters.init;

        var template = document.getElementById(parameters.templateId);
        if (!template && !parameters.runtimeTemplate) {
            console.log("Шаблон указан неверно. " + this.name);
            return;
        }

        if (parameters.extends) {
            var parentCmp = cmpCore.cmps[this.extends];

            for (var method in parentCmp.methods) {
                if (this.methods[method]) {
                    continue;
                }

                this.methods[method] = parentCmp.methods[method];
            }

            if (parameters.constructor === Object.prototype.constructor) {
                this.methods.constructor = parentCmp.methods.constructor;
            }

            for (var event in parentCmp.events) {
                if (this.events[event]) {
                    continue;
                }

                this.events[event] = parentCmp.events[event];
            }

            if (!parameters.init) {
                parameters.init = parentCmp.init;
            }

            if (!this.afterRender) {
                this.afterRender = parentCmp.afterRender;
            }
        }

        this.htmlContent = (!parameters.runtimeTemplate) ? _.template(template.innerHTML) : null;

        if (parameters.init) {
            parameters.init.call(this);
        }
    }

    /**
     * Element
     * @param config
     * @constructor - Конструктор
     * model
     * name
     * container
     * clearContainer - container без селекторов.
     * events
     * component
     * properties - Кастомные свойства.
     **      hidden: резервированное св-во для скрытия компонента
     * methods - для кастомных Методов
     * temp - Временное хранилище переменных (таймеров)
     * childred - дочерние компоненты...
     * commonCmpMap - мапа с потомками (для поиска по имени). Есть только у детей cmpCore
     * rootElm - ссылка на крневой элемент (у которого есть commonCmpMap)
     * containerElm - HTML element.
     * parent - ссылка на родителя.
     * dynamicConfig - Настройки для динамических элементов.
     *       array           Здесь хранится информация о массиве, из которого нужно брать информацию для заполнения.
     *       children        Для каждого дин. компонента.
     * dynChildren - Служебное поле. С ним работает только движок.
     * component.methods.preAction - резервированный метод проверки запуска событий события.
     * addAfterRender - Событие после рендера
     */

    function Element (config) {
        var self = this;
        self.model = config.model || {};
        self.name = config.name;
        self.container = config.container;
        self.clearContainer = self.container.replace(/\./g, "");
        self.events = config.events;
        self.component = cmpCore.cmps[config.type];
        self.properties = config.properties || {};
        self.constructor = config.constructor;
        self.addAfterRender = config.addAfterRender;
        self.temp = {};
        self.children = [];
        self.childNames = {};
        self.rootElm = config.rootElm || self;
        self.commonCmpMap = {};
        self.dynamicConfig = config.dynamicConfig || false;
        self.dynChildren = {};
        self.arrayModel = config.arrayModel || {};
        self.hasDynChildren = false;
        self.parent = config.parent || null;

        var template = (self.component.runtimeTemplate) ? document.getElementById(config.templateId) : null;
        if (template) {
            self.htmlContent = _.template(template.innerHTML);
        }

        if (!self.component) {
            console.error("Отсутствует компонент " + config.type);
            return;
        }

        if (self.rootElm !== self) {
            if (self.rootElm.commonCmpMap[self.name]) {
                console.error("Повторяющееся имя элемента " + self.name);
            }
            self.rootElm.commonCmpMap[self.name] = self;
        }

        if (config.children) {
            // Добавляем дочерние компоненты.
            for (var i = 0; i < config.children.length; i++) {
                config.children[i].rootElm = self.rootElm;
                config.children[i].parent = self;

                var child = new Element(config.children[i]);
                self.children.push(child);
                self.childNames[child.name] = i;
            }
        }

        if (this.dynamicConfig && this.dynamicConfig.array) {
            let parent = self.parent;
            parent.hasDynChildren = true;
            let dynCmpList = [];

            for (let i = 0; i < this.dynamicConfig.array.length; i++) {
                let config = {
                    name: self.name + cmpCore.dynChildrenIndex++,
                    type: self.component.name,
                    container: self.container,
                    model: Object.assign({}, self.model),
                    children: self.dynamicConfig.children,
                    // Будут скопированы всем дочерним компонентам
                    properties: self.properties
                };

                for (let propertyName in self.arrayModel) {
                    config.model[propertyName] = self.dynamicConfig.array[i][self.arrayModel[propertyName]];
                }

                dynCmpList.push(new Element(config));
            }

            this.parent.dynChildren[this.name] = dynCmpList;
        }

        if (this.component.methods) {
            // let methodArr = [];
            for (var method in self.component.methods) {
                // methodArr.push(method);
                if (!config.methods || config.methods && !config.methods[method]) {
                    self[method] = self.component.methods[method];
                    /*this[method] = (function () {
                        var methodClosure = method;
                        return function (params) {
                            return self.component.methods[methodClosure].call(self, params);
                        }
                    })();*/
                }
            }
        }

        if (config.methods) {
            for (var method in config.methods) {
                this[method] = config.methods[method];
            }
        }

        if (self.component.methods && self.component.methods.constructor) {
            self.component.methods.constructor.call(self);
        }

        if (config.constructor) {
            config.constructor.call(self);
        }
    }

    Element.prototype.findByName = function (name) {
        let result = this.rootElm.commonCmpMap[name];

        if (!result && this.rootElm.name === name) {
            return this.rootElm;
        }

        return result;
    };

    Element.prototype.updateModel = function (childConfig) {
        this.model = childConfig.model || {};
        this.properties = childConfig.properties || {};

        if (!childConfig.children || childConfig.children.length === 0) {
            return;
        }

        this.addChildren(childConfig.children);
    };

    Element.prototype.addChildren = function (children) {

        if (!children || children.length === 0) {
            console.error("Массив children не передан!");
            return;
        }

        // Добавляем дочерние компоненты.
        for (var i = 0; i < children.length; i++) {

            if (children[i].dynType) {
                let dynChildrenArr = this.dynChildren[children[i].dynType];

                children[i].name = children[i].dynType + cmpCore.dynChildrenIndex++;
                children[i].rootElm = this.rootElm;
                children[i].parent = this;
                var child = new Element(children[i]);
                this.childNames[child.name] = i;

                dynChildrenArr.push(child);
                continue;
            }

            var childNum = this.childNames[children[i].name];
            if (childNum || childNum === 0) {
                this.children[childNum].updateModel(children[i]);
                continue;
            }

            children[i].rootElm = this.rootElm;
            children[i].parent = this;
            var child = new Element(children[i]);
            this.children.push(child);
            var childLength = this.children.length - 1;
            this.childNames[child.name] = i;
        }
    };

    Element.prototype.removeChildren = function (childNumArr, dynamicName) {
        if (!childNumArr || childNumArr.length === 0) {
            console.error("Массив childNumArr не передан!");
            return;
        }

        if (!dynamicName) {
            for (let i = 0; childNumArr.length; i++) {
                let child = this.children[childNumArr[i]];
                let childName = child.name;

                delete this.childNames[childName];
                child.finalize();

                this.children.splice(childNumArr[i], 1);
            }
            return;
        }

        for (let i = 0; i < childNumArr.length; i++) {
            let child = this.dynChildren[dynamicName][childNumArr[i]];
            let childName = child.name;

            delete this.childNames[childName];
            child.finalize();

            this.dynChildren[dynamicName].splice(childNumArr[i], 1);
        }

    };

    /**
     * --Вызывается при изъятии компонента из DOM
     *
     * Вызывается руками.
     * для освобождения ресурсов.
     * @param isRoot Нужно ли удалить контейнер для снятия eventListeners
     */
    Element.prototype.finalize = function (isRoot) {

        if (this.destroy) {
            this.destroy();
        }

        // Сннть обработчики.
        if (isRoot) {
            let newContainer = this.containerElm.cloneNode();
            this.containerElm.parentNode.replaceChild(newContainer, this.containerElm);
        }

        // Добавляем дочерние компоненты.
        for (var i = 0; i < this.children.length; i++) {
            this.children[i].finalize();
        }
    };

    /**
     * Обновляет dynConfig у компонента.
     *
     * Добввляет нехванающие компоненты или удаляет лишние.
     *
     * @param innerDocument
     * @param forceConteinerElm
     */
    Element.prototype.updateDinamicCmps = function (dynConfigArr) {
        const self = this;

        for (let i = 0; i < dynConfigArr.length; i++) {
            let dynChildrenName = dynConfigArr[i].name;
            const dynChild = self.children[self.childNames[dynChildrenName]];

            let dynChildrenArr = self.dynChildren[dynChildrenName];

            if (dynConfigArr[i].array.length < dynChildrenArr.length) {
                dynChildrenArr.splice(dynConfigArr[i].array.length, dynChildrenArr.length - dynConfigArr[i].array.length);
            }

            for (let j = 0; j < dynConfigArr[i].array.length; j++) {
                let cmpNewConfig = dynConfigArr[i].array[j];

                if (j > dynChildrenArr.length - 1) {
                    let config = {
                        name: dynChild.name + cmpCore.dynChildrenIndex++,
                        type: dynChild.component.name,
                        container: dynChild.container,
                        model: Object.assign({}, dynChild.model),
                        children: dynChild.dynamicConfig.children
                    };

                    for (let propertyName in cmpNewConfig) {
                        config.model[propertyName] = cmpNewConfig[propertyName];
                    }

                    dynChildrenArr.push(new Element(config));
                    continue;
                }

                let newModelConfig = Object.assign({}, dynChild.model);
                for (let propertyName in cmpNewConfig) {
                    newModelConfig[propertyName] = cmpNewConfig[propertyName];
                }

                dynChildrenArr[i].model = newModelConfig;
                // dynChildrenArr[i].updateModel(newModelConfig);
            }
        }

    };

    /**
     * innerDocument - Для рендеринга дочерних компонентов...
     * @param innerDocument
     * @param forceConteinerElm - Рендер Элемента в указанный DOM
     */
    Element.prototype.draw = function (innerDocument, forceConteinerElm) {

        if (this.properties.hidden) {
            // не рендерим.
            return;
        }

        //this.addBeforeRender();
        this.render(innerDocument, forceConteinerElm);
    };

    /**
     * @param innerDocument
     * @param forceConteinerElm - Рендер Элемента в указанный DOM
     */
    Element.prototype.render = function (innerDocument, forceConteinerElm) {

        if (!innerDocument) {
            innerDocument = document;
        }

        var self = this;

        if (!forceConteinerElm) {
            var htmlContainer = (innerDocument.classList && innerDocument.classList.contains(self.clearContainer)) ? innerDocument : innerDocument.querySelector(self.container);
            if (!htmlContainer) {
                console.log("Контейнер указан неверно: " + self.name);
                return;
            }
            self.containerElm = htmlContainer;
        } else {
            self.containerElm = forceConteinerElm;
        }

        /*if (self.constructor) {
            self.constructor.call(self);
        }*/
        var elementHtml;
        try {
            let htmlContent = (!self.component.runtimeTemplate) ? self.component.htmlContent : self.htmlContent;
            elementHtml = htmlContent({data: self.model});
        } catch (ex) {
            console.log("Ошибка при заполнении шаблона. " + self.name);
            console.log(ex);
        }


        // Могут быть подкомпоненты.
        if (self.children && self.children.length > 0) {
            var parser = new DOMParser();
            var tmpDocument = parser.parseFromString(elementHtml, "text/html");

            for (var i = 0; i < self.children.length; i++) {
                // Рендер Дин. компонентов
                if (self.hasDynChildren && self.dynChildren[self.children[i].name]) {
                    let dynCnildren = self.dynChildren[self.children[i].name];
                    let forceConteinerElmList =  tmpDocument.querySelectorAll(self.children[i].container);

                    if (!forceConteinerElmList) {
                        console.error("Отсутствует контейнер компонента!");
                    }

                    for (let j = 0; j < dynCnildren.length; j++) {
                        if (!forceConteinerElmList[j]) {
                            // Если компонентов оказалось больше, чем контейнеров под них.
                            break;
                        }
                        dynCnildren[j].draw(tmpDocument, forceConteinerElmList[j]);
                    }

                    continue;
                }

                self.children[i].draw(tmpDocument);
            }

            elementHtml = tmpDocument.body.outerHTML;
            delete tmpDocument;
        }

        self.containerElm.innerHTML = elementHtml;

        self.isDrawn = true;
    };

    /**
     * Добавление обработчиков событий
     * @param container
     */

    Element.prototype.bind = function (container) {
        var self = this;

        if (self.properties.hidden) {
            // не рендерим.
            return;
        }

        if (!container) {
            container = self.containerElm;
        }

        var htmlContainer = container;

        for (var event in self.component.events) {
            htmlContainer.addEventListener(event, (function () {
                var evt = event;
                return function (e) {
                    self.component.events[evt].call(self, e);
                }
            })(), true);
        }

        for (var event in self.events) {
            htmlContainer.addEventListener(event, (function () {
                var evt = event;
                return function (e) {
                    if (self.component.methods.preAction) {
                        var ans = self.component.methods.preAction.call(self, e);
                        if (!ans) {
                            return;
                        }
                    }

                    self.events[evt].call(self, e);
                }
            })(), true);
        }

        if (self.children && self.children.length > 0) {
            for (var i = 0; i < self.children.length; i++) {

                // Bind Дин. компонентов
                if (self.hasDynChildren && self.dynChildren[self.children[i].name]) {
                    let dynCnildren = self.dynChildren[self.children[i].name];
                    let forceConteinerElmList =  htmlContainer.querySelectorAll(self.children[i].container);

                    for (let j = 0; j < dynCnildren.length; j++) {

                        if (!dynCnildren[j].isDrawn) {
                            continue;
                        }

                        dynCnildren[j].containerElm = forceConteinerElmList[j];
                        dynCnildren[j].bind(forceConteinerElmList[j]);
                    }

                    continue;
                }

                // Bind стат. компонентов
                var childContainerElm = htmlContainer.querySelector(self.children[i].container);
                self.children[i].containerElm = childContainerElm;

                self.children[i].bind(childContainerElm);
            }
        }

        if (self.component.afterRender) {
            self.component.afterRender.call(this);
        }

        if (self.addAfterRender) {
            self.addAfterRender.call(this);
        }
    };
    Element.prototype.show = function (forceRender) {
        if (!this.isDrawn || forceRender) {
            // doDraw
            this.properties.hidden = false;
            this.draw(this.rootElm.containerElm);
            this.bind();
            return;
        }

        if (!this.properties.hidden) {
            return;
        }

        this.properties.hidden = false;
        this.containerElm.classList.remove("hidden");
    };

    Element.prototype.hide = function () {
        if (this.properties.hidden) {
            return;
        }

        this.properties.hidden = true;
        this.containerElm.classList.add("hidden");
    };

    Element.prototype.reDraw = function () {

        /*if (!this.isDrawn) {
            console.log("Компонент ещё не отрисован.");
            return;
        }*/

        this.finalize();
        this.draw(this.containerElm);
        this.bind();
    };


    var cmpCore = new CmpCore();

    window.cmpCore = cmpCore;
})();
