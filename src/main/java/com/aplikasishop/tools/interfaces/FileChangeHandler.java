package com.aplikasishop.tools.interfaces;

import java.io.File;
import java.io.IOException;

public interface FileChangeHandler {
    void onFileCreated(File file) throws IOException;
}
