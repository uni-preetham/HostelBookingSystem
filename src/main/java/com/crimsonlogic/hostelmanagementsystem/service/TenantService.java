package com.crimsonlogic.hostelmanagementsystem.service;

import java.util.List;

import com.crimsonlogic.hostelmanagementsystem.entity.Tenant;
import com.crimsonlogic.hostelmanagementsystem.exception.ResourceNotFoundException;

public interface TenantService {
	public Tenant registerTenant(Tenant tenant);
	public List<Tenant> listAllTenant();
	public Tenant showTenantById(String tenantId);
	public void deleteTenant(String tenantId);
	public void updateTenant(String tenantId, Tenant tenant) throws ResourceNotFoundException;
	public Tenant findByEmailAndPassword(String email, String password);
}