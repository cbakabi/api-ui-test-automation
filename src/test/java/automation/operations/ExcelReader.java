package automation.operations;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
  private static final Logger LOGGER = LoggerFactory.getLogger(ExcelReader.class);

  public static String readExcelValue(String idValue) {
    String cell = null;
    try {
      FileInputStream fs = new FileInputStream("src/test/resources/automation/Credentials.xlsx");
      XSSFWorkbook workbook = new XSSFWorkbook(fs);
      XSSFSheet sheet = workbook.getSheetAt(0);
      Row row = sheet.getRow(1);
      if (idValue.equals("login-username")) {
        cell = row.getCell(0).getStringCellValue();
        LOGGER.info("Username retrieved successfully");
      } else {
        cell = row.getCell(1).getStringCellValue();
        LOGGER.info("Password retrieved successfully");
      }
    } catch (IOException e) {
      LOGGER.error(e.getMessage());
    }
    return cell;
  }
}
