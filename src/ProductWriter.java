import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductWriter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> productDataList = new ArrayList<>();

        // Loop to input product data
        do {
            String productData = getProductData(scanner);
            productDataList.add(productData);
        } while (SafeInput.getYNConfirm(scanner, "Do you want to enter another product?"));

        // Get the file name from the user
        String fileName = SafeInput.getNonZeroLenString(scanner, "Enter the file name to save the data");

        // Save data to the file
        saveDataToFile(productDataList, fileName);

        System.out.println("Data saved successfully!");
    }

    // Method to get product data from the user
    private static String getProductData(Scanner scanner) {
        String id = SafeInput.getNonZeroLenString(scanner, "Enter ID");
        String name = SafeInput.getNonZeroLenString(scanner, "Enter Name");
        String description = SafeInput.getNonZeroLenString(scanner, "Enter Description");
        double cost = SafeInput.getDouble(scanner, "Enter Cost");

        return String.format("%s, %s, %s, %.2f", id, name, description, cost);
    }

    // Method to save data to a file
    private static void saveDataToFile(ArrayList<String> data, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}