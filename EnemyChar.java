public class EnemyChar extends Character{

    //constructor
    public EnemyChar(){
        super(5, 2, 15);
    }

    //arg constructor
    public EnemyChar(int atk, int def, int hp) {
        super(atk, def, hp);
    }

    /**
     * Method Name: stageScale
     * Description: scales the fields up based off of parameter
     * @param stageNum - stage# from main class, is same as player level
     */
    public void stageScale(int stageNum) {
        float stgLvl = stageNum;
        //scales all stats around attack stat
        float scale = (stgLvl/2)*attack; 

        attack += (int)scale;
        defence += (int)scale;
        maxHealthPoints += (int)scale;
        healthPoints = maxHealthPoints;
    }

    /**
     * Method Name: toString
     * Description: creates and returns a stats menu for object
     * @return - returns stats menu for object
     */
    public String toString(){
        return("\tEnemy\n" + super.toString());
    }
}