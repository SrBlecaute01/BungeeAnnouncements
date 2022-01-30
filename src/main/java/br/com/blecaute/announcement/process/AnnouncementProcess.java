package br.com.blecaute.announcement.process;

import br.com.blecaute.announcement.Main;
import br.com.blecaute.announcement.dao.AnnouncementDao;
import br.com.blecaute.announcement.model.Announcement;
import br.com.blecaute.announcement.model.file.GsonFile;
import br.com.blecaute.announcement.util.FileUtil;

import java.io.File;

public class AnnouncementProcess {

    public static void process() {
        Main plugin = Main.getInstance();

        File directory = new File(plugin.getDataFolder(), "servers");
        if (!directory.exists()) {
            FileUtil.saveResource("servers" + File.separator +  "lobby.json");
        }

        File[] files = directory.listFiles((dir, name) -> name.endsWith(".json"));
        if (files == null) return;

        for (File target : files) {
            Announcement announcement = new Announcement(new GsonFile(target));
            AnnouncementDao.getInstance().add(announcement);
        }

        plugin.getLogger().info("Loaded " + AnnouncementDao.getInstance().size() + " files.");
    }

}
