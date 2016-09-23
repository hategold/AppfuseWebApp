package yt.item8.service.impl;

import yt.item8.dao.ShoesDao;
import yt.item8.model.Shoes;
import yt.item8.service.ShoesManager;
import org.appfuse.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("shoesManager")
@WebService(serviceName = "ShoesService", endpointInterface = "yt.item8.service.ShoesManager")
public class ShoesManagerImpl extends GenericManagerImpl<Shoes, Integer> implements ShoesManager {
    ShoesDao shoesDao;

    @Autowired
    public ShoesManagerImpl(ShoesDao shoesDao) {
        super(shoesDao);
        this.shoesDao = shoesDao;
    }
}