import java.io.*;
import java.util.concurrent.TimeUnit;

public class ProcessTask {

    public static void main(String[] args) {
        try {
            // Launch first process F(10, 20, 1500)
            ProcessBuilder processBuilder1 = new ProcessBuilder("java", "-cp", ".", "SumTask", "10", "20", "1500");
            processBuilder1.redirectOutput(new File("result1.txt"));
            Process process1 = processBuilder1.start();

            // Launch second process F(10, 20, 300)
            ProcessBuilder processBuilder2 = new ProcessBuilder("java", "-cp", ".", "SumTask", "10", "20", "300");
            processBuilder2.redirectOutput(new File("result2.txt"));
            Process process2 = processBuilder2.start();

            // Wait for processes to complete
            process1.waitFor(20, TimeUnit.SECONDS);
            process2.waitFor(20, TimeUnit.SECONDS);

            System.out.println("Processes finished.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class SumTask {
    public static void main(String[] args) {
        int num_base = Integer.parseInt(args[0]);
        int num_veces = Integer.parseInt(args[1]);
        int time_sleep = Integer.parseInt(args[2]);

        int sum = 0;
        for (int i = 0; i < num_veces; i++) {
            sum += num_base + i;
            try {
                Thread.sleep(time_sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Sum after iteration " + (i + 1) + ": " + sum);
        }
    }
}
