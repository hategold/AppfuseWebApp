package yt.item8.webapp.controller;

import org.apache.commons.lang.StringUtils;

import yt.item8.service.BrandManager;
import yt.item8.service.ShoesManager;
import yt.item8.service.impl.BrandManagerImpl;
import yt.item8.dao.hibernate.BrandDaoHibernate;
import yt.item8.model.Brand;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/shoesform*")
public class ShoesFormController extends BaseFormController {

	private ShoesManager shoesManager = null;
	private BrandManager brandManager = null;

	protected Map brandMap;
	protected List brandList;

	@Autowired
	public void setShoesManager(ShoesManager shoesManager) {
		this.shoesManager = shoesManager;
	}
	
	@Autowired
	public void setBrandManager(BrandManager brandManager) {
		log.debug("autowired bmanager");
		this.brandManager = brandManager;
	}

	public ShoesFormController() {
		setCancelView("redirect:shoess");
		setSuccessView("redirect:shoess");
	}

	@ModelAttribute
	@RequestMapping(
		method = RequestMethod.GET)
	protected Shoes showForm(HttpServletRequest request) throws Exception {
		String shoesId = request.getParameter("shoesId");

		if (!StringUtils.isBlank(shoesId)) {
			return shoesManager.get(new Integer(shoesId));
		}

		return new Shoes();
	}

	@RequestMapping(
		method = RequestMethod.POST)
	public String onSubmit(Shoes shoes, BindingResult errors, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getParameter("cancel") != null) {
			return getCancelView();
		}
		shoes.setBrand(this.brandManager.get(shoes.getBrand().getBrandId()));

		if (validator != null) { // validator is null during testing
			validator.validate(shoes, errors);

			if (errors.hasErrors() && request.getParameter("delete") == null && shoes.getShoesId() != null) { // don't validate when deleting
				return "shoesform";
			}
		}

		log.debug("entering 'onSubmit' method...");
		log.debug(shoes.toString());
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
				success = "redirect:shoess";
			}
		}

		return success;
	}

//	@ModelAttribute("brandMap")
//	public Map getBrandMapData(HttpServletRequest request, Object command) throws Exception {
//
//		Map<Integer, String> brandMap = new HashMap<Integer, String>();
//		Brand b = new Brand(1, "hhw", "www", "UCC");
//		Brand c = new Brand(2, "kkk", "www", "UCC");
//		brandMap.put(b.getBrandId(), b.getBrandName());
//		brandMap.put(c.getBrandId(), c.getBrandName());
//		log.debug("in brandMap");
//		this.brandMap = brandMap;
//		return this.brandMap;
//	}
//	
	@ModelAttribute("brandList")
	public List getBrandMap(HttpServletRequest request, Object command) throws Exception{
		
		List<Brand> brandList = this.brandManager.getAll();
		
		log.debug("in brandList getter");
		this.brandList = brandList;
		return brandList;
	}

//	protected Map referenceData(HttpServletRequest request) throws Exception {
//		Map referenceData = new HashMap();
//		Map<Integer, Integer> country = new LinkedHashMap<Integer, Integer>();
//		country.put(1, 3);
//		country.put(2, 4);
//		country.put(3,5);
//		country.put(4, 6);
//		referenceData.put("countryList", country);
//		return referenceData;
//	}
}
