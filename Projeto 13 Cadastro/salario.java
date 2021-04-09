import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class Funcionario{
    int bonus = 0;
    int diarias;
    int maxDiarias = 1;
    String nome;

    Funcionario(String nome){
        this.nome = nome;
    }

    void addDiaria(){
        diarias += 100;
        maxDiarias += 1;
    }

    String getNome(){
        return nome;
    }

    Integer getSalario(){
        return bonus + diarias;
    }

    void setBonus(int bonus){
        this.bonus += bonus;
    }

}

class UFC{
    Map<String, Funcionario> funcionarios = new TreeMap<String, Funcionario>();

    UFC(){

    }

    void addFuncionarios(Funcionario funcionario){
        funcionarios.put(funcionario.nome, funcionario);
    }

    Funcionario getFuncionarios(String nome){
        return funcionarios.get(nome);
    }

    void rmFuncionarios(String nome){
        funcionarios.remove(nome);
    }

    void setBonus(int bonus){
        int total = funcionarios.size();

        for (Map.Entry<String,Funcionario> entry : funcionarios.entrySet()){
            Funcionario f = entry.getValue();
            f.setBonus(bonus/total);
        }
            

    }

    public String toString(){
        String x = "";
        for (Map.Entry<String,Funcionario> entry : funcionarios.entrySet()){
            x += entry.getValue().toString() + "\n";
        }
        return x;
    }

    
}

class Tercerizado extends Funcionario{
    int horas;
    boolean isSalubre;

    /* salario = 4 * horas (+ 500 se insalubre) */

    Tercerizado(String nome, int horas, boolean isSalubre){
        super(nome);
        this.horas = horas;
        this.isSalubre = isSalubre;
    }
    
    void addDiaria(){
        System.out.println("Este usuario nao aceita Diarias");
    }

    Integer getHoras(){
        return horas;
    }

    boolean getIsSalubre(){
        return isSalubre;
    }

    Integer getSalario(){
        return (4 * horas) + (isSalubre ? 500 : 0) + super.bonus;
    }

    public String toString(){
        return "ter:" + nome + ":" + horas + ":" + (isSalubre ? "sim" : "nao") + ":" + getSalario();
    }



}

class STA extends Funcionario{
    int nivel;

    STA(String nome, int nivel){
        super(nome);
        this.nivel = nivel;
    }

    void addDiaria(){
        if(super.maxDiarias <= 1){
            super.addDiaria();
        } else {
            System.out.println("Fail: Maximo de Diarias");
        }
    }

    Integer getNivel(){
        return nivel;
    }

    Integer getSalario(){
        return (3000 + (300 * nivel) + super.diarias + super.bonus);
    }

    public String toString(){
        return "sta:" + nome + ":" + nivel + ":" + getSalario();
    }
}

class Professor extends Funcionario{
    String classe;

    Map<String, Integer> rangos = new TreeMap<String, Integer>();

    Professor(String nome, String classe){
        super(nome);
        this.classe = classe;
        rangos.put("A", 3000);
        rangos.put("B", 5000);
        rangos.put("C", 7000);
        rangos.put("D", 9000);
        rangos.put("E", 11000);

    }

    void addDiaria(){
        if(super.maxDiarias < 3){
            super.addDiaria();
        } else {
            System.out.println("Fail: Maximo de Diarias");
        }
    }

    String getClasse(){
        return classe;
    }

    Integer getSalario(){
        int val = rangos.get(classe);
        return val + super.diarias + super.bonus;
    }

    public String toString(){
        return "prof:" + nome + ":" + classe + ":" + getSalario();
    }

    

}

public class salario{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        UFC ufc = new UFC();
        while(true){
            try {
                String line = scanner.nextLine();
                String[] ui = line.split(" ");
                if(ui[0].equals("stop")){
                    break;
                } else if(ui[0].equals("show")){
                    System.out.println(ufc.getFuncionarios(ui[1]));
                } else if(ui[0].equals("showall")){
                    System.out.println(ufc);
                } else if (ui[0].equals("addprof")) {
                    ufc.addFuncionarios(new Professor(ui[1], ui[2]));
                } else if (ui[0].equals("addsta")) {
                    ufc.addFuncionarios(new STA(ui[1], Integer.parseInt(ui[2])));
                } else if (ui[0].equals("addter")) {
                    ufc.addFuncionarios(new Tercerizado(ui[1], Integer.parseInt(ui[2]), (ui[3]) == "sim" ? false : true));
                } else if (ui[0].equals("remove")) {
                    ufc.rmFuncionarios(ui[1]);
                } else if (ui[0].equals("adddiaria")) {
                    ufc.getFuncionarios(ui[1]).addDiaria();
                } else if (ui[0].equals("setbonus")) {
                    ufc.setBonus(Integer.parseInt(ui[1]));
                } else {
                    System.out.print("fail: comando invalido");
                }
            } catch (Exception e) {
                System.out.println("Opcion Invalida");
            }
            
        } 
        scanner.close();
    }

}
