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

    Dish(String id,
         String category,
         HashMap<String,String> description_by_Language,
         HashMap<String,String> descrition3Lang,
         Set<String> allergens,
         List<String> ingredients,
         double price){

        this.id = id;
        this.category = category;
        this.name_in_3_languages =description_by_Language;
        this.description_in_3_languages = descrition3Lang;
        this.allergens = allergens;
        this.ingredients = ingredients;
        this.price = price;
    }

    void addIngridients(String ingredients){
        this.ingredients.add(ingredients);
    }
    void addAllergens(String allergen){
        this.allergens.add(allergen);
    }

    void addLang(String id, String name){
        this.description_in_3_languages.put(id, name);
    }

    void addDesc(String id,String description){
        this.description_in_3_languages.put(id, description);
    }

    void setID(String id){
        this.id = id;
    }

    void setCategory(String cat){
        this.category = category;
    }

    void setPrice(double price){
        this.price = price;
    }

    List<String> getIngredients(){
        return this.ingredients;
    }

    Set<String> getAllergens(){
        return this.allergens;
    }

    HashMap<String, String> getName_in_3_languages(){
        return this.name_in_3_languages;
    }
    HashMap<String,String> getDescription_in_3_languages(){
        return this.description_in_3_languages;
    }

    String getId(){
        return this.id;
    }

    String getCategory(){
        return this.category;
    }

    double getPrice(){
        return this.price;
    }

    @Override
    public String toString(){
        return "Id: " + this.id + " Category " + this.category + " Price " + this.price +
                " Allergens: " + getAllergens() + " Ingredients: " + getIngredients() +
                " Name: " + getName_in_3_languages() + " Description: " + getDescription_in_3_languages();
    }
}

