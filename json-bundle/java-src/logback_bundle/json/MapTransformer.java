package logback_bundle.json;

import java.util.Map;

public interface MapTransformer {

    public static final MapTransformer NOP = new MapTransformer() {
        public Map<String, Object> transform(Map<String, Object> map) {
            return map;
        }
    };

    public Map<String, Object> transform(Map<String, Object> map);

}
