package yt.item8.webapp.controller;

import yt.item8.service.ShoesManager;
import yt.item8.model.Shoes;

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

public class ShoesControllerTest extends BaseControllerTestCase {
    @Autowired
    private ShoesManager shoesManager;
    @Autowired
    private ShoesController controller;

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
        mockMvc.perform(get("/shoess"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("shoesList"))
            .andExpect(view().name("shoess"));
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        shoesManager.reindex();

        Map<String,Object> model = mockMvc.perform((get("/shoess")).param("q", "*"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("shoesList"))
            .andReturn()
            .getModelAndView()
            .getModel();

        List results = (List) model.get("shoesList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}
