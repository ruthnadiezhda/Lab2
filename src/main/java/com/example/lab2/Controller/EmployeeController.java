package com.example.lab2.Controller;


import com.example.lab2.Entity.Employee;
import com.example.lab2.Entity.Job;
import com.example.lab2.Repository.EmployeeRepository;
import com.example.lab2.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;
    JobRepository jobRepository;

    @GetMapping(value={"/list"})
    public String listarEmpleados(Model model){
        List<Employee> lista = employeeRepository.findAll();


        model.addAttribute("lista", lista);

        return "employee/list";
    }

    @GetMapping("/edit")
    public String editarEmpleado(Model model,
                                      @RequestParam("id") String id) {

        Optional<Employee> optEmploye = employeeRepository.findById(id);

        if (optEmploye.isPresent()) {
            Employee employee = optEmploye.get();
            model.addAttribute("employee", employee);
            return "employee/editFrm";
        } else {
            return "redirect:/employee/list";
        }
    }


    @GetMapping("/delete")
    public String borrarEmpleado(Model model,
                                      @RequestParam("id") String id,
                                      RedirectAttributes attr) {

        Optional<Employee> optShipper = employeeRepository.findById(id);

        if (optShipper.isPresent()) {
            employeeRepository.deleteById(id);
            attr.addFlashAttribute("msgDelete","El transportista ha sido borrado correctamente");
        }
        return "redirect:/employee/list";

    }


    @PostMapping("/save")
    public String guardarNuevoEmpleado(Employee employee, RedirectAttributes attr) {

        if(employee.getEmployee_id() !=null){
            attr.addFlashAttribute("msgCreate","Empleado actualizado correctamente");
        }else {
            attr.addFlashAttribute("msgCreate","Empleado creado correctamente");
        }

        employeeRepository.save(employee);
        return "redirect:/employee/list";
    }

    @GetMapping("/new")
    public String nuevoEmpleadoFrm() {
        return "employee/newFrm";
    }




}
