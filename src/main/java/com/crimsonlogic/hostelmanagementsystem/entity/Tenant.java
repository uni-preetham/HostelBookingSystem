package com.crimsonlogic.hostelmanagementsystem.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * Represents a booking in the hostel management system.
 * Author: Preetham A A
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "tenants")
public class Tenant {
    @Id
    @Column(name = "tenant_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "custom-id-generator")
	@GenericGenerator(name = "custom-id-generator", strategy = "com.crimsonlogic.hostelmanagementsystem.util.TenantIdGenerator")
    private String tenantId;
    
    @Column(name = "tenant_fname", nullable = false)
    private String tenantFname;

    @Column(name = "tenant_lname", nullable = false)
    private String tenantLname;
    
//    @Column(name = "tenant_username", unique = true, nullable = false)
//    private String tenantUsername;
    
    @Column(name = "tenant_email", unique = true, nullable = false)
    private String tenantEmail;
    
    @Column(name = "tenant_password", nullable = false)
    private String tenantPassword;
    
    @Column(name = "tenant_phone", unique = true)
    private String tenantPhone;
    
    @Column(name = "is_admin")
    private Boolean isAdmin = false;
}