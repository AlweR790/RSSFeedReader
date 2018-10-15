package cz.uhk.fim.RSSFeedReader.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils
    {
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
    }
