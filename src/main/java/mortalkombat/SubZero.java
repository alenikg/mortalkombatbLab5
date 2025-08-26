/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombat;

/**
 * Класс, представляющий игрового персонажа Саб-Зиро.
 * Наследует базовые характеристики и поведение от класса Player.
 * 
 * @author Мария
 */
public class SubZero extends Player{
    
    /**
     * Конструктор для создания экземпляра Саб-Зиро
     * 
     * @param level уровень персонажа
     * @param health здоровье персонажа
     * @param damage урон персонажа
     * @param attack тип атаки персонажа
     */
    public SubZero(int level, int health, int damage , int attack){
        super (level, health, damage, attack);
        setCanWeaken(true);
    }
    
    /**
     * Возвращает имя персонажа
     * 
     * @return строку с именем персонажа "Sub-Zero"
     */    
    @Override
    public String getName(){
        return "Sub-Zero";
    }
}
