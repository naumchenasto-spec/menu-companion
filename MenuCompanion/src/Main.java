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
    public static void main(String[] args) {



        List<Dish> menu = load_dishes("dishes.txt");
        Scanner scanner = new Scanner(System.in);

        while(true) {

            System.out.print("Enter the name of you dish in any language or (quit) to quit: ");
            String input = scanner.nextLine().trim();

            if( input.equalsIgnoreCase("quit")){
                break;
            }

            boolean found = false;

            for (Dish d : menu){

                String matchingLang = null;

                if(input.equalsIgnoreCase(d.getName_in_3_languages().get("mk"))){
                    matchingLang = "mk";
                }
                else if(input.equalsIgnoreCase(d.getName_in_3_languages().get("sq"))){
                    matchingLang = "sq";
                }
                else if(input.equalsIgnoreCase(d.getName_in_3_languages().get("en"))) {
                    matchingLang = "en";
                }

                if(matchingLang != null) {
                    d.formatingMenu(matchingLang);
                    found = true;
                }
            }



            if(!found){
                System.out.println("Dish not found!");
            }

        }

        scanner.close();

    }
}
