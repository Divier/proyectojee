package co.com.datatools.c2.util;

import java.util.HashMap;

public class MapUtils {
    public static <K> MapBuilder<K> asMap(K key, Object value) {
        return new MapBuilder<K>().entry(key, value);
    }

    public static class MapBuilder<K extends Object> extends HashMap<K, Object> {
        private static final long serialVersionUID = 4360214419384810152L;

        public MapBuilder<K> entry(K key, Object value) {
            this.put(key, value);
            return this;
        }
    }
}
