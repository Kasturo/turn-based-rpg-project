public class Character {

    //protected fields 
    protected int attack;
    protected int defence;
    protected int maxHealthPoints;
    protected int healthPoints;

    //constructor
    public Character() {
        maxHealthPoints = 15;
        healthPoints = maxHealthPoints;
        attack = 5;
        defence = 2;
    }
    //arg constructor
    public Character(int atk, int def, int hp) {
        attack = atk;
        defence = def;
        maxHealthPoints = hp;
        healthPoints = maxHealthPoints;
    }

    //getters/setters
    public int getAttack() {
        return attack;
    }
    public int getDefence() {
        return defence;
    }
    public int getHealthPoints() {
        return healthPoints;
    }
    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public void setDefence(int defence) {
        this.defence = defence;
    }
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }
    public void setMaxHealthPoints(int maxHealthPoints) {
        this.maxHealthPoints = maxHealthPoints;
    }

    
    /**
     * Method Name: attackCommand
     * Description: Subtracts hp from enemy attack with a slight probabilty 
     *      for attack event
     * @param opponent - Character object used to subtract health 
     *          from the opponent of the user of this method
     */
    public void attackCommand(Character opponent) {
        int damage;
        //subtracts attack from defence stat of opponent 
        damage = attack - opponent.getDefence();   

       //random num used for attack event
       int attackChance = (int)(1 + Math.random()*(8));
       //crit event
       if (attackChance == 1) {
            damage *= 2;
            System.out.println("critical hit");
       }
       //miss attack event
       else if (attackChance == 7) {
            damage = 0;
            System.out.println("the attack missed");
       }
       //ineffective attack event
       else if (attackChance == 4) {
            System.out.println("Ineffective attack");
            damage /=2 ;
       }
       if (damage < 0) {
            damage = 1;
        }
       //calls method to subtract damage
       opponent.takeDamage(damage);
       System.out.println(damage + " damage was dealt"); 
    }

    /**
     * Method Name: takeDamage
     * Description: subtracts damage from health point field of object
     * @param dmg - value to subtract by
     */
    protected void takeDamage(int dmg) {

        if (dmg > healthPoints) {
            healthPoints = 0;
            return;
        }
        healthPoints -= dmg;
    }

    
    /**
     * Method Name: status
     * Description: checks if health has not depleted
     * @return - boolean for if it is depleted or not
     */
    public boolean status() {
        if (healthPoints <= 0) {
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Method Name: toString
     * Description: creates and returns a stats menu for object
     * @return - returns stats menu for object
     */
    public String toString() {
        return ("HP: " + healthPoints + "/" + maxHealthPoints 
        + "\nAtk: " + attack + "\nDEF: " + defence);
    }



    //Player Subclass methods
    public int getManaPoints() {
        return -1;
    }
    public int getMaxManaPoints() {
        return -1;
    }
    public int getLevel() {
        return -1;
    }
    public int getItemPotion() {
        return -1;
    }
    public int getItemEther() {
        return -1;
    }
    public int getItemAtkBuff() {
        return -1;
    }
    public int getAblCost() {
        return -1;
    }
    public void setManaPoints(int manaPoints) {
        System.out.println("Error");
    }
    public void setMaxManaPoints(int maxManaPoints) {
        System.out.println("Error");
    }
    public void setLevel(int level) {
        System.out.println("Error");
    }
    public void setAblCost(int ablCost) {
        System.out.println("Error");
    }
    public void levelUpMenu() {
        System.out.println("Error");
    }
    public void levelUp(int userChoice) {
        System.out.println("Error");
    }
    public boolean itemMenu() {
        return false;
    }
    public void itemUse() {
        System.out.println("Error");
    }
    public void itemSet() {
        System.out.println("Error");
    }
    public void itemEvent(){
        System.out.println("Error");
    }
    public void abilityUse(Character enemy) {
        System.out.println("Error");
    }
    public void endBattleReset() {
        System.out.println("Error");
    }
    public void itemSet(int pt, int et, int atB) {
        System.out.println("Error");
    }

    
    //Enemy Subclass methods 
    public void stageScale(int stageNum) {
        System.out.println("Error");
    }

}