package com.crimsonlogic.hostelmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.hostelmanagementsystem.entity.Tenant;


@Repository
public interface TenantRepository extends JpaRepository<Tenant, String>{
	Tenant findByTenantEmailAndTenantPassword(String tenantEmail, String tenantPassword);
	Tenant findByTenantEmail(String tenantEmail);

}