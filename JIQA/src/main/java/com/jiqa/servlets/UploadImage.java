package com.jiqa.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author gagan
 */
@MultipartConfig
@WebServlet(name = "UploadImage", urlPatterns = {"/UploadImage"})
public class UploadImage extends HttpServlet {

	private static final long serialVersionUID = -1411402141503358746L;
	
	private String uploadImagePath = "D:/Apache/apache-tomcat-8.0.33/webapps/Demo/";
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
//        String userId = request.getSession().getAttribute("userId").toString();
//        String rootpath = new ReadFromPropertiesFile().getUploadPath() + File.separator + userId;
        String rootpath = uploadImagePath;
        String filename = "", msg = "";
        try {	
            Part f1Image = request.getPart("uploadFile");
            if (f1Image != null) {
                InputStream is = null;
                FileOutputStream fos = null;
                String header = f1Image.getHeader("content-disposition");
                filename = getFileName(header);
                File fileToUpload = new File(rootpath + File.separator + filename);
                if (!fileToUpload.getParentFile().exists()) {
                    fileToUpload.getParentFile().mkdirs();
                }

                if (fileToUpload.isFile()) {
                    msg = "file already exists";
                } else {
                    is = f1Image.getInputStream();
                    fos = new FileOutputStream(fileToUpload);
                    byte[] buffer = new byte[4096];
                    int count = 0;
                    while ((count = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, count);
                    }
                    fos.flush();
                    fos.close();
//                    msg = new CommonServices().updateProfileImage(filename, userId);
                }
            }
        } catch(Exception e) {
        	System.out.println("UploadImage: " + e);
        } finally {
        	request.setAttribute("title", request.getParameter("title"));
        	request.setAttribute("status", request.getParameter("status"));
        	response.sendRedirect("saveCat");
        }
    }

    private String getFileName(String header) {
        String[] data = header.split(";");
        return data[2].substring(11, data[2].length() - 1);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
