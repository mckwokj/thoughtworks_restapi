package com.afs.restapi;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("companies")
public class CompanyController {

    private CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Integer id) {
        return companyRepository.findById(id);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getAllEmployeesByCompany(@PathVariable Integer id) {
        return companyRepository.findEmployeesByCompany(id);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Company> getCompaniesByPage(@RequestParam Integer page, @RequestParam Integer pageSize) {
        return companyRepository.findByPage(page, pageSize);
    }

    @PostMapping
    public Company createCompany(@RequestBody Company company) {
        return companyRepository.create(company);
    }

    @PutMapping("/{id}")
    public Company editCompany(@PathVariable  Integer id, @RequestBody Company updatedCompany) {
        Company company = companyRepository.findById(id);

        if (updatedCompany.getCompanyName() != null) {
            company.setCompanyName(updatedCompany.getCompanyName());
        }

        if (updatedCompany.getEmployees() != null) {
            company.setEmployees(updatedCompany.getEmployees());
        }

        return companyRepository.save(id, updatedCompany);
    }
}
