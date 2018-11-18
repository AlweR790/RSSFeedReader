package cz.uhk.fim.RSSFeedReader.utils;

import cz.uhk.fim.RSSFeedReader.model.RSSSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtils
    {
        private static String CONFIG_FILE = "config.csv";

        public static String loadStringFromFile(String path)
            {
                try
                    {
                        return new String(Files.readAllBytes(Paths.get(path)));
                    }
                catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                return "";
            }
        public static void stringToFile(String path, byte[] data)
            {
                Path filepath = Paths.get(path);
                try
                    {
                        Files.write(filepath, data);
                    }
                catch (IOException e)
                    {
                        e.printStackTrace();
                    }
            }
        public static void saveSources(List<RSSSource> sources)
            {
                StringBuilder sb =  new StringBuilder();
                for (RSSSource src :sources)
                    {
                        sb.append(String.format("%s;%s\n", src.getName(), src.getSource()));
                    }
                stringToFile(CONFIG_FILE, sb.toString().getBytes(StandardCharsets.UTF_8));
            }
        public static List<RSSSource> loadSources()
            {
                List<RSSSource> sources = new ArrayList<>();

                new BufferedReader(new StringReader(loadStringFromFile(CONFIG_FILE))).lines().forEach(line -> {
                    String[] parts = line.split(";");
                    sources.add(new RSSSource(parts[0], parts[1]));
                });

                return sources;
            }

    }
