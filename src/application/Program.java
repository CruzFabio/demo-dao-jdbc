package application;

import db.DB;
import db.DbException;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            SellerDao sellerDao = DaoFactory.createSellerDao();

            System.out.println("=== Test 1: FindById ===");
            System.out.print("Enter the ID you wish to query: ");
            int id = sc.nextInt();
            sc.nextLine();
            sellerDao.findById(id).ifPresentOrElse(
                            System.out::println,
                            () -> System.out.println("Department not found!"));

            System.out.println("\n=== Test 2: FindByDepartment ===");
            System.out.print("Enter the ID you wish to query: ");
            id = sc.nextInt();
            sc.nextLine();
            Department department = new Department(id, null);
            List<Seller> list = sellerDao.findByDepartment(department);
            list.forEach(System.out::println);

            System.out.println("\n=== Test 3: FindAll ===");
            list = sellerDao.findAll();
            list.forEach(System.out::println);

            System.out.println("\n=== Test 4: Seller Insert ===");
            Seller newSeller = new Seller(null, "Greg", "greg@email.com", new Date(), 5000.0, department);
            sellerDao.insert(newSeller);
            System.out.println("New ID created: " + newSeller.getId());

            System.out.println("\n=== Test 5: Seller Update ===");
            System.out.print("Enter the ID you wish to query: ");
            id = sc.nextInt();
            sc.nextLine();
            Seller seller = sellerDao.findById(id)
                    .orElseThrow(() -> new DbException("Seller not found!"));
            System.out.print("Enter the seller name: ");
            String name = sc.nextLine();
            seller.setName(name);
            sellerDao.update(seller);
            System.out.println("Update completed!");

            System.out.println("\n=== Test 6: Seller Delete ===");
            System.out.print("Enter the seller ID: ");
            id = sc.nextInt();
            sc.nextLine();
            sellerDao.deleteById(id);
            System.out.println("Deleted completed!");

        } catch (DbException e) {
            System.err.println("Database error: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.err.println("Input error: Please enter a valid number!");
        } finally {
            DB.closeConnection();
        }
    }
}
