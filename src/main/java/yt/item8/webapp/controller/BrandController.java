package yt.item8.webapp.controller;

import org.appfuse.dao.SearchException;
import yt.item8.service.BrandManager;
import yt.item8.model.Brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/brands*")
public class BrandController {
    private BrandManager brandManager;

    @Autowired
    public void setBrandManager(BrandManager brandManager) {
        this.brandManager = brandManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(brandManager.search(query, Brand.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(brandManager.getAll());
        }
        return model;
    }
}
