package lfu;


/**
 * Created by Игорь on 27.01.2017.
 */
public interface Cache<E,V> {

    void remove (E entry);

    void clear();

    void put(E entry,V value);

    boolean isExist(E entry);

    V get(E entry);
}
