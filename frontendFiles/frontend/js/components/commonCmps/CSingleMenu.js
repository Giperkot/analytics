"use strict";

(function () {

    /**
     * Компонент отвечает за главное меню и меню пользователя.
     */

    function exitUser () {
        helper.getHttpPromise({
            url: "/api/auth/logout",
            method: "POST",
            contentType: "application/json"
        }).then(function (response) {
            let ans = JSON.parse(response);

            if (ans.success) {
                // На окно авторизации.
                window.location.href = "/";
            }
        });
    }


    document.addEventListener("DOMContentLoaded", function () {

        let menuWrapper = document.querySelector(".menu_wrapper");
        let closeMenuButton = menuWrapper.querySelector(".close_menu");
        let menuInnerContent = menuWrapper.querySelector(".menu_inner__content");

        let isMenuOpened = false;
        let openUserMenu = document.querySelector(".header_right");
        let userMenu = openUserMenu.querySelector(".user_menu");

        let overlay = document.querySelector(".overlay");
        let openMenuButton = document.querySelector(".header_left");

        openMenuButton.addEventListener("click", function (evt) {
            menuWrapper.classList.add("opened");
            overlay.style.display = "block";
        });

        closeMenuButton.addEventListener("click", function (evt) {
            menuWrapper.classList.remove("opened");
            overlay.style.display = "none";
        });

        overlay.addEventListener("click", function (evt) {
            menuWrapper.classList.remove("opened");
            overlay.style.display = "none";
        });

        function openMenuItem (target) {

            if (target.tagName !== "A") {
                return;
            }

            if (target.href.indexOf("exit") !== -1) {
                exitUser();

                return true;
            }


            return false;
        }

        function toggleUserMenu () {
            if (!isMenuOpened) {
                userMenu.classList.remove("hidden");
                isMenuOpened = true;
                return;
            }

            userMenu.classList.add("hidden");
            isMenuOpened = false;
        }

        menuInnerContent.addEventListener("click", function (evt) {

            let target = evt.target;
            let menuItem = target.closest(".menu_item");

            if (!menuItem) {
                return;
            }

            let linkPage = menuItem.getAttribute("data-cmp");

            if (!linkPage) {
                console.log("Ссылка не указана");
                return;
            }

            var pageSwitcher = cmpCore.findByName("pageSwitcher");

            if (pageSwitcher) {
                let successChange = pageSwitcher.changePageAndhistory({
                    toPage: linkPage
                });

                if (!successChange) {
                    let directLink = menuItem.getAttribute("data-link");
                    window.location.href = directLink;
                }

            } else {
                window.location.href = "/available/";
            }


        });

        openUserMenu.addEventListener("click", function (evt) {

            if (evt.target.closest(".user_menu")) {
                let cancelBubbling = openMenuItem(evt.target);

                if (cancelBubbling) {
                    evt.preventDefault();
                    evt.stopPropagation();
                }

                return;
            }

            toggleUserMenu();

        });

        document.addEventListener("click", function (evt) {
            if (!isMenuOpened) {
                return;
            }

            if (!evt.target.closest(".header_right")) {
                // Закрываем
                toggleUserMenu();
                return;
            }
        })


    });

})();
