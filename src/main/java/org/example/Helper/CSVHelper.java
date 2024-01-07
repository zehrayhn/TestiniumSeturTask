package org.example.Helper;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;

public class CSVHelper {
    private String filePath;

    public CSVHelper(String filePath) {
        this.filePath = filePath;
    }

    public String getCellValue(int row, int column) throws IOException {
        try (FileReader fileReader = new FileReader(filePath);
             CSVParser csvParser = CSVParser.parse(fileReader, CSVFormat.DEFAULT)) {
            int currentRow = 0;
            for (CSVRecord csvRecord : csvParser) {
                if (currentRow == row) {
                    if (column < csvRecord.size()) {
                        return csvRecord.get(column);
                    } else {
                        return null;
                    }
                }
                currentRow++;
            }
        }
        return null;
    }

}
