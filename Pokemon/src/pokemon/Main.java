package pokemon;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Игорь on 23.01.2017.
 */
public  class  Main {
    public static void fight(Pokemon pok1, Pokemon pok2) {
        pok1.setStatus(Pokemon.status.FITING);
        pok2.setStatus(Pokemon.status.FITING);
        Scanner in = new Scanner(System.in);
        System.out.println("----------------"+ pok1.getName()+" VS "+ pok2.getName()+"----------------");
        while(pok1.isAlive() & pok2.isAlive()) {
            /*
            * 1 - damage
            * 2 - damageUlt
            * 3 -
            * 0- quit
            */
            int checkTrainer1= 100;
            int checkTrainer2= 100;
            System.out.println("Energy of "+pok1.getName()+" = "+pok1.getEnergy());
            System.out.println("Energy of "+pok2.getName()+" = "+pok2.getEnergy());

            while (checkTrainer1!=0 && checkTrainer2!=0 && pok1.isAlive() && pok2.isAlive() ){
                System.out.println("for Trainer #1:\n" +
                        "            * 1 - damage\n" +
                        "            * 2 - damageUlt\n" +
                        "            * 0- quit ");
                checkTrainer1 = in.nextInt();

                switch(checkTrainer1) {
                    case 1: { pok2.protection(pok1.Damage());
                        break;
                    }
                    case 2: pok2.protection(pok1.DamageUlt());
                        break;
                    case 0: System.out.println("Fight is over");
                        break;
                    default: System.out.println("unknownk");
                        break;
                }

                if (pok2.isAlive()!=true) break;
                System.out.println("for Trainer #2:\n" +
                        "            * 1 - damage\n" +
                        "            * 2 - damageUlt\n" +
                        "            * 0- quit ");
                checkTrainer2 =in.nextInt();


                switch(checkTrainer2) {
                    case 1: pok1.protection(pok2.Damage());
                        break;
                    case 2: pok1.protection(pok2.DamageUlt());
                        break;
                    case 0: System.out.println("Fight is over");
                        break;
                    default: System.out.println("unknown");
                        break;
                }
            }

        }
        if (pok1.getEnergy()>pok2.getEnergy()) System.out.println(pok1.getName()+ "WINS!!!");
        else System.out.println(pok2.getName()+ " WINS!!!");
        System.out.println("status pic="+ pok1.getEnergy()+"|status bul="+ pok2.getEnergy());
        pok1.recovering();
        pok2.recovering();
    }

    public static void main(String[] args) {

        Pokemon pikachu = new Pokemon("PIKACHU",20,1,5);
        Pokemon bulbasaur = new Pokemon("BULBASAUR",21,1,7);
        pikachu.train();
        bulbasaur.train();
        fight(pikachu,bulbasaur);

    }
}
