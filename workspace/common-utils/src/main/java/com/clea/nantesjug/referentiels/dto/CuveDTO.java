package com.clea.nantesjug.referentiels.dto;

public class CuveDTO {

	private int idCuve;
	private String libelle;
	private double temperature;
	private double temperatureMax;
	private double epaisseur;
	
	public CuveDTO(){
		
	}
	
	public int getIdCuve() {
		return idCuve;
	}
	public void setIdCuve(int idCuve) {
		this.idCuve = idCuve;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getTemperatureMax() {
		return temperatureMax;
	}
	public void setTemperatureMax(double temperatureMax) {
		this.temperatureMax = temperatureMax;
	}
	public double getEpaisseur() {
		return epaisseur;
	}
	public void setEpaisseur(double epaisseur) {
		this.epaisseur = epaisseur;
	}
}
