
package mortalkombat;

/**
 * Класс, представляющий игрового персонажа Лю Кэна.
 * Наследует базовые характеристики и поведение от класса Player.
 * Лю Кэн является бойцом с высоким уроном, но относительно низким здоровьем.
 * 
 * @author Мария
 */
public class LiuKang extends Player{
    
    /**
     * Конструктор для создания экземпляра Лю Кэна
     * 
     * @param level уровень персонажа
     * @param health здоровье персонажа
     * @param damage урон персонажа
     * @param attack тип атаки персонажа
     */
    public LiuKang(int level, int health, int  damage, int attack){
        super (level, health, damage, attack);
    }
    
    /**
     * Возвращает имя персонажа
     * 
     * @return строку с именем персонажа "Liu Kang"
     */
    @Override
    public String getName(){
        return "Liu Kang";
    }
}
