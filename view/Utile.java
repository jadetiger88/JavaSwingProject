package view;

import java.net.URL;

import javax.swing.ImageIcon;

public class Utile {
	public static String getFileExtension(String name) {
		int extensionIndex = name.lastIndexOf(".");

		// No extension
		if (extensionIndex == -1) {
			return null;
		}

		// Nothing after the .
		if (extensionIndex == name.length() - 1) {
			return null;
		}

		// Valid Extension
		return (name.substring(extensionIndex + 1, name.length()));
	}

	public static ImageIcon createIcon(String path) {
		URL url = System.class.getResource(path);
		if (url == null) {
			System.err.println("Uanble to load icon image");
		}
		ImageIcon icon = new ImageIcon(url);
		return icon;
	}

}
