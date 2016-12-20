package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSpinnerUI;

import service.QuanLyDeTai;


@WebServlet("/XoaDeTaiServlet")
public class XoaDeTaiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public XoaDeTaiServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id_detai");
		if(QuanLyDeTai.XoaDeTai(Integer.parseInt(id))){
			response.sendRedirect("XemDanhSachDeTai.jsp");}
		}

}
