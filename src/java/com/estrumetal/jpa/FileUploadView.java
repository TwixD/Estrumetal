package com.estrumetal.jpa;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FileUtils;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class FileUploadView implements Serializable{

    public void handleFileUpload(FileUploadEvent event) {
        System.out.println("FUCK");
        if (event==null) {
            System.out.println("FUCKING NULL");
        }
      
        FacesMessage message = new FacesMessage("Exito ", event.getFile().getFileName() + " esta cargado.");
        FacesContext.getCurrentInstance().addMessage(null, message);

        UploadedFile uploadedFile = (UploadedFile) event.getFile();

        InputStream inputStr = null;
        try {
            inputStr = uploadedFile.getInputstream();
        } catch (IOException e) {
            //log error
        }

        String destPath = "/home/twix/NetBeansProjects/";
        File destFile = new File(destPath);

        try {
            FileUtils.copyInputStreamToFile(inputStr, destFile);
        } catch (IOException e) {
            //log error
        }

    }
}
