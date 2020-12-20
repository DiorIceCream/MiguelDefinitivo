import java.util.Scanner;

public class Tamagotchi {
    private int energy;
    private int hunger;
    private int clean;
    private int energyMax;
    private int hungerMax;
    private int cleanMax;
    private int diamonds = 0;
    private int age = 0;
    private boolean alive;

    Tamagotchi(int energyMax, int hungerMax, int cleanMax){
        this.energyMax = energyMax;
        this.energy = energyMax;
        
        this.hungerMax = hungerMax;
        this.hunger = hungerMax;

        this.cleanMax = cleanMax;
        this.clean = cleanMax;

        this.alive = true;
    }

    public String toString(){
        return "Energy: " + energy + " Hunger: " + hunger + " Clean: " + clean + " Diamonds: " + diamonds + " Age: " + age;
    }

    void setEnergy(int num){
        
        this.energy = num;

        if(this.energy <= 0){
            this.energy = 0;
            this.alive = false;
            System.out.println("RIP..... Sem Energia!");
            return;
        }
        if(this.energy > this.energyMax){
            this.energy = this.energyMax;
        }
    }

    int getEnergy(){
        return this.energy;
    }

    void setHunger(int num){
        this.hunger = num;

        if(this.hunger <= 0){
            this.hunger = 0;
            this.alive = false;
            System.out.println("RIP..... Muita Fome!");
            return;
        }
        if(this.hunger > this.hungerMax){
            this.hunger = this.hungerMax;
        }
    }

    int getHunger(){
        return this.hunger;
    }

    void setClean(int num){
        this.clean = num;

        if(this.clean <= 0){
            this.clean = 0;
            this.alive = false;
            System.out.println("RIP.... Falta um banho!");
        }
    }

    int getClean(){
        return this.clean;
    }

    boolean isAlive(){
        if(!alive){
            System.out.println("Morreu, deixa ele empaz!");
            return false;
        }
        return true;
    }

    void play(){
        if(!this.isAlive())
            return;
        setEnergy(energy - 2);
        setHunger(hunger - 1);
        setClean(clean - 3);
        this.diamonds += 1;
        this.age += 1;
    }

    void eat(){
        if(!this.isAlive())
            return;
        setEnergy(energy - 2);
        setHunger(hunger + 4);
        setClean(clean - 2);
        this.age += 1;
    }

    void sleep(){
        if(!this.isAlive())
            return;
        if(this.energy == this.energy - 5){
            setEnergy(this.energyMax);
        } else {
            System.out.println("To Com Energias Ainda!");
        }
    }

    void bath(){
        if(!this.isAlive())
            return;
        setEnergy(energy - 3);
        setHunger(hunger - 1);
        setClean(this.cleanMax);
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Tamagotchi tama = new Tamagotchi(0, 0, 0);
        while(true){
            String line = scanner.nextLine();
            String[] ui = line.split(" ");
            if(ui[0].equals("stop")){
                break;
            } else if (ui[0].equals("start")){
                tama = new Tamagotchi( Integer.parseInt(ui[1]), Integer.parseInt(ui[2]), Integer.parseInt(ui[3]));
                System.out.println("Novo Tamagotchi Criado");
            } else if (ui[0].equals("show")){
                System.out.println(tama);
            } else if (ui[0].equals("play")){
                tama.play();
            } else if (ui[0].equals("eat")){
                tama.eat();
            } else if (ui[0].equals("bath")){
                tama.bath();
            } else if (ui[0].equals("sleep")){
                tama.sleep();
            }
        }
        scanner.close();
    }
}