package app;

import model.Dish;
import storage.DishLoader;
import java.util.List;
import java.util.Scanner;

import static storage.DishLoader.load_dishes;

public class MainApp {

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
