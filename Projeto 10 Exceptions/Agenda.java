import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

class Contact {
    String name;
    ArrayList <Fone> fones = new ArrayList<Fone>();
    boolean starred;

    Contact(String name){
        this.name = name;
        this.starred = false;
    }

    void addFone(String label, String number){
        this.fones.add(new Fone(label, number));
    }
    
    void rmFone(int index){
        if(index < 0 && index > this.fones.size()){
            System.out.println("Invalid");
        }else {
            fones.remove(index);
        }
    }

    void addStarred(){
        starred = true;
    }

    void rmStarred(){
        starred = false;
    }

    public String toString(){
        String s = starred ? " @ " : " - ";
        return s + name + fones.toString();
    } 

}

class Fone{
    String label;
    String number;

    Fone(String id, String number) {
        System.out.println(id);
        System.out.println(number);
        this.label = id;
        this. number = number;
    }

    boolean validate(String number){
        return true;
    }

    public String toString(){
        return this.number;
    }
}

class Lista{
    ArrayList <Contact> contacts = new ArrayList<Contact>();

    Lista(){

    }

    int findContact(String name){
        int index = -1;
        for(int i = 0; i < contacts.size(); i++){
            if(contacts.get(i).name.equals(name)){
                index = i; 
            }
        }
        return index;
    }

    void addContact(String name, Fone fone){

        if(contacts.size() == 0){
            Contact c = new Contact(name);
                c.addFone(fone.label, fone.number);
                contacts.add(c);
        } else {
            for (Iterator<Contact> it = contacts.iterator(); it.hasNext(); ) {
                Contact d = it.next();
                if(d.name.equals(name)){
                    d.fones.add(fone);
                    break;
                }else{ 
                    Contact c = new Contact(name);
                    c.addFone(fone.label, fone.number);
                    contacts.add(c);
                    break;
                }
            }
            
        }
        
    }

    boolean rmContact(String name) throws IndexOutOfBoundsException{
        try {
            int index = -1;
            for(int i = 0; i < contacts.size(); i++){
                if(contacts.get(i).name.equals(name)){
                    index = i; 
                    System.out.println("found index" + i);
                }
            } 
            if(contacts.remove(index) != null){
                return true;
            }
                 
            
            return false; 
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return false;
        } 
            
    }
    

    ArrayList<Contact> search(String pattern){
        ArrayList <Integer> indexes = new ArrayList <Integer>();
        ArrayList <Contact> results = new ArrayList <Contact>();

        for(int i = 0; i < contacts.size(); i++){
            if(contacts.get(i).name.contains(pattern)){
                indexes.add(i);
            } 
        }
        if(indexes.size() > 0){
            for(int i = 0; i < indexes.size(); i++){
                results.add(contacts.get(i));
        
            }
        }
        return results.size() > 0 ? results : null;
    }   

    ArrayList<Contact> searchphone(String pattern){

        ArrayList <Integer> indexes = new ArrayList <Integer>();
        ArrayList <Contact> results = new ArrayList <Contact>();

        for(int i = 0; i < contacts.size(); i++){
            for(int j = 0; j < contacts.get(i).fones.size(); j++){
                if(contacts.get(i).fones.get(j).number.contains(pattern)){
                    indexes.add(i);
                }
            } 
        }
        if(indexes.size() > 0){
            for(int i = 0; i < indexes.size(); i++){
                results.add(contacts.get(i));
        
            }
        }
        return results.size() > 0 ? results : null;
        
    }

    ArrayList<Contact> getContacts(){
        return contacts;
    }

    Contact getContact(String name){
        int index = findContact(name);
        if(index != -1){
            return contacts.get(findContact(name));
        } 
        return null;
    }

    void bookmark(String id){
        Contact contact = getContact(id);
        contact.addStarred();
    }

    void unbookmark(String id){
        Contact contact = getContact(id);
        contact.rmStarred();
    }

    ArrayList<Contact> getBookmarks(){
        ArrayList <Contact> bookmarks = new ArrayList<Contact>();
        for(int j = 0; j < contacts.size(); j++){
            if(contacts.get(j).starred){
                bookmarks.add(contacts.get(j));
            }
        } 
        return bookmarks;
    }

    public String toString(){
        String x = "";
        if(contacts.size() > 0){
            for (int counter = 0; counter < contacts.size(); counter++) { 		      
                x += contacts.get(counter).name + "-" + contacts.get(counter).fones ; 		
            }  
        }
         return x;
    }
}

    
public class Agenda{
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        Lista list = new Lista(); 
        try {
            while(true){
                String line = scanner.nextLine();
                String[] ui = line.split(" ");
                if(ui[0].equals("stop")){
                    break;
                } else if(ui[0].equals("show")){
                    System.out.println(list.toString());
                } else if(ui[0].equals("remove")){
                    try {
                        list.rmContact((ui[1]));
                    } catch (Exception e) {
                        Exception r = new Exception("User Not Found");
                        r.printStackTrace();
                    }
                   
                } else if(ui[0].equals("add")){
                    try {
                        Fone f = new Fone(ui[1], ui[2]);
                        list.addContact((ui[1]), f);
                    } catch (Exception e) {
                        Exception t = new Exception("Insufficient Parameters");
                        t.printStackTrace();
                    }
                    
                } else if(ui[0].equals("search")){
                    try {
                        ArrayList <Contact> results = new ArrayList<Contact>();
                        results = list.search(ui[1]);
                        ArrayList <Contact> phoneresults = new ArrayList <Contact>();
                        phoneresults = list.searchphone(ui[1]);
                    
                        System.out.println(results);
                        System.out.println(phoneresults);
                   
                    } catch (Exception e) {
                        Exception t = new Exception("User or Number Not Found");
                        t.printStackTrace();
                    }
                    
                    
                } else if(ui[0].equals("starred")){
                    System.out.println(list.getBookmarks());
                } else if(ui[0].equals("star")){
                    list.bookmark(ui[1]);
                } else if(ui[0].equals("unstar")){
                    list.unbookmark(ui[1]);
                } else {
                    Exception e = new Exception("Opcion No Existente");
                    e.printStackTrace();
                }
                    
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
         
        scanner.close();
    }
}