package yt.item8.webapp.controller;

import org.apache.commons.lang.StringUtils;
import yt.item8.service.ShoesManager;
import yt.item8.model.Shoes;
import yt.item8.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/shoesform*")
public class ShoesFormController extends BaseFormController {
    private ShoesManager shoesManager = null;

    @Autowired
    public void setShoesManager(ShoesManager shoesManager) {
        this.shoesManager = shoesManager;
    }

    public ShoesFormController() {
        setCancelView("redirect:shoess");
        setSuccessView("redirect:shoess");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Shoes showForm(HttpServletRequest request)
    throws Exception {
        String shoesId = request.getParameter("shoesId");

        if (!StringUtils.isBlank(shoesId)) {
            return shoesManager.get(new Integer(shoesId));
        }

        return new Shoes();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Shoes shoes, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(shoes, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "shoesform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (shoes.getShoesId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            shoesManager.remove(shoes.getShoesId());
            saveMessage(request, getText("shoes.deleted", locale));
        } else {
            shoesManager.save(shoes);
            String key = (isNew) ? "shoes.added" : "shoes.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:shoesform?shoesId=" + shoes.getShoesId();
            }
        }

        return success;
    }
}
