<%-- 
    Document   : hibernate_search
    Created on : 12 Feb, 2018, 5:18:37 PM
    Author     : Suhas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#_get_results').click(function () {
                    $.ajax({
                        type: 'GET',
                        url: "/JavaApplication/hibernate_search/" + $('#_search_text').val()

                    });
                });
            });
        </script>

    </head>
    <body>
        <div class="container">
            <div class="form-group row"> <h1>Hibernate Search (Lucen)</h1></div>
            <div class="form-group row">
                Type Search Text Hear:
                <input type="text" id="_search_text"/>
            </div>
            <div class="form-group row">
                <div class="form-group row">
                    <button id="_get_results">Search</button>
                </div>
            </div>

        </div>
    </body>
</html>
