
package mortalkombat;

/**
 * Класс игрока.
 * Наследует {@link Player}, добавляет очки, опыт и счётчик побед.
 *
 * @author Мария
 */
public class Human extends Player{
    
    /** Очки игрока. */
    private int points;
    
    /** Опыт игрока. */
    private int experience;
    
    /** Количество побед. */
    private int win;
    
    /** Опыт, необходимый для следующего уровня. */
    private int nextexperience;
    
    
    /**
     * Конструктор игрока.
     *
     * @param level уровень
     * @param health здоровье
     * @param damage урон
     * @param attack атака
     */    
    public Human(int level, int health, int  damage, int attack){
        super (level, health, damage, attack);
        this.points=0;
        this.experience=0;
        this.nextexperience=40;
        this.win=0;
    }
    
    /** 
     * Возвращает очки игрока. 
     */
    public int getPoints(){
        return this.points;
    }
    
    /** 
     * Возвращает опыт игрока. 
     */
    public int getExperience(){
        return this.experience;
    }
    
    /** 
     * Возвращает опыт до следующего уровня. 
     */
    public int getNextExperience(){
        return this.nextexperience;
    }
    
    /** 
     * Возвращает количество побед. 
     */
    public int getWin(){
        return this.win;
    }

    /** 
     * Увеличивает очки. 
     */
    public void setPoints(int p){
        this.points+=p;
    }
    
    /** 
      * Увеличивает опыт. 
      */
    public void setExperience(int e){
        this.experience+=e;
    }
    
    /** 
     * Устанавливает опыт до следующего уровня. 
     */
    public void setNextExperience(int e){
        this.nextexperience=e;
    }
    
    /** 
     * Увеличивает количество побед. 
     */
    public void setWin(){
        this.win++;
    }
    
    /** 
     * Возвращает имя игрока ("You"). 
     */
    @Override
    public String getName(){
        return "You";
    }

    
}
