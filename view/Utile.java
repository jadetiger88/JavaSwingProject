package view;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
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

	public static Font createFont(String path) {
		URL url = System.class.getResource(path);
		if (url == null) {
			System.err.println("Uanble to load font");
		}
		Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, url.openStream());
		} catch (FontFormatException | IOException e) {
			System.out.println("Unbale to open font file");
		}
		return font;
	}

}
