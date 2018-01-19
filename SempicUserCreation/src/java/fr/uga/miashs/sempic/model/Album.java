package fr.uga.miashs.sempic.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
    @NamedQuery(name="Album.getTitle()",
                query="SELECT a FROM Album a WHERE a.title=:title"),
})
public class Album implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String title;
    
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private Collection<Photo> photos;
    
//    @NotNull
//    @ManyToOne(cascade = CascadeType.PERSIST)
//    private SempicUser owner;
    
//    @ManyToMany
//    private Set<SempicUser> sharedWith;

//    public Album(SempicUser connectedUser) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//	
//    public Album(SempicUser owner) {
//	this.owner=owner;
//    }
    
    public Album(){
        
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public Collection<Photo> getPhotos() { return photos; }
    public void setPhotos(Collection<Photo> photos) { this.photos = photos; }
    
//    public SempicUser getOwner() { return owner; }
//    public void setOwner(SempicUser owner) { this.owner = owner; }
    
//    public Set<SempicUser> getSharedWith() { return sharedWith; }
    
    public Set<String> getPhotosName() {
	Set<String> photosName = new HashSet<String>();
	for (Photo p : photos)
            photosName.add(p.getName());
	return photosName;
    }

//    public SempicUser shareAlbum(SempicUser user){
//	if(sharedWith == null)
//            sharedWith = new HashSet<SempicUser>();
//	sharedWith.add(user);
//	for (SempicUser a : sharedWith)
//            System.out.println("sharedWith : " + a.getFirstname());
//	return user;
//	}
	
    public Photo addPhoto(Photo photo){
	if(photos == null)
            photos = new HashSet<Photo>();
        photos.add(photo);
	return photo;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Album)) { return false; }
        
        Album other = (Album) object;
        
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "fr.uga.miashs.sempic.model.Album[ id=" + id + " ]";
    }    
}