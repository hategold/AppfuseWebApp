package yt.item8.dao;

import org.appfuse.dao.BaseDaoTestCase;
import yt.item8.model.Shoes;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShoesDaoTest extends BaseDaoTestCase {
    @Autowired
    private ShoesDao shoesDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveShoes() {
        Shoes shoes = new Shoes();

        // enter all required fields

        log.debug("adding shoes...");
        shoes = shoesDao.save(shoes);

        shoes = shoesDao.get(shoes.getShoesId());

        assertNotNull(shoes.getShoesId());

        log.debug("removing shoes...");

        shoesDao.remove(shoes.getShoesId());

        // should throw DataAccessException 
        shoesDao.get(shoes.getShoesId());
    }
}