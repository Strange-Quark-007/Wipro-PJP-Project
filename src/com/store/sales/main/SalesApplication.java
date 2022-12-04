package com.store.sales.main;

import java.text.ParseException;
import java.util.Scanner;

import com.store.sales.bean.Quantity;
import com.store.sales.bean.Sales;
import com.store.sales.bean.Stock;
import com.store.sales.service.Administrator;

public class SalesApplication {

    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);

        Administrator admin = new Administrator();
        int choice;

        do {
            System.out.println("1. Insert Stock");
            System.out.println("2. Delete Stock");
            System.out.println("3. Re-stock Stock");
            System.out.println("4. Insert Sales");
            System.out.println("5. View Sales Report");
            System.out.print("Enter your Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    Stock stock = new Stock();
                    System.out.print("Enter product ID: ");
                    stock.setProductID(sc.nextLine());
                    System.out.print("Enter product name: ");
                    stock.setProductName(sc.nextLine());
                    System.out.print("Enter quantity on hand: ");
                    stock.setQuantityOnHand(sc.nextInt());
                    sc.nextLine();
                    System.out.print("Enter product unit price: ");
                    stock.setProductUnitPrice(sc.nextDouble());
                    System.out.print("Enter product reorder level: ");
                    stock.setReorderLevel(sc.nextInt());
                    sc.nextLine();
                    admin.insertStock(stock);
                }
                case 2 -> {
                    System.out.print("Enter product id to be deleted: ");
                    String removeId = sc.nextLine();
                    removeId = admin.deleteStock(removeId);
                    if (removeId != null) System.out.println(removeId + " removed successfully");
                }

                case 3 -> {
                    Quantity qty = new Quantity();
                    System.out.print("Enter product id to be re-stocked: ");
                    qty.setProductID(sc.nextLine());
                    System.out.print("Enter quantity to add: ");
                    qty.setQuantityToAdd(sc.nextInt());
                    admin.reStock(qty);

                }
                case 4 -> {
                    Sales sales = new Sales();
                    System.out.print("Enter sales id: ");
                    sales.setSalesID(sc.nextLine());
                    System.out.print("Enter date (dd-mm-yyyy): ");
                    String sDate = sc.nextLine();
//                    Date date = new SimpleDateFormat("dd-MM-yyyy").parse(sDate);
                    sales.setSalesDate(sDate);
                    System.out.print("Enter product id: ");
                    sales.setProductID(sc.nextLine());
                    System.out.print("Enter quantity sold: ");
                    sales.setQuantitySold(sc.nextInt());
                    sc.nextLine();
                    System.out.print("Enter sales price per unit: ");
                    sales.setSalesPricePerUnit(sc.nextDouble());
                    admin.insertSales(sales);
                }

                case 5 -> admin.getSalesReport();
                default -> {
                    System.out.println("Exiting...");
                    choice = 0;
                }
            }
        } while (choice >= 1);

        sc.close();
    }
}
