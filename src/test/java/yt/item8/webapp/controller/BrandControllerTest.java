package yt.item8.webapp.controller;

import yt.item8.service.BrandManager;
import yt.item8.model.Brand;

import yt.item8.webapp.controller.BaseControllerTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BrandControllerTest extends BaseControllerTestCase {
    @Autowired
    private BrandManager brandManager;
    @Autowired
    private BrandController controller;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver).build();
    }

    @Test
    public void testHandleRequest() throws Exception {
        mockMvc.perform(get("/brands"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("brandList"))
            .andExpect(view().name("brands"));
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        brandManager.reindex();

        Map<String,Object> model = mockMvc.perform((get("/brands")).param("q", "*"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("brandList"))
            .andReturn()
            .getModelAndView()
            .getModel();

        List results = (List) model.get("brandList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}
