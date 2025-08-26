
package mortalkombat;

/**
 * Класс, представляющий босса Шао Кана.
 * Наследует базовые характеристики от класса Player.
 * 
 * @author Мария
 */
public class ShaoKahn extends Player{
    
    /**
     * Конструктор для создания экземпляра Шао Кана
     * 
     * @param level уровень босса
     * @param health здоровье босса
     * @param damage урон босса
     * @param attack тип атаки босса
     */    
    public ShaoKahn(int level, int health, int  damage, int attack){
        super (level, health, damage, attack);
    }
    
    /**
     * Возвращает имя босса
     * 
     * @return строку с именем босса "Shao Kahn"
     */    
    @Override
    public String getName(){
        return "Shao Kahn";
    }
}
