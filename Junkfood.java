import java.util.Scanner;
import java.util.ArrayList;

class Espiral {
    String nome; 
    int qtd;
    float preco;

    Espiral(){

    }

    Espiral(String nome, int qtd, float preco){
        this.nome = nome;
        this.qtd = qtd;
        this.preco = preco;
    }

    public String toString(){
        return this.nome + ":" + this.qtd + ":" + this.preco;
    }
}

class Maquina {
    private ArrayList<Espiral> espirais;
    
    private float saldoCliente;
    public float lucro;
    private int maxProdutos;

    public Maquina (int tamanho, int maxProdutos){

        this.saldoCliente = 0;
        this.lucro = 0;
        this.maxProdutos = maxProdutos;

        espirais = new ArrayList<Espiral>();
        for(int i = 0; i < tamanho; i++)
            espirais.add(new Espiral());
    }

    boolean inserirDinheiro(int valor){
        if(valor <= 0){
            System.out.println("Fail: Invalido");
            return false;
        }
        this.saldoCliente += valor;
        System.out.println("K Ching!!");
        return true;
    }

    float pedirTroco(){
        if(saldoCliente <= 0){
            System.out.println("Fail: Sem Saldo");
            return 0;
        }
        float aux = saldoCliente;
        System.out.println("Troco: " + saldoCliente);
        saldoCliente = 0; 
        return aux;
    }

    boolean vender(int index){
        if(index < 0 || index > espirais.size()){
            System.out.println("Fail: Invalido");
            return false;
        } else if(saldoCliente <= 0){
            System.out.println("Fail: Saldo Insuficiente");
            return false;
        } else {
            Espiral espiral = espirais.get(index);
            if(espiral.qtd == 0){
                System.out.println("Sem Produto");
                return false;
            }
            if(espiral.preco > saldoCliente){
                System.out.println("Sem Dinheiro");
                return false;
            }
            if(espiral.qtd > 0 && saldoCliente >= espiral.preco ){
                saldoCliente = saldoCliente - espiral.preco;
                espiral.qtd--;
                lucro += espiral.preco;
                System.out.println("Comprou!");
                return true;
            }
            return false;
        }
    }

    boolean alterarEspiral(int index, String nome, int qtd, float preco){
        if(index < 0 || index > espirais.size()){
            System.out.println("Fail: Invalido");
            return false;
        }else if(qtd > maxProdutos){
            System.out.println("Fail: Max Capacidade");
            return false;
        } else {
            Espiral espiral = this.espirais.get(index);
            espiral.nome = nome;
            espiral.qtd = qtd;
            espiral.preco = preco;
            System.out.println("Spring Alterado");
            return true;
        }
    }

    boolean limparEspiral(int index){
        if(index < 0 || index > espirais.size()) {
            return false;
        } else if (espirais.get(index) != null){
            Espiral espiral = new Espiral();
            this.espirais.set(index, espiral);
            System.out.println("Maquina Limpada!");
            return true;
        } else {
            return false;
        }
    }

    public String toString(){
        int i = 0;
        String saida = new String();
        System.out.println("Saldo: " + this.saldoCliente);
        for(Espiral espiral : espirais){
            if(espiral == null)
                saida += "- ";
            else 
                saida += String.valueOf(i) + " [" + getEspiralNome(espiral.nome) + " : " + espiral.qtd + " U : " + espiral.preco + " RS " + "]" + "\n";
            i++;
        }
        return saida;
    }

    public String getEspiralNome(String nome){
        if(nome == null)
            return "empty";
        return nome;
    }

}

public class Junkfood{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Maquina machine = new Maquina(0, 0);
        while(true){
            String line = scanner.nextLine();
            String[] ui = line.split(" ");
            if(ui[0].equals("stop")){
                break;
            } else if(ui[0].equals("show")){
                System.out.println(machine);
            } else if(ui[0].equals("init")){
                int tam = Integer.parseInt(ui[1]);
                int max = Integer.parseInt(ui[2]);
                machine = new Maquina(tam, max);
                System.out.println("Vendor Ready");
            } else if(ui[0].equals("money")){
                machine.inserirDinheiro(Integer.parseInt(ui[1]));
            } else if(ui[0].equals("alterar")){
                machine.alterarEspiral(Integer.parseInt(ui[1]), (ui[2]), Integer.parseInt(ui[3]), Float.parseFloat(ui[4]));
            } else if(ui[0].equals("troco")){
                machine.pedirTroco();
            } else if(ui[0].equals("limpar")){
                machine.limparEspiral(Integer.parseInt(ui[1]));
            } else if(ui[0].equals("comprar")){
                machine.vender(Integer.parseInt(ui[1]));
            } else if(ui[0].equals("lucro")){
                System.out.println(String.valueOf(machine.lucro + " RS"));
            }
        }
        scanner.close();
    }
}


