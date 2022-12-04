package com.store.sales.service;

import java.util.List;

import com.store.sales.bean.Quantity;
import com.store.sales.bean.Sales;
import com.store.sales.bean.SalesReport;
import com.store.sales.bean.Stock;
import com.store.sales.dao.SalesDao;
import com.store.sales.dao.StockDao;

public class Administrator {
    private static StockDao stockDao = new StockDao();
    private static SalesDao salesDao = new SalesDao();

    public String insertStock(Stock stock) {
        if (stockDao.insertStock(stock) == 1)
            return "Success!";
        else
            return "Failed. Please check the details entered.";
    }

    public String deleteStock(String ProductID) {
        int a = stockDao.deleteStock(ProductID);
        System.out.println(a);
        if (a == 1)
            return "Success!";
        else if (a == 2) {
            return "Failed. Product record exists in sales table. Cannot delete stock.";
        } else
            return "Failed. Product ID does not exist.";
    }

    public String reStock(Quantity quantity) {
        if (stockDao.reStock(quantity) == 1)
            return "Success!";
        else
            return "Failed. Verify the entered Product ID.";
    }

    public String insertSales(Sales sales) {

        if (stockDao.getStock(sales.getProductID()) == null)
            return "Failed. Product ID does not exists in table Stock.";

        if (stockDao.getStock(sales.getProductID()).getQuantityOnHand() < sales.getQuantitySold())
            return "Failed. Quantity On Hand less than Quantity Sold.";

        if (salesDao.insertSales(sales) == 1) {
            if (stockDao.updateStock(sales.getProductID(), sales.getQuantitySold()) == 1)
                return "Success!";
            else
                return "Failed!";
        } else {
            return "Failed. Please verify your entered values.";
        }
    }
    

    public void getSalesReport() {
	System.out.println("\n\n");
	List<SalesReport> slsRep =  salesDao.getSalesReport();
	
	for(SalesReport entry : slsRep)
	{
	    System.out.println(entry.toString()+"\n");
	}
    }

}
