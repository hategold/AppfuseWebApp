package yt.item8.webapp.controller;

import org.appfuse.dao.SearchException;
import yt.item8.service.ShoesManager;
import yt.item8.model.Shoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shoess*")
public class ShoesController {
    private ShoesManager shoesManager;

    @Autowired
    public void setShoesManager(ShoesManager shoesManager) {
        this.shoesManager = shoesManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(shoesManager.search(query, Shoes.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(shoesManager.getAll());
        }
        return model;
    }
}
