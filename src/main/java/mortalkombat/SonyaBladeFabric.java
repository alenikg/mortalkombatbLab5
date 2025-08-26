
package mortalkombat;

/**
 * Фабрика для создания экземпляров противника типа SonyaBlade.
 * Реализует интерфейс EnemyFabricInterface.
 * 
 * @author Мария
 */
public class SonyaBladeFabric implements EnemyFabricInterface {
    
    /**
     * Создает нового противника типа SonyaBlade
     * 
     * @param i параметр для настройки характеристик противника (не используется)
     * @return новый экземпляр класса SonyaBlade
     */
    @Override
    public Player create(int i) {
        Player enemy;
        enemy = new SonyaBlade(1, 80, 16, 1);
        return enemy;
    }

}
