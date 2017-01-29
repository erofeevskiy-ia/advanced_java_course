package lfu;

/**
 * Created by Игорь on 27.01.2017.
 */
public class Main {
    private static void showResult(LFUCache<?,?> lfu){
        System.out.println("Stack "+lfu.getEntries().toString());
        System.out.println("Value "+lfu.getEntryValue().toString());
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        LFUCache<String,Integer> lfu = new LFUCache<String,Integer>(0.9f,3);
        lfu.put("A", 1);
        lfu.put("B", 1);
        lfu.put("C", 5);
        lfu.put("D", 1);
        showResult(lfu);
        lfu.put("A", 1);
        lfu.get("D");
        lfu.get("D");
        lfu.get("D");
        lfu.get("C");
        lfu.get("C");
        lfu.get("C");
        System.out.println(lfu.get("D"));
        System.out.println(lfu.isExist("D"));
        showResult(lfu);
        lfu.remove("D");
        showResult(lfu);
        lfu.clear();
        showResult(lfu);
        System.out.println(lfu.isExist("D"));
    };

}