import java.util.Scanner;

class pessoa{
    int idade;
    String nome;

    pessoa(String nome, int idade){
        this.idade = idade;
        this.nome = nome;
    }
    
    public String toString(){
        return "Idade: " + idade + " Nome: " + nome;
    }

}

public class motoca {
    int potencia;
    int min;
    pessoa pass;
    int maxPass = 1;


    motoca(int potencia, int min){
        this.potencia = potencia;
        this.min = min;
        this.pass = null;
    }

    public String toString(){
        return "Potencia: " + potencia + " Minutos: " + min + " Passageiros: " + pass;
    }

    void inicializar(){
        this.potencia +=1;
        System.out.println("motoca inicializada com sucesso");
    }

    void subir(pessoa p){
        if(pass == null){
            pass = p;
            System.out.println(p.nome + " subiuu! ");
        } else {
            System.out.println("Moto Cheia!");
        }
    }

    pessoa descer(){
        if(pass == null){
            System.out.println("Passageiro e requerido");
            return null;
        } else {
            pessoa aux = pass;
            pass = null;
            System.out.println("Passageiro desceu");
            return aux;
        }
    }

    void comprar(int dinheiro){
        this.min += dinheiro;
        System.out.println("Minutos Comprado");
    }

    void dirigir(int dis){
        if(this.min > 0 && this.pass != null){
            if(dis > this.min){
                System.out.println("Dirigiu por: " + min);
                this.min = 0;
            } else if (pass.idade > 10){
                System.out.println("So Criancas Igual ou Menor que 10");
            } else if(dis <= this.min){
                this.min -= dis;
                System.out.println("Dirigiu por: " + dis);
            }
        }
        if (this.min == 0){
            System.out.println("Tem que comprar minutos");
        } else if(this.pass == null){
            System.out.println("Falta passageiro");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        motoca sport = new motoca(0, 0);
        while(true){
            String line = scanner.nextLine();
            String[] ui = line.split(" ");
            if(ui[0].equals("stop")){
                break;
            } else if(ui[0].equals("start")){
                sport.inicializar();
            } else if(ui[0].equals("show")){
                System.out.println(sport);
            } else if(ui[0].equals("subir")){
                String nome = ui[1];
                int idade = Integer.parseInt(ui[2]);
                pessoa temp = new pessoa(nome, idade);
                sport.subir(temp);
            } else if(ui[0].equals("descer")){
                sport.descer();
            } else if(ui[0].equals("comprar")){
                sport.comprar(Integer.parseInt(ui[1]));
            } else if(ui[0].equals("dirigir")){
                sport.dirigir(Integer.parseInt(ui[1]));
            }
        }
        scanner.close();
    }
}



/*

Você deverá implementar a classe Pessoa e a class Moto.

INICIAR
A moto inicia com 1 de potência, sem minutos e sem ninguém.

SUBIR
Só pode estar uma pessoa na moto por vez. Para subir, informe nome e idade de quem está subindo.

DESCER
Só pode descer se tiver alguém na moto.

COMPRAR TEMPO PESSOA
O tempo em minutos é comprado e enquanto houver tempo, qualquer pessoa pode dirigir.

DIRIGIR TEMPO
Se houver uma pessoa com 10 anos ou menos e houver minutos, então ela pode passear de moto.
Se o tempo acabar no meio do passeio, informe o quanto a pessoa andou.

*/