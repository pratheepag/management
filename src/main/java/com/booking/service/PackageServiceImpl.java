package com.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.model.Packages;
import com.booking.repository.PackageRepository;

@Service("packageService")
public class PackageServiceImpl implements PackageService {

	@Autowired
	private PackageRepository packageRepository;
	
	public Packages save(Packages packages) {
		// TODO Auto-generated method stub
		packageRepository.saveAndFlush(packages);
		return null;
	}

	public List<Packages> findAllPackages() {
		// TODO Auto-generated method stub
		return packageRepository.findAll();
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		packageRepository.delete(id); 
	}

	public Packages findPackage(Long id) {
		// TODO Auto-generated method stub
		return packageRepository.findOne(id);
	}

}
