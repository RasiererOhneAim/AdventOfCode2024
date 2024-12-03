import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        printList(List());
        System.out.println(checkArrays());
    }

    public static void printList(List<int[]> lineArrays){
        System.out.println("Line Arrays:");
        for (int[] array : lineArrays) {
            for (int num : array) {
                System.out.print(num + " ");
            }
            System.out.println(); // Move to the next line after printing each array
        }
    }


    public static List<int[]> List(){

        String filePath = "day02/test_input.txt";
        List<int[]> lineArrays = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                int[] numbers = new int[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    numbers[i] = Integer.parseInt(parts[i]);
                }
                lineArrays.add(numbers);
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
        return lineArrays;
    }

    public static int[] grabArray(List<int[]> lineArrays, int indexToAccess){
        int[] selectedArray = lineArrays.get(indexToAccess);
        return selectedArray;
    }

    public static int checkArrays(){
        int safeness=0;
        for(int i = 0; i < List().size(); i++){
            for(int j = 1; j < grabArray(List(), i).length; j++){
                int diff = grabArray(List(), i)[j] - grabArray(List(), i)[j-1];
                if(Math.abs(diff)<1 || Math.abs(diff)>3){
                    System.out.println(i+" "+j+" "+diff);
                    safeness--;
                    break;
                }
            }
            safeness++;
        }
        return safeness;
    }
}