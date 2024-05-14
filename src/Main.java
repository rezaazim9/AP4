import com.fasterxml.jackson.databind.ObjectMapper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

class Board {
    public int widthTiles;
    public int heightTiles;
    public ArrayList<Integer> list = new ArrayList<>();
    public ArrayList<String> list2 = new ArrayList<>();

    public Board(ArrayList<Integer> list, ArrayList<String> list2, int widthTiles, int heightTiles) {
        this.list = list;
        this.list2 = list2;
        this.heightTiles=heightTiles;
        this.widthTiles=widthTiles;
    }

    public Board() {
    }

    public ArrayList<Integer> getList() {
        return list;
    }

    public void setList(ArrayList<Integer> list) {
        this.list = list;
    }

    public ArrayList<String> getList2() {
        return list2;
    }

    public void setList2(ArrayList<String> list2) {
        this.list2 = list2;
    }

    public int getWidthTiles() {
        return widthTiles;
    }

    public void setWidthTiles(int widthTiles) {
        this.widthTiles = widthTiles;
    }

    public int getHeightTiles() {
        return heightTiles;
    }

    public void setHeightTiles(int heightTiles) {
        this.heightTiles = heightTiles;
    }
}

class Location {
    private int x, y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Location() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

class PuzzlePiece {
    JSON json=new JSON();
    String file="C:\\Users\\ostad\\IdeaProjects\\AP4\\src\\assets\\config.json";
    public Image img;
    protected int pieceNumber=json.heightReader(file)* json.widthReader(file)-1;
    Location location;
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

class MyPanel extends JPanel {
    public static MyPanel panelInstance;
    public ArrayList<PuzzlePiece> puzzlePieces = new ArrayList<>();
    public int missingPiece = 0;
    public String gameState = "#";
    JSON json=new JSON();
    String file="C:\\Users\\ostad\\IdeaProjects\\AP4\\src\\assets\\config.json";
    public static MyPanel getInstance() {
        if (panelInstance == null) {
            panelInstance = new MyPanel();
        }
        return panelInstance;
    }
    public void swapPieces(int i, int j) throws IOException {
        PuzzlePiece copy = this.puzzlePieces.get(i).getClone();
        puzzlePieces.get(i).setImage(puzzlePieces.get(j).img);
        puzzlePieces.get(i).setPieceNumber(puzzlePieces.get(j).pieceNumber);
        puzzlePieces.get(j).setImage(copy.img);
        puzzlePieces.get(j).setPieceNumber(copy.pieceNumber);
        if (gameFinished()) {
            gameState = "finished";
        }
    }
    public boolean gameFinished() throws IOException {
        for (int i = 0; i < json.heightReader(file)* json.widthReader(file); i++) {
            int pieceIdentifier = puzzlePieces.get(i).pieceNumber;
            if (pieceIdentifier != i) {
                return false;
            }
        }
        return true;
    }
    public void setPuzzlePieces(ArrayList<PuzzlePiece> puzzlePieces) {
        this.puzzlePieces = puzzlePieces;
    }

    public void setMissingPiece(int missingPiece) {
        this.missingPiece = missingPiece;
    }

    @Override
    protected void paintComponent(Graphics g) {
        JSON size= new JSON();
        String file="C:\\Users\\ostad\\IdeaProjects\\AP4\\src\\assets\\config.json";
        super.paintComponent(g);
        for (PuzzlePiece piece : puzzlePieces) {
            try {
                g.drawImage(piece.img, piece.location.getX(), piece.location.getY(), (int) this.getSize().getWidth() / size.widthReader(file), (int) this.getSize().getHeight() /size.heightReader(file), null);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


class MyKeyListener implements KeyListener {
    boolean diameter=true;
    JSON json=new JSON();
    String file="C:\\Users\\ostad\\IdeaProjects\\AP4\\src\\assets\\config.json";
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int missingPieceIndex = MyPanel.getInstance().missingPiece;
        if (diameter){
            if (keyEvent.getKeyCode()==keyEvent.VK_Q){
                try {
                    if (missingPieceIndex % json.widthReader(file) != 0&&missingPieceIndex > json.widthReader(file)-1){
                        MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex - json.widthReader(file)-1);
                        MyPanel.getInstance().setMissingPiece(missingPieceIndex - json.widthReader(file)-1);

                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (keyEvent.getKeyCode()==keyEvent.VK_W){
                try {
                    if (missingPieceIndex > json.widthReader(file)-1&&missingPieceIndex % json.widthReader(file) != json.widthReader(file)-1){
                        MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex - json.widthReader(file)+1);
                        MyPanel.getInstance().setMissingPiece(missingPieceIndex - json.widthReader(file)+1);

                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (keyEvent.getKeyCode()==keyEvent.VK_A){
                try {
                    if (missingPieceIndex % json.widthReader(file) != 0&&missingPieceIndex < json.widthReader(file)*(json.heightReader(file)-1)){
                        MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex + json.widthReader(file)-1);
                        MyPanel.getInstance().setMissingPiece(missingPieceIndex + json.widthReader(file)-1);

                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (keyEvent.getKeyCode()==keyEvent.VK_S){
                try {
                    if (missingPieceIndex % json.widthReader(file) != json.widthReader(file)-1&&missingPieceIndex < json.widthReader(file)*(json.heightReader(file)-1)){
                        MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex + json.widthReader(file)+1);
                        MyPanel.getInstance().setMissingPiece(missingPieceIndex + json.widthReader(file)+1);

                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            try {
                if (missingPieceIndex % json.widthReader(file) != json.widthReader(file)-1) {
                    MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex + 1);
                    MyPanel.getInstance().setMissingPiece(missingPieceIndex + 1);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            try {
                if (missingPieceIndex % json.widthReader(file) != 0) {
                    MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex - 1);
                    MyPanel.getInstance().setMissingPiece(missingPieceIndex - 1);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            try {
                if (missingPieceIndex > json.widthReader(file)-1) {
                    MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex - json.widthReader(file));
                    MyPanel.getInstance().setMissingPiece(missingPieceIndex - json.widthReader(file));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            try {
                if (missingPieceIndex < json.widthReader(file)*(json.heightReader(file)-1)) {
                    MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex + json.widthReader(file));
                    MyPanel.getInstance().setMissingPiece(missingPieceIndex + json.widthReader(file));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
    }
}

class JSON {
    public ArrayList<Integer> orderReader(String source) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Board order = objectMapper.readValue(new File(source), Board.class);
        return order.list;
    }
    public int heightReader(String source) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Board order = objectMapper.readValue(new File(source), Board.class);
        return order.heightTiles;

    }
    public int widthReader(String source) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Board order = objectMapper.readValue(new File(source), Board.class);
        return order.widthTiles;

    }
    public void writer(String source, Object object) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(source), object);

    }

    public ArrayList<String> imageReader(String source) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Board order = objectMapper.readValue(new File(source), Board.class);
        return order.list2;
    }

}

public class Main {
    static int fps=10;
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame();
        JSON json=new JSON();
        String file="C:\\Users\\ostad\\IdeaProjects\\AP4\\src\\assets\\config.json";
        MyPanel panel = MyPanel.getInstance();
        boolean gameFinished = false;
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int maxSize = Math.max(screenWidth, screenHeight) / 3;
        panel.setSize(maxSize, maxSize);
        panel.setLocation(screenWidth / 2 - maxSize / 2, screenHeight / 2 - maxSize / 2);
        frame.setSize(panel.getSize());
        frame.setLocation(panel.getLocation());
        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ArrayList<PuzzlePiece> puzzlePieces = new ArrayList<>();
        ArrayList<Integer> piecesRandomOrder = new JSON().orderReader("C:\\Users\\ostad\\IdeaProjects\\AP4\\src\\assets\\config.json");
       ArrayList<String> images = new JSON().imageReader("C:\\Users\\ostad\\IdeaProjects\\AP4\\src\\assets\\config.json");
        Collections.shuffle(piecesRandomOrder);
        for (int i = 0; i < piecesRandomOrder.size(); i++)
            if (piecesRandomOrder.get(i) +1== json.heightReader(file)* json.widthReader(file))
                panel.setMissingPiece(i);
//        if (!solvable(panel.missingPiece, piecesRandomOrder)) {
//            JOptionPane.showMessageDialog(frame, "this puzzle is not solvable, change your config and try again", "Puzzle not solvable", JOptionPane.WARNING_MESSAGE);
//            gameFinished = true;
//        }
        for (int i = 0; i < piecesRandomOrder.size(); i++) {
            if (panel.missingPiece != i) {
                puzzlePieces.add(new PuzzlePiece(images.get(piecesRandomOrder.get(i)) , new Location(panel.getHeight() / json.widthReader(file) * (i % json.widthReader(file)), panel.getWidth() / json.heightReader(file) * (i /json.widthReader(file)))));
            } else {
                puzzlePieces.add(new PuzzlePiece("missing.jpg", new Location(panel.getHeight() / json.widthReader(file) * (i % json.widthReader(file)), panel.getWidth() / json.heightReader(file) * (i /json.widthReader(file)))));
            }
        }
        panel.setPuzzlePieces(puzzlePieces);
        frame.addKeyListener(new MyKeyListener());
        frame.setVisible(true);
        while (true) {
            try {
                Thread.sleep(fps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            panel.repaint();
            frame.repaint();
            if (gameFinished) {
                break;
            }
            if (panel.gameState.equals("finished")) {
                JOptionPane.showMessageDialog(frame, "You finished the game, congratulation", "Game Finished", JOptionPane.INFORMATION_MESSAGE);
                gameFinished = true;
            }
        }
    }

    public static boolean solvable(int missingPiece, ArrayList<Integer> piecesOrder) {
        int inversionCount = 0;
        for (int i = 0; i < piecesOrder.size(); i++) {
            for (int j = i + 1; j < piecesOrder.size(); j++) {
                if (piecesOrder.get(i) > piecesOrder.get(j)) {
                    inversionCount += 1;
                }
            }
        }
        int parity = inversionCount % 2;
        int distanceOfMissingPiece = (2 - (missingPiece % 3)) + (2 - (missingPiece / 3));
        parity ^= (distanceOfMissingPiece % 2);
        return parity == 0;
    }
}