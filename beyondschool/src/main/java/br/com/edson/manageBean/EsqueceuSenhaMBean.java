package br.com.edson.manageBean;

import java.io.Serializable;

import javax.inject.Named;

@Named
@javax.faces.view.ViewScoped
public class EsqueceuSenhaMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private String senha;
    
    private String nascimento;
    
    private String nomeResponsavel;
    
 
}
