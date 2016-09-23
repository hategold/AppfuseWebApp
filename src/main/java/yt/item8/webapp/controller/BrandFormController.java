package yt.item8.webapp.controller;

import org.apache.commons.lang.StringUtils;
import yt.item8.service.BrandManager;
import yt.item8.model.Brand;
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
@RequestMapping("/brandform*")
public class BrandFormController extends BaseFormController {

	private BrandManager brandManager = null;

	@Autowired
	public void setBrandManager(BrandManager brandManager) {
		this.brandManager = brandManager;
	}

	public BrandFormController() {
		setCancelView("redirect:brands");
		setSuccessView("redirect:brands");
	}

	@ModelAttribute
	@RequestMapping(
		method = RequestMethod.GET)
	protected Brand showForm(HttpServletRequest request) throws Exception {
		String brandId = request.getParameter("brandId");

		if (!StringUtils.isBlank(brandId)) {
			return brandManager.get(new Integer(brandId));
		}

		return new Brand();
	}

	@RequestMapping(
		method = RequestMethod.POST)
	public String onSubmit(Brand brand, BindingResult errors, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getParameter("cancel") != null) {
			return getCancelView();
		}

		if (validator != null) { // validator is null during testing
			validator.validate(brand, errors);
			
			if (errors.hasErrors() && request.getParameter("delete") == null && brand.getBrandId() != null) { // don't validate when deleting
				log.debug("validate failed...");
				log.debug(brand);
				return "brandform";
			}
		}

		log.debug("entering 'onSubmit' method...");

		boolean isNew = (brand.getBrandId() == null);
		String success = getSuccessView();
		Locale locale = request.getLocale();

		if (request.getParameter("delete") != null) {
			brandManager.remove(brand.getBrandId());
			saveMessage(request, getText("brand.deleted", locale));
		} else {
			brandManager.save(brand);
			String key = (isNew) ? "brand.added" : "brand.updated";
			saveMessage(request, getText(key, locale));

			if (!isNew) {
				success = "redirect:brandform?brandId=" + brand.getBrandId();
			}
		}

		return success;
	}
}
