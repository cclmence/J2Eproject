package fr.uga.miashs.sempic.model.datalayer;

import fr.uga.miashs.sempic.model.Album;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;


@Stateless
public class AlbumDao extends AbstractJpaDao<Album, Long> {

    @PersistenceContext(unitName = "SempicPU")
    private EntityManager em;

    public AlbumDao() { 
        super(Album.class); 
    }

    @Override
    protected EntityManager getEntityManager() { 
        return em; 
    }

    public Album getByTitle(String title) {
        try {
            return (Album) getEntityManager()
                    .createQuery("SELECT a FROM Album a " + "WHERE a.title=:title")
                    .setParameter("title", title)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }    
}