package com.example.weather;

import java.util.List;

import java.util.List;

public class ForecastWeather {
    private List<ListItem> list;

    public List<ListItem> getList() {
        return list;
    }

    public class ListItem {
        private Main main;
        private Weather[] weather;
        private String dt_txt;

        public Main getMain() {
            return main;
        }

        public Weather[] getWeather() {
            return weather;
        }

        public String getDt_txt() {
            return dt_txt;
        }

        public class Main {
            private float temp;

            public float getTemp() {
                return temp;
            }
        }

        public class Weather {
            private String description;

            public String getDescription() {
                return description;
            }
        }
    }
}
