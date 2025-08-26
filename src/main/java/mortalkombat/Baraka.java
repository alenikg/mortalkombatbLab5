
package mortalkombat;
/**
 * Класс, описывающий врага Baraka.
 * Наследуется от {@link Player}.
 * Характеризуется начальными параметрами уровня, здоровья, урона и атаки.
 *
 * @author Мария
 */
public class Baraka extends Player{
    
    /**
     * Конструктор для создания объекта Baraka.
     *
     * @param level   уровень врага
     * @param health  количество здоровья
     * @param damage  сила урона
     * @param attack  тип атаки
     */
    public Baraka(int level, int health, int  damage, int attack){
        super (level, health, damage, attack);
    }
    
    /**
     * Возвращает имя персонажа.
     *
     * @return строка "Baraka"
     */    
    @Override
    public String getName(){
        return "Baraka";
    }
    
}
