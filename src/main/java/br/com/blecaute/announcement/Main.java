package br.com.blecaute.announcement;

import br.com.blecaute.announcement.process.AnnouncementProcess;
import br.com.blecaute.announcement.task.AnnouncementTask;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {

    @Getter private static Main instance;

    @Override
    public void onEnable() {
        instance = this;

        loadProcess();
        loadTasks();
    }

    private void loadProcess() {
        AnnouncementProcess.process();
    }

    private void loadTasks() {
        new AnnouncementTask();
    }


}
