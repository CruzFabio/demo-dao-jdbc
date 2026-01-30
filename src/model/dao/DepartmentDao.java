package model.dao;

import model.entities.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentDao {

    void insert(Department obj);
    void update(Department obj);
    void deleteById(Integer id);
    Optional<Department> findById(Integer id);
    List<Department> findAll();
}
