
package mortalkombat;

import java.io.*;
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

public class Game {
    int locationCount = 0;
    int currLocationIndex = 0;
    CharacterAction action = new CharacterAction();
    ChangeTexts change = new ChangeTexts();
    Fight fight = new Fight();
    Human human = null;
    private ArrayList<Result> results = new ArrayList<>();

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

    public void incrementCurrLocation() {
        currLocationIndex ++;
    }

    public Boolean isLastLocation() {
        return currLocationIndex == locationCount - 1;
    }

    public Boolean hasNotLocation() {
        return currLocationIndex > locationCount - 1;
    }

    private File getResultsFile() {
        String userHomePath = System.getProperty("user.home");
        Path path = Paths.get(userHomePath, "Desktop", "Results.xlsx");
        return path.toFile();
    }

    private int getRandomEnemiesCount(int level) {
        int min = level + 2;
        int max = level + 5;
        return new Random().nextInt(max - min + 1) + min;
    }

    public void setLocationCount(int locationCount) {
        this.locationCount = locationCount;
    }

    public Human NewHuman(JProgressBar pr1) {
        Human human = new Human(0, 80, 16, 1);
        this.human = human;
        action.HP(human, pr1);
        pr1.setMaximum(human.getMaxHealth());
        return human;
    }

    public void EndGameTop(Human human, JTextField text, JTable table) throws IOException {
        results.add(new Result(text.getText(), human.getPoints()));
        results.sort(Comparator.comparing(Result::getPoints).reversed());
        WriteToTable(table);
        WriteToExcel();
    }

    public void WriteToExcel() throws IOException {
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
        book.write(new FileOutputStream(this.getResultsFile()));
        book.close();
    }

    public ArrayList<Result> getResults() {
        return this.results;
    }

    public void ReadFromExcel() {
        try (
                FileInputStream fileInputStream = new FileInputStream(this.getResultsFile());
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
