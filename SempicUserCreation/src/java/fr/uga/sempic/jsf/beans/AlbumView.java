package fr.uga.sempic.jsf.beans;

import fr.uga.miashs.sempic.model.Album;
import fr.uga.miashs.sempic.model.SempicUser;
import fr.uga.miashs.sempic.model.datalayer.*;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.validation.ConstraintViolationException;

@Named
@SessionScoped
public class AlbumView implements Serializable {

    private Album selected;

    @Inject
    private AuthManager auth;

    @Inject
    private PhotoView photoView;

    @EJB
    private AlbumDao dao;
    
    private Album currentAlbum;
//    private String albId;

    public String getAlbumName() {
        return currentAlbum.getTitle();
    }

//    public String getAlbId() {
//        return albId;
//    }
//
//    public void setAlbId(String albId) {
//        this.albId = albId;
//    }
    private Part file;

    public Part getFile() {
        return file;
    }

    public void setFile(Part photo) {
        this.file = photo;
    }

    public Album getSelected() {
        if (selected == null) {
            selected = new Album();
        }
        return selected;
    }
    
    public void setSelected(Album selected) {
        this.selected = selected;
    }

    public List<Album> getAlbums() {
        return dao.readAll();
    }

    public String create() {
        try {
           // Album alb = new Album();
           
//            FacesContext context = FacesContext.getCurrentInstance();
//            Album a = dao.getByTitle(selected.getTitle());
            dao.create(selected);
        } catch (EJBException ex) {
            if (ex.getCause() instanceof ConstraintViolationException) {
                ConstraintViolationException vEx = (ConstraintViolationException) ex.getCause();

                vEx.getConstraintViolations().forEach(cv -> {
                    System.out.println(cv);
                });

                vEx.getConstraintViolations().forEach(cv -> {
                    FacesContext.getCurrentInstance().addMessage("validationError", new FacesMessage(cv.getMessage()));
                });
            }
        }
        
        photoView.newPhoto(selected);

        return "photo.xhtml?redirect=true";
    }

    public void updateAlbum(Album selected) {
        dao.update(selected);
    }
    
    public void deleteAlbum(Album selected) {
        dao.delete(selected);
    }
}

//<ui:repeat var="alb" value="#{albumView.albumList}">
//                    <div class="album">
//                        <h:form>
//                            <p>
//                                <label outcome="alb">#{alb.name}</label>
//                            </p>
//                            <p>
//                                <h:commandButton value="open" action="#{albumView.openAlbum(alb)}" />
//                            </p>
//                            <p>
//                                <h:commandButton value="delete" action="#{albumView.deleteAlbum(alb)}" />
//                            </p>
//                        </h:form>
//
//                    </div>
//                </ui:repeat>

//utiliser pictureStore !!
