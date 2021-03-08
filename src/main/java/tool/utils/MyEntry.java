package tool.utils;

import java.io.Serializable;
import java.util.Map;

public class MyEntry<K, V> implements Map.Entry<K, V>, Serializable {

    private static MyEntry myEntry = new MyEntry();

    private K key;
    private V value;

    public MyEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public MyEntry() {
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        V old = this.value;
        this.value = value;
        return old;
    }

    public static MyEntry getMyEntry() {
        return myEntry;
    }

    public static MyEntry getMyEntry(Object key, Object value) {
        return new MyEntry(key,value);
    }
}
