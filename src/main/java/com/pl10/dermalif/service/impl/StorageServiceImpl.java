package com.pl10.dermalif.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.pl10.dermalif.entity.Person;
import com.pl10.dermalif.repository.PersonRepository;
import com.pl10.dermalif.service.StorageService;

@Service("storageService")
public class StorageServiceImpl implements StorageService {
	
	private final Path rootLocation = Paths.get("upload-dir");
	
	@Autowired
	@Qualifier("personRepository")
	PersonRepository personRepository;

	@Override
	public void store(MultipartFile file, String id) {
		try {			
			String[] extParts = file.getOriginalFilename().split("\\.");
			String filename = id+"."+extParts[extParts.length-1];
			Path filepath = rootLocation.resolve(filename);
			Resource resource = new UrlResource(filepath.toUri());
			if(resource.exists() || resource.isReadable()) {
				Files.delete(filepath);
			}
			Files.copy(file.getInputStream(), this.rootLocation.resolve(filename));
			Person person = personRepository.findById(id);
			person.setImageprofile(filename);
			personRepository.save(person);
        } catch (Exception e) {
        	throw new RuntimeException("FAIL! StorageService.store");
        }

	}

	@Override
	public Resource loadFile(String filename) {
		try {
			Person person = personRepository.findById(filename);
			Resource resource;
			if(person != null) {
				Path file = rootLocation.resolve(person.getImageprofile());
				resource = new UrlResource(file.toUri());
			}else {
				resource = new ClassPathResource("/static/dist/img/avatar5.png");
			}	
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				return new ClassPathResource("/static/dist/img/avatar5.png");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL! StorageService.loadFile");
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			if(Files.exists(rootLocation)==false) {
				Files. createDirectory(rootLocation);
			}            
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }

	}

}
