<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Advert</title>
    <style>
        body {
            border: 2px solid black;
            padding: 20px;
        }
    </style>
</head>
<body>

<p>
    This is an advertisement !
</p>

<p style="overflow-wrap: break-word;">
    Hello user number <% out.println( (String) request.getAttribute("userId") ); %>
</p>

</body>
</html>