package com.example.demo.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Libro;
import com.example.demo.model.Reporte;
import com.example.demo.service.LibroService;

import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;


@Controller
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	private LibroService libroService;
	
	@GetMapping("/report")
	public void report(HttpServletResponse response) throws JRException, IOException {
		
		//1. acceder al reporte
		InputStream jasperStream = this.getClass().getResourceAsStream("/reportes/ReporteLibro.jasper");
		
		List<Libro> listLibros = libroService.getAllLibros();
		
		//2. procesar los datos
		Map<String, Object> params = new HashMap<>();
		params.put("libros", "Jair Fernandez");
		
		List<Reporte> listLibrosRep = new ArrayList<>();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fecha1 = LocalDate.now();
		LocalDate fecha2;
		
		for(Libro libro:listLibros) {
			fecha2 = LocalDate.parse(libro.fecha, formatter);
			long diferenciaDias = calcularDiferenciaDias(fecha1, fecha2);
			System.out.println("diferencia de dias: "+diferenciaDias);
			if(diferenciaDias<=180) {
				listLibrosRep.add(new Reporte(libro.idlibro, libro.nombre, libro.autor, libro.fecha, libro.genero.nombre));
			}
			
		}
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listLibrosRep);
		
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		
		//3. configuración
		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "filename=reporte_ejemplo.pdf");
		
		//4. exportar reporte
		final OutputStream outputStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	}
	public static long calcularDiferenciaDias(LocalDate fecha1, LocalDate fecha2) {
		return Math.abs(fecha1.toEpochDay() - fecha2.toEpochDay());
	}
}
