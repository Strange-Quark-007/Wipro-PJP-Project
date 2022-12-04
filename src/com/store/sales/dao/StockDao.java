package com.store.sales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.store.sales.bean.Quantity;
import com.store.sales.bean.Stock;
import com.store.sales.util.DBUtil;

public class StockDao {

    public int insertStock(Stock stock) {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        String sql1 = "INSERT INTO TBL_STOCK VALUES(?, ?, ?, ?)";
        String sql2 = "INSERT INTO TBL_QUANTITY VALUES(?, ?)";
        
        

        try {
            conn = DBUtil.getDBConnection();
            pstmt1 = conn.prepareStatement(sql1);
            pstmt2 = conn.prepareStatement(sql2);
            pstmt1.setString(1, stock.getProductID());
            pstmt1.setString(2, stock.getProductName());
            pstmt1.setDouble(3, stock.getProductUnitPrice());
            pstmt1.setInt(4, stock.getReorderLevel());

            pstmt2.setString(1, stock.getProductID());
            pstmt2.setInt(2, stock.getQuantityOnHand());

            if ((pstmt1.executeUpdate() == 1) && (pstmt2.executeUpdate() == 1))
                return 1;
            else
                return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateStock(String productID, int soldQty) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE TBL_QUANTITY SET Quantity_On_Hand = (Quantity_On_Hand - ?)" + "WHERE Product_ID = ?";

        try {
            conn = DBUtil.getDBConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, soldQty);
            pstmt.setString(2, productID);

            if (pstmt.executeUpdate() == 1)
                return 1;
            else
                return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Stock getStock(String productID) {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        String sql1 = "SELECT * FROM TBL_STOCK WHERE Product_ID = ?";
        String sql2 = "SELECT Quantity_On_Hand FROM TBL_QUANTITY WHERE Product_ID = ?";

        try {
            conn = DBUtil.getDBConnection();
            pstmt1 = conn.prepareStatement(sql1);
            pstmt2 = conn.prepareStatement(sql2);
            pstmt1.setString(1, productID);
            pstmt2.setString(1, productID);

            ResultSet rs1 = pstmt1.executeQuery();
            ResultSet rs2 = pstmt2.executeQuery();

            rs1.next();
            rs2.next();
            Stock stock = new Stock();
            stock.setProductID(rs1.getString(1));
            stock.setProductName(rs1.getString(2));
            stock.setQuantityOnHand(rs2.getInt(1));
            stock.setProductUnitPrice(rs1.getDouble(3));
            stock.setReorderLevel(rs1.getInt(4));

            return stock;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int deleteStock(String productID) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        String sql = "Delete from TBL_QUANTITY WHERE Product_ID = ?";
        String sql2 = "Delete from TBL_STOCK WHERE Product_ID = ?";

        try {
            conn = DBUtil.getDBConnection();
            String check = "Select * from tbl_sales where Product_ID = ?";
            pstmt = conn.prepareStatement(check);
            pstmt.setString(1, productID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next() == true)
            {
//				removeStock(productID);
                return 2;
            }
            else
            {
                pstmt1 = conn.prepareStatement(sql);
                pstmt1.setString(1, productID);
                if(pstmt1.executeUpdate()==1)
                {
                    pstmt2 = conn.prepareStatement(sql2);
                    pstmt2.setString(1, productID);
                    if(pstmt2.executeUpdate()==1)
                        return 1;
                    else
                        return 0;

                }
                else
                    return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    public int removeStock(String productID) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE TBL_QUANTITY SET QUANTITY_ON_HAND = 0 WHERE Product_ID = ?";

        try {
            conn = DBUtil.getDBConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, productID);

            if (pstmt.executeUpdate() == 1)
                return 1;
            else
                return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int reStock(Quantity quantity) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE TBL_QUANTITY SET QUANTITY_ON_HAND = (QUANTITY_ON_HAND + ?) WHERE Product_ID = ?";

        try {
            conn = DBUtil.getDBConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, quantity.getQuantityToAdd());
            pstmt.setString(2, quantity.getProductID());

            if (pstmt.executeUpdate() == 1)
                return 1;
            else
                return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    

}
