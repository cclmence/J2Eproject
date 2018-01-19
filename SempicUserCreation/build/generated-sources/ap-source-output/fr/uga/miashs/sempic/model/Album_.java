package fr.uga.miashs.sempic.model;

import fr.uga.miashs.sempic.model.Photo;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-19T20:45:25")
@StaticMetamodel(Album.class)
public class Album_ { 

    public static volatile SingularAttribute<Album, Long> id;
    public static volatile SingularAttribute<Album, String> title;
    public static volatile CollectionAttribute<Album, Photo> photos;

}