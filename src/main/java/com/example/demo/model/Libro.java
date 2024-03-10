package com.example.demo.model;

import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "libro")
public class Libro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idlibro", nullable = false)
	public Long idlibro;
	
	@Size(min = 4, max = 60)
	@Column(name = "nombre", nullable = false)
	public String nombre;
	
	@Column(name = "autor", nullable = false)
	public String autor;
	
	@Column(name = "fecha", nullable = false)
	public String fecha;
	
	@ManyToOne
	@JoinColumn(name = "idgenero", nullable = false)
	public Genero genero;
}
