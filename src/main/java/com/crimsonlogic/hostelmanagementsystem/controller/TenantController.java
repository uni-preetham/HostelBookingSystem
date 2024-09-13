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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PutMapping("/updatemapping/{tenantId}")
    public void updateTenant(@PathVariable("tenantId") String tenantId, @RequestBody Tenant tenant)
            throws ResourceNotFoundException {
        tenantService.updateTenant(tenantId, tenant);
    }

    // Show login form
    @GetMapping("/loginform")
    public String showLoginForm(Model model) {
//    	LOG.debug("inside showLoginForm handler method");
        model.addAttribute("tenant", new Tenant());  // Bind an empty tenant object for form
        return "login";  // Return the login JSP page
    }

    // Handle login submission
    @PostMapping("/login")
    public String loginTenant(@ModelAttribute("tenant") Tenant tenant, Model model, HttpSession session) {

    	LOG.debug("inside loginTenant handler method");
        Tenant existingTenant = tenantService.findByEmailAndPassword(tenant.getTenantEmail(), tenant.getTenantPassword());
        
        if (existingTenant != null) {
            if (existingTenant.getIsAdmin()) {
                // Redirect to admin dashboard if user is an admin
            	session.setAttribute("user_fname", existingTenant.getTenantFname());
                return "redirect:/admin/dashboard";
            } else {
                // Redirect to tenant dashboard if user is not an admin
            	session.setAttribute("user_fname", existingTenant.getTenantFname());
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
    @GetMapping("/admin/dashboard")
    public String showAdminDashboard() {
        return "admindashboard";  // Return the admin dashboard JSP page
    }
    
    //Logout (invalidating the session)
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // Invalidate the session
        return "../../index";  // Redirect to login page after logout
    }
}
