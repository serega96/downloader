package com.serega96.service.impl;

import com.serega96.service.ReaderService;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class XmlReaderImpl implements ReaderService {

    final static Logger logger = Logger.getLogger(CsvReaderImpl.class.getName());

    @Override
    public void readFile(String name, String saveUrl) {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(name);
        Document document = null;

        try {

            document = (Document) builder.build(xmlFile);

            Element rootNode = document.getRootElement();
            List list = rootNode.getChildren("object");

            for (int i = 0; i < list.size(); i++) {

                Element node = (Element) list.get(i);

                DownloadServiceImpl image = new DownloadServiceImpl();
                image.download(node.getChildText("url"), saveUrl + node.getChildText("name"));

            }
        } catch (FileNotFoundException e) {
            logger.warning("Wrong path to file");
            e.printStackTrace();
        } catch (IOException | JDOMException io) {
            logger.warning(io.getMessage());
        }

    }
}
