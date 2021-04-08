import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

class Contact{
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
    ArrayList <ContactStar> contacts = new ArrayList<ContactStar>();

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
            ContactStar c = new ContactStar(name);
                c.addFone(fone.label, fone.number);
                contacts.add(c);
        } else {
            for (Iterator<ContactStar> it = contacts.iterator(); it.hasNext(); ) {
                ContactStar d = it.next();
                if(d.name.equals(name)){
                    d.fones.add(fone);
                    break;
                }else{ 
                    ContactStar c = new ContactStar(name);
                    c.addFone(fone.label, fone.number);
                    contacts.add(c);
                    break;
                }
            }
            
        }
        
    }

    boolean rmContact(String name){
        int index = -1;
        for(int i = 0; i < contacts.size(); i++){
            if(contacts.get(i).name.equals(name)){
                index = i; 
                System.out.println("found index" + i);
            }
        } 

        if(index != -1){
            if(contacts.remove(index) != null){
                return true;
            }
             
        } 
        return false;    
    }
    
    /*
    for(int i = 0; i < contacts.size(); i++){
        if(contacts.get(i).name.equals(name)){
            return i;
        } else {
            return -1;
        }
    }
    */

    ArrayList<ContactStar> search(String pattern){
        ArrayList <Integer> indexes = new ArrayList <Integer>();
        ArrayList <ContactStar> results = new ArrayList <ContactStar>();

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

    ArrayList<ContactStar> searchphone(String pattern){

        ArrayList <Integer> indexes = new ArrayList <Integer>();
        ArrayList <ContactStar> results = new ArrayList <ContactStar>();

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

    ArrayList<ContactStar> getContacts(){
        return contacts;
    }

    ContactStar getContact(String name){
        int index = findContact(name);
        if(index != -1){
            return contacts.get(findContact(name));
        } 
        return null;
    }

    void bookmark(String id){
        ContactStar contact = getContact(id);
        contact.addStarred();
    }

    void unbookmark(String id){
        ContactStar contact = getContact(id);
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

class ContactStar extends Contact{
    boolean star;
    
    ContactStar(String name){
        super(name);

    }

    ContactStar(String name, ArrayList<Fone> fones, boolean star){
        super(name);

    }

    boolean getstar(){
        return star;
    }

    void setStar(boolean value){
        star = value;
    }

    public String toString(){
        return super.toString();
    }
}

class AgendaStar extends Lista{

    AgendaStar(){
        super();
    }

    ArrayList<ContactStar> getStarred(){
        return contacts;
    }

    void star(String name, boolean value){
        int index = super.findContact(name);
        ContactStar c = contacts.get(index);
        if(value){
            c.addStarred();
        } else {
            c.rmStarred();
        }
    }

    public String toString(){
        String x = "";
        if(contacts.size() > 0){
            for (int counter = 0; counter < contacts.size(); counter++) { 		
                x +=  contacts.get(counter).starred ? " @ " : " - ";     
                x +=  contacts.get(counter).name + "-" + contacts.get(counter).fones ; 		
            }  
        }
         return x;
    }
}
    
public class Agenda{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Lista list = new Lista(); 
        while(true){
            String line = scanner.nextLine();
            String[] ui = line.split(" ");
            if(ui[0].equals("stop")){
                break;
            } else if(ui[0].equals("show")){
                System.out.println(list.toString());
            } else if(ui[0].equals("remove")){
                list.rmContact((ui[1]));
            } else if(ui[0].equals("add")){
                Fone f = new Fone(ui[1], ui[2]);
                list.addContact((ui[1]), f);
            } else if(ui[0].equals("search")){
                ArrayList <ContactStar> results = new ArrayList<ContactStar>();
                results = list.search(ui[1]);


                ArrayList <ContactStar> phoneresults = new ArrayList <ContactStar>();
                phoneresults = list.searchphone(ui[1]);
                
                System.out.println(results);
                System.out.println(phoneresults);
               
                
            } else if(ui[0].equals("starred")){
                System.out.println(list.getBookmarks());
            } else if(ui[0].equals("star")){
                list.bookmark(ui[1]);
            } else if(ui[0].equals("unstar")){
                list.unbookmark(ui[1]);
            }
                
        } 
        scanner.close();
    }
}