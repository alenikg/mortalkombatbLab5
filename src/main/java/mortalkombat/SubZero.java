/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombat;

/**
 *
 * @author Мария
 */
public class SubZero extends Player{
    
    public SubZero(int level, int health, int damage , int attack){
        super (level, health, damage, attack);
        setCanWeaken(true);
    }
    
    @Override
    public String getName(){
        return "Sub-Zero";
    }
}
