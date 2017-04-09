package com.clea.nantesjug.referentiels.dto;

public class ReacteurDTO {
	private Long id;
	private Long idCuve;
	private CuveDTO cuve;
	private String libelle;
	private boolean actif;
	private double niveauUtilisation;
	private double seuilTolerance;
	
	public ReacteurDTO(){
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public boolean isActif() {
		return actif;
	}
	public void setActif(boolean actif) {
		this.actif = actif;
	}
	public double getNiveauUtilisation() {
		return niveauUtilisation;
	}
	public void setNiveauUtilisation(double niveauUtilisation) {
		this.niveauUtilisation = niveauUtilisation;
	}
	public double getSeuilTolerance() {
		return seuilTolerance;
	}
	public void setSeuilTolerance(double seuilTolerance) {
		this.seuilTolerance = seuilTolerance;
	}

	public Long getIdCuve() {
		return idCuve;
	}

	public void setIdCuve(Long idCuve) {
		this.idCuve = idCuve;
	}

	public CuveDTO getCuve() {
		return cuve;
	}

	public void setCuve(CuveDTO cuve) {
		this.cuve = cuve;
	}
}
