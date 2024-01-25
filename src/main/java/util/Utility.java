package util;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class Utility {
    public static int generateRandomInt(int max) {
        Random random = new Random();
        return random.nextInt(max) + 1;
    }
    public static List<Integer> generateNonRepeatingRandomNumbers(int max, int count) {
        if (count > max) {
            throw new IllegalArgumentException("Cannot generate more numbers than the range (1-6).");
        }
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= max; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers.subList(0, count);
    }
    public static int[] generateNumbers(int start, int end) {
        int[] numbers = new int[end - start + 1];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = start + i;
        }
        return numbers;
    }
    public static float stringToFloat(String str) {
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            // Handle the exception
            return -1f; // or throw your own exception
        }
    }
    // TODO: Capture Screenshot
    public static void captureScreenshot(WebDriver driver, String screenshotName) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        Date currntDate=new Date();
        screenshotName=screenshotName+" "+currntDate.toString().replace(" ","-").replace(":","-");
        try {
            FileHandler.copy(takesScreenshot.getScreenshotAs(OutputType.FILE), new File(System.getProperty("user.dir")
                    + "/src/test/resources/Screenshots/"+ screenshotName + ".png"));
        } catch (WebDriverException | IOException e) {
            e.printStackTrace();
        }
    }
    public static String getSingleJsonData(String jsonFilePath,String jsonField) throws IOException, ParseException, org.json.simple.parser.ParseException {
        JSONParser jsonParser = new JSONParser();

        FileReader fileReader = new FileReader(jsonFilePath);
        Object obj = jsonParser.parse(fileReader);

        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject.get(jsonField).toString();
    }
    public static String getExcelData(int RowNum, int ColNum, String SheetName) {
        XSSFWorkbook workBook;
        XSSFSheet sheet;
        String projectPath = System.getProperty("user.dir");
        String cellData = null;
        try {
            workBook = new XSSFWorkbook(projectPath + "/src/test/resources/data_driven/credentials.xlsx");
            sheet = workBook.getSheet(SheetName);
            cellData = sheet.getRow(RowNum).getCell(ColNum).getStringCellValue();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
        return cellData;
    }
}

