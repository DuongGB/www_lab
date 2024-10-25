/*
 * @ {#} CuahangxemayABCServlet.java   1.0     10/25/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.entities.HangXe;
import vn.edu.iuh.fit.entities.Xe;
import vn.edu.iuh.fit.services.HangXeService;
import vn.edu.iuh.fit.services.Impl.HangXeServiceImpl;
import vn.edu.iuh.fit.services.Impl.XeServiceImpl;
import vn.edu.iuh.fit.services.XeService;

import java.io.IOException;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   10/25/2024
 * @version:    1.0
 */
@WebServlet(name = "CuahangxemayABCServlet", value = "/controller")
public class CuahangxemayABCServlet extends HttpServlet {
    private XeService xeService;
    private HangXeService hangXeService;

    @Override
    public void init() throws ServletException {
        xeService = new XeServiceImpl();
        hangXeService = new HangXeServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null || action.equals("getDsXe")) {
            req.setAttribute("dsXe", xeService.getDsXe());
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else if (action.equals("getXeByTenXeOrGiaXe")) {
            String tenXe = req.getParameter("searchValue");
            req.setAttribute("dsXe", xeService.findByTenXeOrGiaXe(tenXe));
            req.getRequestDispatcher("danhSachXe.jsp").forward(req, resp);
        } else if (action.equals("getDsHangXe")) {
            req.setAttribute("dsHangXe", hangXeService.getDsHangXe());
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else if (action.equals("addXe")) {
            req.setAttribute("dsHangXe", hangXeService.getDsHangXe());
            req.getRequestDispatcher("handleXe.jsp").forward(req, resp);
        } else if (action.equals("suaXe")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Xe xe = xeService.findByID(id);
            req.setAttribute("xe", xe);
            req.setAttribute("dsHangXe", hangXeService.getDsHangXe());
            req.getRequestDispatcher("handleXe.jsp").forward(req, resp);
        } else if (action.equals("xoaXe")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Xe xe = xeService.findByID(id);
            xeService.deleteXe(xe);
            req.setAttribute("dsXe", xeService.getDsXe());
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String tenXe = req.getParameter("tenxe");
        double giaXe = Double.parseDouble(req.getParameter("giaxe"));
        int namSX = Integer.parseInt(req.getParameter("namsanxuat"));
        int maHangXe = Integer.parseInt(req.getParameter("mahangxe"));
        if (action.equals("addXe")) {
            HangXe hangXe = hangXeService.findByID(maHangXe);
            Xe xe = new Xe(tenXe, giaXe, namSX, hangXe);
            Xe x = xeService.addXe(xe);
            if (x != null) {
                resp.sendRedirect("controller?action=getDsXe");
            } else {
                req.setAttribute("message", "Thêm xe không thành công");
                req.getRequestDispatcher("handleXe.jsp").forward(req, resp);
            }
        } else if (action.equals("suaXe")) {
            int id = Integer.parseInt(req.getParameter("id"));
            if (id != 0) {
                Xe xe = xeService.findByID(id);
                HangXe hangXe = hangXeService.findByID(maHangXe);
                xe.setTenxe(tenXe);
                xe.setGiaxe(giaXe);
                xe.setNamsanxuat(namSX);
                xe.setMahangxe(hangXe);
                Xe x = xeService.updateXe(xe);
                if (x != null) {
                    resp.sendRedirect("controller?action=getDsXe");
                } else {
                    req.setAttribute("message", "Cập nhật xe không thành công");
                    req.getRequestDispatcher("handleXe.jsp").forward(req, resp);
                }
            }
        }
    }
}

