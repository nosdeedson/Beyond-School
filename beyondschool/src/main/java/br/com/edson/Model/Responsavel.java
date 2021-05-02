package br.com.edson.Model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "responsavel")
public class Responsavel extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
}
