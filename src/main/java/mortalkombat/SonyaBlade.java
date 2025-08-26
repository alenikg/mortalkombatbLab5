
package mortalkombat;

/**
 * Класс, представляющий игрового персонажа Соню Блейд.
 * Наследует базовые характеристики и поведение от класса Player.
 * 
 * @author Мария
 */
public class SonyaBlade extends Player{
    
    /**
     * Конструктор для создания экземпляра Сони Блейд
     * 
     * @param level уровень персонажа
     * @param health здоровье персонажа
     * @param damage урон персонажа
     * @param attack тип атаки персонажа
     */    
    public SonyaBlade (int level, int health, int  damage, int attack){
        super (level, health, damage, attack);
    }
    
    /**
     * Возвращает имя персонажа
     * 
     * @return строку с именем персонажа "Sonya Blade"
     */    
    @Override
    public String getName(){
        return "Sonya Blade";
    }
}
