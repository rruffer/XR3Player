package librarysystema;

import application.Main;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Window;
import javafx.util.Duration;
import smartcontroller.TitleMenuItem;

/**
 * This is the Context Menu for every Library in the LibraryMode
 * 
 * @author GOXR3PLUS
 *
 */
public class LibraryContextMenu extends ContextMenu {

    /** The open. */
    MenuItem open = new MenuItem("Open");

    /** The close. */
    MenuItem close = new MenuItem("Close");

    /** The rename. */
    MenuItem rename = new MenuItem("Rename(R)");

    /** The delete. */
    MenuItem delete = new MenuItem("Delete(D)");

    /** The image. */
    Menu image = new Menu("Image");

    /** The set image. */
    Menu setImage = new Menu("change...");

    /** The local image. */
    MenuItem localImage = new MenuItem("local");

    /** The internet image. */
    MenuItem internetImage = new MenuItem("internet");

    /** The export image. */
    MenuItem exportImage = new MenuItem("export...(E)");

    /** The reset image. */
    MenuItem resetImage = new MenuItem("default");

    /** The settings. */
    MenuItem settings = new MenuItem("Settings(S)");

    /** The library. */
    private Library library;

    /**
     * Instantiates a new library context menu.
     */
    // Constructor
    public LibraryContextMenu() {

	open.setOnAction(ac -> library.libraryOpenClose(true, false));

	close.setOnAction(c -> library.libraryOpenClose(false, false));

	rename.setOnAction(ac -> library.renameLibrary(library));

	localImage.setOnAction(ac -> library.setNewImage());

	resetImage.setOnAction(ac -> library.setDefaultImage());

	exportImage.setOnAction(a -> library.exportImage());

	settings.setOnAction(ac -> Main.libraryMode.settings.showWindow(library));

	delete.setOnAction(ac -> library.deleteLibrary());

	internetImage.setDisable(true);
	// exportImage.setDisable(true)

	setImage.getItems().addAll(localImage, internetImage);
	image.getItems().addAll(setImage, exportImage, resetImage);

	getItems().addAll(new TitleMenuItem("Common"), open, close, rename, new TitleMenuItem("Other"), settings, image, delete);

    }

    /**
     * Shows the LibraryContextMenu.
     *
     * @param window
     *            the window
     * @param x
     *            the x
     * @param y
     *            the y
     * @param library
     *            the library
     */
    public void show(Window window, double x, double y, Library library) {
	this.library = library;

	// customize the menu accordingly
	if (library.isOpened()) {
	    getItems().remove(open);
	    getItems().add(1, close);
	} else {
	    getItems().remove(close);
	    getItems().add(1, open);
	}

	// Show it
	show(window, x - 15 - super.getWidth() + super.getWidth() * 14 / 100, y - 1);

	//Y axis
	double yIni = y - 50;
	double yEnd = super.getY();
	super.setY(yIni);
	final DoubleProperty yProperty = new SimpleDoubleProperty(yIni);
	yProperty.addListener((ob, n, n1) -> super.setY(n1.doubleValue()));

	//X axis
	//	double xIni = screenX - super.getWidth() + super.getWidth() * 14 / 100 + 30;
	//	double xEnd = screenX - super.getWidth() + super.getWidth() * 14 / 100;
	//	super.setX(xIni);
	//	final DoubleProperty xProperty = new SimpleDoubleProperty(xIni);
	//	xProperty.addListener((ob, n, n1) -> super.setY(n1.doubleValue()));

	//Timeline
	Timeline timeIn = new Timeline();
	timeIn.getKeyFrames().addAll(new KeyFrame(Duration.seconds(0.35), new KeyValue(yProperty, yEnd, Interpolator.EASE_BOTH)));
	//new KeyFrame(Duration.seconds(0.5), new KeyValue(xProperty, xEnd, Interpolator.EASE_BOTH)))
	timeIn.play();

    }

}
