<!DOCTYPE html>
<html lang="ru-RU">
<head>
    <meta charset="utf-8" />
    <link rel="stylesheet" type="text/css" href="/style/main.css" />
    <link rel="shortcut icon" href="/style/images/favicon.ico" type="image/x-icon"/>
    <title>${title}</title>
    <meta name="keywords" content="${keywords}"/>
    <meta name="description" content="${description}"/>
</head>

<body>

<div class="wrap">
    <div class="wrap_top">
        <div class="header">
            <div class="header_left">
                <div class="hamburger open_menu_btn">
                    <svg viewBox="0 0 24 24" style="display: inline-block; color: rgb(92, 109, 110); fill: rgb(92, 109, 110); height: 24px; width: 24px; user-select: none; transition: all 450ms cubic-bezier(0.23, 1, 0.32, 1) 0ms;"><path d="M3 18h18v-2H3v2zm0-5h18v-2H3v2zm0-7v2h18V6H3z"></path></svg>
                </div>
            </div>
            <div class="header_center">
                <div class="project_title">
                    <div class="header_top_left__name_container">
                        <a href="/"><span class="header_top_left__company_name header_top_left__text_style">analytics.stvolov.site</span></a>
                    </div>
                    <div class="header_top_left__shadow_container">
                        <a href="/"><span class="header_top_left__company_shadow header_top_left__text_style">analytics.stvolov.site</span></a>
                    </div>
                </div>
            </div>
            <div class="header_right">
                <div class="user_avatar"></div>
                <div class="user_name">${userInfo.name} ${userInfo.surname}</div>

                <div class="user_menu hidden">
                    <ul>
                        <li><a href="/cabinet">Личный кабинет</a></li>
                        <li><a href="/exit">Выход</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="content">
        ${innerPage}
    </div>

    <div class="footer">
        <div class="site_ar flex">
            <div class="bottomm_company">
                <div class="bottomm_company_about">
                    Меню
                </div>
                <ul>
                    <li><div><a href="/">Мои курсы</a></div></li>
                    <li><div><a href="/available">Доступные курсы</a></div></li>
                </ul>
            </div>
            <div class="bottomm_portfolio">
                <div class="bottomm_portfolio_about">
                    Кабинет
                </div>
                <ul>
                    <li><div><a href="/cabinet">Личный кабинет</a></div></li>
                </ul>
            </div>
            <div class="bottomm_servises">
                <div class="bottomm_servises_about">
                    Поддержка
                </div>
                <ul>
                    <li><div><a href="mailto:support@stvolov.ru">support@stvolov.ru</a></div></li>
                    <li>
                        <div class="social_links">
                            <a href="https://vk.com/id48031794"><div class="vk_back"></div></a>
                            <a href="https://vk.com/id48031794">ВК</a>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="menu_wrapper">
    <div class="close_menu"></div>
    <div class="menu_inner menu_inner__fil_space">
        <div class="menu_inner__content">
            <div class="menu_item" data-link="/cabinet" data-cmp="cabinetOptions">
                <div class="menu_item__icon">
                    <svg viewBox="0 0 24 24" class="menu_svg">
                        <path d="M4 18v3h3v-3h10v3h3v-6H4zm15-8h3v3h-3zM2 10h3v3H2zm15 3H7V5c0-1.1.9-2 2-2h6c1.1 0 2 .9 2 2v8z"/>
                    </svg>
                </div>
                <div class="menu_item__link">
                    Личный кабинет
                </div>
            </div>

            <div class="menu_item" data-link="/available/" data-cmp="specializationListMain">
                <div class="menu_item__icon menu_second_background">
                    <svg viewBox="0 0 24 24" class="menu_svg"><path d="M17 12h-5v5h5v-5zM16 1v2H8V1H6v2H5c-1.11 0-1.99.9-1.99 2L3 19c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2h-1V1h-2zm3 18H5V8h14v11z"></path></svg>
                </div>
                <div class="menu_item__link">
                    Мои курсы
                </div>
            </div>

            <div class="menu_item" data-link="/" data-cmp="availableCources">
                <div class="menu_item__icon menu_third_background">
                    <svg viewBox="0 0 24 24" class="menu_svg"><path d="M13 12h7v1.5h-7zm0-2.5h7V11h-7zm0 5h7V16h-7zM21 4H3c-1.1 0-2 .9-2 2v13c0 1.1.9 2 2 2h18c1.1 0 2-.9 2-2V6c0-1.1-.9-2-2-2zm0 15h-9V6h9v13z"></path></svg>
                </div>
                <div class="menu_item__link">
                    Доступные курсы
                </div>
            </div>

            <div class="menu_item hidden" data-link="/admin/" data-cmp="adminPanel" data-abilities="ADMIN">
                <div class="menu_item__icon menu_third_background">
                    <svg viewBox="0 0 24 24" class="menu_svg"><path d="M13 12h7v1.5h-7zm0-2.5h7V11h-7zm0 5h7V16h-7zM21 4H3c-1.1 0-2 .9-2 2v13c0 1.1.9 2 2 2h18c1.1 0 2-.9 2-2V6c0-1.1-.9-2-2-2zm0 15h-9V6h9v13z"></path></svg>
                </div>
                <div class="menu_item__link">
                    Панель администратора
                </div>
            </div>
        </div>
        <div class="menu_inner__bottom_content">

        </div>
    </div>
</div>

<div class="article_popup">

</div>
<div class="overlay">

</div>

${jsTemplateList}

<script type="text/javascript" src="/style/js/lodash.min.js"></script>
<script type="text/javascript" src="/style/js/componentsCore.js"></script>
<script type="text/javascript" src="/style/js/helper.js"></script>
${scriptList}

</body>
</html>
