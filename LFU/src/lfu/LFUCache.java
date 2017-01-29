package lfu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/**
 * Created by Игорь on 27.01.2017.
 */

public class LFUCache<E,V>  implements Cache<E,V> {
    private Map<E, Integer>  entryValueCache = new HashMap<E,Integer>();
    private Map<E,V> entryValues = new HashMap<E,V>();
    private List<LinkedList<E>> frequencyLists;

    private float evictionFactor;
    private int maxEntryAccess;

    private final int INITIAL_FREQUENCY_LIST = 0;
    private final int INITIAL_CACHE_VALUE = 1;


    public LFUCache(float evictionFactor, int maxEntryAccess) {
        super();
        this.evictionFactor = evictionFactor;
        this.maxEntryAccess = maxEntryAccess;
        createLFUCacheStructure();
    }

    private void createLFUCacheStructure(){
        frequencyLists =  new ArrayList<LinkedList<E>>();
        for(int i=0;i <= maxEntryAccess;i++){
            frequencyLists.add(new LinkedList<E>());
        }

    }

    private  int getEvictionNumber(){
        float en = evictionFactor*maxEntryAccess;
        return (int)en;
    }

    private boolean isExistSpace(){
        return entryValueCache.size() <  maxEntryAccess;
    }

    private void deleteEvictionEntry(){
        int evNumber = getEvictionNumber();
        int countRemove = 0;
        for (LinkedList<E> efl:frequencyLists){
            for(Iterator<E> iterator = efl.iterator();iterator.hasNext();){
                E element = iterator.next();
                if (countRemove < evNumber){
                    iterator.remove();
                    entryValueCache.remove(element);
                    entryValues.remove(element);
                    countRemove += 1;
                } else {
                    return;
                }
            }
        }

    }


    private void addEntryList( LinkedList<E> entryFreqList, E entry, int valueCache){
        if (entryFreqList.size()==0){
            entryFreqList.add(entry);
            entryValueCache.put(entry,valueCache);
        } else if (!entryFreqList.equals(frequencyLists.get(frequencyLists.size()-1))){
            entryFreqList.add(entryFreqList.size(),entry);
            entryValueCache.put(entry,valueCache);
        } else {
            for (Iterator<E> iterator = entryFreqList.iterator();iterator.hasNext();){
                E element = iterator.next();
                int elValue = entryValueCache.get(element);
                if (valueCache < elValue){
                    int index = entryFreqList.indexOf(element);
                    entryFreqList.add(index, entry);
                    entryValueCache.put(entry,valueCache);
                    return;
                }
            }
            entryFreqList.add(entryFreqList.size(),entry);
            entryValueCache.put(entry,valueCache);
        }

    }


    private void changeEntryCachePosition(E entry){
        int indexLinkedList = 0;

        for(LinkedList<E> frq:frequencyLists){
            if(frq.contains(entry)){
                indexLinkedList = frq.indexOf(entry);
                frq.remove(entry);
            }
        }

        LinkedList<E> entryFreqList = new LinkedList<E>();
        if ( indexLinkedList<maxEntryAccess){
            entryFreqList = frequencyLists.get(indexLinkedList+1);
        } else {
            entryFreqList = frequencyLists.get(maxEntryAccess);
        }
        int  valueCache = entryValueCache.get(entry);
        valueCache +=1;
        addEntryList(entryFreqList,entry,valueCache);


    }

    @Override
    public void remove(E entry){
        for(LinkedList<E> frq:frequencyLists){
            if(frq.contains(entry)){
                frq.remove(entry);
                entryValues.remove(entry);
                entryValueCache.remove(entry);

            }
        }
    }

    @Override
    public void clear(){
        frequencyLists.clear();
        entryValues.clear();
        entryValueCache.clear();

    }

    @Override
    public void put(E entry,V value){
        //if entry exist in structure
        if (entryValues.containsKey(entry)){
            entryValues.put(entry, value);
            return;
        }
        if (! isExistSpace()){
            deleteEvictionEntry();
        }
        entryValues.put(entry,value);
        addEntryList(frequencyLists.get(INITIAL_FREQUENCY_LIST),entry,INITIAL_CACHE_VALUE);

    }

    public List<E> getEntries(){
        List<E> entryList = new ArrayList<E>();
        for (List<E> frs:frequencyLists){
            for(E entry:frs){
                entryList.add(entry);
            }
        }
        return entryList;
    }

    @Override
    public boolean isExist(E entry){
        return getEntries().contains(entry);

    }

    @Override
    public V get(E entry){
        changeEntryCachePosition(entry);
        return entryValues.get(entry);
    }

    public  List<LinkedList<E>> getFrequencyLists(){
        return frequencyLists;
    }

    public Map<E, Integer> getEntryValue(){
        return entryValueCache ;
    }
}
