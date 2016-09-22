package yt.item8.dao.hibernate;

import yt.item8.model.Brand;
import yt.item8.dao.BrandDao;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("brandDao")
public class BrandDaoHibernate extends GenericDaoHibernate<Brand, Integer> implements BrandDao {

    public BrandDaoHibernate() {
        super(Brand.class);
    }
}
