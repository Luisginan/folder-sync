package com.aplikasishop.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Param {
    public static Param getValueParam(String[] args) throws Exception {
        var valueParam = new Param();
        //get source and destination if have prefix -s and -d
        for (String arg : args) {
            if (arg.startsWith("-s") || arg.startsWith("--source")) {
                valueParam.source = arg.split("=")[1];

            } else if (arg.startsWith("-d") || arg.startsWith("--destination")) {
                valueParam.destination.add(arg.split("=")[1]);
            }
        }

        printParam(valueParam);
        validateParam(valueParam);
        return valueParam;
    }

    private static void validateParam(Param valueParam) throws Exception {
        if (valueParam.source.isEmpty()) {
            throw new Exception("Source is empty");
        }

        //check if folder source is not exist
        if (!new File(valueParam.source).exists()) {
            throw new Exception("Source is not exist -> " + valueParam.source);
        }
        //check if destination is not empty
        if (valueParam.destination.isEmpty()) {
            throw new Exception("Destination is empty -> " + valueParam.destination);
        }

        //check if folder destination is not exist
        for (String dest : valueParam.destination) {
            if (!new File(dest).exists()) {
                throw new Exception("Destination is not exist -> " + dest);
            }
        }
    }

    private static void printParam(Param valueParam) {
        Logger.logYellow("Parameter :");
        Logger.logBlue("Source : " + valueParam.source);
        Logger.logBlue("Destination : ");
        for (int i = 0; i < valueParam.destination.size(); i++) {
            Logger.logBlue(i + 1 + ". " + valueParam.destination.get(i));
        }
    }
    public final List<String> destination = new ArrayList<>();
    public String source = "";
}
