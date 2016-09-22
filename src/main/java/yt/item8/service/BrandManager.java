package yt.item8.service;

import org.appfuse.service.GenericManager;
import yt.item8.model.Brand;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface BrandManager extends GenericManager<Brand, Integer> {
    
}