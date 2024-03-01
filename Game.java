/**
 * name: Shaheer
 * Program name: Culminating: Rpg Game Demo  
 * Date: June 17, 2022
 * Description: A simple turn-based rpg battle game, just does a series of 
 *      fights for each level
 */

 //import tools
 import java.util.*;
 import java.io.*;


public class Game {

    //initialize global scanner and variables
    public static Scanner scanN = new Scanner(System.in);
    public static Scanner scanS = new Scanner(System.in);
    public static Character player;
    public static int stage = 1;


    public static void main(String[] args) throws IOException, 
            InterruptedException {
        //intialize enemyModel object and userInput variable
        Character enemyModel = new Character();
        int userInput;

        //error trap do...while
        do {
            mainMenu();//prints main menu
            userInput = scanN.nextInt();

            if (userInput > 4 || userInput < 1) {
                System.out.println("enter a value corresponding to the value "
                        + "from the numbers\n");
            }
            else if (userInput == 4) {
                rules();//prints rules
            }

        } while (userInput > 3 || userInput < 1);

        //switch for main menu input
        switch (userInput) {
            case 1: //creates new player object 
                for (int i = 0; i < 3; i++) {
                    Thread.sleep(1000);
                    System.out.print(".");
                }
                player = new PlayerChar();
                break;
            case 2: //loads game
                load();
                break;
            case 3: //exits game
                System.out.println("Thanks for playing");
                System.exit(0);
                break;
            default:
                System.out.println("Error");
                break;
        }

        //do...while for repeating battles until stage 10
        do {
            System.out.println("\n\nStage #" + stage);
            //battle sequence method
            battleSeq(enemyModel);

            //if statements for continueing or quiting
            //continue/save menu
            if (player.status() && stage < 10) {
                do {
                    System.out.println("What would you like to do?");
                    System.out.println("1. Continue");
                    System.out.println("2. Save & Continue");
                    System.out.println("3. Save & Quit");
                    userInput = scanN.nextInt();
                    
                    if (userInput < 1 && userInput < 3) {
                        System.out.println("invalid input, enter correct "
                                + "corresponding value from menu");
                    }
                    else if (userInput == 2 || userInput == 3) {
                        save();//saves game and continues
                    }
                } while (userInput < 1 && userInput > 3);//error trap
            }

            //win message
            else if (stage >= 10) {
                System.out.println("Congrats, you've beaten the game");
            }
            //lose exit message
            else {
                System.out.println("Exiting game");
                userInput = -1;
            }
        } while ((player.status() && userInput != 3) && 
                (player.status() == true && stage < 10));


        System.out.println("Thanks for playing");
    }

    /**
     * Method Name: mainMenu
     * Description: prints out text for main menu
     */
    public static void mainMenu() {
        System.out.println("\tStarForce rpg mechanics Demo");
        System.out.println("\t 1. New Game");
        System.out.println("\t 2. Load Game");
        System.out.println("\t 3. Exit");
        System.out.println("\t 4. Rules");
    }
    
       /**
     * Method Name: rules 
     * Description: prints out text for rules 
     */
    public static void rules() {
        System.out.println("This game is an turn-based battle rpg demo");
        System.out.println("The game will put you into a series of battles, in "
                + "this game is designed to be very easy and just be a test of mechanics");
        System.out.println("As you progress through the stages, you will level "
                + "up and become stronger, but so will your enemies");
        System.out.println("The battle sequence begins on your turn and will "
                + "print out a list of commands");
        System.out.println("Menus will take inputs through number inputs, enter "
                + "the number corresponding to the input you want to use");
        System.out.println("Invalid inputs will not be taken and menu will "
                + "appear again");
        System.out.println("The player has 3 moves in battle, attack, ability "
                + "and item");
        System.out.println("Attack is a basic attack function");
        System.out.println("Ability does a stronger attack and heals a bit "
                + "at the cost of the mp stat");
        System.out.println("Item has another menu with 3 different items to "
                + "use, potion heals, ether restores mp");
        System.out.println("And Atk buff increses attack by 3 times for the "
                + "battle");
        System.out.println("Items are dropped after a fight except for atk buffs"
                + " which are found in chests");
        System.out.println("To  loot the chests on each stage you need to play"
                + " a guessing minigame and guess a correct number to be "
                + "rewarded");
        System.out.println("The game goes up to stage 10\n\n");
        
    }

    /**
     * Method Name: battleSeq
     * Description: executes a repeating battle sequence until either 
     *          player hp or enemy hp is depleated
     * @param enemy - Character object used to create enemy character object
     */
    public static void battleSeq(Character enemy) throws InterruptedException{
        //initializes variables and object
        int userInput; 
        enemy = new EnemyChar();
        //scales enemy stats based on stage
        enemy.stageScale(stage);


        System.out.println("\nBattle begin");
        //main battle do...while
        do {
            //prints player and enemy stat menu
            hpMenu(enemy);
            System.out.println("\nYour turn");
            //error trap do...while
            do {
                System.out.println("\nWhat would you like to do?");
                System.out.println("1. Attack");
                System.out.println("2. Ability \tcost " + player.getAblCost() + "mp");
                System.out.println("3. Items");
                
                userInput = scanN.nextInt();

                if (userInput > 3 || userInput < 1) {
                    System.out.println("invalid input, enter corresponding "
                            + "value from menu");
                }
            } while (userInput > 3 || userInput < 1);

            System.out.println("\n\n");

            //battle command switch
            switch (userInput) {
                case 1:
                    //normal attack
                    player.attackCommand(enemy);
                    break;
                case 2:
                    //ability use 
                    player.abilityUse(enemy);
                    break;
                case 3:
                    //item use
                    boolean itemStatus = player.itemMenu();

                    if(itemStatus == true) { 
                        player.itemUse();
                    }
                    else {
                        //exiting item menu
                        continue;
                    }
                    break;

                default:
                    System.out.println("Error");
                    break;
            }
            Thread.sleep(1500);
            
            //does enemy turn if they have hp
            if (enemy.status() == true) {
                System.out.println("\nEnemy turns");
                System.out.println("Enemy attacks");
                enemy.attackCommand(player);
            }
            else {
                System.out.println("\nEnemy is defeated");
            }

            Thread.sleep(1500);

        } while (gameState(enemy) == true);

        //battle win message
        if (player.status()) {
            System.out.println("You won the battle and advanced to the next "
                    + "stage");
            stage++;//stage increment
            //error trap
            do {
                player.levelUpMenu();//stat level up menu
                userInput = scanN.nextInt();
                if ((userInput > 4 || userInput < 1)) {
                    System.out.println("enter a value corresponding to the "
                            + "value from the numbers\n");
                }
            } while (userInput > 4 || userInput < 1);
            
            //resets player fields for next battle
            player.endBattleReset();
            //levels up specific stat more
            player.levelUp(userInput);
            
            //error trap
            do {
                System.out.println("\nWould you like to search this stage's "
                        + "chest?");
                System.out.println("1.yes\n2.no");
                userInput = scanN.nextInt();
                if (userInput > 2 || userInput < 1) {
                    System.out.println("Invalid input, enter value "
                            + "corresponding to command");
                }
            } while(userInput > 2 || userInput < 1);
            //takes user input for minigame
            if (userInput == 1) {
                //guessing minigame to earn item
                player.itemEvent();
            }

        }
        //death message
        else {
            System.out.println("\nYou Died");
            System.out.println("Game Over...");
        }
    }

    
    /**
     * Method Name: hpMenu
     * Description: prints out a menu with player and enemy stats 
     * @param enemy - Character object variable used to access enemy object
     */
    public static void hpMenu(Character enemy) {
        System.out.println("\tPlayer\t\t\tEnemy");
        System.out.println("level: " + player.getLevel() + "\t\tlevel: " + stage);
        System.out.println("Hp: " + player.getHealthPoints() + "/" + 
                player.getMaxHealthPoints() + "\t\tHp: " + 
                enemy.getHealthPoints() + "/" + enemy.getMaxHealthPoints());
        System.out.println("Mp: " + player.getManaPoints() + "/" + 
                player.getMaxManaPoints() + "\t\tAtk: " + enemy.getAttack());
        System.out.println("Atk: " + player.getAttack() + "\t\t\tDef: " + 
                enemy.getDefence());
        System.out.println("Def: " + player.getDefence());
        
    }


    /**
     * Method Name: gameState
     * Description: checks if hp of either player or enemy is depleted
     * @param enemy - Character enemy object
     * @return state - boolean value dependent on whether either character's 
     *      hp has depleted 
     */
    public static boolean gameState(Character enemy) {
        boolean state = false;

        if (player.status() == true && enemy.status() == true) {
            state = true;
        }

        return state;
    }


    /**
     * Method Name: save
     * Description: saves player object fields to a text file
     */
    public static void save() throws IOException{
        //intializes print writer
        PrintWriter outFile = new PrintWriter("SaveFile.txt");
        //outputs player stats to textfile
        outFile.print(player.getAttack() + ", ");
        outFile.print(player.getDefence() + ", ");
        outFile.print(player.getMaxHealthPoints() + ", ");
        outFile.print(player.getMaxManaPoints() + ", ");
        outFile.print(player.getLevel() + ", ");
        outFile.print(player.getAblCost() + ", ");
        outFile.print(player.getItemPotion() + ", ");
        outFile.print(player.getItemEther() + ", ");
        outFile.print(player.getItemAtkBuff());

        outFile.close();
    }


    /**
     * Method Name: load
     * Description: checks if there is any info in text file and player object 
     *      based on that
     */
    public static void load() throws IOException, InterruptedException{
        //establishes text file
        File myFile = new File("SaveFile.txt");
        //file reader
        Scanner readFile = new Scanner(myFile);
        //checks if file has information
        if (readFile.hasNext() == false) {
            //creates object with no parameters
            System.out.println("no save file");
            System.out.println("starting new game");
            for (int i = 0; i < 3; i++) {
                Thread.sleep(1000);
                System.out.print(".");
            }
            player = new PlayerChar();
        }
        else { 
            //creates objects with same paramater as save file
            String line = readFile.nextLine();
            String[] tokens = line.split(", ");

            player = new PlayerChar(Integer.parseInt(tokens[0]), 
                    Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), 
                    Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]), 
                    Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]), 
                    Integer.parseInt(tokens[7]), Integer.parseInt(tokens[8]));
                    
                    //sets stage to players level
                    stage = player.getLevel();
                    
                    for (int i = 0; i < 3; i++) {
                        Thread.sleep(1000);
                        System.out.print(".");
                    }           
        }
        //closes file reader Scanner
        readFile.close();
    }


}