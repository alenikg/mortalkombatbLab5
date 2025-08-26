
package mortalkombat;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Основной класс игры.
 * Управляет локациями, игроком, врагами и таблицей рекордов.
 *
 * @author Мария
 */
public class Game {
    /** Количество локаций. */
    int locationCount = 0;
    
    /** Индекс текущей локации. */
    int currLocationIndex = 0;
    
    /** Логика действий персонажей. */
    CharacterAction action = new CharacterAction();
    
    /** Класс для работы с текстами. */
    ChangeTexts change = new ChangeTexts();
    
    /** Класс боя. */
    Fight fight = new Fight();
    
    /** Игрок. */
    Human human = null;
    
    /** Список результатов. */
    private ArrayList<Result> results = new ArrayList<>();

    /**
     * Создаёт нового врага для локации.
     *
     * @param L1 метка изображения
     * @param L2 метка имени
     * @param L3 метка урона
     * @param L4 метка здоровья
     * @param pr2 прогрессбар врага
     * @return новый враг
     */
    public Player NewEnemy(JLabel L1, JLabel L2,
                           JLabel L3, JLabel L4, JProgressBar pr2) {
        int enemyCount = this.human != null ? getRandomEnemiesCount(this.human.getLevel()) : 1;
        System.out.println("Количество врагов в локации: " + enemyCount);
        action.setEnemyes(enemyCount);
        Player enemy = action.ChooseEnemy(L1, L2, L3, L4);
        action.HP(enemy, pr2);
        pr2.setMaximum(enemy.getMaxHealth());
        return enemy;
    }

    /** 
     * Увеличивает индекс текущей локации. 
     */
    public void incrementCurrLocation() {
        currLocationIndex ++;
    }

    /** 
     * Проверяет, последняя ли локация.
     */
    public Boolean isLastLocation() {
        return currLocationIndex == locationCount - 1;
    }

    /** 
     * Проверяет, закончились ли локации. 
     */
    public Boolean hasNotLocation() {
        return currLocationIndex > locationCount - 1;
    }

    /** 
     * Возвращает файл с результатами. 
     */
    private File getResultsFile() {
        URL url = getClass().getResource("/Results.xlsx");
        if (url == null) {
            System.err.println("Error: Results.xlsx not found in classpath!");
            return null;
        }

        System.out.println(url.getPath());
        return new File(url.getPath());
    }

    /** 
     * Генерирует случайное количество врагов для локации. 
     */
    private int getRandomEnemiesCount(int level) {
        int min = level + 2;
        int max = level + 5;
        return new Random().nextInt(max - min + 1) + min;
    }

    /** 
     * Устанавливает количество локаций. 
     */
    public void setLocationCount(int locationCount) {
        this.locationCount = locationCount;
    }

    /**
     * Создаёт нового игрока.
     *
     * @param pr1 прогрессбар игрока
     * @return новый игрок
     */    
    public Human NewHuman(JProgressBar pr1) {
        Human human = new Human(0, 80, 16, 1);
        this.human = human;
        action.HP(human, pr1);
        pr1.setMaximum(human.getMaxHealth());
        return human;
    }

    /**
     * Завершает игру, добавляя результат в таблицу.
     *
     * @param human игрок
     * @param text поле с именем
     * @param table таблица
     */
    public void EndGameTop(Human human, JTextField text, JTable table) throws IOException {
        results.add(new Result(text.getText(), human.getPoints()));
        results.sort(Comparator.comparing(Result::getPoints).reversed());
        WriteToTable(table);
        WriteToExcel();
    }

    /** 
     * Записывает таблицу результатов в Excel. 
     */
    public void WriteToExcel() throws IOException {
        File file = this.getResultsFile();
        if (file == null) {
            return;
        }
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet("Результаты ТОП 10");
        XSSFRow r = sheet.createRow(0);
        r.createCell(0).setCellValue("№");
        r.createCell(1).setCellValue("Имя");
        r.createCell(2).setCellValue("Количество баллов");
        for (int i = 0; i < results.size(); i++) {
            if (i < 10) {
                XSSFRow r2 = sheet.createRow(i + 1);
                r2.createCell(0).setCellValue(i + 1);
                r2.createCell(1).setCellValue(results.get(i).getName());
                r2.createCell(2).setCellValue(results.get(i).getPoints());
            }
        }
        book.write(new FileOutputStream(file));
        book.close();
    }

    /** 
     * Возвращает список результатов. 
     */
    public ArrayList<Result> getResults() {
        return this.results;
    }

    /** 
     * Читает таблицу результатов из Excel. 
     */
    public void ReadFromExcel() {
        File file = this.getResultsFile();
        if (file == null) {
            return;
        }
        try (
                FileInputStream fileInputStream = new FileInputStream(file);
                XSSFWorkbook book = new XSSFWorkbook(fileInputStream)
        ) {
            XSSFSheet sh = book.getSheetAt(0);
            for (int i = 1; i <= sh.getLastRowNum(); i++) {
                results.add(new Result(sh.getRow(i).getCell(1).getStringCellValue(), (int) sh.getRow(i).getCell(2).getNumericCellValue()));
            }
        } catch (IOException exception) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, exception);
        }
    }

    /** 
     * Записывает результаты в таблицу. 
     */
    public void WriteToTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < results.size(); i++) {
            if (i < 10) {
                model.setValueAt(results.get(i).getName(), i, 0);
                model.setValueAt(results.get(i).getPoints(), i, 1);
            }
        }
    }
}
