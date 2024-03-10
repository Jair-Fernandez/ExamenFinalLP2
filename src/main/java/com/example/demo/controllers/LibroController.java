package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Genero;
import com.example.demo.model.Libro;
import com.example.demo.service.GeneroService;
import com.example.demo.service.LibroService;

@Controller
@RequestMapping("/libro")
public class LibroController {
	@Autowired
	private LibroService libroService;
	
	@Autowired
	private GeneroService generoService;
	
	@GetMapping("/libros")
	public String getAllLibro(Model model) {
		
		List<Libro> lisLibros = libroService.getAllLibros();
		
		for(Libro libro:lisLibros) {
			
			System.out.println(libro.idlibro);
			System.out.println(libro.nombre);
			System.out.println(libro.genero.idgenero);
			System.out.println(libro.genero.nombre);
			
		}
		
		model.addAttribute("libro",lisLibros);
		return "libroList";
						
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("generos",generoService.getAllGeneros());
		return "libroRegister";				
	}
	
	@PostMapping("/register")
	public String createLibro(@RequestParam("nombre") String nombre, @RequestParam("autor") String autor, @RequestParam("fecha") String fecha, @RequestParam("idgenero") Genero genero, Model model) {
		
		Libro libro = new Libro();
		libro.nombre=nombre;
		libro.autor = autor;
		libro.fecha=fecha;
		libro.genero = genero;
		
		libroService.createLibro(libro);
		List<Libro> lisLibros = libroService.getAllLibros();
		model.addAttribute("libro",lisLibros);
		
		return "libroList";
		
		
	}
	
	
	@GetMapping("/edit/{id}")
	public String getPeliculaByID(@PathVariable Long id,Model model) {
		Libro libro = libroService.getLibroById(id);
		model.addAttribute("libro",libro);
		model.addAttribute("generos",generoService.getAllGeneros());
		return "libroEdit";
	}
	
	@PostMapping("/edit")
	public String editLibro( @RequestParam("id") Long id, @RequestParam("nombre") String nombre, @RequestParam("autor") String autor, @RequestParam("fecha") String fecha, @RequestParam("idgenero") Genero genero, Model model) {
		
		Libro libro = libroService.getLibroById(id);
		libro.nombre=nombre;
		libro.autor = autor;
		libro.fecha = fecha;
		libro.genero = genero;
		
		libroService.createLibro(libro);
		List<Libro> lisLibros = libroService.getAllLibros();
		model.addAttribute("libro",lisLibros);
		
		return "libroList";
		
		
	}
	
	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable Long id, Model model) {
		libroService.deleteLibro(id);
		
		List<Libro> lisLibros = libroService.getAllLibros();
		model.addAttribute("libro", lisLibros);
		
		return "libroList";
	}
}
