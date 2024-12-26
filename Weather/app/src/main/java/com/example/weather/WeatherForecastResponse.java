package com.example.weather;

import java.util.List;

public class WeatherForecastResponse {
    private City city;
    private List<WeatherListItem> list;

    public City getCity() {
        return city;
    }

    public List<WeatherListItem> getList() {
        return list;
    }

    public static class City {
        private String name;

        public String getName() {
            return name;
        }
    }

    public static class WeatherListItem {
        private Main main;
        private Weather[] weather;
        private String dtTxt;

        public Main getMain() {
            return main;
        }

        public Weather[] getWeather() {
            return weather;
        }

        public String getDtTxt() {
            return dtTxt;
        }

        public static class Main {
            private double temp;

            public double getTemp() {
                return temp;
            }
        }

        public static class Weather {
            private String description;

            public String getDescription() {
                return description;
            }
        }
    }
}
