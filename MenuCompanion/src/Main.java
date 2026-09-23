import java.util.*;
import java.io.*;

public class Main {

    static List<Dish> load_dishes(String path){

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


    static boolean findByName(List<Dish> menu, String name){

        boolean found = false;
        for (Dish d : menu){

            String matchingLang = null;

            if(name.equalsIgnoreCase(d.getName_in_3_languages().get("mk"))){
                matchingLang = "mk";
            }
            else if(name.equalsIgnoreCase(d.getName_in_3_languages().get("sq"))){
                matchingLang = "sq";
            }
            else if(name.equalsIgnoreCase(d.getName_in_3_languages().get("en"))) {
                matchingLang = "en";
            }

            if(matchingLang != null) {
                d.formatingMenu(matchingLang);
                found = true;
            }
        }
        return  found;
    }


    static boolean findByIngredients(List<Dish> menu, String input){

        boolean found = false;

        for(Dish d : menu){

            for(String ingredient : d.getIngredients()){

                if(ingredient.toLowerCase().contains(input.toLowerCase())){
                    System.out.println(d.getName_in_3_languages().get("en"));
                    found = true;
                    break; // this prevents from printing the same dish over and over for the same allergen
                }

            }
        }

        return found;
    }


    static boolean findByCategory(List<Dish> menu, String input){

        boolean found = false;

        for( Dish d : menu){

            if(d.getCategory().toLowerCase().contains(input.toLowerCase())){
                System.out.println(d.getName_in_3_languages().get("en"));
                found = true;
            }

        }

        return found;
    }

    public static void main(String[] args) {



        List<Dish> menu = load_dishes("dishes.txt");
        Scanner scanner = new Scanner(System.in);

        while(true) {

            System.out.print("Enter what you want to know about the menu or (quit) to quit: ");
            if (!scanner.hasNextLine()) break;
            String input = scanner.nextLine().trim();

            boolean found = false;

            if(input.equalsIgnoreCase("quit")){
                break;
            }

            if(input.equalsIgnoreCase("name")){

                System.out.println("Type the name:");

                if (!scanner.hasNextLine()) break;
                String newInp = scanner.nextLine().trim();

                if (newInp.equalsIgnoreCase("quit")) break;
                if (newInp.isEmpty()) {
                    System.out.println("You didn't type anything.");
                    continue;
                }

                found = findByName(menu, newInp);

                if(!found){
                    System.out.println("Dish not found!");
                }

            }else if(input.equalsIgnoreCase("ingredients")){

                System.out.println("Type the ingredients:");

                if (!scanner.hasNextLine()) break;
                String newInp = scanner.nextLine().trim();

                if (newInp.equalsIgnoreCase("quit")) break;
                if (newInp.isEmpty()) {
                    System.out.println("You didn't type anything.");
                    continue;
                }

                found = findByIngredients(menu, newInp);

                if(!found){
                    System.out.println("Dish not found!");
                }

            }else if(input.equalsIgnoreCase("category")){

                System.out.println("Type the category:");

                if (!scanner.hasNextLine()) break;
                String newInp = scanner.nextLine().trim();

                if (newInp.equalsIgnoreCase("quit")) break;
                if (newInp.isEmpty()) {
                    System.out.println("You didn't type anything.");
                    continue;
                }

                found = findByCategory(menu, newInp);

                if(!found){
                    System.out.println("Dish not found!");
                }

            }else {
                System.out.println("This command does not exist");
            }


        }
        scanner.close();

    }
}
