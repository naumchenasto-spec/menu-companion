# Menu Companion

A terminal program that answers questions about a restaurant menu in three
languages — Macedonian, Albanian and English.

---

## Why I built it

> This program was build to ease my job as a waiter. 
> With just one command I can get access to different dishes in three languages, and all information about them.
> This makes it easier for me to explain the dish to the guests;
>
> Reasons:
> - I served to different people with different languages. Therefore,
>   I needed an app that will give me the description of each dish in a certain language;
>   
> - It makes easier the access to necessary information like Allergens and Ingredients, which can save lives; 
> 
> - Before this app I had to carry a physical menu with me all the time, or
>   learn a 30-dish menu in my mind; 
>

---

## What it does

Three ways to ask about the menu:

| Command | You then type | You get |
|---|---|---|
| `name` | a dish name in any of the three languages | the full dish — description, ingredients, allergens, price, in the language you typed |
| `ingredients` | an ingredient, or part of one | every dish containing it |
| `category` | a category | every dish in it |
| `quit` | — | exits |

Search is case-insensitive, and ingredient search matches partial words, so
`nuts` finds `walnuts`.

Categories currently in the data: Appetizers, Breakfast & Brunch, Dessert,
Main, Soup, Street Food.

---

## Running it

Needs a JDK (built with Java 25). No build tool, no dependencies.

```bash
git clone https://github.com/naumchenasto-spec/menu-companion.git
cd menu-companion
javac -d out src/model/*.java src/storage/*.java src/app/*.java
java -cp out app.MainApp
```

Run it from the repository root — `dishes.txt` is looked up relative to
where you start it.

If Macedonian shows as `?????`, your terminal's code page can't display Cyrillic.
On Windows, run `chcp 65001` first.

---

## Example

```
Enter what you want to know about the menu or (quit) to quit: category
Type the category:
Dessert
Ohrid Cake
Pistachio Cheesecake
Lava Cake

Enter what you want to know about the menu or (quit) to quit: name
Type the name:
Шопска салата
Е Шопска салата.
Традиционална салата со домати, краставица, пиперки, кромид и сирење.
Состојки: tomatoes, cucumber, peppers, onion, white cheese, olive oil
Алергени: dairy
Цена: 5.5 евра

Enter what you want to know about the menu or (quit) to quit: ingredients
Type the ingredients:
nuts
Ohrid Cake
```

![Terminal Look](<Screenshot 2026-09-26 131310-2.png>)


---

## The data

20 dishes live in `dishes.txt`, one per line, 11 fields separated by `|`:

```
id | category | name_en | name_sq | name_mk | desc_en | desc_sq | desc_mk | ingredients | allergens | price
```

Ingredients and allergens are comma-separated inside their field. Allergen names
come from the restaurant's own allergen guide: `celery, crustaceans, dairy,
eggs, fish, gluten, molluscs, mustard, nuts, sulphites`, or `none`.

Malformed rows don't stop the program. A row with too few fields or an unreadable
price is skipped with a message naming the line number, and the rest still load.

---

## Project structure

```
menu-companion/
├── dishes.txt              the menu data
├── NOTES.md                daily log — what confused me, and what I got wrong
└── src/
    ├── model/Dish.java         one dish
    ├── storage/DishLoader.java reads and parses dishes.txt
    └── app/MainApp.java        the command loop and the three searches
```

---

## What I'd add next

Things I found by testing, in the order I'd fix them:

- **An `allergens` command.** Ingredient search looks at ingredient names only,
  so asking for `nuts` finds the Ohrid Cake (`walnuts`) but misses the Pistachio
  Cheesecake — `pistachio` doesn't contain the word "nuts", even though the dish
  is flagged for nuts. For an app whose point is answering allergy questions,
  that's the first thing to fix.
- **Ignore diacritics when matching.** `Sallate shopska` currently finds nothing
  because the data says `Sallatë shopska`. Nobody types `ë` mid-shift.
- **Separate the output language from the search language.** Right now a dish is
  printed in whatever language you typed the name in. A waiter often knows the
  dish in Macedonian but needs to read it out in English.
- **Hot / cold / seafood appetizers.** I merged them into one `Appetizers`
  category, so "what cold starters do you have?" can't be answered any more.

---

## Notes

`NOTES.md` is a daily log I kept while building this — one line each day about
what confused me. It is not tidied up.
