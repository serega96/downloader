package com.serega96.service.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.serega96.service.ReaderService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class JsonReaderImpl implements ReaderService {

    final static Logger logger = Logger.getLogger(JsonReaderImpl.class.getName());

    @Override
    public void readFile(String name, String saveUrl) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(name));
        } catch (IOException e) {
            logger.warning("Wrong path to file");
            e.printStackTrace();
        }
        String s = "";
        for (String line : lines) {
            s = s + line;
        }
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject) jsonParser.parse(s);

        for (Map.Entry<String, JsonElement> entry : jo.entrySet()) {
            JsonObject object = entry.getValue().getAsJsonObject();
            DownloadServiceImpl image = new DownloadServiceImpl();
            image.download(object.get("url").getAsString(), saveUrl + object.get("name").getAsString());
        }
    }
}
