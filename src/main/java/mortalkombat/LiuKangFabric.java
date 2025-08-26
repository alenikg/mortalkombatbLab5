
package mortalkombat;

/**
 * Фабрика для создания экземпляров противника типа LiuKang.
 * Реализует интерфейс EnemyFabricInterface.
 * 
 * @author Мария
 */
public class LiuKangFabric implements EnemyFabricInterface {

    /**
     * Создает нового противника типа LiuKang
     * 
     * @param i параметр для настройки характеристик противника (не используется)
     * @return новый экземпляр класса LiuKang
     */
    @Override
    public Player create(int i) {
        Player enemy;
        enemy = new LiuKang(1, 70, 20, 1);
        return enemy;
    }
}
