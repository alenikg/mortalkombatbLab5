
package mortalkombat;

/**
 * Базовый абстрактный класс, представляющий игрока или противника.
 * Содержит основные характеристики: уровень, здоровье, урон, атаку,
 * а также механизмы ослабления (weaken) и регенерации.
 * 
 * @author Мария
 */
public class Player {
    
    /** Уровень персонажа */
    private int level;
    /** Текущее здоровье персонажа */
    private int health;
    /** Максимальное здоровье персонажа */
    private int maxhealth;
    /** Наносимый урон */
    private int damage;
    /** Тип текущей атаки */
    private int attack;
    /** Флаг ослабленного состояния */
    private boolean isWeakened;
    /** Количество ходов в ослабленном состоянии */
    private int weakenedTurnsLeft;
    /** Множитель урона */
    private double damageMultiplier = 1.0;
    /** Множитель атаки */
    private double attackMultiplier = 1.0;
    /** Флаг возможности ослаблять противника */
    private boolean canWeaken = false;

    /**
     * Проверяет, может ли персонаж ослаблять противников
     * 
     * @return true если может ослаблять, иначе false
     */
    public boolean isCanWeaken() {
        return canWeaken;
    }

    /**
     * Устанавливает возможность ослабления противников
     * 
     * @param canWeaken флаг возможности ослабления
     */
    public void setCanWeaken(boolean canWeaken) {
        this.canWeaken = canWeaken;
    }
    
    /**
     * Конструктор для создания базового персонажа
     * 
     * @param level начальный уровень
     * @param health начальное здоровье
     * @param damage базовый урон
     * @param attack тип атаки
     */
    public Player(int level, int health, int damage, int attack){
        this.level=level;
        this.health=health;
        this.damage=damage;
        this.attack=attack;
        this.maxhealth=health;
    }
    
    /**
     * Накладывает эффект ослабления на персонажа
     * 
     * @param turns количество ходов действия ослабления
     */
    public void applyWeaken(int turns) {
        this.isWeakened = true;
        this.weakenedTurnsLeft = turns;
        this.damageMultiplier = 1.25;
        this.attackMultiplier = 0.5;
    }
    
    /**
     * Уменьшает счетчик ходов ослабления и снимает эффект при необходимости
     */
    public void weakenSpend() {
        if (isWeakened) {
            weakenedTurnsLeft--;
            if (weakenedTurnsLeft <= 0) {
                removeWeaken();
            }
        }
    }
    
    /**
     * Снимает эффект ослабления с персонажа
     */
    public void removeWeaken() {
        this.isWeakened = false;
        this.damageMultiplier = 1.0;
        this.attackMultiplier = 1.0;
    }
    
    /**
     * Проверяет, находится ли персонаж в ослабленном состоянии
     * 
     * @return true если ослаблен, иначе false
     */
    public boolean isWeakened() {
        return this.isWeakened;
    }
    
    /**
     * Возвращает текущий множитель урона
     * 
     * @return значение множителя урона
     */
    public double getDamageMultiplier() {
        return this.damageMultiplier;
    }
    
    /**
     * Возвращает текущий множитель атаки
     * 
     * @return значение множителя атаки
     */
    public double getAttackMultiplier() {
        return this.attackMultiplier;
    }
    
    /**
     * Повышает уровень персонажа на 1
     */
    public void setLevel(){
        this.level++;
    }
    
    /**
     * Изменяет текущее здоровье персонажа
     * 
     * @param h величина изменения здоровья (может быть отрицательной)
     */
    public void setHealth(int h){
        this.health+=h;
    }
    
    /**
     * Устанавливает абсолютное значение здоровья
     * 
     * @param h новое значение здоровья
     */
    public void setNewHealth(int h){
        this.health=h;
    }
    
    /**
     * Изменяет урон персонажа
     * 
     * @param d величина изменения урона
     */    
    public void setDamage(int d){
        this.damage+=d;
    }
    
    /**
     * Устанавливает тип атаки для текущего хода
     * 
     * @param a тип атаки
     */
    public void setAttack(int a){
        this.attack=a;
    }
    
    /**
     * Увеличивает максимальное здоровье персонажа
     * 
     * @param h величина увеличения максимального здоровья
     */   
    public void setMaxHealth(int h){
        this.maxhealth+=h;
    }

    /**
     * Возвращает текущий уровень персонажа
     * 
     * @return уровень персонажа
     */    
    public int getLevel(){
        return this.level;
    }
    
    /**
     * Возвращает текущее здоровье персонажа
     * 
     * @return текущее здоровье
     */   
    public int getHealth(){
        return this.health;
    }
    
    /**
     * Возвращает значение урона персонажа
     * 
     * @return урон персонажа
     */    
    public int getDamage(){
        return this.damage;
    }
    
    /**
     * Возвращает тип текущей атаки
     * 
     * @return тип атаки
     */    
    public int getAttack(){
        return this.attack;
    }
    
    /**
     * Возвращает максимальное здоровье персонажа
     * 
     * @return максимальное здоровье
     */   
    public int getMaxHealth(){
        return this.maxhealth;
    }
    
    /**
     * Восстанавливает часть здоровья персонажа (регенерация)
     */
    public void regenerate() {
        this.health += (this.maxhealth - this.health) / 2;
    }

    /**
     * Возвращает имя персонажа
     * 
     * @return пустую строку (должен быть переопределен в наследниках)
     */   
    public String getName(){
        return "";
    }
    
}
