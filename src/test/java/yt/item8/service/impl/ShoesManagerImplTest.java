package yt.item8.service.impl;

import java.util.ArrayList;
import java.util.List;

import yt.item8.dao.ShoesDao;
import yt.item8.model.Shoes;
import org.appfuse.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class ShoesManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private ShoesManagerImpl manager;

    @Mock
    private ShoesDao dao;

    @Test
    public void testGetShoes() {
        log.debug("testing get...");
        //given
        final Integer shoesId = 7;
        final Shoes shoes = new Shoes();
        given(dao.get(shoesId)).willReturn(shoes);

        //when
        Shoes result = manager.get(shoesId);

        //then
        assertSame(shoes, result);
    }

    @Test
    public void testGetShoess() {
        log.debug("testing getAll...");
        //given
        final List<Shoes> shoess = new ArrayList<>();
        given(dao.getAll()).willReturn(shoess);

        //when
        List result = manager.getAll();

        //then
        assertSame(shoess, result);
    }

    @Test
    public void testSaveShoes() {
        log.debug("testing save...");

        //given
        final Shoes shoes = new Shoes();
        // enter all required fields

        given(dao.save(shoes)).willReturn(shoes);

        //when
        manager.save(shoes);

        //then
        verify(dao).save(shoes);
    }

    @Test
    public void testRemoveShoes() {
        log.debug("testing remove...");

        //given
        final Integer shoesId = -11;
        willDoNothing().given(dao).remove(shoesId);

        //when
        manager.remove(shoesId);

        //then
        verify(dao).remove(shoesId);
    }
}
