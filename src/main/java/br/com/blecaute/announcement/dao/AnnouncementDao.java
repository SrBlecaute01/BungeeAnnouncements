package br.com.blecaute.announcement.dao;

import br.com.blecaute.announcement.model.Announcement;
import lombok.Getter;

import java.util.HashMap;

public class AnnouncementDao extends Dao<String, Announcement, HashMap<?,?>>{

    @Getter private static final AnnouncementDao instance = new AnnouncementDao();

    @Override
    public void add(Announcement value) {
        add(value.getFile(), value);
    }

    @Override
    public void remove(String key) {
        delete(key);
    }
}
