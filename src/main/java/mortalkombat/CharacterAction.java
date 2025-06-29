/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombat;

import javax.swing.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Мария
 */
public class CharacterAction {

    private final int experience_for_next_level[] = {40, 90, 180, 260, 410, 1000};

    private final int kind_fight[][] = {{1, 0}, {1, 1, 0}, {0, 1, 0}, {1, 1, 1, 1}};
    private final int mag_kind_fight[][] = {{1, 0}, {1, 1, 2}, {0, 1, 2}, {1, 2, 1, 1}};

    private Player enemyes[] = new Player[6];
    private int currEnemyIndex = 0;

    EnemyFabric fabric = new EnemyFabric();

    private Player enemyy = null;

    private String getIconPathStr(String fileName) {
        URL url = getClass().getResource("/images/" + fileName);
        if (url != null) {
            return url.getPath();
        } else {
            return "";
        }
    }

    private static void shuffleExceptLast(Player[] arr) {
        if (arr.length <= 2) {
            return;
        }
        List<Player> list = new ArrayList<>(Arrays.asList(arr));
        List<Player> subList = list.subList(0, list.size() - 1);
        Collections.shuffle(subList);
        subList.add(arr[arr.length - 1]);
        subList.toArray(arr);
    }

    public void setEnemyes(int count) {
        currEnemyIndex = 0;
        if (count < 1) {
            enemyes = new Player[] { fabric.create(4, 0) };
            return;
        }
        enemyes = new Player[count];
        for (int i = 0; i < count; i++) {
            enemyes[i] = i == count -1 ? fabric.create(4, 0) : fabric.create(i % 4, 0);
        }
        shuffleExceptLast(enemyes);
    }

    public Player[] getEnemyes() {
        return this.enemyes;
    }

    public Boolean isLastEnemy() {
        return currEnemyIndex == enemyes.length - 1;
    }

    public Boolean hasNotEnemy() {
        return currEnemyIndex > enemyes.length - 1;
    }

    public Player ChooseEnemy(JLabel label, JLabel label2, JLabel text, JLabel label3) {
        enemyy = enemyes[currEnemyIndex];
        ImageIcon icon1 = null;
        if (enemyy instanceof Baraka) {
            icon1 = new ImageIcon(getIconPathStr("baraka.jpg"));
            label2.setText("Baraka (танк)");
        }
        if (enemyy instanceof SubZero) {
            icon1 = new ImageIcon(getIconPathStr("subzero.jpg"));
            label2.setText("Sub-Zero (маг)");
        }
        if (enemyy instanceof LiuKang) {
            icon1 = new ImageIcon(getIconPathStr("liukang.jpg"));
            label2.setText("Liu Kang (боец)");
        }
        if (enemyy instanceof SonyaBlade) {
            icon1 = new ImageIcon(getIconPathStr("sonyablade.jpg"));
            label2.setText("Sonya Blade (солдат)");
        }
        label.setIcon(icon1);
        text.setText(Integer.toString(enemyy.getDamage()));
        label3.setText(Integer.toString(enemyy.getHealth()) + "/" + Integer.toString(enemyy.getMaxHealth()));
        currEnemyIndex++;
        return enemyy;
    }

    public Player ChooseBoss(JLabel label, JLabel label2, JLabel text, JLabel label3, int i) {
        ImageIcon icon1 = null;
        icon1 = new ImageIcon(getIconPathStr("shaokahn.png"));
        label2.setText("Shao Kahn (босс)");
        enemyy = this.enemyes[this.enemyes.length - 1];
        label.setIcon(icon1);
        text.setText(Integer.toString(enemyy.getDamage()));
        label3.setText(Integer.toString(enemyy.getHealth()) + "/" + Integer.toString(enemyy.getMaxHealth()));
        currEnemyIndex++;
        return enemyy;
    }

    public int[] EnemyBehavior(int k1, int k2, int k3, int k4, double i) {
        int arr[] = null;
        if (i < k1 * 0.01) {
            arr = kind_fight[0];
        }
        if (i >= k1 * 0.01 & i < (k1 + k2) * 0.01) {
            arr = kind_fight[1];
        }
        if (i >= (k1 + k2) * 0.01 & i < (k1 + k2 + k3) * 0.01) {
            arr = kind_fight[2];
        }
        if (i >= (k1 + k2 + k3) * 0.01 & i < 1) {
            arr = kind_fight[3];
        }
        return arr;
    }

    public int[] MagEnemyBehavior(int k1, int k2, int k3, int k4, double i) {
        int arr[] = null;
        if (i < k1 * 0.01) {
            arr = mag_kind_fight[0];
        }
        if (i >= k1 * 0.01 & i < (k1 + k2) * 0.01) {
            arr = mag_kind_fight[1];
        }
        if (i >= (k1 + k2) * 0.01 & i < (k1 + k2 + k3) * 0.01) {
            arr = mag_kind_fight[2];
        }
        if (i >= (k1 + k2 + k3) * 0.01 & i < 1) {
            arr = mag_kind_fight[3];
        }
        return arr;
    }

    public int[] ChooseBehavior(Player enemy, CharacterAction action) {
        int arr[] = null;
        double i = Math.random();
        if (enemy instanceof Baraka) {
            arr = action.EnemyBehavior(15, 15, 60, 10, i);
        }
        if (enemy instanceof SubZero) {
            arr = action.MagEnemyBehavior(25, 25, 0, 50, i);
        }
        if (enemy instanceof LiuKang) {
            arr = action.EnemyBehavior(13, 13, 10, 64, i);
        }
        if (enemy instanceof SonyaBlade) {
            arr = action.EnemyBehavior(25, 25, 50, 0, i);
        }
        if (enemy instanceof ShaoKahn) {
            arr = action.EnemyBehavior(10, 45, 0, 45, i);
        }
        return arr;
    }

    public void HP(Player player, JProgressBar progress) {

        if (player.getHealth() >= 0) {
            progress.setValue(player.getHealth());
        } else {
            progress.setValue(0);
        }
    }

    private void increaseLevelIfNeeded(Human human) {
        for (int i = 0; i < 5; i++) {
            if (experience_for_next_level[i] == human.getExperience()) {
                human.setLevel();
                human.setNextExperience(experience_for_next_level[i + 1]);
                String[] options = {"Урон", "Здоровье"};
                int choice = JOptionPane.showOptionDialog(
                        null,
                        "Вы достигли нового уровня!\nЧто вы хотите улучшить?",
                        "Повышение уровня",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

                if (choice == 0) { // Урон
                    human.setDamage(5); // увеличить урон на 5
                } else if (choice == 1) { // Здоровье
                    human.setMaxHealth(15); // увеличить макс. здоровье на 15
                    human.setHealth(15);    // при этом увеличить текущее здоровье тоже
                }

                NewHealthHuman(human);
                for (int j = 0; j < enemyes.length; j++) {
                    NewHealthEnemy(enemyes[j], human);
                }
            }
        }
    }

    public void AddPoints(Human human, Player[] enemyes) {
        switch (human.getLevel()) {
            case 0:
                human.setExperience(20);
                human.setPoints(25 + human.getHealth() / 4);
                break;
            case 1:
                human.setExperience(25);
                human.setPoints(30 + human.getHealth() / 4);
                break;
            case 2:
                human.setExperience(30);
                human.setPoints(35 + human.getHealth() / 4);
                break;
            case 3:
                human.setExperience(40);
                human.setPoints(45 + human.getHealth() / 4);
                break;
            case 4:
                human.setExperience(50);
                human.setPoints(55 + human.getHealth() / 4);
                break;
        }
        increaseLevelIfNeeded(human);
    }

    public void AddPointsBoss(Human human, Player[] enemyes) {
        switch (human.getLevel()) {
            case 2:
                human.setExperience(30);
                human.setPoints(45 + human.getHealth() / 2);
                break;
            case 4:
                human.setExperience(50);
                human.setPoints(65 + human.getHealth() / 2);
                break;
        }
        increaseLevelIfNeeded(human);
    }

    public void AddItems(int k1, int k2, int k3, Items[] items) {
        double i = Math.random();
        if (i < k1 * 0.01) {
            items[0].setCount(1);
        }
        if (i >= k1 * 0.01 & i < (k1 + k2) * 0.01) {
            items[1].setCount(1);
        }
        if (i >= (k1 + k2) * 0.01 & i < (k1 + k2 + k3) * 0.01) {
            items[2].setCount(1);
        }
    }

    public void NewHealthHuman(Human human) {
        int hp = 0;
        switch (human.getLevel()) {
            case 1:
                hp = 25;
                break;
            case 2:
                hp = 30;
                break;
            case 3:
                hp = 30;
                break;
            case 4:
                hp = 40;
                break;
        }
        human.setMaxHealth(hp);
    }

    public void NewDamageHuman(Human human) {
        int damage = 0;
        switch (human.getLevel()) {
            case 1:
                damage = 3;
                break;
            case 2:
                damage = 3;
                break;
            case 3:
                damage = 4;
                break;
            case 4:
                damage = 6;
                break;
        }
        human.setDamage(damage);
    }

    public void NewHealthEnemy(Player enemy, Human human) {
        int hp = 0;
        int damage = 0;
        switch (human.getLevel()) {
            case 1:
                hp = 32;
                damage = 25;
                break;
            case 2:
                hp = 30;
                damage = 20;
                break;
            case 3:
                hp = 23;
                damage = 24;
                break;
            case 4:
                hp = 25;
                damage = 26;
                break;
        }
        enemy.setMaxHealth((int) enemy.getMaxHealth() * hp / 100);
        enemy.setDamage((int) enemy.getDamage() * damage / 100);
        enemy.setLevel();
    }

    public void UseItem(Player human, Items[] items, String name, JDialog dialog, JDialog dialog1) {
        switch (name) {
            case "jRadioButton1":
                if (items[0].getCount() > 0) {
                    human.setHealth((int) (human.getMaxHealth() * 0.25));
                    items[0].setCount(-1);
                } else {
                    dialog.setVisible(true);
                    dialog.setBounds(300, 200, 400, 300);
                }
                break;
            case "jRadioButton2":
                if (items[1].getCount() > 0) {
                    human.setHealth((int) (human.getMaxHealth() * 0.5));
                    items[1].setCount(-1);
                } else {
                    dialog.setVisible(true);
                    dialog.setBounds(300, 200, 400, 300);
                }
                break;
            case "jRadioButton3":
                dialog.setVisible(true);
                dialog.setBounds(300, 200, 400, 300);
                break;
        }
        
        if(dialog.isVisible()==false){
            dialog1.dispose();
        }
    }
}
