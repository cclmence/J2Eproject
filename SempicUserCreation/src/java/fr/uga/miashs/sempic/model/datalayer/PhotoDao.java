package fr.uga.miashs.sempic.model.datalayer;

import fr.uga.miashs.sempic.model.Photo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class PhotoDao extends AbstractJpaDao<Photo, Long> {

    @PersistenceContext(unitName = "SempicPU")
    private EntityManager em;

    public PhotoDao() {
        super(Photo.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Photo getByName(String name) {
        try {
            return (Photo) getEntityManager()
                    .createQuery("SELECT p FROM Photo p " + "WHERE p.name=:name")
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
