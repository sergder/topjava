package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MealsUtil {
    public static List<Meal> meals = new ArrayList<>();

    static {
        meals.add(new Meal(LocalDateTime.of(2020,6,1,7,30),"Breakfast",500));
        meals.add(new Meal(LocalDateTime.of(2020,6,1,12,30),"Lunch",1000));
        meals.add(new Meal(LocalDateTime.of(2020,6,1,18,30),"Supper",500));
        meals.add(new Meal(LocalDateTime.of(2020,6,2,7,30),"Breakfast",520));
        meals.add(new Meal(LocalDateTime.of(2020,6,2,12,30),"Lunch",1000));
        meals.add(new Meal(LocalDateTime.of(2020,6,2,18,30),"Supper",510));
        meals.add(new Meal(LocalDateTime.of(2020,6,3,7,30),"Breakfast",440));
        meals.add(new Meal(LocalDateTime.of(2020,6,3,12,30),"Lunch",1200));
        meals.add(new Meal(LocalDateTime.of(2020,6,3,18,30),"Supper",600));
        meals.add(new Meal(LocalDateTime.of(2020,6,4,7,30),"Breakfast",480));
        meals.add(new Meal(LocalDateTime.of(2020,6,4,12,30),"Lunch",1100));
        meals.add(new Meal(LocalDateTime.of(2020,6,4,18,30),"Supper",330));
    }

    public static List<MealTo> filteredByStreams(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
//                      Collectors.toMap(Meal::getDate, Meal::getCalories, Integer::sum)
                );

        return meals.stream()
                .filter(meal -> TimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime))
                .map(meal -> createTo(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    private static MealTo createTo(Meal meal, boolean excess) {
        return new MealTo(meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }
}
