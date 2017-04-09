package com.clea.nantesjug.referentiels.dto;

import java.io.Serializable;

public class PersonnageDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7131458558913888855L;

	private String id;
	
	private String nom, prenom, surnom,email;
	
	private int age;
	
	
	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PersonneDTO [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", age=" + age
				+ "]";
	}



	public PersonnageDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
}
