package com.serega96.service.impl;

import com.opencsv.CSVReader;
import com.serega96.service.ReaderService;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

public class CsvReaderImpl implements ReaderService {

    final static Logger logger = Logger.getLogger(CsvReaderImpl.class.getName());

    @Override
    public void readFile(String name, String saveUrl) {
        String csvFile = name;
        char cvsSplitBy = '\n';
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile), cvsSplitBy);
        } catch (FileNotFoundException e) {
            logger.warning("Wrong path to file");
            e.printStackTrace();
        }
        String[] stringOfData;
        int i = 0;
        try {
            while ((stringOfData = reader.readNext()) != null) {
                String address = stringOfData[i];
                String[] addressParts = address.split(" ");
                DownloadServiceImpl s = new DownloadServiceImpl();
                s.download(addressParts[0], saveUrl + addressParts[1]);
            }
        } catch (IOException e) {
            // logger.warn("Trouble in download second");
            e.printStackTrace();
        }
    }
}
