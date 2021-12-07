package com.afs.restapi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public class CompanyRepository {
    private List<Company> companies = new ArrayList<>();

    public CompanyRepository() {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(1, "Johnson", 20, "male", 1000));
        employees.add(new Employee(2, "May", 25, "female", 1000));
        employees.add(new Employee(3, "Apple", 25, "female", 1000));

        List<Employee> employees2 = new ArrayList<>();

        employees2.add(new Employee(1, "Ken", 20, "male", 1000));
        employees2.add(new Employee(2, "Ben", 25, "female", 1000));
        employees2.add(new Employee(3, "Sam", 25, "female", 1000));

        List<Employee> employees3 = new ArrayList<>();

        employees3.add(new Employee(1, "John", 20, "male", 1000));
        employees3.add(new Employee(2, "On", 25, "female", 1000));
        employees3.add(new Employee(3, "Bon", 25, "female", 1000));

        List<Employee> employees4 = new ArrayList<>();

        employees4.add(new Employee(1, "April", 20, "male", 1000));
        employees4.add(new Employee(2, "June", 25, "female", 1000));
        employees4.add(new Employee(3, "Seven", 25, "female", 1000));

        companies.add(new Company(1, "spring", employees));
        companies.add(new Company(2, "boot", employees2));
        companies.add(new Company(3, "java", employees3));
        companies.add(new Company(4, "abcde", employees4));
    }

    public List<Company> findAll() {
        return companies;
    }

    public Company findById(Integer id) {
        return companies.stream()
                .filter(company -> company.getId().equals(id))
                .findFirst()
                .orElseThrow(NoCompanyFoundException::new);
    }

    public List<Employee> findEmployeesByCompany(Integer id) {
        return companies.stream()
                .filter(company -> company.getId().equals(id))
                .findFirst()
                .get().getEmployees();
    }

    public List<Company> findByPage(Integer page, Integer pageSize) {
        return companies.stream()
                .skip((long) page * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }


    public Company create(Company company) {
        Integer nextId = companies.stream()
                .mapToInt(Company::getId)
                .max()
                .orElse(0) + 1;

        company.setId(nextId);
        companies.add(company);
        return company;
    }

    public Company save(Integer id, Company updatedCompany) {
        Company company = findById(id);
        companies.remove(company);
        companies.add(updatedCompany);
        return updatedCompany;
    }
}
