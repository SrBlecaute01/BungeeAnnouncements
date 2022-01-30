package br.com.blecaute.announcement.model.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Getter;

import java.io.*;

public class GsonFile {

    @Getter private final File file;
    @Getter private JsonObject object;

    private final Gson gson = new GsonBuilder()
            .disableHtmlEscaping()
            .enableComplexMapKeySerialization()
            .setPrettyPrinting()
            .create();

    public GsonFile(File file) {
        this.file = file;

        try (FileReader reader = new FileReader(this.file)) {
            object = gson.fromJson(reader, JsonElement.class).getAsJsonObject();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void save() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(this.file))) {
            writer.write(gson.toJson(object));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        try (FileReader reader = new FileReader(this.file)) {
            object = gson.fromJson(reader, JsonElement.class).getAsJsonObject();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void delete() {
        this.file.delete();
    }
}