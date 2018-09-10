package com.serega96.downloader;

import com.serega96.service.impl.CsvReaderImpl;
import com.serega96.service.impl.DownloadServiceImpl;
import com.serega96.service.impl.JsonReaderImpl;
import com.serega96.service.impl.XmlReaderImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class DownloaderApplication {

    final static Logger logger = Logger.getLogger(DownloaderApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(DownloaderApplication.class, args);
        logger.info("Welcome in Downloader, wait few seconds I'm working");
        String url = "";
        String saveUrl = "";
        String name = "";
        String nameOfFile = "";
        String buf;
        for (int i = 0; i < args.length; i++) {
            buf = args[i];

            switch (buf) {
                case "-l":
                    url = args[i + 1];
                    i++;
                    break;
                case "-p":
                    saveUrl = args[i + 1];
                    i++;
                    break;
                case "-n":
                    name = args[i + 1];
                    i++;
                    break;
                case "-f":
                    nameOfFile = args[i + 1];
                    i++;
                    break;
            }
        }

        if (!name.equals("")) {
            DownloadServiceImpl dw = new DownloadServiceImpl();

            dw.download(url, saveUrl + name);
        }

        if (!nameOfFile.equals("")) {
            if (nameOfFile.contains(".csv")) {
                CsvReaderImpl csv = new CsvReaderImpl();
                csv.readFile(nameOfFile, saveUrl);
            }

            if (nameOfFile.contains(".json")) {
                JsonReaderImpl json = new JsonReaderImpl();
                json.readFile(nameOfFile, saveUrl);
            }

            if (nameOfFile.contains(".xml")) {
                XmlReaderImpl xml = new XmlReaderImpl();
                xml.readFile(nameOfFile, saveUrl);
            }
        }
        logger.info("The program was completed successfully");
    }
}
