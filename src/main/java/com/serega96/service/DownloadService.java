package com.serega96.service;

import java.io.IOException;

public interface DownloadService {
    void download(String urlStr, String file) throws IOException;
}
