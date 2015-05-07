import java.io.File;

import javax.swing.filechooser.FileFilter;

public class PersonFileFilter extends FileFilter {

	public boolean accept(File f) {

		String name = f.getName();

		String extension = Utile.getFileExtension(name);

		if (f.isDirectory()) {
			return true;
		}

		if ((extension != null) && (extension.equals("per"))) {
			return true;
		}
		return false;
	}

	public String getDescription() {
		return "Personal Database Files (*.per)";
	}

}
