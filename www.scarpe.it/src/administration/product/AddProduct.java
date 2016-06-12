package administration.product;

import java.io.*;

import java.util.Iterator;
import java.util.List;

import administration.product.FileLocationContextListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class AddProduct
 */
@MultipartConfig
@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ServletFileUpload uploader = null;

	@Override
	public void init() throws ServletException {
		DiskFileItemFactory fileFactory = new DiskFileItemFactory();
		File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
		fileFactory.setRepository(filesDir);
		this.uploader = new ServletFileUpload(fileFactory);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// segnalare mancato caricamento file
		// da autogenerare int id =
		// Integer.parseInt(request.getParameter("idScarpe"));
		String marca = request.getParameter("marca");
		// String modello = request.getParameter("modello");
		// int prezzo_vendita =
		// Integer.parseInt(request.getParameter("prezzo_vendita"));
		// int prezzo_acquisto =
		// Integer.parseInt(request.getParameter("prezzo_acquisto"));
		// int quantitaDisp =
		// Integer.parseInt(request.getParameter("quantitaDisp"));
		// int scorta_minima =
		// Integer.parseInt(request.getParameter("scorta_minima"));
		// String alt = request.getParameter("alt");
		// String descrizione = request.getParameter("descrizione");
		System.out.println("marca " + marca + " quantitaDisp ");
		/**********************************************/
		// Immagini

		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new ServletException("Content type is not multipart/form-data");
		}

		try {
			List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			for (FileItem fileItem : multiparts) {
				if (!fileItem.isFormField()) {

					// List<FileItem> fileItemsList =
					// uploader.parseRequest(request);
					// ottengo lista di oggetti
					// Iterator<FileItem> fileItemsIterator =
					// fileItemsList.iterator();
					// istanzio un Iterator
					// while(fileItemsIterator.hasNext()){
					System.out.println("Sono while");
					// FileItem fileItem = fileItemsIterator.next();
					System.out.println("FieldName=" + fileItem.getFieldName());
					System.out.println("FileName=" + fileItem.getName());
					System.out.println("ContentType=" + fileItem.getContentType());
					System.out.println("Size in bytes=" + fileItem.getSize());

					File file = new File(request.getServletContext().getAttribute("FILES_DIR") + File.separator
							+ fileItem.getName());
					System.out.println("Absolute Path at server=" + file.getAbsolutePath());
					fileItem.write(file);
					System.out.println("File " + fileItem.getName() + " uploaded successfully.");
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
