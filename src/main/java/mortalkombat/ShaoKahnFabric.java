
package mortalkombat;

/**
 * Фабрика для создания экземпляров босса Шао Кана.
 * Реализует интерфейс EnemyFabricInterface.
 * Создает различные варианты Шао Кана в зависимости от параметра.
 * 
 * @author Мария
 */
public class ShaoKahnFabric implements EnemyFabricInterface{
    
    /**
     * Создает нового босса типа ShaoKahn с различными характеристиками
     * в зависимости от параметра
     * 
     * @param i параметр, определяющий вариант босса:
     *          0 - стандартный вариант,
     *          другие значения - усиленный вариант
     * @return новый экземпляр класса ShaoKahn
     */    
    @Override
    public Player create(int i) {
        Player enemy;
        if(i==0){
            enemy = new ShaoKahn(3, 100, 30, 1);
        }
        else{
           enemy = new ShaoKahn(3, 145, 44, 1); 
        }
        return enemy;
    }
}
