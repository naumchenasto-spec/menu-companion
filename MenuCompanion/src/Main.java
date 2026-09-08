import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        // ---- Dish 1: Buffalo burrata with chutney ----
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
        }
    }
}
