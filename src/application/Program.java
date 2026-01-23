package application;

import model.entities.Department;
import model.entities.Seller;
import model.entities.dao.DaoFactory;
import model.entities.dao.SellerDao;

import java.util.Date;

public class Program {

    public static void main(String[] args) {

        Department obj = new Department(1, "Books");
        System.out.println(obj);

        Seller obj2 = new Seller(1, "Alex Green", "greenAlex@email.com", new Date(), 5000.0, obj);
        System.out.println(obj2);

        SellerDao sellerDao = DaoFactory.createSellerDao();
    }
}
