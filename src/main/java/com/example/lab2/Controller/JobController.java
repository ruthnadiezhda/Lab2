package com.example.lab2.Controller;

import com.example.lab2.Entity.Job;
import com.example.lab2.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class JobController {

    @Autowired
    JobRepository jobRepository;

    @GetMapping(value = {"", "/list"})
    public String listaJobs(Model model) {

        List<Job> lista = jobRepository.findAll();
        model.addAttribute("jobList", lista);
        return "list";
    }







}
