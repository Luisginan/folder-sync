package com.aplikasishop.tools.interfaces;

import java.io.IOException;

public interface Watcher {
    void startWatch(String path, FileChangeHandler fileChangeHandler) throws IOException, InterruptedException;
}
