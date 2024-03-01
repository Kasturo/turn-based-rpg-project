import java.util.*;
public class PlayerChar extends Character {

    //object fields
    private int manaPoints;
    private int maxManaPoints;
    private int level;
    private int itemChoice;
    private int defAtk;
    private int ablCost;

    private ArrayList<String> itemList = new ArrayList<>(
        Arrays.asList("Potions", "Ethers", "Atk Buff"));
    private ArrayList<Integer> itemNum = new ArrayList<>();

    //constructor
    public PlayerChar(){
        super(13, 5, 20);
        maxManaPoints = 10;
        manaPoints = maxManaPoints;
        level = 1;
        defAtk = attack;
        ablCost = 5;
        itemSet();
    }
    //arg constructor
    public PlayerChar(int atk, int def, int hp,int mp, int lvl, int aCst, 
            int pt, int et, int atB){
        super(atk, def, hp);
        maxManaPoints = mp;
        manaPoints = maxManaPoints;
        level = lvl;
        defAtk = attack;
        ablCost = aCst;
        itemSet(pt, et, atB);
    }

    //getters/setters
    public int getManaPoints() {
        return manaPoints;
    }
    public int getMaxManaPoints() {
        return maxManaPoints;
    }
    public int getLevel() {
        return level;
    }
    public int getItemPotion() {
        return itemNum.get(itemList.indexOf("Potions"));
    }
    public int getItemEther() {
        return itemNum.get(itemList.indexOf("Ethers"));
    }
    public int getItemAtkBuff() {
        return itemNum.get(itemList.indexOf("Atk Buff"));
    }
    public int getAblCost() {
        return ablCost;
    }
    public void setManaPoints(int manaPoints) {
        this.manaPoints = manaPoints;
    }
    public void setMaxManaPoints(int maxManaPoints) {
        this.maxManaPoints = maxManaPoints;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public void setAblCost(int ablCost) {
        this.ablCost = ablCost;
    }


    /**
     * Method Name: levelUpMenu
     * Description: prints a menu asking which stat to upgrade
     */
    public void levelUpMenu() {
        System.out.println("You leveled up");
        System.out.println("all your stats have increased");
        System.out.println("Which stat would you like to prioritize");
        System.out.println("1. atk");
        System.out.println("2. def");
        System.out.println("3. hp");
        System.out.println("4. mp");
    }


    /**
     * Method Name: levelUp
     * Description: increases stats and upgrades 1 specific stat more than others
     * @param userChoice - userInput for which stat to upgrade
     */
    public void levelUp(int userChoice) {
        //stat increase
        level++;
        attack *= 1.5;
        defAtk = attack;
        defence *= 1.5;
        maxHealthPoints *= 1.5;
        maxManaPoints *= 1.5;
        ablCost += 2;

        //switch for which stat to upgrade
        switch (userChoice) {
            case 1:
                attack *= 1.3;
                defAtk = attack;
                break;
            case 2:
                defence *= 1.3;
                break;
            case 3:
                maxHealthPoints *= 1.3;
                break;
            case 4:
                maxManaPoints *= 1.3;
                break;
            default:
                System.out.println("Error");
                break;
        }
        //makes mana and hp = to their maximums
        manaPoints = maxManaPoints;
        healthPoints = maxHealthPoints;
    }

    /**
     * Method Name: itemMenu
     * Description: prints menu and takes user input to see if item is available
     * @return itemAvailable - boolean value for if item is available or not
     */
    public boolean itemMenu() {
        //intializes variables and scanner
        boolean itemAvailable = false;
        int userChoice;
        Scanner scanN = new Scanner(System.in);

        //sorts ArrayList
        sortItems();
        
        //error trap do...while
        do {
            printItem();
            System.out.println("4. Exit items");
            System.out.println("which item would you like to use?");

            userChoice = scanN.nextInt();

            if (userChoice > 4 || userChoice < 1) {
                System.out.println("invalid, enter number corresponding to item");
            }
        } while (userChoice > 4 || userChoice < 1);

        userChoice--;
        //returns false if user wants to leave menu
        if (userChoice == 3) {
            System.out.println("Exiting items menu...");
            System.out.println(userChoice);
        }
        //sets itemChoice to index of item which is to be used in another method 
        else if (itemNum.get(userChoice) > 0 ) {
            itemAvailable = true;
            itemChoice = (userChoice);
        }
        //if there is 0 of item
        else{
            System.out.println("Item is unavailable");
        }
        return itemAvailable;
    }

    
    /**
     * Method Name: printItem
     * Description: prints items in a list format
     */
    private void printItem() {
        for (int i = 0; i < itemList.size(); i++) {
            System.out.println((i+1) + ". " + itemList.get(i) + "\t#of item: " 
                    + itemNum.get(i));
        }
    }


    /**
     * Method Name: itemUse
     * Description: uses item using switch to affect object fields
     */
    public void itemUse() {
        //switch using name of item
        switch (itemList.get(itemChoice)) {
            //potion adds hp
            case "Potions":
                healthPoints += 15;
                if (healthPoints > maxHealthPoints) {
                    healthPoints = maxHealthPoints;
                }

                System.out.println("you used a potion to heal 10 hp");
                break;
            //ether adds mp
            case "Ethers":
                manaPoints += 15;
                if (manaPoints > maxManaPoints) {
                    manaPoints = maxManaPoints;
                }

                System.out.println("you used an ether to recover 10 mp");
                break;
            //atk buff upgrades attack stat x3
            case "Atk Buff":
                attack *= 3;
                System.out.println("you used an attack buff");
                break;
            default:
                System.out.println("Error");
                break;
        }
        //subtracts one from item quantity
        itemNum.set(itemChoice, itemNum.get(itemChoice) - 1);
    }

    /**
     * Method Name: sortItem
     * Description: sorts parallel arrayLists based off quantity of 
     *      items(itemNum)
     */
    private void sortItems() {
        int minVal, minIndex;
        String minItem;

        for (int i = 0; i < itemNum.size()-1; i++) {
            minVal = itemNum.get(i);
            minItem = itemList.get(i);
            minIndex = i;

            for (int j = i + 1; j < itemNum.size(); j++) {
                if (itemNum.get(j) > minVal) {
                    minVal = itemNum.get(j);
                    minItem = itemList.get(j);
                    minIndex = j;
                }
            }
            itemNum.set(minIndex, itemNum.get(i));
            itemList.set(minIndex, itemList.get(i));
            itemNum.set(i, minVal);
            itemList.set(i, minItem); 
        }
    }


    /**
     * Method Name: itemSet
     * Description: itemNum arrayList setter
     */
    public void itemSet() {
        for (int i = 0; i < itemList.size()-1; i++) {
            itemNum.add((int)(1 + Math.random()*(5)));
        }
        itemNum.add(1);
    }


    /**
     * Method Name: itemSet
     * Description: itemNum arraylist setter
     * @param pt - potion quantity
     * @param et - ether quantity
     * @param atb - atk buff quantity
     */
    public void itemSet(int pt, int et, int atB) {
        itemNum.add(pt);
        itemNum.add(et);
        itemNum.add(atB);
    }


     /**
     * Method Name: itemUpdate
     * Description: adds random number of items to potion and ether
     */
    private void itemUpdate() {
        int potionDrop = (int)(1 + Math.random()*(4));
        int etherDrop = (int)(1 + Math.random()*(4));
        int potionIndex = itemList.indexOf("Potions");
        int etherIndex = itemList.indexOf("Ethers");

        itemNum.set(potionIndex, itemNum.get(potionIndex) + potionDrop);
        itemNum.set(etherIndex, itemNum.get(etherIndex) + etherDrop);
        
        System.out.println("you found " + potionDrop + " potions");
        System.out.println("you found " + etherDrop + " ethers");
    }


    /**
     * Method Name: itemEvent
     * Description: does a guessing number minigame and rewards with atk buff
     */
    public void itemEvent(){
        //intialize variables
        Scanner scanN = new Scanner(System.in);
        ArrayList<Integer> itemSearch = new ArrayList<>();
        int userInput;
        int randNum;
        int atkIndex = itemList.indexOf("Atk Buff");
        
        //for loop to give value to array
        //keeps numbers from repeating
        for (int i = 0; i < 5; i++) {
            do {
                randNum = (int)(Math.random()*(15));
            } while (itemSearch.contains(randNum) == true);

            itemSearch.add(randNum);
        }
        //sorts array
        Collections.sort(itemSearch);

        System.out.println("You search a chest for items");
        System.out.println("Guess a number between 1 and 10, if you guess right"
                + " you will recive an atk buff");
        //error trap do while
        do {
            userInput = scanN.nextInt();
            if (userInput > 10 || userInput < 1) {
             System.out.println("guess between 1 and 10");   
            }
        } while (userInput > 10 || userInput < 1);
        //rewards 1 atk buff if you win minigame
        if(searchChest(itemSearch, userInput)) {
            System.out.println("You found 1 atk buff");
            itemNum.set(atkIndex, (itemNum.get(atkIndex) + 1));
        }
        //prints message
        else {
            System.out.println("you found nothing");
        }  
    }


    /**
     * Method Name: searchChest
     * Description: uses binary search to check if users guess is in arrayList
     * @param numArray - arrayList of #'s for user to guess
     * @param key - int value of users guess
     * @return - boolean value for if key is in numArray
     */
    private boolean searchChest(ArrayList<Integer> numArray, int key){
        int first = 0;
        int last = numArray.size()-1;
        int mid;
        boolean found = false;

        //searching algorithm
        while (!found && first <= last) {
            mid = (first + last)/2;

            if (numArray.get(mid) == key) {
                found = true;
            }
            else if (numArray.get(mid) > key) {
                last = mid - 1;
            }
            else if (numArray.get(mid) < key) {
                first = mid + 1;
            }
        }

        return found;
    }


    /**
     * Method Name: abilityUse
     * Description: command for using ability, it is buffed attack command 
     *      which increases hp field and decreases mp field
     * @param enemy - enemy object whos hp is decreased 
     */
    public void abilityUse(Character enemy) {
        int damage = (int)((attack) + Math.random()*((attack+10) - attack +1));
        int recovery = damage/4;

        if (manaPoints > 5){
            System.out.println("You used an ability and did " + damage + " "
                    + "damage");
            System.out.println("You recovered " + recovery + " hp");
            System.out.println("You consumed " + ablCost + " mp");
            healthPoints += recovery;
            if (healthPoints > maxHealthPoints) {
                healthPoints = maxHealthPoints;
            }
            manaPoints -= ablCost;
            enemy.takeDamage(damage);
        } 
        else{
            System.out.println("Not enough mana");
            System.out.println("You used normal attack instead");
            attackCommand(enemy);
        }
    }

    /**
     * Method Name: endBattleReset
     * Description: resets healthPoints, manapoints and attack to default 
     *      after battle and updates items
     */
    public void endBattleReset() {
        healthPoints = maxHealthPoints;
        manaPoints = maxManaPoints;
        attack = defAtk;

        itemUpdate();
    }

    /**
     * Method Name: toString
     * Description: creates and returns a stats menu for object
     * @return - returns stats menu for object
     */
    public String toString() {
        return ("\tPlayer\n" + "Level: " + level + 
                "\nHP: " + healthPoints + "/" + maxHealthPoints
                +"\nMP: " + manaPoints + "/" +  maxManaPoints  
                + "\nAtk: " + attack + 
                "\nDEF: " + defence);
    }
}