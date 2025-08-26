
package mortalkombat;

/**
 * Интерфейс фабрики врагов.
 * Определяет метод создания врага.
 *
 * @author Мария
 */
public interface EnemyFabricInterface {
    
    /**
     * Создаёт врага.
     *
     * @param i параметр
     * @return объект врага
     */
    public Player create(int i);
}
