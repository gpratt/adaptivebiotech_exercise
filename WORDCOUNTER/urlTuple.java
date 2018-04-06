package WORDCOUNTER;

/**
 * Tuple to store date and url resource object for WORDCOUNTER.urlCache
 */

public class urlTuple<Integer, String> {
    public final Long timestamp;
    public final String resource;

    public urlTuple(Long integer, String string) {
        this.timestamp = integer;
        this.resource = string;
    }
}