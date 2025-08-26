
package mortalkombat;

/**
 * Фабрика для создания экземпляров противника типа SubZero.
 * Реализует интерфейс EnemyFabricInterface.
 * 
 * @author Мария
 */
public class SubZeroFabric implements EnemyFabricInterface {
    
    /**
     * Создает нового противника типа SubZero
     * 
     * @param i параметр для настройки характеристик противника (не используется)
     * @return новый экземпляр класса SubZero с установленной способностью ослабления
     */
    @Override
    public Player create(int i) {
        Player enemy;
        enemy = new SubZero(1, 60, 16, 1);
        return enemy;
    }

}
