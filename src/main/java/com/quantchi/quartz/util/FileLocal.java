package com.quantchi.quartz.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileLocal extends FileSave {

	public FileLocal(MultipartFile file) throws IOException {
		Path path = Paths.get(rootDir, file.getOriginalFilename());
		file.transferTo(path.toFile());
		this.setFilePath(path.toString());
	}

}
