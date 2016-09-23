package yt.item8.service;

import org.appfuse.service.GenericManager;
import yt.item8.model.Shoes;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface ShoesManager extends GenericManager<Shoes, Integer> {
    
}