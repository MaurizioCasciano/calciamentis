package administration.product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

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
@MultipartConfig(
		fileSizeThreshold   = 1024 * 1024 * 1,  // 1 MB
        maxFileSize         = 1024 * 1024 * 10, // 10 MB
        maxRequestSize      = 1024 * 1024 * 15 // 15 MB);
        )
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// process only if its multipart content
		if (ServletFileUpload.isMultipartContent(request)) {
			Enumeration<String> parameters = request.getParameterNames();
			boolean isComplete = true;
			Item newItem;
			System.out.println("il contenuto è multipart");
			while (parameters.hasMoreElements()) {
				String nextElement = parameters.nextElement();
				String nextValue = request.getParameter(nextElement);

				if (!Check.isValid(nextValue)) {
					isComplete = false;
					request.setAttribute(nextElement, "Error");
					System.err.println("INVALID: " + nextElement + " = " + nextValue);
				} else {
					System.out.println(nextElement + " = " + nextValue);
				}
			}
		
			if (isComplete) {
				String marca = request.getParameter("marca");
				String modello = request.getParameter("modello");
				int prezzo_vendita = Integer.parseInt(request.getParameter("prezzo_vendita"));
				int prezzo_acquisto =Integer.parseInt( request.getParameter("prezzo_acquisto"));
				int quantitaDisp = Integer.parseInt(request.getParameter("quantitaDisp"));
				int scorta_minima = Integer.parseInt(request.getParameter("scorta_minima"));
				String alt = request.getParameter("alt");
				String descrizione = request.getParameter("descrizione");
				
				
				ArrayList<String> images=new ArrayList<>();
				
				
				Properties sysprops = System.getProperties();
				String fs = sysprops.getProperty("file.separator");
				String UPLOAD_DIRECTORY = getServletContext().getRealPath("/") + "img" + fs;
				System.out.println("real path " + UPLOAD_DIRECTORY);
				File dir = new File(UPLOAD_DIRECTORY+fs+ marca + "_" + modello);
				System.out.println(dir.mkdir());
				try {
					
					List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
					int count = 0;
					boolean errSize = false, errNF = false;
					System.out.println("size multiparts "+ multiparts.size());
					for (FileItem item : multiparts) {
						System.out.println("Sono nel for");
						if (!item.isFormField()) {
							System.out.println("è form-field");
							if (item.getSize() > 100000) {
								errSize = true;
							} else if (item.getSize() == 0) {
								errNF = true;
							}
							String ext = item.getName().substring(item.getName().lastIndexOf("."));
							System.out.println(item.getName());
							System.out.println(item.getSize());
							item.write(new File(UPLOAD_DIRECTORY + fs + marca + "_" + modello + fs + marca + "_"
									+ modello + count + ext));
							images.add("img" + fs + marca + "_" + modello + fs + marca + "_"
									+ modello + count + ext);
							System.out.println("Immagine in array "+"img" + fs + marca + "_" + modello + fs + marca + "_"
									+ modello + count + ext);
							count++;
						}
					}

					// File uploaded successfully
					request.setAttribute("message", "File Uploaded Successfully");
				} catch (Exception ex) {
					request.setAttribute("message", "File Upload Failed due to " + ex);
				}
				ArrayList<Detail> details=new ArrayList<>();
				for(int p=1;p<=4;p++){
					Detail e=new Detail(request.getParameter("intestazione"+p),request.getParameter("corpo"+p));
					System.out.println("dettaglio in array "+ e.getCorpo()+e.getIntestazione());
					details.add(e);
				}
				newItem = new Item(-1, marca, modello, prezzo_vendita, prezzo_acquisto, quantitaDisp,
						scorta_minima, images, alt, descrizione, details);
				System.out.println(Database.insertItem(newItem));
			}else{request.setAttribute("errorCom", "Form not complete");}

		} else {
			request.setAttribute("message", "Sorry this Servlet only handles file upload request");
		}

		// request.getRequestDispatcher("/result.jsp").forward(request,
		// response);

	}
}
