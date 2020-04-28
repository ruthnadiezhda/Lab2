package com.example.lab2.Controller;


import com.example.lab2.Entity.Department;
import com.example.lab2.Repository.DepartmentRepository;
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
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentRepository departmentRepository;

    @GetMapping(value = {"listar",""})
    public String listarCountry(Model model){

        List<Department> listaDep = departmentRepository.findAll();

        model.addAttribute("listaDepas",listaDep);

        return "department/listaDep";
    }

    @GetMapping("/nuevo")
    public String nuevoCountryForm(){

        return "department/nuevoFormDep";

    }


    @PostMapping("/guardar")
    public String guardarShipper(Department department){

        departmentRepository.save(department);


        return "redirect:/department";
    }

    @GetMapping("/editarDepa")
    public String editarRegionForm(@RequestParam("id") int id,
                                   Model model){
        Optional<Department> opt = departmentRepository.findById(id);

        if (opt.isPresent()){
            Department department = opt.get();
            model.addAttribute("department",department);
            return "department/editFormDepa";
        }else{
            return "redirect:/department";
        }

    }

    @GetMapping("/borrarDepa")
    public String borrarShipperForm(@RequestParam("id") int id,
                                    Model model){
        Optional<Department> opt = departmentRepository.findById(id);

        if (opt.isPresent()){

            departmentRepository.deleteById(id);

        }

        return "redirect:/department";
    }


    @PostMapping(value = "/buscarDepa")
    public String buscarTransportista(@RequestParam("department_name") String department_name,
                                      RedirectAttributes attr,
                                      Model model){

        //List<Shipper> listaTransportistas = shipperRepository.findByCompanyName(companyname);


        List<Department> listaDep = departmentRepository.buscarDepa(department_name);

        if (listaDep.isEmpty()){

            attr.addFlashAttribute("msg","no se encontraron resultados");

        }

        model.addAttribute("listaDepas",listaDep);

        return "department/listaDep";
    }









}
