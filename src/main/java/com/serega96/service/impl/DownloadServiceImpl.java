package com.serega96.service.impl;

import com.serega96.service.DownloadService;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Logger;

public class DownloadServiceImpl implements DownloadService {

    final static Logger logger = Logger.getLogger(DownloadServiceImpl.class.getName());

    @Override
    public void download(String urlStr, String file) {

        URL url = null;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            logger.warning("Trouble in download first");
            e.printStackTrace();
        }

        ReadableByteChannel readableByteChannel = null;
        try {
            readableByteChannel = Channels.newChannel(url.openStream());
        } catch (IOException e) {
            logger.warning("Wrong Url");
            //e.printStackTrace();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            logger.warning("Access is denied");
            //e.printStackTrace();
        }
        try {
            fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            logger.warning("Trouble in download fourth");
            e.printStackTrace();
        }
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            logger.warning("Trouble in download fifth");
            e.printStackTrace();
        }
        try {
            readableByteChannel.close();
        } catch (IOException e) {
            logger.warning("Trouble in download sixth");
            e.printStackTrace();
        }
        logger.info("File save in " + file);
    }
}
