package com.aplikasishop.tools;

import com.aplikasishop.tools.interfaces.FileOperation;
import com.aplikasishop.tools.interfaces.Watcher;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            var valueParam = getValueParam(args);

            printParam(valueParam);

            var watcher = getWatcher();
            var fileOperation = getFileOperation();
            watcher.startWatch(valueParam.source, file -> valueParam.destination.forEach(dest -> {
                onFolderChange(fileOperation, file, dest);
            }));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static FileOperation getFileOperation() {
        return new AsFileOperation();
    }

    private static Watcher getWatcher() {
        return new AsFolderWatcher();
    }

    private static void printParam(Param valueParam) {
        System.out.println("Source: " + valueParam.source);
        valueParam.destination.forEach(dest -> System.out.println("Destination: " + dest));
    }

    private static void onFolderChange(FileOperation fileOperation, File file, String dest) {
        try {
            while (true) {
                try {
                    fileOperation.CopyFile(file.getAbsolutePath(), dest + "/" + file.getName());
                    break;
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
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