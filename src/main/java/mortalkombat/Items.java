
package mortalkombat;

/**
 * Класс предмета.
 * Хранит имя и количество.
 *
 * @author Мария
 */
public class Items {
    
    /** Название предмета. */
    private String name;
    
    /** Количество предмета. */
    private int count;

    /**
     * Конструктор предмета.
     *
     * @param n имя
     * @param c количество
     */    
    public Items(String n, int c){
        this.name=n;
        this.count=c;
    }

    /** 
     * Устанавливает имя предмета. 
     */    
    public void setName(String s){
        this.name=s;
    }
    
    /** 
     * Изменяет количество предметов. 
     */    
    public void setCount(int c){
        this.count+=c;
    }

    /** 
     * Возвращает имя предмета. 
     */    
    public String getName(){
        return this.name;
    }
    
    /** 
     * Возвращает количество предметов.
     */    
    public int getCount(){
        return this.count;
    }
}
