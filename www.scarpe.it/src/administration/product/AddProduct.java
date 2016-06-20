package administration.product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import catalog.Detail;
import catalog.Item;
import database.Database;
import utilities.Check;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 15 // 15 MB);
)
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// process only if its multipart content
		if (ServletFileUpload.isMultipartContent(request)) {
			boolean isComplete = true;
			// Properties sysprops = System.getProperties();
			String fs = "/"; // sysprops.getProperty("file.separator");
			String UPLOAD_DIRECTORY = getServletContext().getRealPath("/") + "img" + fs;
			System.out.println("real path " + UPLOAD_DIRECTORY);
			Item newItem;
			String marca = null;
			String modello = null;
			int prezzo_vendita = 0;
			int prezzo_acquisto = 0;
			int quantitaDisp = 0;
			int scorta_minima = 0;
			String alt = null;
			String descrizione = null;
			String intestazione1 = null;
			String intestazione2 = null;
			String intestazione3 = null;
			String intestazione4 = null;
			String corpo1 = null;
			String corpo2 = null;
			String corpo3 = null;
			String corpo4 = null;
			File dir;
			ArrayList<String> images = null;
			try {
				// Create a factory for disk-based file items
				DiskFileItemFactory factory = new DiskFileItemFactory();

				// Configure a repository (to ensure a secure temp location is
				// used)
				ServletContext servletContext = this.getServletConfig().getServletContext();
				File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				factory.setRepository(repository);
				// Create a new file upload handler
				ServletFileUpload upload = new ServletFileUpload(factory);
				// Parse the request
				List<FileItem> multiparts = upload.parseRequest(request);
				System.out.println("size di multiparts " + multiparts.size());
				Iterator<FileItem> parameters = multiparts.iterator();
				System.out.println("il contenuto è multipart ");
				while (parameters.hasNext()) {
					FileItem nextElement = parameters.next();
					if (nextElement.isFormField()) {
						String nextValue = nextElement.getString();

						if (!Check.isValid(nextValue)) {
							isComplete = false;
							// request.setAttribute(nextElement, "Error");
							System.err.println("INVALID: " + nextElement.getName() + " = " + nextValue);
						} else {
							System.out.println("validazione form " + nextElement.getFieldName() + " = " + nextValue);
						}
					}
				}

				if (isComplete) {

					images = new ArrayList<String>();

					// List<FileItem> multiparts = new ServletFileUpload(new
					// DiskFileItemFactory()).parseRequest(request);
					int count = 0;
					System.out.println("size multiparts " + multiparts.size());
					boolean b = true;
					for (FileItem item : multiparts) {
						System.out.println("Sono nel for");
						if (!item.isFormField()) {
							if (b) {
								dir = new File(UPLOAD_DIRECTORY + marca + "_" + modello);
								System.out.println(UPLOAD_DIRECTORY + marca + "_" + modello);
								System.out.println(dir.mkdir());
								b = false;
							}
							;
							System.out.println("non è form-field");
							String ext = item.getName().substring(item.getName().lastIndexOf("."));
							System.out.println(item.getName());
							System.out.println(item.getSize());
							System.out.println("percorso " + UPLOAD_DIRECTORY + marca + "_" + modello + fs + marca + "_"
									+ modello + count + ext);
							item.write(new File(UPLOAD_DIRECTORY + marca + "_" + modello + fs + marca + "_" + modello
									+ count + ext));
							images.add("img" + fs + marca + "_" + modello + fs + marca + "_" + modello + count + ext);
							System.out.println("Immagine in array " + "img" + fs + marca + "_" + modello + fs + marca
									+ "_" + modello + count + ext);
							count++;
						} else {
							System.out.println("e form field");
							String name = item.getFieldName();
							if (name.equals("marca")) {
								marca = item.getString();
							} else if (name.equals("modello")) {
								modello = item.getString();
							} else if (name.equals("prezzo_vendita")) {
								prezzo_vendita = Integer.parseInt(item.getString());
							} else if (name.equals("prezzo_acquisto")) {
								prezzo_acquisto = Integer.parseInt(item.getString());
							} else if (name.equals("quantitaDisp")) {
								quantitaDisp = Integer.parseInt(item.getString());
							} else if (name.equals("scorta_minima")) {
								scorta_minima = Integer.parseInt(item.getString());
							} else if (name.equals("alt")) {
								alt = item.getString();
							} else if (name.equals("descrizione")) {
								descrizione = item.getString();
							} else if (name.equals("intestazione1")) {
								intestazione1 = item.getString();
							} else if (name.equals("corpo1")) {
								corpo1 = item.getString();
							} else if (name.equals("intestazione2")) {
								intestazione2 = item.getString();
							} else if (name.equals("corpo2")) {
								corpo2 = item.getString();
							} else if (name.equals("intestazione3")) {
								intestazione3 = item.getString();
							} else if (name.equals("corpo3")) {
								corpo3 = item.getString();
							} else if (name.equals("intestazione4")) {
								intestazione4 = item.getString();
							} else if (name.equals("corpo4")) {
								corpo4 = item.getString();
							}
						}
					}
				}
				// File uploaded successfully
				// request.setAttribute("message", "File Uploaded
				// Successfully");
			} catch (Exception ex) {
				// request.setAttribute("message", "File Upload Failed due to "
				// + ex);
			}
			ArrayList<Detail> details = new ArrayList<Detail>();

			Detail e = new Detail(intestazione1, corpo1);
			details.add(e);
			e = new Detail(intestazione2, corpo2);
			details.add(e);
			e = new Detail(intestazione3, corpo3);
			details.add(e);
			e = new Detail(intestazione4, corpo4);
			details.add(e);
			// System.out.println("dettaglio in array " + e.getCorpo() +
			// e.getIntestazione());

			newItem = new Item(-1, marca, modello, prezzo_vendita, prezzo_acquisto, quantitaDisp, scorta_minima, images,
					alt, descrizione, details);

			if (isComplete) {
				int databaseFeedback = Database.insertItem(newItem);
				if (databaseFeedback != -1) {
					response.sendRedirect("management.jsp?id=" + databaseFeedback + "&message=Aggiunto" + "&feed=ok");
				} else {
					response.sendRedirect("management.jsp?feed=no" + "&oldLoad=addItemPage.jsp" + "&message=error");
					// aggiungere parametro di riapertura
				}
			} else {
				response.sendRedirect("management.jsp?feed=no" + "&oldLoad=addItemPage.jsp" + "&message=error");
				// aggiungere parametro di riaperturaF
			}
		} else {

		}
	}
}
