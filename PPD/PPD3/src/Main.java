import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main
{
    static SortedLinkedList list = new SortedLinkedList();
    static List<Thread> threads = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {


        //addValuesWithThreads();
        addMoreValuesWithThreads();


    }

    private static void addMoreValuesWithThreads() throws IOException, InterruptedException {
        resetFile();
        Thread t1 = new Thread( () -> {
            for(int i = 100; i > 0; i--){
                double valToBeInserted = i - 0.75;
                list.insert(valToBeInserted);
                Writer.writeToFile("1", "INSERT", valToBeInserted);

            }
        });
        Thread t2 = new Thread( () -> {
            for(int i = 1; i <= 50; i++){
                list.insert(i+0.65);
                Writer.writeToFile("2", "INSERT", Double.valueOf(i+0.1));
            }
        });
        Thread t3 = new Thread( () -> {
            for(int i = 1; i <= 25; i++){
                double valToDelete = i + 0.65;
                list.deleteElement(valToDelete);
                Writer.writeToFile("3","DELETE", valToDelete);
            }
            for(int i = 100; i > 75; i--){
                double valToDelete = i - 0.75;
                list.deleteElement(valToDelete);
                Writer.writeToFile("3","DELETE", valToDelete);
            }

        });

        Thread t4 = new Thread( () -> {
            Iterator it = list.getIterator();
            while(it.hasNext() && (t1.isAlive() || t2.isAlive()|| t3.isAlive())){
                it.next();

            }
        });


        t1.start();
        t2.start();
        //t4.sleep(200);
        t1.sleep(200);
        t2.sleep(200);
        t3.sleep(1000);
        t3.start();
        t4.start();

        threads.add(t1);
        threads.add(t2);
        threads.add(t3);
        threads.add(t4);
        joinThreads();
    }

    private static void addValuesWithThreads() throws IOException, InterruptedException {

        resetFile();
        Thread t1 = new Thread( () -> {
            for(int i = 10; i > 0; i--){
                double valToBeInserted = i - 0.75;
                list.insert(valToBeInserted);
                Writer.writeToFile("1", "INSERT", valToBeInserted);

            }
        });
        Thread t2 = new Thread( () -> {
            for(int i = 1; i <= 5; i++){
                list.insert(i+0.1);
                Writer.writeToFile("2", "INSERT", Double.valueOf(i+0.1));
            }
        });
        Thread t3 = new Thread( () -> {
            for(int i = 1; i <= 5; i++){
                double valToDelete = i + 0.01;
                list.deleteElement(valToDelete);
                Writer.writeToFile("3","DELETE", valToDelete);
            }
        });

        Thread t4 = new Thread( () -> {
            Iterator it = list.getIterator();
            while (it.hasNext() && (t1.isAlive() || t2.isAlive())) {
                it.next();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });


        t1.start();
        t2.start();
        //t3.sleep(2000);
        t4.sleep(200);
        t4.start();
        t3.start();
        threads.add(t1);
        threads.add(t2);
        threads.add(t4);
        threads.add(t3);
        joinThreads();
    }

    private static void resetFile() throws IOException {
        FileOutputStream writer = new FileOutputStream("Log.txt");
        writer.write(("").getBytes());
        writer.close();
    }

    private static void joinThreads() {
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }



}