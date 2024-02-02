package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DinnerConstructor {
    HashMap<String, ArrayList<String>> allDishes = new HashMap<>();

    public void addNewDish (String dishType, String dishName) {
        ArrayList<String> currentTypeDishes;
        if (checkType(dishType)) {
            currentTypeDishes = allDishes.get(dishType);
        } else {
            currentTypeDishes = new ArrayList<>();
            allDishes.put(dishType, currentTypeDishes);
        }
        currentTypeDishes.add(dishName);
    }

    public boolean checkType(String dishType) {
        return allDishes.containsKey(dishType);
    }

    public ArrayList<ArrayList <String>> createListOfCombos (int numberOfCombos, ArrayList<String> dishTypesList) {
        ArrayList<ArrayList <String>> combos = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < numberOfCombos; i++) {
            ArrayList<String> currentCombo = new ArrayList<>();
            for (String dishType : dishTypesList) {
                ArrayList<String> allDishesOfChosenType = allDishes.get(dishType);
                int randomIndex = random.nextInt(allDishesOfChosenType.size());
                String randomDish = allDishesOfChosenType.get(randomIndex);
                currentCombo.add(randomDish);
            }
            combos.add(currentCombo);
        }
        return combos;
    }

}
