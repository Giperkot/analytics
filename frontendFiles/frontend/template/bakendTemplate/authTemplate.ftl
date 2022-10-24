<!DOCTYPE html>
<html lang="en" >

<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <meta name="keywords" content="${keywords}"/>
    <meta name="description" content="${description}"/>

    <link rel="stylesheet" href="/style/auth.css">
    <link rel="shortcut icon" href="/style/images/favicon.ico" type="image/x-icon"/>

    <meta property="og:url" content="http://edu.stvolov.site"/>
    <meta property="og:image" content="http://edu.stvolov.site/style/images/og_image.jpg"/>
    <meta property="og:image:width" content="655"/>
    <meta property="og:image:height" content="287"/>
    <meta property="og:title" content="${title}"/>
    <meta property="og:description" content="${description}"/>

</head>

<body>

    <div class="login_container">

    </div>

    <div class="form_popup">

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
