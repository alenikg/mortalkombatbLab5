/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombat;

//ADD IMAGE!!!
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;

/**
 *
 * @author Мария
 */
public class Fight {

    ChangeTexts change = new ChangeTexts();
    int kind_attack[] = {0};
    int experiences[] = {40, 90, 180, 260, 410};
    EnemyFabric fabric = new EnemyFabric();
    int i = 1;
    int k = -1;
    int stun = 0;
    double v = 0.0;

    public void Move(Player p1, Player p2, JLabel l, JLabel l2) {
        if (stun == 1) {
            p1.setAttack(-1);
        }
        switch (Integer.toString(p1.getAttack()) + Integer.toString(p2.getAttack())) {
            // 3 - регенерация босса (ShaoKahn), 2 - ослабление, 1 - атака, 0 - защита
            case "30":
                p1.regenerate();
                l2.setText(p1.getName() + " is regenerated");
                break;
            case "31":
                p1.setHealth(-(int) (p2.getDamage() * p2.getAttackMultiplier() * p1.getDamageMultiplier()));
                l.setText("regenerate is broken");
                l2.setText(p2.getName() + " attacked");
                break;
            case "32":
                l2.setText(p2.getName() + " attacked");
                if (Math.random() < 0.75) {
                    l.setText("regenerate is broken");
                    p1.applyWeaken(p2.getLevel() + 1);
                    l2.setText(p1.getName() + " is weakened");
                } else {
                    p1.regenerate();
                    l.setText(p1.getName() + " is regenerated");
                    l2.setText(p1.getName() + " is not weakened");
                }
                break;
            case "03":
                p2.regenerate();
                l2.setText(p2.getName() + " is regenerated");
                break;
            case "13":
                p2.setHealth(-(int) (p1.getDamage() * p1.getAttackMultiplier() * p2.getDamageMultiplier()));
                l.setText("regenerate is broken");
                l2.setText(p1.getName() + " attacked");
                break;
            case "20":
                if (Math.random() < 0.75) {
                    p2.applyWeaken(p1.getLevel() + 1);
                    l2.setText(p2.getName() + " is weakened");
                } else {
                    l2.setText(p2.getName() + " is not weakened");
                }
                 break;
            case "23":
                if (Math.random() < 0.75) {
                    l.setText("regenerate is broken");
                    p2.applyWeaken(p1.getLevel() + 1);
                    l2.setText(p2.getName() + " is weakened");
                } else {
                    p2.regenerate();
                    l.setText(p2.getName() + " is regenerated");
                    l2.setText(p2.getName() + " is not weakened");
                }
                break;
            case "21":
                p1.setHealth(-(int) (p2.getDamage() * p2.getAttackMultiplier() * p1.getDamageMultiplier() * 0.5 *1.15));
                l.setText("weakened is broken");
                l2.setText(p2.getName() + " attacked");
                break;
            case "02":
                if (Math.random() < 0.75) {
                    p1.applyWeaken(p2.getLevel() + 1);
                    l2.setText(p1.getName() + " is weakened");
                } else {
                    l2.setText(p1.getName() + " is not weakened");
                }
                break;
            case "12":
                p2.setHealth(-(int) (p1.getDamage() * p1.getAttackMultiplier() * p2.getDamageMultiplier() * 0.5 *1.15));
                l.setText("weakened is broken");
                l2.setText(p1.getName() + " attacked");
                break;
            case "10":
                v = Math.random();
                if (p1 instanceof ShaoKahn & v < 0.15) {
                    p2.setHealth(-(int) (p1.getDamage() * p1.getAttackMultiplier() * p2.getDamageMultiplier() * 0.5));
                    l2.setText("Your block is broken");

                } else {
                    p1.setHealth(-(int) (p2.getDamage() * p2.getAttackMultiplier() * p1.getDamageMultiplier() * 0.5));
                    l2.setText(p2.getName() + " counterattacked");
                }
                break;
            case "11":
                p2.setHealth(-(int) (p1.getDamage() * p1.getAttackMultiplier() * p2.getDamageMultiplier()));
                l2.setText(p1.getName() + " attacked");
                break;
            case "00":
                v = Math.random();
                if (v <= 0.5) {
                    stun = 1;
                }
                l2.setText("Both defended themselves");
                break;
            case "01":
                l2.setText(p1.getName() + " didn't attacked");
                break;
            case "22":
                l.setText(p1.getName() + " didn't weaken");
                l2.setText(p2.getName() + " didn't weaken");
                break;
            case "-13":
                l.setText(p1.getName() + " was stunned");
                stun = 0;
                p2.regenerate();
                l2.setText(p2.getName() + " is regenerated");
                break;
            case "-12":
                l.setText(p1.getName() + " was stunned");
                stun = 0;
                if (Math.random() < 0.75) {
                    p1.applyWeaken(p2.getLevel() + 1);
                    l2.setText(p1.getName() + " is weakened");
                } else {
                    l2.setText(p1.getName() + " is not weakened");
                }
                break;
            case "-10":
                l.setText(p1.getName() + " was stunned");
                stun = 0;
                l2.setText(p2.getName() + " didn't attacked");
                break;
            case "-11":
                p1.setHealth(-(int) (p2.getDamage() * p2.getAttackMultiplier() * p1.getDamageMultiplier()));
                l.setText(p1.getName() + " was stunned");
                stun = 0;
                l2.setText(p2.getName() + " attacked");
                break;
            default:
                l2.setText("unknown result");
        }
        p1.weakenSpend();
    }

    public void Hit(Game game, Player human, Player enemy, int a, JLabel label,
            JLabel label2, JDialog dialog, JLabel label3, CharacterAction action,
            JProgressBar pr1, JProgressBar pr2, JDialog dialog1,
            JDialog dialog2, JFrame frame, ArrayList<Result> results,
            JLabel label4, JLabel label5, JLabel label6, JLabel label7,
            JLabel label8, Items[] items, JRadioButton rb, JDialog locationDialog,
                    JLabel locationLabel, JLabel weakenLabel,  JLabel weakenHumanLabel) {
        label7.setText("");
        human.setAttack(a);

        if (k < kind_attack.length - 1) {
            k++;
        } else {
            kind_attack = action.ChooseBehavior(enemy, action);
            k = 0;
        }
        enemy.setAttack(kind_attack[k]);
        if (enemy instanceof ShaoKahn && Math.random() < 0.3) {
           enemy.setAttack(3);
        }
        if (i % 2 == 1) {
            Move(human, enemy, label7, label8);
        } else {
            Move(enemy, human, label7, label8);
        }
        i++;
        if (enemy.isWeakened()) {
            weakenLabel.setText("Ослаблен");
        } else {
            weakenLabel.setText("");
        }
        if (human.isWeakened()) {
            weakenHumanLabel.setText("Ослаблен");
        } else {
            weakenHumanLabel.setText("");
        }
        change.RoundTexts(human, enemy, label, label2, i, label6);
        action.HP(human, pr1);
        action.HP(enemy, pr2);
        if (human.getHealth() <= 0 & items[2].getCount() > 0) {
            human.setNewHealth((int) (human.getMaxHealth() * 0.05));
            items[2].setCount(-1);
            action.HP(human, pr1);
            label2.setText(human.getHealth() + "/" + human.getMaxHealth());
            rb.setText(items[2].getName() + ", " + items[2].getCount() + " шт");
            label7.setText("Вы воскресли");
        }
        if (human.getHealth() <= 0 | enemy.getHealth() <= 0) {
            if (action.hasNotEnemy() && game.isLastLocation()) {
                EndFinalRound(((Human) human), action, results, dialog1, dialog2,
                        frame, label4, label5);
            } else {
                EndRound(human, enemy, dialog, label3, action, items, locationDialog, locationLabel);
            }
        }
    }

    public void EndRound(Player human, Player enemy, JDialog dialog, JLabel label,
            CharacterAction action, Items[] items, JDialog locationDialog, JLabel locationLabel) {

        if (human.getHealth() > 0) {
            ((Human) human).setWin();

            if (enemy instanceof ShaoKahn) {
                action.AddItems(38, 23, 8, items);
                action.AddPointsBoss(((Human) human), action.getEnemyes());
            } else {
                action.AddItems(25, 15, 5, items);
                action.AddPoints(((Human) human), action.getEnemyes());
            }
        }
        if (human.getHealth() > 0) {
            label.setText("You win");
            locationLabel.setText("You win");
        } else {
            label.setText(enemy.getName() + " win");
            locationLabel.setText(enemy.getName() + " win");
        }
        if (action.hasNotEnemy()) {
            locationDialog.setVisible(true);
            locationDialog.setBounds(300, 150, 700, 600);
        } else {
            dialog.setVisible(true);
            dialog.setBounds(300, 150, 700, 600);
        }

        i = 1;
        k = -1;
        kind_attack = ResetAttack();

    }

    public void EndFinalRound(Human human, CharacterAction action,
            ArrayList<Result> results, JDialog dialog1, JDialog dialog2, JFrame frame,
            JLabel label1, JLabel label2) {
        String text = "Победа не на вашей стороне";
        if (human.getHealth() > 0) {
            human.setWin();
            action.AddPoints(human, action.getEnemyes());
            text = "Победа на вашей стороне";
        }
        boolean top = false;
        if (results == null) {
            top = true;
        } else {
            int i = 0;
            for (int j = 0; j < results.size(); j++) {
                if (human.getPoints() < results.get(j).getPoints()) {
                    i++;
                }
            }
            if (i < 10) {
                top = true;
            }
        }
        if (top) {
            dialog1.setVisible(true);
            dialog1.setBounds(150, 150, 600, 500);
            label1.setText(text);
        } else {
            dialog2.setVisible(true);
            dialog2.setBounds(150, 150, 470, 360);
            label2.setText(text);
        }
        frame.dispose();
    }

    public int[] ResetAttack() {
        int a[] = {0};
        return a;
    }

    public Player NewRound(Player human, JLabel label, JProgressBar pr1,
            JProgressBar pr2, JLabel label2, JLabel text, JLabel label3, Game game) {
        CharacterAction action = game.action;
        if (action.hasNotEnemy()) {
            return null;
        }
        Player enemy1 = null;
        if (action.isLastEnemy()) {
            enemy1 = action.ChooseBoss(label, label2, text, label3, human.getLevel());
        } else {
            enemy1 = action.ChooseEnemy(label, label2, text, label3);
        }
        pr1.setMaximum(human.getMaxHealth());
        pr2.setMaximum(enemy1.getMaxHealth());
        human.setNewHealth(human.getMaxHealth());
        enemy1.setNewHealth(enemy1.getMaxHealth());
        action.HP(human, pr1);
        action.HP(enemy1, pr2);
        return enemy1;
    }

}
