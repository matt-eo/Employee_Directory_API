package com.matteo88.employeedirectoryapi.controller;

import com.matteo88.employeedirectoryapi.model.Employee;
import com.matteo88.employeedirectoryapi.service.EmployeeService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
@Api(
        name = "Employee Directory API",
        description = "Provides a list of methods that manage employees details",
        stage = ApiStage.RC)
public class DirectoryController {

    private EmployeeService employeeService;

    public DirectoryController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    // add mapping for "/list"

    @GetMapping("/list")
    @ApiMethod(description = "Get all employees from database")
    public String listEmployees(Model theModel) {

        // get employees from db
        List<Employee> theEmployees = employeeService.findAll();

        // add to the spring model
        theModel.addAttribute("employees", theEmployees);

        return "list-employees";
    }

    @GetMapping("/showFormForAdd")
    @ApiMethod(description = "Show form to add employee")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Employee theEmployee = new Employee();

        theModel.addAttribute("employee", theEmployee);

        return "employee-form";
    }

    @GetMapping("/showFormForUpdate")
    @ApiMethod(description = "Show form to update the employee details")
    public String showFormForUpdate(@RequestParam("employeeId") int theId,
                                    Model theModel) {

        // get the employee from the service
        Employee theEmployee = employeeService.findById(theId);

        // set employee as a model attribute to pre-populate the form
        theModel.addAttribute("employee", theEmployee);

        // send over to our form
        return "employee-form";
    }

    @GetMapping("/contact")
    @ApiMethod(description = "Show form to contact employee")
    public String showContactForm() {
        return "email-form";
    }

    @PostMapping("/save")
    @ApiMethod(description = "Saves the employee to the database")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

        // save the employee
        employeeService.save(theEmployee);

        // use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }

    @GetMapping("/delete")
    @ApiMethod(description = "Deletes the employee with the given id")
    public String delete(@RequestParam("employeeId") int theId) {

        // delete the employee
        employeeService.deleteById(theId);

        // redirect to /employees/list
        return "redirect:/employees/list";

    }
}
