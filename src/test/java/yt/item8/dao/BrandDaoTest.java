package yt.item8.dao;

import org.appfuse.dao.BaseDaoTestCase;
import yt.item8.model.Brand;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BrandDaoTest extends BaseDaoTestCase {
    @Autowired
    private BrandDao brandDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveBrand() {
        Brand brand = new Brand();

        // enter all required fields

        log.debug("adding brand...");
        brand = brandDao.save(brand);

        brand = brandDao.get(brand.getBrandId());

        assertNotNull(brand.getBrandId());

        log.debug("removing brand...");

        brandDao.remove(brand.getBrandId());

        // should throw DataAccessException 
        brandDao.get(brand.getBrandId());
    }
}