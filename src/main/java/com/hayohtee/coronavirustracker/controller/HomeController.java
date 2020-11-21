package com.hayohtee.coronavirustracker.controller;

import java.util.List;

import com.hayohtee.coronavirustracker.model.CoronavirusData;
import com.hayohtee.coronavirustracker.service.CoronavirusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    CoronavirusService coronaVirusService;

    @GetMapping("/")
    public String home(Model model){
        List<CoronavirusData> coronavirusData = coronaVirusService.getData();
        int sumCurrentCases = coronavirusData.stream().mapToInt(data -> data.getCurrentCases()).sum();
        int sumPrevCases = coronavirusData.stream().mapToInt(data -> data.getPrevDayCases()).sum(); 

        model.addAttribute("coronavirusData", coronavirusData);
        model.addAttribute("sumCurrentCases", sumCurrentCases);
        model.addAttribute("sumPrevCases", sumPrevCases);
        
        return "home";
    }
}
