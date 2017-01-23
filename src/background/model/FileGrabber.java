package background.model;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import javax.swing.ImageIcon;

public class FileGrabber
{
	private String filePath;

	public FileGrabber()
	{
		filePath = getParentDirectory() + "/Pictures";
	}
	

	public File grabNewPic()
	{
		String option = getOption(3);
	
		if(option.equals("true")){
	
			File[] files = new File(filePath).listFiles(new FilenameFilter(){

				@Override
				public boolean accept(File dir, String name)
				{
					if(name.endsWith(".jpg")||name.endsWith(".jpeg")||name.endsWith(".png"))
					{
						return true;
					}
					else{
					return false;}
				}});
			Random rand = new Random();
			
			return files[rand.nextInt(files.length)];}
		else
		{
			return new File(getParentDirectory()+"/Picture.jpg");}
		
		
	}
	
	public String getOption(int optionNumber)
	{
		String option = "";
		try
		{
			option = Files.readAllLines(Paths.get(getParentDirectory()+"/BackgroundData.txt")).get(optionNumber);
		
		option = option.substring(option.indexOf(" ")+1);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return option;
	}


	public void switchDesktop()
	{

	}

	public void setPath(String filePath)
	{

	}
	private String getParentDirectory()
	{
		String parentPath = "";
		try
	{
		
		URL childPath = FileGrabber.class.getProtectionDomain().getCodeSource().getLocation();
		String childFilePath;
		
			childFilePath = URLDecoder.decode(childPath.getFile(), "UTF-8");
		
		parentPath = new File(childFilePath).getParentFile().getPath();
	} catch (UnsupportedEncodingException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return parentPath;
		

	}}
