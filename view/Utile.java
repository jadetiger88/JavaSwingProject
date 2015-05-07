package view;
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
}
