<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="web/pwc/reset.css" />
        <link rel="stylesheet" type="text/css" href="web/pwc/theme.css" />
        <link rel="stylesheet" type="text/css" href="web/pwc/flaticon.css" />
        <link rel="stylesheet" type="text/css" href="theme-light.css" />
        <title>Crossover tech trial login page</title>
    </head>
    <body>
        <script type="text/javascript" src="web/pwc/pwc.nocache.js" source-path="app" entry-point="start"></script>
        <form class="security-form" name="logout-form" action="logout.jsp" method="GET">
            <h3 style="display: inline;"><%= request.getUserPrincipal().getName()%></h3>
            <button class="btn btn-default" type="submit">Log out</button>
        </form>
    </body>
</html>

