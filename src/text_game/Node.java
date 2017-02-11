package text_game;

import java.util.ArrayList;

public class Node {
    String name;
    String des;
    ArrayList<String> directions;
    ArrayList<String> locations;
    ArrayList<String> items;

    public Node(String name, String des) {
        this.name = name;
        this.des = des;
        this.directions = new ArrayList();
        this.items = new ArrayList();
        this.locations = new ArrayList();
    }

    public ArrayList getLocations() {
        return locations;
    }

    public void setLocations(String l) {
        locations.add(l);
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDirections(String s) {
        for (int i = 0;i <directions.size();i++) {
            if (s.equals(directions.get(i))) {
                return locations.get(i);
            }
        }
            return null;
    }
    
    public ArrayList showDirections() {
        return directions;
    }

    public void setDirections(String s) {
        directions.add(s);
    }
    public int getItem(String s) {
        for (int i = 0; i<items.size();i++){
            if(s.equals(items.get(i))){
                items.remove(i);
                return 1;
            }
        }
        return 0;
    }

    public ArrayList getItems() {
        return items;
    }

    public void setItems(String i) {
        items.add(i);
    }
    
    
}
