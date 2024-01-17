import java.util.Random;
import java.util.Scanner;
public class chaosbolt{
    public static void main(String[] args) {
        onCast();
    };
    private static void onCast(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the spell level: ");
        int spellLvl = sc.nextInt();
        toHit(spellLvl);
        sc.close();
    };
    private static void toHit(int spellLvl){
        Scanner sc = new Scanner(System.in);
        int roll = diceRoller(20);
        boolean crit = false;
        if(roll == 20){
            crit = true;
            System.out.println("You Crit!! and ");
            newBolt(spellLvl, crit);
            sc.close();
            return;
        };
        if(roll == 1){
            System.out.println("Oh no natural 1. thats unfortunate better luck next time.");
            sc.close();
            return;
        };
        System.out.print("Please enter the targets armor class: ");
        int ac = sc.nextInt();//AC is armor class
        System.out.print("Enter your spell attack modifier: ");
        int SAM = sc.nextInt(); //SAM -- Spell Attack Modifier
        roll = roll + SAM;
        System.out.print("With that you rolled a "+ roll + " and ");
        if(roll >= ac){
            newBolt(spellLvl,crit);
        }else{
            System.out.println("missed thats to bad");
        };
        sc.close();
    };
    private static void newBolt(int spellLvl,boolean crit){
        int roll1 = diceRoller(8);
        int roll2 = diceRoller(8);
        int total = roll1 + roll2;
        for(int i = 0; i < spellLvl; i++ ){
            int dice = diceRoller(6);
            total = total + dice; 
        };
        if(crit == true){
            total =  total * 2;
        }
        String type1 = damageType(roll1);
        String type2 = damageType(roll2);
        if (roll1 == roll2){
            System.out.println("did " + total + type1 + "damage! and it bounced. Do you have another target? (y/n)");
            Scanner sc = new Scanner(System.in);
            String userIn = sc.next();
            if(userIn == "y"){
                toHit(spellLvl);
            }else{
                System.out.println("Well thats unfortunate :(");
            };
            sc.close();
        }else{
            System.out.println("did " + total + type1 + "or" + type2 + "damage!");
        };
    
    };
    private static String damageType(int diceRoll){
        String[] types = {" acid "," cold "," fire ", " force ", " lightning ", " poison ", " psychic ", " thunder " };
                 
        return types[diceRoll];
    };
    private static int diceRoller(int dSize){
        dSize = dSize - 1;
        Random r =  new Random();
        int dice = r.nextInt(dSize) + 1;
        return dice;
    };
};