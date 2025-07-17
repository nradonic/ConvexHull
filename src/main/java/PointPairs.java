import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

public class PointPairs {
    HashMap<String, PointPair> pointPairs = new HashMap<String, PointPair>();

    public void put(PointPair pointPair) {
        if (!pointPairs.containsKey(pointPair.hash())) {
            pointPairs.put(pointPair.hash(), pointPair);
        }
    }

    public boolean contains(PointPair pointPair) {
        return pointPairs.containsKey(pointPair.hash());
    }

    public Iterator<String> iterator() {
        Iterator<String>  keyIterator = new TreeSet<String>( pointPairs.keySet()).iterator();
        return keyIterator;
    }

    public PointPair get(String key) {
        return pointPairs.get(key);
    }

    public String toString(){
        String result ="";
        Iterator<String> ipp = new TreeSet<String>( pointPairs.keySet()).iterator();
        while (ipp.hasNext()){
            result += pointPairs.get(ipp.next());
        }
        return result;
    }

    public int size(){
        return pointPairs.size();
    }

    public void clear(){
        pointPairs.clear();
    }


    public void addAll(PointPairs pps){
        Iterator<String> ipp = pps.iterator();
        while (ipp.hasNext()){
            String key = ipp.next();
            PointPair pp = pps.get(key);
            this.put(pp);
        }
    }
}
