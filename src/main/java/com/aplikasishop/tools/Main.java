package com.aplikasishop.tools;

import com.aplikasishop.tools.interfaces.FileOperation;
import com.aplikasishop.tools.interfaces.Watcher;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static final String INTERRUPTED = "Interrupted!";

    public static void main(String[] args) {
        try {
            var valueParam = getValueParam(args);

            printParam(valueParam);

            var watcher = getWatcher();
            var fileOperation = getFileOperation();
            watcher.startWatch(valueParam.source, file -> valueParam.destination.forEach(dest -> onFolderChange(fileOperation, file, dest)));
        } catch (IOException | InterruptedException e) {
            logger.log(Level.SEVERE, INTERRUPTED, e);
            Thread.currentThread().interrupt();
        }
    }

    private static FileOperation getFileOperation() {
        return new AsFileOperation();
    }

    private static Watcher getWatcher() {
        return new AsFolderWatcher();
    }

    private static void printParam(Param valueParam) {
       logger.log(Level.INFO,"Source: {0}",valueParam.source);
        valueParam.destination.forEach(dest -> logger.log(Level.INFO,"Destination: {0}", dest));
    }

    private static void onFolderChange(FileOperation fileOperation, File file, String dest) {
        try {
            while (true) {
                if (executeCopyFile(fileOperation, file, dest)) break;
            }
        } catch (InterruptedException exception) {
            logger.log(Level.SEVERE, INTERRUPTED, exception);
            Thread.currentThread().interrupt();
        }
    }

    private static boolean executeCopyFile(FileOperation fileOperation, File file, String dest) throws InterruptedException {
        try {
            fileOperation.CopyFile(file.getAbsolutePath(), dest + "/" + file.getName());
            return true;
        } catch (Exception e) {
            sleep(30000);
            logger.log(Level.SEVERE, INTERRUPTED, e);
        }
        return false;
    }

    private static Param getValueParam(String[] args) {
        var ref = new Param();
        Arrays.stream(args).forEach(arg -> {
            if (arg.startsWith("--source=")) {
                var value = arg.trim().split("=");
                var s = value[1];
                ref.source = s.trim();
            }

            if (arg.startsWith("--destination=")) {
                var value = arg.trim().split("=");
                var s = value[1];
                ref.destination.add(s);
            }
        });

        return ref;
    }
}