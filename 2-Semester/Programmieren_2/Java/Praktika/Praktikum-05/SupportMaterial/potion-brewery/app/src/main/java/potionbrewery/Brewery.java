package potionbrewery;

import java.lang.reflect.Parameter;
import java.util.*;

public class Brewery {

    private List<BreweryItem> items;

    public Brewery(){
        items = new LinkedList<>();
    }

    public void add(BreweryItem item){
        if(item == null)
            throw new IllegalArgumentException("Item cannot be null");
        items.add(item);
    }

    public List<BreweryItem> getItems(){
        return Collections.unmodifiableList(items);
    }

    public boolean contains(BreweryItem item){
        if(item == null)
            throw new IllegalArgumentException("Item cannot be null");
        return items.contains(item);
    }

    public void remove(BreweryItem item){
        if(item == null)
            throw new IllegalArgumentException("item cannot be null");
        if(!items.contains(item))
            throw new BreweryException("No such item in Brewery");
        items.remove(item);
    }

    public Potion brew(Ingredient mainIngredient, Ingredient effectIngredient, Ingredient potencyIngredient){
        if (mainIngredient == null)
            throw new IllegalArgumentException("mainIngredient cannot be null");

        if (effectIngredient == null)
            throw new IllegalArgumentException("effectIngredient cannot be null");

        if (potencyIngredient == null)
            throw new IllegalArgumentException("potencyIngredient cannot be null");

        if(!items.contains(mainIngredient))
            throw new BreweryException("You don't have the main ingredient: " +mainIngredient.getName()+ " in your inventory");

        if(!items.contains(effectIngredient))
            throw new BreweryException("You don't have the effect ingredient: " +effectIngredient.getName()+ " in your inventory");

        if(!items.contains(potencyIngredient))
            throw new BreweryException("You don't have the potency ingredient: " +potencyIngredient.getName()+ " in your inventory");

        remove(mainIngredient);
        remove(effectIngredient);
        remove(potencyIngredient);

        Potion newPotion = new Potion(mainIngredient, effectIngredient, potencyIngredient);

        add(newPotion);
        return newPotion;
    }

    public List<BreweryItem> sortedItems(){
        Collections.sort(items);
        return getItems();
    }

    public List<Ingredient> getIngredients(){
        List<Ingredient> list = new ArrayList<>();
        for(BreweryItem item : items){
            if(item instanceof Ingredient)
                list.add((Ingredient) item);
        }
        return list;
    }

    private static class IngredientComparator implements Comparator<Ingredient>{

        @Override
        public int compare(Ingredient x, Ingredient y){
            int result = x.getEffect().compareTo(y.getEffect());
            if(result == 0){
                result = x.getPotency() - y.getPotency();
            }
            if(result == 0){
                result = x.compareTo(y);
            }
            return result;
        }
    }

    public List<Ingredient> sortedIngredients(){
        List<Ingredient> ingredients = getIngredients();

        ingredients.sort(new IngredientComparator());

        return ingredients;
    }

    public int getTotalValue(){
        int total = 0;
        for(BreweryItem item : items){
            total += item.getValue();
        }
        return total;
    }

    public Potion getMostValuablePotion(){
        Potion mostValuable = null;
        for(BreweryItem item : items){
            if(item instanceof Potion){
                Potion currentPotion = (Potion)item;
                if(mostValuable == null || currentPotion.getValue() > mostValuable.getValue()){
                    mostValuable = currentPotion;
                }
            }
        }
        return mostValuable;
    }

    public Map<BreweryItem, Long> getInventory(){
        Map<BreweryItem, Long> map = new HashMap<>();
        for(BreweryItem item : items){
            if(map.containsKey(item)){
                map.put(item, map.get(item) + 1);
            } else {
                map.put(item, 1L);
            }
        }
        return map;
    }
}
