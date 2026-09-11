import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.*;

public class Main {

    static List<Dish> load_dishes(String path){

        List<Dish> menu = new ArrayList<>();
        try (  BufferedReader br = new BufferedReader(new FileReader(path))){
                //here the bufferReader is closed automatically
            String dishes;

            while((dishes = br.readLine()) != null){

                if(dishes.isBlank()){
                    continue;
                }

                String[] parts = dishes.split("\\|");

                HashMap<String, String> names = new HashMap<>();
                HashMap<String, String> descriptions = new HashMap<>();

                Set<String> allergens = new HashSet<>();
                List<String> ingredients = new ArrayList<>();

                String id = parts[0];
                String category = parts[1];
                double price = Double.parseDouble(parts[10]);
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
            System.out.println("Can't read file dishes.txt" + e.getMessage());
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }

        return menu;
    }
    public static void main(String[] args) {

      /*  // ---- Dish 1: Buffalo burrata with chutney ----
        HashMap<String, String> burrataNames = new HashMap<>();
        HashMap<String, String> burrataDescriptions = new HashMap<>();
        Set<String> burrataAllergens = new HashSet<>();
        List<String> burrataIngredients = new ArrayList<>();

        burrataNames.put("en", "Buffalo Burrata with Chutney");
        burrataNames.put("mk", "Биволска бурата со чатни");
        burrataNames.put("sq", "Burratë buallice me çatni");

        burrataDescriptions.put("en",
                "Creamy buffalo-milk burrata served with a spiced fruit chutney and toasted sourdough.");
        burrataDescriptions.put("mk",
                "Кремаста бурата од биволско млеко со зачинет овошен чатни и препечен леб со квасец.");
        burrataDescriptions.put("sq",
                "Burratë kremoze nga qumështi i buallit, me çatni frutash të erëzuara dhe bukë thekre të thekur.");

        burrataAllergens.add("dairy products");

        burrataIngredients.add("Buffalo Burrata");
        burrataIngredients.add("chutney");
        burrataIngredients.add("pesto gjenoveze");
        burrataIngredients.add("14 karat gold");

        Dish burrata = new Dish(
                "D001",
                "Cold Appetizers",
                burrataDescriptions,
                burrataNames,
                burrataAllergens,
                burrataIngredients,
                14.50);

        // ---- Dish 2: Asparagus with parmesan ----
        HashMap<String, String> asparagusNames = new HashMap<>();
        HashMap<String, String> asparagusDescriptions = new HashMap<>();
        Set<String> asparagusAllergens = new HashSet<>();
        List<String> asparagusIngredients = new ArrayList<>();

        asparagusNames.put("en", "Asparagus with Parmesan");
        asparagusNames.put("mk", "Шпаргла со пармезан");
        asparagusNames.put("sq", "Asparagus me parmezan");

        asparagusDescriptions.put("en",
                "Grilled green asparagus with shaved parmesan, olive oil and cracked black pepper.");
        asparagusDescriptions.put("mk",
                "Печена зелена шпаргла со ренде пармезан, маслиново масло и свежо мелен црн бибер.");
        asparagusDescriptions.put("sq",
                "Asparagus i gjelbër në skarë me parmezan të grirë, vaj ulliri dhe piper të zi të bluar.");

        asparagusAllergens.add("dairy products");

        asparagusIngredients.add("Asparagus");
        asparagusIngredients.add("Parmesan");

        Dish asparagus = new Dish(
                "D002",
                "Starters",
                asparagusDescriptions,
                asparagusNames,
                asparagusAllergens,
                asparagusIngredients,
                12.00);

        // ---- Put every dish in one list and print it ----
        List<Dish> menu = new ArrayList<>();
        menu.add(burrata);
        menu.add(asparagus);

        for (Dish a : menu) {
            System.out.println(a);
            System.out.println();
        }*/

        List<Dish> menu = load_dishes("dishes.txt");

        System.out.println("Loaded " + menu.size() + " dishes.");
        System.out.println();

        String lang = "sq";

        for (Dish d : menu) {
            d.formatingMenu(lang);
            System.out.println();
        }

    }
}
