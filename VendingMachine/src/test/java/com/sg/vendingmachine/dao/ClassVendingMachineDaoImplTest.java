
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Product;
import com.sg.vendingmachine.service.ClassVendingMachineService;
import com.sg.vendingmachine.service.ClassVendingMachineServiceImpl;
import com.sg.vendingmachine.service.VendingMachineAuditDaoStubImpl;
import com.sg.vendingmachine.service.VendingMachineDaoStubImpl;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;


public class ClassVendingMachineDaoImplTest {
    ClassVendingMachineDao testDao;
    //ClassVendingMachineService service;

    public ClassVendingMachineDaoImplTest() {
        testDao = new ClassVendingMachineDaoImpl();
        /*ClassVendingMachineAuditDao auditDao = new ClassVendingMachineAuditDaoFileImpl();
        service = new ClassVendingMachineServiceImpl(testDao, auditDao);*/
    }    
    
    // set the testProducts file to be used for all tests
    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testProducts.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new ClassVendingMachineDaoImpl(testFile);
    }
    
    @Test // method by Michaela
    @DisplayName("Test get list products")
    public void testGetListProducts() throws Exception {
        // create sample data and load it into the file

        // -1::Dr Pepper::2.00::3
        // -2::Lemonade::1.23::4
        // -3::Fanta::0.85::12
        // -4::Gatorade::2.22::6

        Product p1 = testDao.addProduct(new Product("-1", "Dr Pepper", new BigDecimal("2.00"), 3));
        Product p2 = testDao.addProduct(new Product("-2", "Lemonade", new BigDecimal("1.23"), 4));
        Product p3 = testDao.addProduct(new Product("-3", "Fanta", new BigDecimal("0.85"), 12));
        Product p4 = testDao.addProduct(new Product("-4", "Gatorade", new BigDecimal("2.22"), 6));

        // put all the products into a list for comparison
        ArrayList<Product> expectedResult = new ArrayList<>();
        expectedResult.add(p1);
        expectedResult.add(p2);
        expectedResult.add(p3);
        expectedResult.add(p4);

        // get a list of the actual products
        ArrayList<Product> result = testDao.getListProducts();
        assertEquals(result, expectedResult, "Checking getListProducts");
    }
    
    @Test // method by Michaela
    @DisplayName("Test load products from file")
    public void testLoadProductsFromFile() throws Exception {

        // create sample data to be loaded to the file

        // 99::Chex Mix::0.75::16
        // 98::Rice Krispy::1.11::8
        // 97::Cheetos::1.50::4

        Product p1 = new Product("99", "Chex Mix", new BigDecimal("0.75"), 16);
        Product p2 = new Product("98", "Rice Krispy", new BigDecimal("1.11"), 8);
        Product p3 = new Product("97", "Cheetos", new BigDecimal("1.50"), 4);

        // load all the products
        testDao.addProduct(p1);
        testDao.addProduct(p2);
        testDao.addProduct(p3);

        // put all the products into a list for comparison
        ArrayList<Product> expectedResult = new ArrayList<>();
        expectedResult.add(p1);
        expectedResult.add(p2);
        expectedResult.add(p3);

        ArrayList<Product> result = testDao.getListProducts();

        assertEquals(expectedResult, result, "File should match expected.");

    }
    
    @Test // method by Michaela
    @DisplayName("Test add get product")
    public void testAddGetProduct() throws Exception {

        // add a new Product with values
        // 9::Gummies::0.99::4
        Product result = new Product("3456789", "Gummies", new BigDecimal("0.99"), 4);
        testDao.addProduct(result);

        // search the products for the new id
        Product expectedResult = testDao.getProductByID(result.getId());

        // String id, String name, BigDecimal price, int numberItemsInventory
        assertEquals(expectedResult.getId(), result.getId(), "Ids should be equal");
        assertEquals(expectedResult.getName(), result.getName(), "Name should be equal");
        assertEquals(expectedResult.getPrice(), result.getPrice(), "Price should be equal");
        assertEquals(expectedResult.getNumberItemsInventory(), result.getNumberItemsInventory(), "Inventory should be equal");
    }
    /*
    @Test // method by Michaela
    @DisplayName("Test get list products")
    public void testGetListProducts() throws Exception {

        // create sample data and load it into the file

        // -1::Dr Pepper::2.00::3
        // -2::Lemonade::1.23::4
        // -3::Fanta::0.85::12
        // -4::Gatorade::2.22::6

        Product p1 = testDao.addProduct(new Product("-1", "Dr Pepper", new BigDecimal("2.00"), 3));
        Product p2 = testDao.addProduct(new Product("-2", "Lemonade", new BigDecimal("1.23"), 4));
        Product p3 = testDao.addProduct(new Product("-3", "Fanta", new BigDecimal("0.85"), 12));
        Product p4 = testDao.addProduct(new Product("-4", "Gatorade", new BigDecimal("2.22"), 6));

        // put all the products into a list for comparison
        ArrayList<Product> expectedResult = new ArrayList<>();
        expectedResult.add(p1);
        expectedResult.add(p2);
        expectedResult.add(p3);
        expectedResult.add(p4);

        // get a list of the actual products
        ArrayList<Product> result = testDao.getListProducts();
        assertEquals(result, expectedResult, "Checking getListProducts");
    }
 */
    
}
