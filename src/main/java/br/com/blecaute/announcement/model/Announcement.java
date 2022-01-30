package br.com.blecaute.announcement.model;

import br.com.blecaute.announcement.exception.InvalidAnnouncementException;
import br.com.blecaute.announcement.model.file.GsonFile;
import com.google.gson.JsonObject;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.chat.ComponentSerializer;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@Data
public class Announcement {

    private static final BaseComponent[] EMPTY_COMPONENTS = {};

    private final String file;
    private final String serverName;
    private final int interval;

    @Getter(AccessLevel.NONE)
    private final Queue<BaseComponent[]> queue = new LinkedList<>();

    public Announcement(@NotNull GsonFile file) {
        JsonObject object = file.getObject();

        this.file = file.getFile().getName();
        this.serverName = object.getAsJsonPrimitive("server").getAsString();
        this.interval = object.getAsJsonPrimitive("interval").getAsInt();

        if (this.interval <= 0) {
            throw new InvalidAnnouncementException("The interval of " + file + " must be positive.");
        }

        try {
            object.getAsJsonArray("messages").forEach(element -> {
                BaseComponent[] components = ComponentSerializer.parse(element.toString());
                queue.add(components);
            });

        } catch (Exception exception) {
            throw new InvalidAnnouncementException("Invalid announcement messages for " + file, exception);
        }

    }

    public Optional<ServerInfo> getServer() {
        return Optional.ofNullable(ProxyServer.getInstance().getServerInfo(this.serverName));
    }

    @NotNull
    public Collection<ProxiedPlayer> getServerPlayers() {
        return getServer()
                .map(ServerInfo::getPlayers)
                .orElse(Collections.emptyList());
    }

    @NotNull
    public BaseComponent[] getNextComponent() {
        BaseComponent[] components = queue.poll();
        if (components != null) {
            queue.add(components);
            return components;
        }

        return EMPTY_COMPONENTS;
    }

}
