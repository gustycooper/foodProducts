package sample;

import java.util.*;
import java.text.*;

/**
 *  Class Product stores the essential information needed to model a product
 *  in any inventory control system:  the product name, price and the quantity
 *  in stock.
 */

class Product {
    /**
     * Comparators allows sorting FoodProduct[] on priority, patient id, and names
     */
    public static Comparator<Product> NAME_COMPARATOR = new Comparator<Product>() {
        @Override
        public int compare(final Product o1, final Product o2) {
            return o1.name.compareTo(o2.name);
        }
    };

    protected String name;  //name of the product
    protected int quantity; //quantity of the product in stock
    protected Double price; //price of the proudct

    /**
     *  Default constructor to initialize a product that is not in stock and has no price.
     */
    public Product() {
        quantity = 0;
        price = new Double(0.0);
    }

    /**
     *  Nondefault constructor to initialize a product.
     *  @param n the name of a food product
     *  @param q the quantity of a food product
     *  @param p the price of a food product
     */
    public Product(String n, int q, Double p){
        name = n;
        quantity = q;
        price = p;
    }

    /**
     * Accessor method for name.
     * @return the name of the product
     */
    public String getName(){
        return name;
    }

    /**
     * Accessor method for price.
     * @return the price of the product
     */
    public Double getPrice(){
        return price;
    }

    /**
     * Accessor method for quantity.
     * @return the quanitty of the product
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Change the quantity of the item.
     * @param newQuantity  the updated number of items in stock
     */
    public void setQuantity(int newQuantity) {
        quantity = newQuantity;
    }



    /**
     * Compare two products to see if they are equal based on name.
     * @return true if the names match and false otherwise
     */
    public boolean equals(Product other) {
        return (name.equals(other.name));
    }

    /**
     * Convert the information concerning a food product to a string.
     * @return the string representing the name, price in USD, and quantity in that order
     */
    public String toString() {

        //assumes price is in US dollars
        Locale currentLocale = new Locale.Builder().setLanguage("en").setRegion("US").build();
        Currency currentCurrency = Currency.getInstance(currentLocale);
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);

        String s = name + " " + currencyFormatter.format(price) + " " + quantity;
        return s;
    }
}