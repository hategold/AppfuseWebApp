package yt.item8.service.impl;

import java.util.ArrayList;
import java.util.List;

import yt.item8.dao.BrandDao;
import yt.item8.model.Brand;
import org.appfuse.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class BrandManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private BrandManagerImpl manager;

    @Mock
    private BrandDao dao;

    @Test
    public void testGetBrand() {
        log.debug("testing get...");
        //given
        final int brandId = 7L;
        final Brand brand = new Brand();
        given(dao.get(brandId)).willReturn(brand);

        //when
        Brand result = manager.get(brandId);

        //then
        assertSame(brand, result);
    }

    @Test
    public void testGetBrands() {
        log.debug("testing getAll...");
        //given
        final List<Brand> brands = new ArrayList<>();
        given(dao.getAll()).willReturn(brands);

        //when
        List result = manager.getAll();

        //then
        assertSame(brands, result);
    }

    @Test
    public void testSaveBrand() {
        log.debug("testing save...");

        //given
        final Brand brand = new Brand();
        // enter all required fields

        given(dao.save(brand)).willReturn(brand);

        //when
        manager.save(brand);

        //then
        verify(dao).save(brand);
    }

    @Test
    public void testRemoveBrand() {
        log.debug("testing remove...");

        //given
        final int brandId = -11L;
        willDoNothing().given(dao).remove(brandId);

        //when
        manager.remove(brandId);

        //then
        verify(dao).remove(brandId);
    }
}
