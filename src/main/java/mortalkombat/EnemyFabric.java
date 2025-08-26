
package mortalkombat;

/**
 * Фабрика для создания врагов разных типов.
 * Использует паттерн "Фабрика", чтобы по индексу создать определённого персонажа.
 *
 * @author Мария
 */
public class EnemyFabric {

    /**
     * Создаёт врага по указанному индексу.
     *
     * @param i номер типа врага (0 - Baraka, 1 - SubZero, 2 - Liu Kang, 3 - Sonya Blade, 4 - Shao Kahn)
     * @param j параметр, передаваемый в метод create конкретной фабрики (например, уровень)
     * @return объект {@link Player}, представляющий выбранного врага
     */
    public Player create(int i, int j) {
        EnemyFabricInterface fabric = null;

        switch (i) {
            case 0:
                fabric = new BarakaFabric();
                break;
            case 1:
                fabric = new SubZeroFabric();
                break;
            case 2:
                fabric = new LiuKangFabric();
                break;
            case 3:
                fabric = new SonyaBladeFabric();
                break;
            case 4:
                fabric = new ShaoKahnFabric();
                break;
        }
        Player enemy = fabric.create(j);
        return enemy;
    }
}
