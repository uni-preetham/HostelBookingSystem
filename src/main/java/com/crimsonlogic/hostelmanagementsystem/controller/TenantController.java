package com.crimsonlogic.hostelmanagementsystem.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crimsonlogic.hostelmanagementsystem.entity.Tenant;
import com.crimsonlogic.hostelmanagementsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.hostelmanagementsystem.service.HostelService;
import com.crimsonlogic.hostelmanagementsystem.service.TenantService;


/**
 * Represents a booking in the hostel management system.
 * Author: Preetham A A
 */

@Controller
@RequestMapping("/tenant")
public class TenantController {

	private static final Logger LOG = LoggerFactory.getLogger(TenantController.class);
	
    @Autowired
    private TenantService tenantService;
    
    @Autowired
    private HostelService hostelService;
    
    @GetMapping("/registertenantform")
    public String showRegistrationForm(Model model) {
    	LOG.debug("inside showRegistrationForm handler method");
        model.addAttribute("tenant", new Tenant()); 
        return "registertenant"; 
    }

    
    /*
	 * Register a user.
	 * 
	 */
    @PostMapping("/register")
    public String registerTenant(@ModelAttribute("tenant") Tenant tenant, Model model) {
    	LOG.debug("inside registerTenant handler method");
        tenantService.registerTenant(tenant); 
        model.addAttribute("tenants", tenantService.listAllTenant()); 
        return "redirect:/tenant/loginform?success";
    }

    /*
	 * Displays the tenant list.
	 * 
	 */
    @GetMapping("/listalltenant")
    public String listAllTenants(Model model) {
    	LOG.debug("inside listAllTenants handler method");
        model.addAttribute("tenants", tenantService.listAllTenant());
        return "tenantlist"; 
    }

    
    /*
	 * Displays the tenant by id.
	 * 
	 */
    @GetMapping("/showtenantbyid/{tenantId}")
    public Tenant showTenantById(@PathVariable("tenantId") String tenantId) {
        return tenantService.showTenantById(tenantId);
    }

    
    /*
	 * Displays the edit user form.
	 * 
	 */
    @GetMapping("/edituserform/{tenantId}")
    public String showEditUserForm(@PathVariable("tenantId") String tenantId, Model model) {
        Tenant tenantDetails = tenantService.showTenantById(tenantId);
        if (tenantDetails != null) {
            model.addAttribute("tenant", tenantDetails); 
            return "edittenant";
        }
        return "redirect:/tenant/listalltenant"; 
    }

    
    /*
	 * Update the tenant details.
	 * 
	 */
    @PostMapping("/updatetenant")
    public String updateTenant(@ModelAttribute Tenant tenant, Model model, HttpSession session)
            throws ResourceNotFoundException {
        if (tenant != null && tenant.getTenantId() != null) {
            tenantService.updateTenant(tenant.getTenantId(), tenant);
            Tenant updatedTenant = tenantService.showTenantById(tenant.getTenantId());
            System.out.println(updatedTenant);
            session.setAttribute("user", updatedTenant);
            model.addAttribute("message", "User details updated successfully.");
            return "tenantdashboard";
            
        }
        return "redirect:/tenant/listalltenant";
    }

    
    /*
	 * Displays the login form.
	 * 
	 */
    @GetMapping("/loginform")
    public String showLoginForm(Model model) {
    	LOG.debug("inside showLoginForm handler method");
        model.addAttribute("tenant", new Tenant()); 
        return "login"; 
    }

    
    /*
	 * User login.
	 * 
	 */
    @PostMapping("/login")
    public String loginTenant(@ModelAttribute("tenant") Tenant tenant, Model model, HttpSession session) {

    	LOG.debug("inside loginTenant handler method");
        Tenant existingTenant = tenantService.findByEmailAndPassword(tenant.getTenantEmail(), tenant.getTenantPassword());
        System.out.println(existingTenant);
        
        if (existingTenant != null) {
            if (existingTenant.getIsAdmin()) {
            	session.setAttribute("user", existingTenant);
                return "redirect:/manager/dashboard";
            } else {
            	session.setAttribute("user", existingTenant);
                return "redirect:/tenant/dashboard";
            }
        } else {
            model.addAttribute("error", "We couldn't find an account matching the username and password you entered. Please check your username and password and try again.");
            return "login"; 
        }
    }

    
    /*
	 * Displays the customer dashboard.
	 * 
	 */
    @GetMapping("/dashboard")
    public String showTenantDashboard(Model model) {
    	LOG.debug("inside showTenantDashboard handler method");
    	model.addAttribute("hostellist",hostelService.listAllHostels());
        return "tenantdashboard"; 
    }

    
    /*
	 * Displays the manager dashboard.
	 * 
	 */
    @GetMapping("/manager/dashboard")
    public String showAdminDashboard() {
        return "managerdashboard"; 
    }
    
    /*
	 * Logs the user out.
	 * 
	 */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "../../index"; 
    }
    
    /*
	 * Display forgot password form.
	 * 
	 */
    @GetMapping("/forgotPassword")
    public String showForgotPasswordForm() {
        return "forgotPassword";
    }

    /*
	 * Display OTP form.
	 * 
	 */
    @PostMapping("/sendOtp")
    public String sendOtp(@RequestParam("email") String email, Model model) {
        if (tenantService.emailExists(email)) {
            model.addAttribute("email", email);
            model.addAttribute("otp", "123456"); // Dummy OTP
            return "verifyotp";
        } else {
            model.addAttribute("error", "Email not found");
            return "forgotPassword";
        }
    }

    /*
	 * Displays the feedback form.
	 * 
	 */
    @PostMapping("/verifyOtp")
    public String verifyotp(@RequestParam("email") String email, @RequestParam("otp") String otp, Model model) {
        if ("123456".equals(otp)) {
            model.addAttribute("email", email);
            return "resetPassword";
        } else {
            model.addAttribute("error", "Invalid OTP");
            return "verifyotp";
        }
    }

    
    /*
	 * Reseting the password.
	 * 
	 */
    @PostMapping("/resetPassword")
    public String resetPassword(@RequestParam("email") String email, @RequestParam("newPassword") String newPassword, Model model) {
        try {
            tenantService.resetPassword(email, newPassword);
            return "login"; 
        } catch (ResourceNotFoundException e) {
            model.addAttribute("error", e.getMessage());
            return "forgotPassword";
        }
    }
    
    
    /*
	 * Displays the update password form.
	 * 
	 */
    @GetMapping("/updatepasswordform/{tenantId}")
    public String showUpdatePasswordForm(@PathVariable("tenantId") String tenantId, Model model) {
        model.addAttribute("tenantId", tenantId);
        return "updatepassword";
    }
    
    
    /*
	 * Update the user password.
	 * 
	 */
    @PostMapping("/updatepassword")
    public String updatePassword(@RequestParam("tenantId") String tenantId,
                                 @RequestParam("currentPassword") String currentPassword,
                                 @RequestParam("newPassword") String newPassword, 
                                 Model model) {
        try {
            boolean isPasswordUpdated = tenantService.updatePassword(tenantId, currentPassword, newPassword);
            if (isPasswordUpdated) {
                return "redirect:/tenant/loginform?success"; // Redirect to login after successful password update
            } else {
                model.addAttribute("error", "Current password is incorrect");
                return "updatepassword"; // Return to password update form if the current password is wrong
            }
        } catch (ResourceNotFoundException e) {
            model.addAttribute("error", e.getMessage());
            return "updatepassword"; // Return to password update form on error
        }
    }

}
