package fr.uga.sempic.jsf.beans;

import fr.uga.miashs.sempic.model.Album;
import fr.uga.miashs.sempic.model.Photo;
import fr.uga.miashs.sempic.model.datalayer.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import javax.validation.constraints.NotNull;

@Named
@SessionScoped
public class PhotoView implements Serializable {

    private Photo selected;

    @Inject
    private AuthManager auth;

    @EJB
    private PhotoDao dao;

    @NotNull
    private Part file;

    public Photo getSelected() {
        if (selected == null) {
            selected = new Photo();
        }
        return selected;
    }

    public void setSelected(Photo selected) {
        this.selected = selected;
    }

    public List<Photo> getPhotos() {
        return dao.readAll();
    }

//    public InputStream getInputStream() throws IOException {
//        InputStream in = selected.getInputStream();
//        return in;
//    }
    public String create() throws IOException {
        try {
            
            
            //file.getInputStream(); // récupère l'image elle-même
            //selected.setName; // mettre dans bdd puis dans picture store 
            // ajout picture dans la bdd
            // nom album récup nom du fichier file.getSubmittedFileName()
            //prendre inputstream le mettre dans picture store puis récupérer l'input avec son nom
            // puis le mettre dans le graphicImage (utiliser le stream)
//            FacesContext context = FacesContext.getCurrentInstance();
            //Photo p = dao.getByName(selected.getName());
            
            //selected.setName(file.getSubmittedFileName());
             uploadPhoto();
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
        return "";
    }

    public void uploadPhoto() {

        Path pictureStore = Paths.get("/Users/Clémence/Documents/NetBeansProjects/SempicUserCreation/build/web/resources/photos");
        Path thumbnailStore = Paths.get("/Users/Clémence/Documents/NetBeansProjects/SempicUserCreation/build/web/resources/photos/thumbnails");

        try {
            PictureStore ps = new PictureStore(pictureStore, thumbnailStore);
            InputStream is = file.getInputStream();

            String picPathString = pictureStore.toString() + "/" + file.getSubmittedFileName();
            Path picPath = Paths.get(picPathString);

            ps.storePicture(picPath, is);

            System.out.println("eveuzzgaga");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public void updatePhoto(Photo selected) {
        dao.update(selected);
    }

    public void deletePhoto(Photo selected) {
        dao.delete(selected);
    }

    void newPhoto(Album alb) {
        selected = new Photo();
        selected.setAlbum(alb);
    }
}

// utilisateur admin : système de promo utilisateur lambda 
