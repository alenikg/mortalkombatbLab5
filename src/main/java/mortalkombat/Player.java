
package mortalkombat;

public class Player {
    
    private int level;
    private int health;
    private int maxhealth;
    private int damage;
    private int attack;

    private boolean isWeakened;
    private int weakenedTurnsLeft;
    private double damageMultiplier = 1.0;
    private double attackMultiplier = 1.0;
    private boolean canWeaken = false;

    public boolean isCanWeaken() {
        return canWeaken;
    }

    public void setCanWeaken(boolean canWeaken) {
        this.canWeaken = canWeaken;
    }

    public Player(int level, int health, int damage, int attack){
        this.level=level;
        this.health=health;
        this.damage=damage;
        this.attack=attack;
        this.maxhealth=health;
    }

    public void applyWeaken(int turns) {
        this.isWeakened = true;
        this.weakenedTurnsLeft = turns;
        this.damageMultiplier = 1.25;
        this.attackMultiplier = 0.5;
    }

    public void weakenSpend() {
        if (isWeakened) {
            weakenedTurnsLeft--;
            if (weakenedTurnsLeft <= 0) {
                removeWeaken();
            }
        }
    }

    public void removeWeaken() {
        this.isWeakened = false;
        this.damageMultiplier = 1.0;
        this.attackMultiplier = 1.0;
    }

    public boolean isWeakened() {
        return this.isWeakened;
    }

    public double getDamageMultiplier() {
        return this.damageMultiplier;
    }

    public double getAttackMultiplier() {
        return this.attackMultiplier;
    }

    public void setLevel(){
        this.level++;
    }
    public void setHealth(int h){
        this.health+=h;
    }
    public void setNewHealth(int h){
        this.health=h;
    }
    public void setDamage(int d){
        this.damage+=d;
    }
    public void setAttack(int a){
        this.attack=a;
    }
    public void setMaxHealth(int h){
        this.maxhealth+=h;
    }
    
    public int getLevel(){
        return this.level;
    }
    public int getHealth(){
        return this.health;
    }
    public int getDamage(){
        return this.damage;
    }
    public int getAttack(){
        return this.attack;
    }
    public int getMaxHealth(){
        return this.maxhealth;
    }

    public void regenerate() {
        this.health += (this.maxhealth - this.health) / 2;
    }

    public String getName(){
        return "";
    }
    
}
