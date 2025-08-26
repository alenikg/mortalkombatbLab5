
package mortalkombat;

/**
 * Класс для хранения результата игрока (имя и количество очков).
 * Используется для формирования таблицы рекордов.
 * 
 * @author Мария
 */
public class Result {
    /** Имя игрока */
    private String name;
    /** Количество набранных очков */
    private int points;

    /**
     * Конструктор для создания результата
     * 
     * @param n имя игрока
     * @param p количество очков
     */
    public Result(String n, int p){
        this.name = n;
        this.points = p;
    }

    /**
     * Устанавливает имя игрока
     * 
     * @param s новое имя игрока
     */
    public void setName(String s){
        this.name = s;
    }

    /**
     * Устанавливает количество очков
     * 
     * @param p новое количество очков
     */
    public void setPoints(int p){
        this.points = p;
    }

    /**
     * Возвращает имя игрока
     * 
     * @return имя игрока
     */
    public String getName(){
        return this.name;
    }

    /**
     * Возвращает количество очков
     * 
     * @return количество очков
     */
    public int getPoints(){
        return this.points;
    }   
}
