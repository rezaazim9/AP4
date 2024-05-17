package Model;

import Controller.JSON;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PuzzlePiece {
    private JSON json = new JSON();
    private final String file = "C:\\Users\\ostad\\IdeaProjects\\AP4\\src\\assets\\config.json";
    private Image img;
   private int pieceNumber = json.heightReader(file) * json.widthReader(file) - 1;

    public JSON getJson() {
        return json;
    }

    public void setJson(JSON json) {
        this.json = json;
    }

    public String getFile() {
        return file;
    }


    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getPieceNumber() {
        return pieceNumber;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    private Location location;

    public PuzzlePiece(String imageName) throws IOException {
        try {
            img = ImageIO.read(new File("src/assets/" + imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!imageName.equals("missing.jpg")) {
            pieceNumber = Integer.parseInt(imageName.substring(0, imageName.indexOf('.'))) - 1;
        }
    }

    public PuzzlePiece(String ImagePath, Location location) throws IOException {
        this(ImagePath);
        this.location = location;
    }

    public PuzzlePiece(Image img, Location location, int pieceIdentifier) throws IOException {
        this.img = img;
        this.location = location;
        this.pieceNumber = pieceIdentifier;
    }

    public void setPieceNumber(int pieceNumber) {
        this.pieceNumber = pieceNumber;
    }

    public void setImage(Image img) {
        this.img = img;
    }

    public PuzzlePiece getClone() throws IOException {
        return new PuzzlePiece(img, location, pieceNumber);
    }
}
