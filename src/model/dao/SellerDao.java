package model.dao;

import model.entities.Department;
import model.entities.Seller;

import java.util.List;
import java.util.Optional;

public interface SellerDao {

    void insert(Seller obj);
    void update(Seller obj);
    void deleteById(Integer id);
    Optional<Seller> findById(Integer id);
    List<Seller> findAll();
    List<Seller> findByDepartment(Department department);
}
