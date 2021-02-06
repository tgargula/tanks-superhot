package pl.edu.agh.cs.lab.tgargula.basics;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public interface SetMap<K, V> {

    int size();

    boolean isEmpty();

    boolean containsKey(K key);

    boolean containsValue(V value);

    Set<V> get(K key);

    void put(K key, V value);

    void remove(K key);

    void putAll(SetMap<? extends K, ? extends V> setMap);

    void clear();

    Set<K> keySet();

    Collection<V> values();

    void forEach(BiConsumer<? super K, ? super V> action);

    Stream<Map.Entry<K, Set<V>>> stream();

    Map<K, V> flatten();

}
