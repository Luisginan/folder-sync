package com.aplikasishop.tools;

import com.aplikasishop.tools.interfaces.FileChangeHandler;
import com.aplikasishop.tools.interfaces.Watcher;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static java.lang.Thread.sleep;

public class AsFolderWatcher implements Watcher {
    @Override
    public void startWatch(String path, FileChangeHandler fileChangeHandler) throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path folderPath = Paths.get(path);
        folderPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                System.out.println(
                        "Event kind:" + event.kind()
                                + ". File affected: " + event.context() + ".");
                System.out.print("Preparation data...");
                sleep(30000);
                System.out.println("DONE");
                fileChangeHandler.onFileCreated(new File(path + "/" + event.context()));
            }
            key.reset();
        }
    }
}
