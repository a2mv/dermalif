package com.pl10.dermalif.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	public abstract void store(MultipartFile file, String id);
	
	public abstract Resource loadFile(String filename);
	
	public abstract void deleteAll();
	
	public abstract void init();
}
