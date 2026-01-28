package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;

public class Program {

    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== Test 1: FindById ===");
        Seller seller = sellerDao.findById(3);

        System.out.println(seller);

        System.out.println("\n=== Test 2: FindByDepartment ===");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findAllDepartment(department);
        for (Seller obj : list) {
            System.out.println(obj);
        }

        System.out.println("\n=== Test 3: FindAll ===");
        list = sellerDao.findAll();
        for (Seller obj : list) {
            System.out.println(obj);
        }

        System.out.println("\n=== Test 4: Seller Insert ===");
        Seller newSeller = new Seller(null, "Greg", "greg@email.com", new Date(), 5000.0, department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted new Id = " + newSeller.getId());

        System.out.println("\n=== Test 5: Seller Update ===");
        seller = sellerDao.findById(1);
        seller.setName("Luke Blue");
        sellerDao.update(seller);
        System.out.println("Update completed!");
    }
}
