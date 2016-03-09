package logback_bundle.json;

public interface ValueDecoder {

    /**
     * No-Operation decoder that simply returns the encoded value.
     */
    public static final ValueDecoder NOP = new ValueDecoder() {
        public Object decode(String encodedValue) {
            return encodedValue;
        }
    };

    public Object decode(String encodedValue);

}
