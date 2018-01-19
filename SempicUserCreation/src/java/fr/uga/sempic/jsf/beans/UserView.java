/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.sempic.jsf.beans;

import fr.uga.miashs.sempic.model.SempicUser;
import fr.uga.miashs.sempic.model.datalayer.*;
import fr.uga.miashs.sempic.util.Roles;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author Jerome David <jerome.david@univ-grenoble-alpes.fr>
 */
@Named
@SessionScoped
public class UserView implements Serializable {

    private SempicUser selected;
    
    @Inject
    private AuthManager auth;

    @EJB
    private SempicUserDao dao;
    
    private SempicUser current;
    
    public SempicUser getCurrent(){
        return current;
    }
    public void setCurrent(SempicUser current){
        this.current = selected;
    }
    
    public String create() {
        try { 
            dao.create(selected);
        
            // verifions mail si user@root donc admin de base
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

    public SempicUser getSelected() {
        if (selected == null) {
            selected = new SempicUser();
            selected.setRoles(Collections.singleton(Roles.USER));
        }
        return selected;
    }

    public void setSelected(SempicUser selected) {
        this.selected = selected;
    }
    
    public List<SempicUser> getUsers() {
        return dao.readAll();
    }
}




