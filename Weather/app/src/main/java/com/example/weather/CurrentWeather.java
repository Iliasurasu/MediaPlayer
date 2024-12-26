package com.example.weather;

public class CurrentWeather {
    private String name;  // Имя города
    private Main main;    // Объект, содержащий температуру
    private Weather[] weather;  // Массив объектов погодных условий (например, описание)

    // Геттер для имени города
    public String getName() {
        return name;
    }

    // Геттер для объекта main (с температурой)
    public Main getMain() {
        return main;
    }

    // Геттер для массива объектов Weather (с описанием)
    public Weather[] getWeather() {
        return weather;
    }

    // Вложенный класс для температуры
    public static class Main {
        private double temp;  // Температура, тип double для большей точности

        // Геттер для температуры
        public double getTemp() {
            return temp;
        }
    }

    // Вложенный класс для погодных условий (например, описание)
    public static class Weather {
        private String description;  // Описание погоды

        // Геттер для описания
        public String getDescription() {
            return description;
        }
    }
}
