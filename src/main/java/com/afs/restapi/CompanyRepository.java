package com.afs.restapi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class CompanyRepository {
    private List<Company> companies = new ArrayList<>();

    public CompanyRepository() {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(1, "Johnson", 20, "male", 1000));
        employees.add(new Employee(2, "May", 25, "female", 1000));
        employees.add(new Employee(3, "Apple", 25, "female", 1000));

        companies.add(new Company(1, "spring", employees));
    }

    public List<Company> findAll() {
        return companies;
    }
}
