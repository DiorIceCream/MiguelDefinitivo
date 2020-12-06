import java.util.Scanner;
public class carro {
    int tanque;
    int pass;
    int km;
    int maxTanque = 100;
    int maxPass = 2;


    carro(int tanque, int pass, int km){
        this.tanque = tanque;
        this.pass = pass;
        this.km = km;
       // this.maxTanque = 100;
        //this.maxPass = 2;

    }

    public String toString(){
        return "Tanque: " + tanque + " Pessoas: " + pass + " Quilometragem: " + km; 
    }

    void embarcar(){
        if(pass < maxPass){
            pass += 1;
            System.out.println("Embarcando uma pessoa");
        } else {
            System.out.println("opa limite de pessoas!");
        }
    }

    void sair(){
        if(pass > 0){
            pass -= 1;
            System.out.println("Uma Pessoa Saindo");
        } else {
            System.out.println("Niguem dentro do carro");
        }
    }

    void abastecer(int qtd){
        this.tanque += qtd;
        if(this.tanque > this.maxTanque){
            this.tanque = this.maxTanque;
        }
    }

    void dirigir(int dis){
        if(this.tanque > 0 && this.pass > 0){
            if(dis > this.tanque){
            this.km += this.tanque;
            this.tanque = 0;
            System.out.println("quase sem gasolina");
            System.out.println("vrp vrp vrp vrp!");
            } else {
            this.tanque -= dis;
            this.km += dis;
            System.out.println("vrrrrrrrrrrrrrrrrr");
            }
            
        }
        if (this.tanque == 0){
            System.out.println("falta gasolina no tanque");
        } else if(this.pass == 0){
            System.out.println("falta passageiro");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        carro sport = new carro(0, 0, 0);
        while(true){
            String line = scanner.nextLine();
            String[] ui = line.split(" ");
            if(ui[0].equals("stop")){
                break;
            }else if(ui[0].equals("start")){
                System.out.println("ignition start vrrrrrrrr!!!");
            } else if(ui[0].equals("show")){
                System.out.println(sport);
            } else if(ui[0].equals("entrar")){
                sport.embarcar();   
            } else if(ui[0].equals("sair")){
                sport.sair();
            } else if(ui[0].equals("abastecer")){
                sport.abastecer(Integer.parseInt(ui[1]));
                System.out.println("glug glug glug");
            } else if(ui[0].equals("dirigir")){
                sport.dirigir(Integer.parseInt(ui[1]));
            }

        }
        scanner.close();
    }

}


//INSTRUÇÕES
//start = inicializar o motor
//show = mostrar os parametros do carro
//abastecer = abastecer tanque com gasolina
//entrar = embarcar passageiro
//sair = desembarcar passageiro
//dirigir = pois eu acho que vc sabe para que e dirigir kkkk

