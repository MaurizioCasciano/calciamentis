package administration.manipulationDB;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import utilities.xml.ImportaProdotti;

/**
 * Servlet implementation class LoadProduct
 */
@WebServlet("/LoadProduct")
public class LoadProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Sono la servlet");
		if (ServletFileUpload.isMultipartContent(request)) {
			File prodotti = null;
			// Properties sysprops = System.getProperties();
			String fs = "/"; // sysprops.getProperty("file.separator");
			String UPLOAD_DIRECTORY = getServletContext().getRealPath("/") + "xml" + fs;
			System.out.println("real path " + UPLOAD_DIRECTORY);
			File uploadDirectory = new File(UPLOAD_DIRECTORY);
			uploadDirectory.mkdir();

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
			List<FileItem> multiparts = null;
			try {
				multiparts = upload.parseRequest(request);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Iterator<FileItem> parameters = multiparts.iterator();
			while (parameters.hasNext()) {
				FileItem nextElement = parameters.next();
				if (!nextElement.isFormField()) {

					for (FileItem item : multiparts) {
						System.out.println("Sono nel for");
						if (!item.isFormField()) {
							System.out.println("è form-field");
							System.out.println(item.getName());
							System.out.println(item.getSize());
							try {
								prodotti = new File(uploadDirectory, "prodotti.xml");
								// prodotti = new File(UPLOAD_DIRECTORY +
								// "prodotti.xml");
								// prodotti.createNewFile();// crea il file se
								// non
								// esiste (NON SERVE)
								item.write(prodotti);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
						System.out.println("Tutto ok , ora ecco prodotti " + prodotti);
						try {
							ImportaProdotti.aggiornaProdotti(prodotti);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						response.sendRedirect("management.jsp?message=Aggiunti%20Prodotti&feed=ok");
					}
				}
			}

		}
	}
}
