package view;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ControladorImagen;
import model.Imagen;

@WebServlet(urlPatterns="/imagenes/*")
public class ServletImagen extends HttpServlet {
	private static final long serialVersionUID = 1251001105853430500L;
	
	@Inject
	private ControladorImagen CtrlImagen;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String [] urlParts = req.getRequestURI().split("/");
		String url =  urlParts[urlParts.length - 1];
		try{
			Imagen img = CtrlImagen.findByPath(url);
			resp.setContentLength((int) img.getPeso());
			resp.setContentType(img.getTipo());
			InputStream in = CtrlImagen.read(url);
			OutputStream out = resp.getOutputStream();
			int reads;
			byte[] buffer = new byte [1024];
			while((reads = in.read(buffer)) > -1){
				out.write(buffer, 0, reads);
			}
			in.close();
			out.close();
		} catch (Exception e){
			e.printStackTrace();
			resp.sendError(404,"NO SE ENCONTRO EL ARCHIVO XDXDXDXD");
		}
		
	}
}
