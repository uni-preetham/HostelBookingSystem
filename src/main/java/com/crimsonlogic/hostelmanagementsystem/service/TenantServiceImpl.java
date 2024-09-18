package com.crimsonlogic.hostelmanagementsystem.service;

import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crimsonlogic.hostelmanagementsystem.entity.Tenant;
import com.crimsonlogic.hostelmanagementsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.hostelmanagementsystem.repository.TenantRepository;

@Service
@Transactional
public class TenantServiceImpl implements TenantService {

	@Autowired
	private TenantRepository tenantRepository;

	@Override
	public Tenant registerTenant(Tenant tenant) {
	    String tenantPassword = tenant.getTenantPassword();
	    String encryptedPassword = Base64.getEncoder().encodeToString(tenantPassword.getBytes());
	    System.out.print("\n\n\n\n\n\n\n\n\n"+encryptedPassword+"\n\n\n\n\n\n\n\n\n");
	    tenant.setTenantPassword(encryptedPassword);
		return tenantRepository.save(tenant);
	}

	@Override
	public List<Tenant> listAllTenant() {
		return tenantRepository.findAll();
	}


	@Override
	public Tenant showTenantById(String tenantId) {
		return tenantRepository.findById(tenantId)
				.orElseThrow(() -> new NoSuchElementException("Tenant with tenantId " + tenantId + " not found"));
	}

	@Override
	public void deleteTenant(String tenantId) {
		tenantRepository.deleteById(tenantId);

	}

	@Override
	public void updateTenant(String tenantId, Tenant tenant) throws ResourceNotFoundException {
		Tenant existingTenant = showTenantById(tenantId);
		if (existingTenant != null) {
			existingTenant.setTenantFname(tenant.getTenantFname());
			existingTenant.setTenantLname(tenant.getTenantLname());
			existingTenant.setTenantEmail(tenant.getTenantEmail());
			existingTenant.setTenantPassword(tenant.getTenantPassword());
			existingTenant.setTenantPhone(tenant.getTenantPhone());
			tenantRepository.save(existingTenant);
		} else {
			throw new ResourceNotFoundException("Customer not found");
		}

	}

	
	@Override
	public Tenant findByEmailAndPassword(String email, String password) {
	    Tenant tenant = tenantRepository.findByTenantEmail(email);
	    if (tenant != null) {
	        String encodedPassword = tenant.getTenantPassword();
	        byte[] decodedBytes = Base64.getDecoder().decode(encodedPassword);
	        String decodedPassword = new String(decodedBytes);
	        System.out.print("\n\n\n\n\n\n\n\n\n"+decodedPassword+"\n\n\n\n\n\n\n\n\n");
	        if (decodedPassword.equals(password)) {
	            return tenant;
	        }
	    }
	    
	    return null;
	}

	
	@Override
    public void resetPassword(String email, String newPassword) throws ResourceNotFoundException {
        Tenant tenant = tenantRepository.findByTenantEmail(email);
        if (tenant != null) {
            // Encode the new password before saving
            String encryptedPassword = Base64.getEncoder().encodeToString(newPassword.getBytes());
            tenant.setTenantPassword(encryptedPassword);
            tenantRepository.save(tenant);
        } else {
            throw new ResourceNotFoundException("Tenant with email " + email + " not found");
        }
    }
	
	@Override
	public boolean emailExists(String email) {
	    return tenantRepository.findByTenantEmail(email) != null;
	}

}