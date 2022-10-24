
(function () {
    cmpCore.registryComponent({
        name: "CMainMenu",
        templateId: "CMainMenuTemplate",
        methods: {

        },
        events: {
            click: function (evt) {
                let target = evt.target.closest(".main_menu_item");

                if (!target) {
                    return;
                }

                let cmpName = target.getAttribute("data-cmp");
                let directLink = target.getAttribute("data-link");

                var pageSwitcher = cmpCore.findByName("pageSwitcher");

                if (pageSwitcher) {
                    let successChange = pageSwitcher.changePageAndhistory({
                        toPage: cmpName,
                        url: directLink
                    });

                    if (!successChange) {
                        window.location.href = directLink;
                    }
                } else {
                    window.location.href = "/lk/";
                }

            }
        }
    });
})();
