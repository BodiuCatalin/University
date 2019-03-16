import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Writer {

    private static String fileName = "Log.txt";
    public static List<String> operationList = new ArrayList<>();
    public static void writeToFile(String threadNumber, String operation, double paramaterValue){
        long currTime = System.currentTimeMillis();
        operationList.add(threadNumber+" "+operation+" "+Double.valueOf(paramaterValue).toString() +" "+currTime);
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(new File(fileName), true));
            br.append(threadNumber + " " + operation + " " + paramaterValue + " " + currTime);
            br.newLine();
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}