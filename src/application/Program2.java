package application;

import db.DB;
import db.DbException;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Program2 {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

            System.out.println("=== Test 1: Department Insert ===");
            System.out.print("Enter the department name: ");
            String name = sc.next();
            Department dep = new Department(null, name);
            departmentDao.insert(dep);
            System.out.println("Insert new id = " + dep.getId());

            System.out.println("\n=== Test 2: Department Update ===");
            System.out.print("Enter the department ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            dep = departmentDao.findById(id);
            if (dep != null) {
                System.out.print("Enter the new department name: ");
                name = sc.next();
                dep.setName(name);
                departmentDao.update(dep);
                System.out.println("Update completed!");
            } else {
                System.out.println("Update failed: ID not found.");
            }

            System.out.println("\n=== Test 3: Department DeleteById ===");
            System.out.print("Enter the department ID: ");
            id = sc.nextInt();
            sc.nextLine();
            departmentDao.deleteById(id);
            System.out.println("Deleted completed!");

            System.out.println("\n=== Test 4: Department FindById ===");
            System.out.print("Enter the ID you wish to query: ");
            id = sc.nextInt();
            dep = departmentDao.findById(id);
            System.out.println(dep != null ? dep : "Department not found!.");

            System.out.println("\n=== Test 5: Department FindAll ===");
            List<Department> list = departmentDao.findAll();
            for (Department obj : list) {
                System.out.println(obj);
            }

        } catch (DbException e) {
            System.err.println("Database error: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.err.println("Input error: Please enter a valid number!");
        } finally {
            DB.closeConnection();
        }
    }
}
