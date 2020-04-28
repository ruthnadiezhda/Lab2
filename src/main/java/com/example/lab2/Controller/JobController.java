package com.example.lab2.Controller;

import com.example.lab2.Entity.Job;
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
@RequestMapping("/job")
public class JobController {

    @Autowired
    JobRepository jobRepository;

    @GetMapping(value = {"", "/list"})
    public String listaJobs(Model model) {

        List<Job> lista = jobRepository.findAll();
        model.addAttribute("jobList", lista);
        return "Jobs/list";
    }

    @PostMapping("/buscarsalario")
    public String buscarSalario(@RequestParam("searchField") Integer searchField,
                                Model model,
                                RedirectAttributes attr) {

        List<Job> listaJobs = jobRepository.buscarSalario(searchField);

        if (listaJobs.isEmpty()) {
            attr.addFlashAttribute("msg", "No hay elementos");

        } else {
            model.addAttribute("jobList", listaJobs);
        }
        return "redirect:/Jobs/list";

    }

    @GetMapping("/new")
    public String nuevoJobForm() {

        return "Jobs/newForm";
    }

    @PostMapping(value = "/guardar")
    public String guardarJob(Job job) {
        if (job.getJob_id() != null) {
            Optional<Job> opt = jobRepository.findById(job.getJob_id());
            jobRepository.save(job);
        }
        return "redirect:/job/list";
    }


    @GetMapping(value = "/borrar")
    public String borrarJob(@RequestParam("job_id") String job_id, RedirectAttributes attr) {
        Optional<Job> opt = jobRepository.findById(job_id);
        if (opt.isPresent()) {
            jobRepository.deleteById(job_id);
            attr.addFlashAttribute("msg", "Job borrado exitosamente");
        }
        return "redirect:/Job/list";


    }

    @GetMapping(value = "/editar")
    public String editForm(Model model,
                           @RequestParam("job_id") String job_id) {
        Optional<Job> opt = jobRepository.findById(job_id);
        if (opt.isPresent()) {
            model.addAttribute("job", opt.get());
            return "Jobs/editForm";
        } else {
            return "redirect:/Job/list";
        }

    }




}
