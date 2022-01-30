package br.com.blecaute.announcement.util;

import br.com.blecaute.announcement.Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtil {

    private FileUtil() {}

    public static void saveResource(String name) {
        try {
            File file = new File(Main.getInstance().getDataFolder(), name);
            if (!file.exists() && (file.getParentFile().exists() || file.getParentFile().mkdirs())) {
                Files.copy(Main.getInstance().getResourceAsStream(name), file.toPath());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
