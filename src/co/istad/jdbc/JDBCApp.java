package co.istad.jdbc;

import co.istad.jdbc.config.DbConfig;
import co.istad.jdbc.dao.ProductDao;
import co.istad.jdbc.dao.ProductDaoImpl;
import co.istad.jdbc.model.Product;
import co.istad.jdbc.util.InputUtil;
import co.istad.jdbc.view.View;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

public class JDBCApp {


    private ProductDao productDao;


    public static void main(String[] args) {


        DbConfig.init();
        Connection conn = DbConfig.getInstance();
        new JDBCApp().run();


    }
    private void run(){
        productDao = new ProductDaoImpl();
        do{

            View.printAppMenu();
            int menu= InputUtil.getInteger("Enter your option: ");

            switch (menu){

                case 1-> {
                    System.out.println("List Items");
                    List<Product> products = productDao.findAll();
                    View.table(products);
                }
                case 2-> {
                    View.print("Add a new product" );
                    String code = InputUtil.getText("Enter code: ");
                    String name = InputUtil.getText("Enter name: ");
                    BigDecimal price = InputUtil.getMoney("Enter price: ");
                    Integer qty = InputUtil.getInteger("Enter qty: ");

                    // Create a new product object
                    Product newProduct = new Product(code, name, price, qty, false);

                    try {
                        int affectedRow =  productDao.insert(newProduct);
                        if (affectedRow > 0) {
                            View.print("Insert a new product successfully..!" );
                        } else {
                            View.print("Insert operation is not affected");
                        }
                    } catch (RuntimeException e) {
                        View.print(e.getMessage() );
                    }
                }
                case 3-> System.out.println("Update Item");
                case 4-> {
                    View.print("Delete a product by code" );
                    String code = InputUtil.getText("Enter code: ");
                    try {
                        String confirmation = InputUtil.getText("Are you sure to delete? [Y/n]");
                        if (confirmation.equalsIgnoreCase("y")) {
                            int affectedRow = productDao.delete(code);
                            if (affectedRow > 0) {
                                View.print("Deleted product successfully..!" );
                            } else {
                                View.print("Deleted operation is not affected" );
                            }
                        } else {
                            View.print("Delete operation cancelled" );
                        }
                    } catch (RuntimeException e) {
                        View.print(e.getMessage() );
                    }
                }
                case 0-> System.exit(0);
                default -> System.out.println("Invalid option");

            }

        }while (true);
    }
}
