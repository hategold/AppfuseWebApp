package yt.item8.dao.hibernate;

import yt.item8.model.Shoes;
import yt.item8.dao.ShoesDao;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("shoesDao")
public class ShoesDaoHibernate extends GenericDaoHibernate<Shoes, Integer> implements ShoesDao {

    public ShoesDaoHibernate() {
        super(Shoes.class);
    }
}
