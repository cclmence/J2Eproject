package fr.uga.miashs.sempic.model;

import fr.uga.miashs.sempic.model.Album;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-19T14:13:06")
@StaticMetamodel(Photo.class)
public class Photo_ { 

    public static volatile SingularAttribute<Photo, Album> album;
    public static volatile SingularAttribute<Photo, String> name;
    public static volatile SingularAttribute<Photo, Long> id;
    public static volatile SingularAttribute<Photo, String> place;
    public static volatile SingularAttribute<Photo, String> photoDate;

}