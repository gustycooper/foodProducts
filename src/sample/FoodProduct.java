package sample;

import java.text.DecimalFormat;
import java.util.Comparator;

class FoodProduct extends Product implements Comparable<FoodProduct> {

    /**
     * Comparators allows sorting FoodProduct[] expiration data and upc
     */
    public static Comparator<FoodProduct> EXPIRATION_DATE_COMPARATOR = new Comparator<FoodProduct>() {
        @Override
        public int compare(final FoodProduct o1, final FoodProduct o2) {
            String o1Date = o1.expirationDate.substring(4) + o1.expirationDate.substring(0,4);
            String o2Date = o2.expirationDate.substring(4) + o2.expirationDate.substring(0,4);
            //System.out.println("Gusty " + o1Date + " " + o2Date);
            return o1Date.compareTo(o2Date);
        }
    };

    public static Comparator<FoodProduct> UPC_COMPARATOR = new Comparator<FoodProduct>() {
        @Override
        public int compare(final FoodProduct o1, final FoodProduct o2) {
            return o1.upc.compareTo(o2.upc);
        }
    };

    private String expirationDate;
    private String upc;

    public FoodProduct() {
        super();
        expirationDate = "";
        upc = "";
    }

    public FoodProduct(String n, int q, Double p, String e, String u) {
        super(n, q, p);
        expirationDate = e;
        upc = u;
    }

    public String getExpirationDate() { return expirationDate; }

    public String getUpc() { return upc; }

    public String toString() {
        DecimalFormat df = new DecimalFormat( "#.00" );
        return name + "," + upc + "," + quantity + "," + df.format(price) + "," + expirationDate;
    }

    public int compareTo(FoodProduct other) {
        return name.compareTo(other.name);
    }

}
