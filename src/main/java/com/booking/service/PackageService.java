package com.booking.service;

import java.util.List;

import com.booking.model.Packages;

public interface PackageService {
	Packages save(Packages packages);

	List<Packages> findAllPackages();
	
	void delete(Long id);
	
	Packages findPackage(Long id);
}
