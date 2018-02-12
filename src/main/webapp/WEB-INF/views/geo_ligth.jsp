<%-- 
    Document   : geo_ligth
    Created on : 5 Feb, 2018, 3:30:23 PM
    Author     : Suhas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.8.3/underscore-min.js"></script>
        <script type="text/javascript"
        src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCty1DznBG4x1xiWH532guSXOqVE6P5UJw&sensor=false"></script>
        <style type="text/css">
            div#map_container{
                height:550px;
            }
        </style>
        <script type="text/javascript">

        </script>

        <script>
            $(document).ready(function () {


                var latlng = new google.maps.LatLng(20.5937, 78.9629);
                var myOptions = {
                    zoom: 4,
                    center: latlng,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };
                var map = new google.maps.Map(document.getElementById("map_container"), myOptions);
                var marker = new google.maps.Marker({
                    position: latlng,
                    map: map,
                    title: "my hometown, Malim Nawar!"
                });



                $('#country').unbind().bind("change", function () {
                    $.ajax({
                        url: "/JavaApplication/geo_light_city/" + encodeURIComponent($('#country').val()),
                        success: function (data, textStatus, jqXHR) {
                            var str_inner_html;
                            _.each(data, function (object, index, objects) {
                                console.log(object);
                                if (object.city !== "")
                                    str_inner_html = str_inner_html + "<option value=" + object.city + " >" + object.city + "</option>";
                            });
                            $('#city').html(str_inner_html);
                            $('#lat').text("");
                            $('#lang').text("");
                        }
                    });
                });

                $('#city').unbind().bind("change", function () {
                    $.ajax({
                        url: "/JavaApplication/geo_light_lat/" + encodeURIComponent($('#country').val()) + "/" + encodeURIComponent($('#city').val()),
                        success: function (data, textStatus, jqXHR) {
                            var str_inner_html;
                            console.log(data);
                            _.each(data, function (object, index, objects) {
                                console.log(object);
                                if (object.postalCode !== "")
                                    str_inner_html = str_inner_html + "<option value=" + object.postalCode + " >" + object.postalCode + "</option>";
                            });
                            $('#postal_code').html(str_inner_html);
                            $('#lat').text("");
                            $('#lang').text("");
                        }
                    });
                });

                $('#postal_code').unbind().bind("change", function () {
                    $.ajax({
                        url: "/JavaApplication/geo_light_lat/" + encodeURIComponent($('#country').val()) + "/" + encodeURIComponent($('#city').val()) + "/" + encodeURIComponent($('#postal_code').val()),
                        success: function (data, textStatus, jqXHR) {
                            console.log(data);
                            $('#lat').text(data.latitude);
                            $('#lang').text(data.longitude);

                            var marker = new google.maps.Marker({
                                position: new google.maps.LatLng(data.latitude, data.longitude),
                                map: map
                            });
                        }
                    });
                });
            });
        </script>
    </head>
    <body >
        <h1>Hello World!</h1>
        <div class="container">
            <div class="form-group row">
                <select id="country" class="form-control">
                    <c:forEach items="${geolightCountry}" var="geo_city">
                        <option value="${geo_city}" >${geo_city}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group row">
                <select id="city" class="form-control"></select>
            </div>
            <div class="form-group row">
                <select id="postal_code" class="form-control"></select>
            </div>
            <div class="form-group row">
                Lat:<text id="lat"></text>
            </div>
            <div class="form-group row">
                Land:<text id="lang"></text>
            </div>


        </div>

        <div class="container" id="map_container">

        </div>

    </body>
</html>
