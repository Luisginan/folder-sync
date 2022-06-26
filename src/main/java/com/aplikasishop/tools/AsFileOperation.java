package com.aplikasishop.tools;

import com.aplikasishop.tools.interfaces.FileOperation;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class AsFileOperation implements FileOperation {
    @Override
    public void CopyFile(String path, String destination) throws IOException {
        var file = new File(path);
        Path copied = Paths.get(destination);
        Path originalPath = file.toPath();
        System.out.print("Copy file  " + copied + " to " + destination + " ... ");
        Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("DONE");
    }
}
