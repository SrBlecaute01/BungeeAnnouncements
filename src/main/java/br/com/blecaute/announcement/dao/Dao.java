package br.com.blecaute.announcement.dao;

import com.google.common.reflect.TypeToken;

import java.util.*;

@SuppressWarnings({"UnstableApiUsage", "rawtypes", "unchecked"})
public abstract class Dao<K, V, T extends Map> {

    private Map<K, V> OBJECTS;

    public Dao() {
        try {
            TypeToken<T> typeToken = new TypeToken<T>(getClass()) {};
            OBJECTS = (Map<K, V>) typeToken.getRawType().getConstructor().newInstance();
        } catch (Exception exception) {
            exception.printStackTrace();
            OBJECTS = new HashMap<>();
        }
    }

    public abstract void add(V value);

    public Collection<V> getValues() {
        return OBJECTS.values();
    }

    public int size() {
        return OBJECTS.size();
    }

    public void add(K key, V value) {
        OBJECTS.put(key, value);
    }

    public boolean has(K key) {
        return OBJECTS.containsKey(key);
    }

    public V get(K key) {
        return OBJECTS.get(key);
    }

    public List<V> get() {
        return new ArrayList<>(OBJECTS.values());
    }

    public abstract void remove(K key);

    public V delete(K key) {
        return OBJECTS.remove(key);
    }

    public void clear() {
        OBJECTS.clear();
    }
}