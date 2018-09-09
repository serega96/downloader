package com.serega96.service;

import java.io.IOException;

public interface ReaderService {
    void readFile(String name, String saveUrl) throws IOException;
}
