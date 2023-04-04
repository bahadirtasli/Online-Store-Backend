$(document).ready(function() {
    // Get the user's location
    if ("geolocation" in navigator) {
        navigator.geolocation.getCurrentPosition(function(position) {
            var lat = position.coords.latitude;
            var lon = position.coords.longitude;

            // Get the weather for the user's location
            var apiKey = "\tibICyN7sosvhIBWocvGygyDcoNZEvD1G";
            var apiUrl = "https://dataservice.accuweather.com/locations/v1/cities/geoposition/search?apikey=" + apiKey + "&q=" + lat + "," + lon;

            $.getJSON(apiUrl, function(data) {
                var locationKey = data.Key;
                var locationName = data.EnglishName;

                var weatherApiUrl = "https://dataservice.accuweather.com/currentconditions/v1/" + locationKey + "?apikey=" + apiKey;

                // Get the current weather for the user's location
                $.getJSON(weatherApiUrl, function(weatherData) {
                    var temp = weatherData[0].Temperature.Metric.Value;
                    var description = weatherData[0].WeatherText;

                    // Display the weather widget
                    var widgetHtml = "<h2>" + locationName + "</h2>";
                    widgetHtml += "<p>Current temperature: " + temp + " C</p>";
                    widgetHtml += "<p>" + description + "</p>";


                    $("#weather-widget").html(widgetHtml);
                });
            });
        });

    } else {
        Toastify({
            text: "Geolocation is not supported by this browser.",
            backgroundColor: "#ff6961"
        }).showToast();
    }
});