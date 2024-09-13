package com.crimsonlogic.hostelmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crimsonlogic.hostelmanagementsystem.service.TenantService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TenantService tenantServ;

    // Admin dashboard
    @GetMapping("/dashboard")
    public String showAdminDashboard(Model model) {
        // You can load admin-specific data here, such as the list of all tenants or stats
        model.addAttribute("tenants", tenantServ.listAllTenant());
        return "wardendashboard";  // Return the admin dashboard JSP page
    }

    // Additional admin routes for managing tenants, properties, etc.
}
