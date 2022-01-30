package br.com.blecaute.announcement.task;

import br.com.blecaute.announcement.Main;
import br.com.blecaute.announcement.dao.AnnouncementDao;
import br.com.blecaute.announcement.model.Announcement;
import br.com.blecaute.announcement.util.MathUtil;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.scheduler.TaskScheduler;

import java.util.concurrent.TimeUnit;

public class AnnouncementTask implements Runnable {

    private final AnnouncementDao announcementDao = AnnouncementDao.getInstance();

    private final int gcd;
    private int index = 0;

    /**
     * Start interval at the longest interval possible
     * to save resources and run on only one thread
     */
    public AnnouncementTask() {
        int[] intervals = announcementDao.getValues().stream()
                .mapToInt(Announcement::getInterval)
                .toArray();

        this.gcd = MathUtil.getGCD(intervals);

        TaskScheduler scheduler = ProxyServer.getInstance().getScheduler();
        scheduler.schedule(Main.getInstance(), this, gcd, gcd, TimeUnit.SECONDS);
    }

    @Override
    public void run() {

        try {
            announcementDao.getValues().stream()
                    .filter(announcement -> (announcement.getInterval() / gcd) % index == 0)
                    .forEach(announcement -> {
                        BaseComponent[] components = announcement.getNextComponent();
                        announcement.getServerPlayers().forEach(player ->
                                player.sendMessage(ChatMessageType.CHAT, components));
                    });

            index++;

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
