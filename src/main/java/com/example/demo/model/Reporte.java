package com.example.demo.model;

public class Reporte {
	public Long idlibro;
	public String nombre;
	public String autor;
	public String fecha;
	public String genero;
	public Reporte(Long idlibro, String nombre, String autor, String fecha, String genero) {
		super();
		this.idlibro = idlibro;
		this.nombre = nombre;
		this.autor = autor;
		this.fecha = fecha;
		this.genero = genero;
	}
	public Long getIdlibro() {
		return idlibro;
	}
	public void setIdlibro(Long idlibro) {
		this.idlibro = idlibro;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
}
