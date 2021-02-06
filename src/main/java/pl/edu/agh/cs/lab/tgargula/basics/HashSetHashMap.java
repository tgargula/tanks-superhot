package pl.edu.agh.cs.lab.tgargula.basics;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class HashSetHashMap<K, V> implements SetMap<K, V> {

    private final Map<K, Set<V>> structure = new HashMap<>();

    public HashSetHashMap() { }

    public HashSetHashMap(SetMap<? extends K, ? extends V> setMap) {
        putAll(setMap);
    }

    @Override
    public int size() {
        return values().size();
    }

    @Override
    public boolean isEmpty() {
        return structure.isEmpty();
    }

    @Override
    public boolean containsKey(K key) {
        return structure.containsKey(key);
    }

    @Override
    public boolean containsValue(V value) {
        return values().contains(value);
    }

    @Override
    public Set<V> get(K key) {
        return structure.get(key);
    }

    @Override
    public void put(K key, V value) {
        if (!containsKey(key))
            structure.put(key, new HashSet<>());
        get(key).add(value);
    }

    @Override
    public void remove(K key) {
        structure.remove(key);
    }

    @Override
    public void putAll(SetMap<? extends K, ? extends V> setMap) {
        setMap.forEach((k, v) -> {
            if (containsKey(k))
                structure.put(k, Set.of(v));
            else
                Set.of(v).forEach(value -> structure.get(k).add(value));
        });
    }

    @Override
    public void clear() {
        structure.clear();
    }

    @Override
    public Set<K> keySet() {
        return structure.keySet();
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        for (K key : keySet())
            for (V value : get(key))
                action.accept(key, value);
    }

    @Override
    public String toString() {
        return structure.toString();
    }

    @Override
    public Stream<Map.Entry<K, Set<V>>> stream() {
        return structure.entrySet().stream();
    }

    @Override
    public Map<K, V> flatten() {
        Map<K, V> result = new HashMap<>();
        structure.forEach(
                (key, set) -> set.stream().findAny().ifPresent(v -> result.put(key, v))
        );
        return result;
    }


}
