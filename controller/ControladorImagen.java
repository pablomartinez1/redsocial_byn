package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Random;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.Part;

import model.Imagen;

@Stateless
public class ControladorImagen {
	@PersistenceContext
	private EntityManager imgManager;
	
	private static final File UPLOAD_DIR = new File("C:/imagenes");
	
	private static final char [] CHARS = "abcdefghijklmnopqrstwxyz0123456789".toCharArray();
	
	private static final int URL_LENGTH = 10;
	
	private final Random rnd = new Random();
	
	public boolean existFile(String path){
		return Files.exists(new File (UPLOAD_DIR,path).toPath());
	}
	
	public InputStream read(String url) throws FileNotFoundException{
		return new FileInputStream(new File (UPLOAD_DIR, url));
	}
	
	private String generarUrl(){
		char[] rndChars = new char [URL_LENGTH];
		for (int i = 0; i < URL_LENGTH; i++) {
			rndChars[i] = CHARS[rnd.nextInt(CHARS.length)];
		}
		String rndPath = new String(rndChars);
		if(existFile(rndPath)){
			return generarUrl();
		} else {
			return rndPath;
		}
	}
	
	public Imagen upload(Part file) throws IOException{
		if(!UPLOAD_DIR.exists()){
			UPLOAD_DIR.mkdirs();
		}
		Imagen img = new Imagen();
		String url = generarUrl();
		img.setUrl(url);
		img.setPeso(file.getSize());
		img.setTipo(file.getContentType());
		imgManager.persist(img);
		Files.copy(file.getInputStream(), new File (UPLOAD_DIR,url).toPath());
		return img;
	}

	public Imagen findByPath(String url) {
		String jpql = "Select i From Imagen i Where i.url = :url";
		TypedQuery<Imagen> q = imgManager.createQuery(jpql, Imagen.class);
		q.setParameter("url", url);
		return q.getSingleResult();
	}
}
