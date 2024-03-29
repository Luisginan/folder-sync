package com.aplikasishop.tools;

import com.aplikasishop.tools.interfaces.FileOperation;
import com.aplikasishop.tools.interfaces.Watcher;

import java.io.File;
import java.util.Arrays;

import static java.lang.Thread.sleep;

public class Main {


    public static void main(String[] args) {
        try {
            Logger.logGreen("Starting application...");
            var valueParam = Param.getValueParam(args);
            Logger.logGreen("Starting watcher...");

            var watcher = getWatcher();
            var fileOperation = getFileOperation();
            watcher.startWatch(valueParam.source, file -> valueParam.destination.forEach(dest -> onFolderChange(fileOperation, file, dest)));
        } catch (Exception e) {
            Logger.logRed(e);
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
        Logger.logYellow("Source: " + valueParam.source);
        valueParam.destination.forEach(dest -> Logger.logBlue("Destination: " + dest));
    }



    private static void onFolderChange(FileOperation fileOperation, File file, String dest) {
        try {
            while (true) {
                if (executeCopyFile(fileOperation, file, dest)) break;
            }
        } catch (InterruptedException exception) {
            Logger.logRed(exception);
            Thread.currentThread().interrupt();
        }
    }

    private static boolean executeCopyFile(FileOperation fileOperation, File file, String dest) throws InterruptedException {
        try {
            fileOperation.CopyFile(file.getAbsolutePath(), dest + "/" + file.getName());
            return true;
        } catch (Exception e) {
            sleep(30000);
            Logger.logRed(e);
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