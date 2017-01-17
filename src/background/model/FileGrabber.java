package background.model;

import java.io.File;
import java.io.FilenameFilter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Random;

import javax.swing.ImageIcon;

public class FileGrabber
{
	private String filePath = "/Users/nwhi5696/Desktop/Pictures";

	public FileGrabber()
	{
	}

	public File grabRandomPic()
	{
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
			
			return files[rand.nextInt(files.length)];
		
	}


	public void switchDesktop()
	{

	}

	public void setPath(String filePath)
	{

	}

}
