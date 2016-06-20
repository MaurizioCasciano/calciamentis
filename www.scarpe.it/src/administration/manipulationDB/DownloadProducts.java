package administration.manipulationDB;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.jdom2.Element;

import utilities.xml.EsportaAcquisti;
import utilities.xml.EsportaProdotti;
import utilities.xml.ExportDB;

/**
 * Servlet implementation class DownloadProducts
 */
@WebServlet("/DownloadProducts")
public class DownloadProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	//set repository
	private ServletFileUpload uploader = null;

	@Override
	public void init() throws ServletException {
		DiskFileItemFactory fileFactory = new DiskFileItemFactory();
		File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
		fileFactory.setRepository(filesDir);
		this.uploader = new ServletFileUpload(fileFactory);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Sono la servlet");
		String fileName = request.getParameter("fileName");
		System.out.println(fileName);
		Element root2 = null;
		String pathDtdProdotti=request.getSession().getServletContext().getRealPath("dtd/scarpe.dtd");
		String pathDtdAcquisti=request.getSession().getServletContext().getRealPath("dtd/acquisti.dtd");
		System.out.println("path Prodotti "+pathDtdProdotti);
		System.out.println("path acquisti "+pathDtdAcquisti);
		try {
			if (fileName.equals("acquisti")) {
				System.out.println(fileName);
				root2 = EsportaAcquisti.makeExportPurchasesXML(pathDtdAcquisti);
				ExportDB.makeFile(root2, EsportaAcquisti.getDtd(), getServletContext().getRealPath("/"));
			} else if (fileName.equals("scarpe")) {
				System.out.println(fileName);
				root2 = EsportaProdotti.makeExportShoesXML(pathDtdProdotti);
				ExportDB.makeFile(root2, EsportaProdotti.getDtd(), getServletContext().getRealPath("/"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (fileName == null || fileName.equals("")) {
			throw new ServletException("File Name can't be null or empty");
		}
		System.out.println(getServletContext().getRealPath("/") + fileName+".xml");
		File file = new File(getServletContext().getRealPath("/") + fileName+".xml");
		if (!file.exists()) {
			throw new ServletException("File doesn't exists on server.");
		}
		System.out.println("File location on server::" + file.getAbsolutePath());
		ServletContext ctx = getServletContext();
		InputStream fis = new FileInputStream(file);
		String mimeType = ctx.getMimeType(file.getAbsolutePath());
		response.setContentType(mimeType != null ? mimeType : "application/octet-stream");
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName +".xml"+ "\"");

		ServletOutputStream os = response.getOutputStream();
		byte[] bufferData = new byte[1024];
		int read = 0;
		while ((read = fis.read(bufferData)) != -1) {
			os.write(bufferData, 0, read);
		}
		os.flush();
		os.close();
		fis.close();
		System.out.println("File downloaded at client successfully");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
