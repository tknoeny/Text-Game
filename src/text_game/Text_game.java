package Text_game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;


public class Text_game {

    public static void main(String[] args) {
        ArrayList<String> inventory = new ArrayList();
        ArrayList<String> tokens;
        ArrayList<String> keys = new ArrayList<>();
        boolean Quit = false;
        Hashtable<String,Node> nodes = new Hashtable<>();
        try{
        FileReader reader = new FileReader("text.txt");
        BufferedReader br = new BufferedReader(reader);
        String name,des,dir,item;
        while((name = br.readLine())!= null){
            keys.add(name);
            des = br.readLine();
            nodes.put(name, new Node(name,des));
            Node name1 = nodes.get(name);
            while(!"#".equals(dir = br.readLine())){
                String[] words = dir.split(" ");
                name1.setDirections(words[0]);
                name1.setLocations(words[1]);
            }
            while(!"/".equals(item=br.readLine())){
                name1.setItems(item);
            }
        }
        }
        catch(IOException e){
            System.out.println("error");
        }
        System.out.println("Go forth at your own risk..\n");
        Scanner scanner = new Scanner(System.in);
        int index = 0;
        while (!Quit) {
            System.out.print("Command: ");
            
            tokens = tokenizer(scanner.nextLine());

            switch (tokens.get(0)) {
                case "go":
                    if (tokens.size() < 2) {
                        System.out.println("Go where?");
                        break;
                    } else if (tokens.size() > 2) {
                        System.out.println("Too many arguments");
                        break;
                    } else {
                        String direction = nodes.get(keys.get(index)).getDirections(tokens.get(1));
                        if(direction==null){
                            System.out.println("I don't know how to do that!");
                            break;
                        }
                        String location = nodes.get(direction).getDes();
                        System.out.println(location);
                        index = indexe(nodes.get(keys.get(index)).getDirections(tokens.get(1)));
                        System.out.println("There is a " + nodes.get(keys.get(index)).getItems());
                        System.out.println("Available paths " + nodes.get(keys.get(index)).showDirections() + " lead toward " + nodes.get(keys.get(index)).getLocations() + " respectively.");
                        break;
                    }
                case "pickup":
                    if (tokens.size() < 2) {
                        System.out.println("Pickup what?");
                        break;
                    } else {
                        String item = tokens.get(1);
                        int i = nodes.get(keys.get(index)).getItem(item);
                        if(i == 0){
                            System.out.println("No " + item + " here!");
                            break;
                        }
                        else{
                            System.out.println(item + " was picked up");
                            inventory.add(item);
                        }
                          
                        //System.out.println(tokens.get(1) + " was picked up");
                        break;
                    }
                case "drop":
                    if (tokens.size() < 2) {
                        System.out.println("Drop what?");
                        break;
                    } else {
                        System.out.println(tokens.get(1) + " was dropped");
                        break;
                    }
                case "save":
                    System.out.println("Game saved");
                    break;
                case "look":
                    System.out.println(nodes.get(keys.get(index)).getDes());
                    System.out.println("There is a " + nodes.get(keys.get(index)).getItems());
                    System.out.println("Available paths " + nodes.get(keys.get(index)).showDirections() + " lead toward " + nodes.get(keys.get(index)).getLocations() + " respectively.");
                    break;
                case "quit":
                    System.out.println("Game over!");
                    Quit = true;
                    break;
                case "things":
                    String order = tokens.get(1);
                    if(order.equals("asc")||order.equals("des")){
                    System.out.println("There is a " + insertSort(nodes.get(keys.get(index)).getItems(),order));
                    }
                    else{
                        System.out.println("Not a valid command!");
                    }
                    break;
                case "leave":
                    boolean remove = false;
                    String left = tokens.get(1);
                    for(int i = 0;i<inventory.size();i++){
                        if(left.equals(inventory.get(i))){
                            nodes.get(keys.get(index)).setItems(left);
                            inventory.remove(i);
                            System.out.println("You left " + left + " on the ground.");
                            remove = true;
                    } 
                    }
                    if(!remove)
                            System.out.println("That item isn't in your inventory!");
                    
                    break;
                case "inventory":
                    String order1 = tokens.get(1);
                    if(order1.equals("asc")||order1.equals("des")){
                    System.out.println("You are holding " + insertSort(inventory,order1));
                    }
                    else{
                        System.out.println("Not a valid command!");
                    }
                    break;
                default:
                    System.out.println("Error - No such command.");
            }
        }
    }

    public static ArrayList<String> tokenizer(String input) {
        ArrayList<String> tokens = new ArrayList<>();

        StringTokenizer tokenizer = new StringTokenizer(input);
        while (tokenizer.hasMoreTokens()) {
            tokens.add(tokenizer.nextToken());
        }
        return tokens;
    }
    
    public static int indexe(String in){
        switch(in){
            case "swamp":
                return 0;
            case "forest":
                return 1;
            case "field":
                return 2;
            case "house":
                return 3;
            case "portal":
                return 4;
            default:
                return -1;
        }
    }
    
    public static ArrayList insertSort(ArrayList<String> a,String order){
        String temp;
        if (order.equals("asc")){
        for(int i = 1;i<a.size();i++){
            for(int j = i;j>0;j--){
                if(a.get(j).compareToIgnoreCase(a.get(j-1))<0){
                temp = a.get(j);
                a.set(j,a.get(j-1));
                a.set(j-1,temp);
                }
            }
        }
        return a;}
        else
            for(int i = 1;i<a.size();i++){
                for(int j = i;j>0;j--){
                    if(a.get(j).compareToIgnoreCase(a.get(j-1))>0){
                    temp = a.get(j);
                    a.set(j,a.get(j-1));
                    a.set(j-1,temp);
                    }
            }
        }
        return a;
    }
}
