
package mortalkombat;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;

/**
 * Класс для обновления текста на элементах интерфейса во время игры.
 * Используется для отображения состояния персонажей, предметов и сообщений на экране.
 *
 * @author Мария
 */
public class ChangeTexts {
    
    /**
     * Устанавливает текстовые значения для начала нового раунда.
     *
     * @param human   игрок
     * @param enemy   враг
     * @param pr1     прогрессбар игрока
     * @param pr2     прогрессбар врага
     * @param label   метка с очками игрока
     * @param label2  метка с опытом игрока
     * @param label3  уровень игрока
     * @param label4  уровень врага
     * @param label5  здоровье игрока
     * @param label6  здоровье врага
     * @param label7  урон игрока
     * @param label8  чей сейчас ход
     * @param label9  текстовое сообщение
     * @param i       номер хода
     * @param items   массив предметов
     * @param rb1     кнопка выбора первого предмета
     * @param rb2     кнопка выбора второго предмета
     * @param rb3     кнопка выбора третьего предмета
     */
    public void NewRoundTexts(Player human, Player enemy, JProgressBar pr1,
            JProgressBar pr2, JLabel label, JLabel label2, JLabel label3,
            JLabel label4, JLabel label5, JLabel label6, JLabel label7, JLabel label8, JLabel label9,
            int i, Items[] items, JRadioButton rb1, JRadioButton rb2, JRadioButton rb3) {
        label.setText(Integer.toString(((Human) human).getPoints()));
        label2.setText(Integer.toString(((Human) human).getExperience()) + "/" + ((Human) human).getNextExperience());
        label3.setText(Integer.toString(human.getLevel()) + " level");
        label4.setText(Integer.toString(enemy.getLevel()) + " level");
        label5.setText(Integer.toString(human.getMaxHealth()) + "/" + Integer.toString(human.getMaxHealth()));
        label6.setText(Integer.toString(enemy.getMaxHealth()) + "/" + Integer.toString(enemy.getMaxHealth()));
        label7.setText(Integer.toString(human.getDamage()));
        if (i % 2 == 1) {
            label8.setText("Your turn");
        }
        else{
            label8.setText(enemy.getName()+"'s turn");
        }
        /*rb1.setText(items[0].getName()+", "+items[0].getCount()+" шт");
        rb2.setText(items[1].getName()+", "+items[1].getCount()+" шт");
        rb3.setText(items[2].getName()+", "+items[2].getCount()+" шт");*/
        BagText(items, rb1, rb2, rb3);
        label9.setText("");
    }

    /**
     * Обновляет тексты во время раунда (здоровье игроков и очередность хода).
     *
     * @param human  игрок
     * @param enemy  враг
     * @param label  метка здоровья врага
     * @param label2 метка здоровья игрока
     * @param i      номер хода
     * @param label3 метка с информацией о том, чей ход
     */
    public void RoundTexts(Player human, Player enemy, JLabel label, JLabel label2, int i, JLabel label3) {
        if (enemy.getHealth() >= 0) {
            label.setText(Integer.toString(enemy.getHealth()) + "/" + Integer.toString(enemy.getMaxHealth()));
        } else {
            label.setText("0/" + Integer.toString(enemy.getMaxHealth()));
        }
        if (human.getHealth() >= 0) {
            label2.setText(Integer.toString(human.getHealth()) + "/" + Integer.toString(human.getMaxHealth()));
        } else {
            label2.setText("0/" + Integer.toString(human.getMaxHealth()));
        }
        if (i % 2 == 1) {
            label3.setText("Your turn");
        }
        else{
            label3.setText(enemy.getName()+"'s turn");
        }
    }
    
    /**
     * Устанавливает текст по завершении игры (победа или поражение).
     *
     * @param human игрок
     * @param label метка для вывода результата
     */    
    public void EndGameText(Human human, JLabel label){
        if(human.getWin()==12){
            label.setText("Победа на вашей стороне");
        }
        else {
            label.setText("Победа не на вашей стороне");
        }
    }
    
    /**
     * Обновляет текст на кнопках выбора предметов в инвентаре.
     *
     * @param items массив предметов
     * @param rb1   кнопка первого предмета
     * @param rb2   кнопка второго предмета
     * @param rb3   кнопка третьего предмета
     */    
    public void BagText( Items[] items, JRadioButton rb1, JRadioButton rb2, JRadioButton rb3){
        rb1.setText(items[0].getName()+", "+items[0].getCount()+" шт");
        rb2.setText(items[1].getName()+", "+items[1].getCount()+" шт");
        rb3.setText(items[2].getName()+", "+items[2].getCount()+" шт");
    }

}
