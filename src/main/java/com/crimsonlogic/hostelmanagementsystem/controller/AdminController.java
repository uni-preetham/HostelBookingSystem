package com.crimsonlogic.hostelmanagementsystem.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crimsonlogic.hostelmanagementsystem.service.BookingService;
import com.crimsonlogic.hostelmanagementsystem.service.TenantService;
import com.crimsonlogic.hostelmanagementsystem.util.MonthlyRevenue;

@Controller
@RequestMapping("/manager")
public class AdminController {

    @Autowired
    private TenantService tenantServ;
    
    @Autowired
    private BookingService bookingService;

    // Admin dashboard
    @GetMapping("/dashboard")
    public String showAdminDashboard(Model model) {
        // You can load admin-specific data here, such as the list of all tenants or stats
        model.addAttribute("tenants", tenantServ.listAllTenant());
        
        
        int year = LocalDate.now().getYear();  // Get current year
        List<MonthlyRevenue> monthlyRevenue = bookingService.calculateMonthlyRevenue(year);

        model.addAttribute("monthlyRevenue", monthlyRevenue);
        
        
        return "managerdashboard";  // Return the admin dashboard JSP page
    }

    // Additional admin routes for managing tenants, properties, etc.
}
