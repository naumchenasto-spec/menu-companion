package model;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Dish {

    private String id;
    private String category;
    private HashMap<String, String> name_in_3_languages;
    private HashMap<String, String> description_in_3_languages;
    private Set<String> allergens;
    private List<String> ingredients;
    private double price;

    public Dish(String id,
         String category,
         HashMap<String,String> description_by_Language,
         HashMap<String,String> descrpition3Lang,
         Set<String> allergens,
         List<String> ingredients,
         double price){

        this.id = id;
        this.category = category;
        this.name_in_3_languages =description_by_Language;
        this.description_in_3_languages = descrpition3Lang;
        this.allergens = allergens;
        this.ingredients = ingredients;
        this.price = price;
    }

    public void addIngredients(String ingredients){
        this.ingredients.add(ingredients);
    }
    public void addAllergens(String allergen){
        this.allergens.add(allergen);
    }

    public void addLang(String id, String name){this.name_in_3_languages.put(id, name);}

    public void addDesc(String id,String description){
        this.description_in_3_languages.put(id, description);
    }

    public void setID(String id){
        this.id = id;
    }

    public void setCategory(String cat){
        this.category = cat;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public List<String> getIngredients(){return this.ingredients;}

    public Set<String> getAllergens(){return this.allergens;}

    public HashMap<String, String> getName_in_3_languages(){
        return this.name_in_3_languages;
    }
    public HashMap<String,String> getDescription_in_3_languages(){
        return this.description_in_3_languages;
    }

    public String getId(){
        return this.id;
    }

    public String getCategory(){return this.category;}

    public double getPrice(){
        return this.price;
    }

    @Override
    public String toString(){
        return "Id: " + this.id + " Category " + this.category + " Price " + this.price +
                " Allergens: " + getAllergens() + " Ingredients: " + getIngredients() +
                " Name: " + getName_in_3_languages() + " Description: " + getDescription_in_3_languages();
    }

    public void formatingMenu(String lang){

        if(lang.equalsIgnoreCase("en")){
            System.out.println("This is one of our " + this.category);
            System.out.println("It is  a " + this.name_in_3_languages.get("en") + ".");
            System.out.println(this.description_in_3_languages.get("en"));
            System.out.println("Ingredients: " + String.join(", ",this.ingredients));
            System.out.println("Allergens: " + String.join(", ", this.allergens));
            System.out.println("The price is: " + this.price + " Euros");
        }else if(lang.equalsIgnoreCase("sq")){
            System.out.println("Është një " + this.name_in_3_languages.get("sq") + ".");
            System.out.println(this.description_in_3_languages.get("sq"));
            System.out.println("Përbërësit: " + String.join(", ",this.ingredients));
            System.out.println("Alergen: " + String.join(", ", this.allergens));
            System.out.println("Çmimi është: " + this.price + " Evro");
        }else if (lang.equalsIgnoreCase("mk")){
            System.out.println("Е " + this.name_in_3_languages.get("mk") + ".");
            System.out.println(this.description_in_3_languages.get("mk"));
            System.out.println("Состојки: " + String.join(", ",this.ingredients));
            System.out.println("Алергени: " + String.join(", ", this.allergens));
            System.out.println("Цена: " + this.price + " евра");
        }else{
            System.out.println("We do not support that language");
        }
    }
}

