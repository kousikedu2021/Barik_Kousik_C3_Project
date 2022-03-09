import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        boolean isRestaurantOpen = false;
        if (getCurrentTime().isAfter(openingTime) && getCurrentTime().isBefore(closingTime))
            isRestaurantOpen = true;
        else if (getCurrentTime().equals(openingTime))
            isRestaurantOpen = true;

        return isRestaurantOpen;
    }

    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    public List<Item> getMenu() {
        return menu;
        //DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE
    }

    private Item findItemByName(String itemName) {
        for (Item item : menu) {
            if (item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name, price);
        menu.add(newItem);
    }

    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }

    public void displayDetails() {
        System.out.println("Restaurant:" + name + "\n"
                + "Location:" + location + "\n"
                + "Opening time:" + openingTime + "\n"
                + "Closing time:" + closingTime + "\n"
                + "Menu:" + "\n" + getMenu());

    }

    public String getName() {
        return name;
    }
// Part 3:

    public int getTotalOrder(List<String> itemChosen) {
        List<Item> allMenuItems = getMenu();
        int totalOrderCost=0;
        if(itemChosen.isEmpty()){
            return totalOrderCost;
        }else{
            for(Item item : allMenuItems){
                if(itemChosen.contains(item.getName())){
                    totalOrderCost+=item.getPrice();
                }
            }
        }
        return totalOrderCost;
    }
}

