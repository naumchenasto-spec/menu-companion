package storage;

import model.Dish;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DishLoader {

    public static List<Dish> load_dishes(String path){

        List<Dish> menu = new ArrayList<>();
        try (  BufferedReader br = new BufferedReader(new FileReader(path))){
            //here the bufferReader is closed automatically
            String dishes;

            int countLine = 0;

            while((dishes = br.readLine()) != null){

                countLine++;

                if(dishes.isBlank()){
                    continue;
                }

                String[] parts = dishes.split("\\|");

                if(parts.length < 11){
                    System.out.println("The line number "  + countLine + " is out of bounds.");
                    continue;
                }

                HashMap<String, String> names = new HashMap<>();
                HashMap<String, String> descriptions = new HashMap<>();

                Set<String> allergens = new HashSet<>();
                List<String> ingredients = new ArrayList<>();

                String id = parts[0];
                String category = parts[1];


                double price;

                try {
                    price = Double.parseDouble(parts[10]);
                } catch (NumberFormatException e) {
                    System.out.println("Line " + countLine + ": bad price, skipping.");
                    continue;                                    // <-- this is doing real work
                }



                for(String a : parts[9].split(",")){
                    allergens.add(a.trim());
                }

                for(String a : parts[8].split(",")){
                    ingredients.add(a.trim());
                }

                names.put("en", parts[2]);
                names.put("sq", parts[3]);
                names.put("mk", parts[4]);

                descriptions.put("en", parts[5]);
                descriptions.put("sq", parts[6]);
                descriptions.put("mk", parts[7]);

                Dish d = new Dish(id, category,names, descriptions,allergens,ingredients,price);

                menu.add(d);
            }
        } catch (IOException e) {
            System.out.println("Can't read file " + path + " " + e.getMessage());
        }

        return menu;
    }
}
