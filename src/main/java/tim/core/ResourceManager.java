package tim.core;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

import tim.game.ImageMapper;

public class ResourceManager {
	private static ResourceManager instance;
	ImageMapper imageMapper;
	
	HashMap<String, Image> images = new HashMap<String, Image>();
	
	private ResourceManager() {
		imageMapper = new ImageMapper();
		File imageDir = new File("images");
		images = addDirFiles(imageDir, "");
	}
	

	private HashMap<String, Image> addDirFiles(File dir, String path) {
		File[] imageFiles = dir.listFiles();
		HashMap<String, Image> images = new HashMap<String, Image>();
		if (imageFiles == null) {
		    // Either dir does not exist or is not a directory
		} else {
		    for (int i=0; i<imageFiles.length; i++) {
		        // Get filename of file or directory
		    	
		        File imageFile = imageFiles[i];
		        if (imageFile.isDirectory()) {
		        	System.out.println("loading menu" + path);
		        	String pathName = imageFile.getName() + "/";
		        	if (!path.equals("")) {
		        		
		        	}
		        	HashMap<String, Image> map = addDirFiles(imageFile, pathName);
		        	images.putAll(map);
		        } else {
			        String filename = imageFile.getName();
			        int dot = filename.indexOf('.');
			        if (dot != -1) {
			        	String name = path + filename.substring(0, dot);
				        System.out.println(name);
				        System.out.println(filename);
				        Image image = loadImageFromFile(imageFile);
				        images.put(name, image);
			        }
		        }
		    }
		}
		return images;
	}
	
	
	public static ResourceManager getInstance() {
		if (instance == null) {
			instance = new ResourceManager();
		}
		return instance;
	}
	
	private static InputStream getFileStream (String name) {
		return Thread.class.getResourceAsStream(
				name);
	}
	
	public Image loadImageFromFile(File imageName) {
		try {
			return ImageIO.read(imageName);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Image loadImage(String name) {
		try {
			return ImageIO.read(getFileStream(name));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Image getImage(String type) {
		return images.get(imageMapper.getImageName(type));
	}
	
	public Image getMenuImage(String type) {
		return images.get("menu/" + type);
	}

}
