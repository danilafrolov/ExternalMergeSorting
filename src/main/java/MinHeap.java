import java.io.*;
import java.util.PriorityQueue;

public class MinHeap {
    public static class HeapItem<T extends Comparable<T>> implements Comparable<HeapItem<T>> {
        BufferedReader reader;
        T value;

        public HeapItem(String filename) throws IOException {
            reader = new BufferedReader(new FileReader(filename));
        }

        public HeapItem(BufferedReader reader, T value) {
            this.reader = reader;
            this.value = value;
        }

        @Override
        public int compareTo(HeapItem<T> o) {
            return value.compareTo(o.value);
        }
    }

    public static void mergeFiles(String[] files, String resultFileName) throws Exception {
        if (files == null || files.length == 0) {
            throw new IllegalArgumentException("Invalid input!");
        }

        PriorityQueue<HeapItem<String>> pq = new PriorityQueue<HeapItem<String>>();

        for (String filename: files) {
            HeapItem<String> item = new HeapItem<String>(filename);
            item.value = item.reader.readLine();
            pq.add(item);
        }
        PrintWriter writer = new PrintWriter(new FileWriter(resultFileName));
        while (!pq.isEmpty()) {
            HeapItem current = pq.remove();
            writer.println(current.value);
            String next = current.reader.readLine();
            if (next != null) {
//                int nextNumber = Integer.parseInt(next);
                pq.add(new HeapItem(current.reader, next));
            }
        }
        writer.close();
    }

    public static void main(String[] args){
        String[] files = new String[]{
                "C:/Work/Demo Apps/s1.txt", "C:/Work/Demo Apps/s2.txt"
        };
        try {
            mergeFiles(files, "C:/Work/Demo Apps/out.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
