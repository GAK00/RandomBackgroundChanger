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
	private int minutes = 5;
	private final int seconds = 60;
	public BackgroundController()
	{
	}
	public void start()
	{
	
		
		
		TimerTask changeBackground = new TimerTask(){

			
			public void run()
			{
				FileGrabber grab = new FileGrabber();
				String Path = grab.grabRandomPic().getAbsolutePath();
				if (Files.exists(Paths.get(new File(Path).toURI())))
				{
					Process process;
					try
					{
						process = Runtime.getRuntime().exec("/usr/bin/sudo -S sudo cp " + Path + " /System/Library/CoreServices/DefaultDesktop.jpg");

						Writer toSudo = new OutputStreamWriter(process.getOutputStream());
						String password = "gRb4yP6s5";

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
		};
			
			Timer t = new Timer();
			t.schedule(changeBackground,0, seconds *1000*minutes);
			
			
		

	
	}
}
