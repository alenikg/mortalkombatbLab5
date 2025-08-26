
package mortalkombat;

/**
 * Фабрика для создания объекта врага Baraka.
 * Реализует интерфейс {@link EnemyFabricInterface}.
 *
 * @author Мария
 */
public class BarakaFabric implements EnemyFabricInterface {
    
    /**
     * Создаёт новый экземпляр {@link Baraka}.
     * Значения параметров фиксированы (уровень = 1, здоровье = 100, урон = 12, атака = 1).
     *
     * @param i не используется, присутствует для совместимости с интерфейсом
     * @return объект {@link Player}, представляющий Baraka
     */
    @Override
    public Player create(int i) {
        Player enemy;
        enemy = new Baraka(1, 100, 12, 1);
        return enemy;
    }
}
