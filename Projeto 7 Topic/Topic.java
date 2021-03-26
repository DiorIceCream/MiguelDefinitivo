import java.util.ArrayList;
import java.util.Scanner;

class Pessoa{
    String name;
    int idade;
    boolean pref;
    String sim;
    
    Pessoa(){

    }

    Pessoa(boolean pref){
        this.pref = pref;
        if(pref)
            sim = "@";
        else 
            sim = "=";
    }

    Pessoa(String name, int idade){
        this.name = name;
        this.idade = idade;

    }

    public String toString(){
        return this.name + this.idade;
    }
}

class Sala{
    private ArrayList<Pessoa> pessoas;

    private int qtdPref;

    public Sala(int lotacaoMax, int qtdPref){

        this.qtdPref = qtdPref;

        pessoas = new ArrayList<Pessoa>();
        for(int i = 0; i < lotacaoMax; i++){
            if(i < qtdPref){
                pessoas.add(new Pessoa(true));
            } else 
                pessoas.add(new Pessoa(false));
        }

    }

    boolean subir(Pessoa p){

        if(seatsAv()){
            if(p.idade > 60){
                if(checkPrefAv()){
                    int i = getPrefSeat();
                    pessoas.get(i).name = p.name;
                    pessoas.get(i).idade = p.idade;
                } else {
                    int i = getNormSeat();
                    pessoas.get(i).name = p.name;
                    pessoas.get(i).idade = p.idade;
                } 
            } else {
                if(checkNormAv()){
                    int i = getNormSeat();
                    pessoas.get(i).name = p.name;
                    pessoas.get(i).idade = p.idade;
                } else {
                    int i = getPrefSeat();
                    pessoas.get(i).name = p.name;
                    pessoas.get(i).idade = p.idade;
                }
                
            }
            return true;
        } else {
            return false;
        }
   
    }

    boolean seatsAv(){
        int cont = 0;
        for(Pessoa pessoa : pessoas){
            if(pessoa.name == null){
                cont++;
            }
        } 
        return cont > 0 ? true : false;
    }

    boolean checkPrefAv(){
        int cont = 0;
        for(Pessoa pessoa : pessoas){
            if(pessoa.pref && pessoa.name == null){
                cont++;
            }
        } 
        return cont > 0 ? true : false;
    }

    boolean checkNormAv(){
        int cont = 0;
        for(Pessoa pessoa : pessoas){
            if(!pessoa.pref && pessoa.name == null){
                cont++;
            }
        } 
        return cont > 0 ? true : false;
    }

    int getPrefSeat(){
        for(Pessoa pessoa : pessoas){
            if(pessoa.pref && pessoa.name == null){
                return pessoas.indexOf(pessoa);
            }
        } 
        return -1;
    }

    int getNormSeat(){
        for(Pessoa pessoa : pessoas){
            if(!pessoa.pref && pessoa.name == null){
                return pessoas.indexOf(pessoa);
            }
        } 
        return -1;
    }

    //inline if 
    // if(cont > 0){
    //     return true;
    // } else {
    //     return false;
    // }

    boolean descer(String name){
        int index = -1;
        for(Pessoa pessoa : pessoas){
            if(pessoa.name != null && pessoa.name.equals(name)){
                index = pessoas.indexOf(pessoa);
            }
        }
        if(index >= 0){
            pessoas.get(index).name = null;
            return true;
        } else {
            return false;
        }
        
        
    }

    public String toString(){
        String saida = new String("[ ");
        if(pessoas.size() > 0){
            for(Pessoa pessoa : pessoas){
                saida += pessoa.pref ? "@ " : "- ";
                saida += pessoa.name != null ? pessoa.name + " " + pessoa.idade : " ";

            }
        }
        saida += " ] ";
        return saida;
    }
}

public class Topic{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Sala sala = new Sala(0, 0);
        while(true){
            String line = scanner.nextLine();
            String[] ui = line.split(" ");
            if(ui[0].equals("stop")){
                break;
            } else if(ui[0].equals("show")){
                System.out.println(sala);
            } else if(ui[0].equals("init")){
                int lot = Integer.parseInt(ui[1]);
                int pref = Integer.parseInt(ui[2]);
                sala = new Sala(lot, pref);
                System.out.println("Sala Pronta");
            } else if(ui[0].equals("add")){
                boolean s = sala.subir(new Pessoa(ui[1], Integer.parseInt(ui[2])));
                if(s){
                    System.out.println("Person Added Sucessfully");
                } else {
                    System.out.println("Not Enough Space");
                } 
            } else if(ui[0].equals("remove")){
                boolean s = sala.descer(ui[1]);
                if(s){
                    System.out.println("Person Removed Sucessfully");
                } else {
                    System.out.println("Person Not Found");
                } 
            } 
        }
        scanner.close();
    }
}