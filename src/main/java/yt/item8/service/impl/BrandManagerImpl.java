package yt.item8.service.impl;

import yt.item8.dao.BrandDao;
import yt.item8.model.Brand;
import yt.item8.service.BrandManager;
import org.appfuse.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("brandManager")
@WebService(serviceName = "BrandService", endpointInterface = "yt.item8.service.BrandManager")
public class BrandManagerImpl extends GenericManagerImpl<Brand, Integer> implements BrandManager {
    BrandDao brandDao;

    @Autowired
    public BrandManagerImpl(BrandDao brandDao) {
        super(brandDao);
        this.brandDao = brandDao;
    }
}