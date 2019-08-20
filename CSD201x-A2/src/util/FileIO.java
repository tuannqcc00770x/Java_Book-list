package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileIO {
	public void saveData(MyList o, String name) {//save file
		try (FileOutputStream fos = new FileOutputStream(new File(name))){
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(o);
			oos.close();
		}
			catch (Exception e) {
				System.out.println(e);
			}
	}
	public MyList loadData(String name) {//load file
		MyList o = new MyList();
		try (FileInputStream fos = new FileInputStream(new File(name))){
			ObjectInputStream oos = new ObjectInputStream(fos);
			o = ((MyList) oos.readObject());
		}
			catch (Exception e) {
				System.out.println(e);
			}
		return o;
	}
}
