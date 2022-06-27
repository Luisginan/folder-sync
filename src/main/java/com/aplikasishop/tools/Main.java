package com.aplikasishop.tools;

import com.aplikasishop.tools.interfaces.FileOperation;
import com.aplikasishop.tools.interfaces.Watcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        var ref = new Object() {
            String source = "";
            final List<String> destination = new ArrayList<>();
        };

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

        System.out.println("Source: " + ref.source);
        ref.destination.forEach(dest -> System.out.println("Destination: " + dest));

        Watcher watcher = new AsFolderWatcher();
        FileOperation fileOperation = new AsFileOperation();
        watcher.startWatch(ref.source, file -> ref.destination.forEach(dest -> {
            try {
                fileOperation.CopyFile(file.getAbsolutePath(),dest + "/" + file.getName() );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
    }
}