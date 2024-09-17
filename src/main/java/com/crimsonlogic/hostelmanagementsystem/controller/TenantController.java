package com.crimsonlogic.hostelmanagementsystem.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crimsonlogic.hostelmanagementsystem.entity.Tenant;
import com.crimsonlogic.hostelmanagementsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.hostelmanagementsystem.service.HostelService;
import com.crimsonlogic.hostelmanagementsystem.service.TenantService;

@Controller
@RequestMapping("/tenant")
public class TenantController {

	private static final Logger LOG = LoggerFactory.getLogger(TenantController.class);
	
    @Autowired
    private TenantService tenantService;
    
    @Autowired
    private HostelService hostelService;
    
    // Show registration form
    @GetMapping("/registertenantform")
    public String showRegistrationForm(Model model) {
    	LOG.debug("inside showRegistrationForm handler method");
        model.addAttribute("tenant", new Tenant());  // Bind an empty tenant object for form
        return "registertenant";  // Return the name of the form (HTML or JSP)
    }

    // Handle form submission
    @PostMapping("/register")
    public String registerTenant(@ModelAttribute("tenant") Tenant tenant, Model model) {
    	LOG.debug("inside registerTenant handler method");
        tenantService.registerTenant(tenant);  // Register tenant using service
        model.addAttribute("tenants", tenantService.listAllTenant());  // Reload list of tenants
        return "redirect:/tenant/loginform?success";  // Redirect to the login form after registration
    }

    // List all tenants
    @GetMapping("/listalltenant")
    public String listAllTenants(Model model) {
    	LOG.debug("inside listAllTenants handler method");
        model.addAttribute("tenants", tenantService.listAllTenant());
        return "tenantlist";  // The view that lists all tenants
    }

    @GetMapping("/showtenantbyid/{tenantId}")
    public Tenant showTenantById(@PathVariable("tenantId") String tenantId) {
        return tenantService.showTenantById(tenantId);
    }

    @DeleteMapping("/deletetenant/{tenantId}")
    public void deleteCustomer(@PathVariable("tenantId") String tenantId) {
        tenantService.deleteTenant(tenantId);
    }
    
    @GetMapping("/edituserform/{tenantId}")
    public String showEditUserForm(@PathVariable("tenantId") String tenantId, Model model) {
        Tenant tenantDetails = tenantService.showTenantById(tenantId);
        if (tenantDetails != null) {
            model.addAttribute("tenant", tenantDetails); // Add tenant details to the model
            return "edittenant";
        }
        return "redirect:/tenant/listalltenant"; // Redirect if tenant not found
    }

    @PostMapping("/updatetenant")
    public String updateTenant(@ModelAttribute Tenant tenant, Model model, HttpSession session)
            throws ResourceNotFoundException {
        if (tenant != null && tenant.getTenantId() != null) {
            tenantService.updateTenant(tenant.getTenantId(), tenant);
//            Tenant updatedTenant = tenantService.findByEmailAndPassword(tenant.getTenantEmail(), tenant.getTenantPassword());
            Tenant updatedTenant = tenantService.showTenantById(tenant.getTenantId());
            System.out.println(updatedTenant);
            session.setAttribute("user", updatedTenant);
            model.addAttribute("message", "User details updated successfully.");
            return "tenantdashboard"; // Redirect to the dashboard after update
            
        }
        return "redirect:/tenant/listalltenant"; // Redirect if tenant ID is missing
    }


    // Show login form
    @GetMapping("/loginform")
    public String showLoginForm(Model model) {
    	LOG.debug("inside showLoginForm handler method");
        model.addAttribute("tenant", new Tenant());  // Bind an empty tenant object for form
        return "login";  // Return the login JSP page
    }

    // Handle login submission
    @PostMapping("/login")
    public String loginTenant(@ModelAttribute("tenant") Tenant tenant, Model model, HttpSession session) {

    	LOG.debug("inside loginTenant handler method");
        Tenant existingTenant = tenantService.findByEmailAndPassword(tenant.getTenantEmail(), tenant.getTenantPassword());
        System.out.println(existingTenant);
        
        if (existingTenant != null) {
            if (existingTenant.getIsAdmin()) {
                // Redirect to admin dashboard if user is an admin
            	session.setAttribute("user", existingTenant);
                return "redirect:/manager/dashboard";
            } else {
                // Redirect to tenant dashboard if user is not an admin
            	session.setAttribute("user", existingTenant);
                return "redirect:/tenant/dashboard";
            }
        } else {
            model.addAttribute("error", "We couldn't find an account matching the username and password you entered. Please check your username and password and try again.");
            return "login";  // Return to login page with error message
        }
    }

    // Tenant dashboard
    @GetMapping("/dashboard")
    public String showTenantDashboard(Model model) {
    	LOG.debug("inside showTenantDashboard handler method");
    	model.addAttribute("hostellist",hostelService.listAllHostels());
        return "tenantdashboard";  // Return the tenant dashboard JSP page
    }

    // Admin dashboard (for admins)
    @GetMapping("/manager/dashboard")
    public String showAdminDashboard() {
        return "managerdashboard";  // Return the admin dashboard JSP page
    }
    
    //Logout (invalidating the session)
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // Invalidate the session
        return "../../index";  // Redirect to login page after logout
    }
}
