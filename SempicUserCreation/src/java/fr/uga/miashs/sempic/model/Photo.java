package fr.uga.miashs.sempic.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.servlet.http.Part;
import javax.validation.constraints.NotNull;
import javax.persistence.ManyToOne;

@Entity
public class Photo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(unique = true)
    private String name;
    
    //@NotNull
    @ManyToOne
    private Album album;
    
    @NotNull
    private String place;
    
    @NotNull
    private String photoDate;
    
//    @NotNull
//    private String owner;
    // private Part file;
    
    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
           this.id = id; 
    }

    public String getName() { 
        return name; 
    }
    public void setName(String name) { 
        this.name = name; 
    }
    
    public Album getAlbum() { 
        return album; 
    }
    public void setAlbum(Album album) { 
        this.album = album; 
    }

    public String getPlace() { 
        return place; 
    }
    public void setPlace(String place) { 
        this.place = place; 
    }

    public String getPhotoDate() { 
        return photoDate; 
    }
    public void setPhotoDate(String photoDate) { 
        this.photoDate = photoDate; 
    }
    
//    public String getOwner() { 
//        return owner; 
//    }
//    public void setOwner(String owner) { 
//        this.owner = owner; 
//    }
    
//    public Part getFile() { 
//        return this.file; 
//    }
//    public void setFile(Part file) { 
//        this.file = file; 
//    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Photo)) { return false; }
        
        Photo other = (Photo) object;
        
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "fr.uga.miashs.sempic.model.Photo[ id=" + id + " ]";
    }
}