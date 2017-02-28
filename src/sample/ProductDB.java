package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class ProductDB {

    private List<FoodProduct> productDB = new ArrayList<>();
    /**
     * productMap maps product name ot a FoodProduct object
     */
    private Map<String,FoodProduct> productMap = new HashMap<>();
    /**
     * upcMap maps a upc to a product name
     */
    private Map<String,String> upcMap = new HashMap<>();
    /**
     * byExpirationDate is an array of all products sorted by expiration date
     */
    private FoodProduct[] byExpirationDate;
    /**
     * byUpc is an array of all products sorted by UPC
     */
    private FoodProduct[] byUpc;
    /**
     * productDBFileName is the name of the product database file
     */
    private String productDBFileName;

    public ProductDB(String fileName) {
        readProductDB(fileName);
    }
    private void updateSortedArrays() {
        String[] keys = new String[productMap.size()];
        byExpirationDate = new FoodProduct[productMap.size()];
        byUpc = new  FoodProduct[productMap.size()];
        int index = 0;
        for (Map.Entry<String, FoodProduct> mapEntry : productMap.entrySet()) {
            keys[index] = mapEntry.getKey();
            byExpirationDate[index] = mapEntry.getValue();
            byUpc[index] = mapEntry.getValue();
            index++;
        }
        Arrays.sort(byExpirationDate, FoodProduct.EXPIRATION_DATE_COMPARATOR);
        Arrays.sort(byUpc, FoodProduct.UPC_COMPARATOR);

    }

    public void add(FoodProduct p) {
        productMap.put(p.getName(),p);
        upcMap.put(p.getUpc(), p.getName());
        updateSortedArrays();
    }

    public FoodProduct get(String name) {
        return productMap.getOrDefault(name, null);
    }

    public FoodProduct remove(String name) {
        FoodProduct fp;
        if ((fp = productMap.remove(name)) != null) {
            updateSortedArrays();
            upcMap.remove(fp.getUpc());
            return fp;
        }
        else
            return null;
    }

    public FoodProduct scan(String upc) {
        String productName = upcMap.get(upc);
        FoodProduct fp = productMap.get(productName);
        if (fp != null) {
            fp.setQuantity(fp.getQuantity() - 1);
            return fp;
        }
        else
            return null;
    }

    public FoodProduct[] productArray(FoodProductOrder fpo) {
        if (fpo == FoodProductOrder.BY_EXPIRATION_DATE)
            return byExpirationDate;
        else
            return byUpc;
    }

    /**
     * Read product information from DB
     * DB formate is crackers,1234567891,10,2.99,05162018
     * @param fileName name of file to be read
     */
    private void readProductDB(String fileName) {
        if (fileName == null || fileName.equals(""))
            fileName = "productDB.txt";
        productDBFileName = fileName;
        File file = new File(fileName);
        try {
            Scanner read = new Scanner(file);
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String regExp = "\\s*(\\s|,)\\s*";
                String[] pv = line.split(regExp);
                System.out.println(Arrays.toString(pv));
                FoodProduct fp = new FoodProduct(pv[0],Integer.parseInt(pv[2]),new Double(Double.parseDouble(pv[3])),pv[4],pv[1]);
                productMap.put(pv[0], fp);
                upcMap.put(pv[1],pv[0]);
            }
            updateSortedArrays();
        }
        catch (FileNotFoundException e) {
            System.out.println("Product DB file not found");
            e.printStackTrace();
        }
    }

    /**
     * Write product information to DB
     */
    public void writeProductDB() {
        try{
            PrintWriter writer = new PrintWriter(productDBFileName, "UTF-8");
            for (Product p : byUpc)
                writer.println(p);
            writer.close();
        } catch (IOException e) {
            System.out.println("Product DB file error!");
            e.printStackTrace();
        }
    }

    public String toString() {
        return Arrays.toString(byUpc);
    }
}

