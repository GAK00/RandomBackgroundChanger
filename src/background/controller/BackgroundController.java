package background.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Timer;
import java.util.TimerTask;

import background.model.FileGrabber;

public class BackgroundController
{
	private final int seconds = 60;

	Timer t;
	FileGrabber grab;
	public BackgroundController()
	{

		grab = new FileGrabber();
		t = new Timer();
	}
	
	public void changeDesktopBackground()
	{
		if(!grab.getOption(5).equals(true)){
		String Path = grab.grabNewPic().getAbsolutePath();
		if (Files.exists(Paths.get(new File(Path).toURI())))
		{
			Process process;
			Writer toSudo;
			String password = grab.getOption(1);
			try
			{
				System.out.println("ran");
				
				if(grab.getOption(2).equals("true"))
				{
					process = Runtime.getRuntime().exec("/usr/bin/sudo -S sudo chmod 755 /System/Library/CoreServices/DefaultDesktop.jpg");
					toSudo = new OutputStreamWriter(process.getOutputStream());
					toSudo.write(password);
					toSudo.write('\n'); // sudo's docs demand a newline after the
					                    // password
					toSudo.close(); // but closing the stream might be sufficient
				}
				process = Runtime.getRuntime().exec("/usr/bin/sudo -S sudo cp " + Path + " /System/Library/CoreServices/DefaultDesktop.jpg");

				toSudo = new OutputStreamWriter(process.getOutputStream());

				toSudo.write(password);
				toSudo.write('\n'); // sudo's docs demand a newline after the
				                    // password
				toSudo.close(); // but closing the stream might be sufficient
				process = Runtime.getRuntime().exec("/usr/bin/sudo -S sudo killall Dock");
				toSudo = new OutputStreamWriter(process.getOutputStream());
				toSudo.write(password);
				toSudo.write('\n'); // sudo's docs demand a newline after the
				                    // password
				toSudo.close(); // but closing the stream might be sufficient
			} catch (IOException e1)
			{
				System.out.println("nope");
				e1.printStackTrace();
			}

		}
		}
		else
		{
			t.cancel();
			start();
		}
	}
	public void start()
	{
	
		
		changeDesktopBackground();
		if(grab.getOption(3).equals("true")){
		TimerTask changeBackground = new TimerTask(){

			
			public void run()
			{
				changeDesktopBackground();
			}
		};
			
			String timerOption = grab.getOption(4);
			int time = Integer.parseInt(timerOption.substring(timerOption.indexOf("M")+2,timerOption.indexOf(",",timerOption.indexOf("M")+2)))*1000*seconds;
			time = time + Integer.parseInt(timerOption.substring(timerOption.indexOf("S")+2,timerOption.indexOf(",",timerOption.indexOf("S")+2)))*1000;
			time = time + Integer.parseInt(timerOption.substring(timerOption.indexOf("MS")+3));
			System.out.println(time);
			t.schedule(changeBackground,time,time);}
			
			
		

	
	}
}
