package sample;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public class Test {
	public void findPath(){try{
		ClassLoader loader =this.getClass().getClassLoader();
		URL url= loader.getResource("Lezioni");
		System.out.println(url==null);
		InputStream stream= Test.class.getResourceAsStream("/Lezioni");
		InputStreamReader isr =new InputStreamReader(stream);
		BufferedReader buf=new BufferedReader(isr);
		String line=buf.readLine();
		while (line!=null){
			System.out.println(line);
			line=buf.readLine();
		}
		buf.close();
	}catch(Exception e){e.printStackTrace();}
	}
}
