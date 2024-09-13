package com.crimsonlogic.hostelmanagementsystem.service;

import java.util.List;

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
		// Encrypt the password before saving
		/*
		 * String encodedPassword = passwordEncoder.encode(tenant.getTenantPassword());
		 * tenant.setTenantPassword(encodedPassword);
		 */
        // Save the tenant to the database
		return tenantRepository.save(tenant);
	}

	@Override
	public List<Tenant> listAllTenant() {
		return tenantRepository.findAll();
	}

	@Override
	public Tenant showTenantById(String tenantId) {
		return tenantRepository.findById(tenantId).get();
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
		return tenantRepository.findByTenantEmailAndTenantPassword(email, password);
		/*
		 * Tenant tenant = tenantRepository.findByTenantEmail(email);
		 * 
		 * // Check if tenant exists and if the raw password matches the encoded
		 * password if (tenant != null && passwordEncoder.matches(password,
		 * tenant.getTenantPassword())) { return tenant; // Return tenant if the
		 * password matches }
		 */
		    
//		    return tenant;  // Return null if credentials are invalid
	}

}