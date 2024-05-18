package Controller;

import View.CLI;
import View.MyPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import static Controller.Main.diameter;

class MyKeyListener implements KeyListener {
    String file = "C:\\Users\\ostad\\IdeaProjects\\AP4\\src\\assets\\config.json";

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int missingPieceIndex = MyPanel.getInstance().getMissingPiece();
        if (diameter) {
            if (keyEvent.getKeyCode() == keyEvent.VK_Q) {
                try {
                    if (missingPieceIndex % JSON.getJson().widthReader(file) != 0 && missingPieceIndex > JSON.getJson().widthReader(file) - 1) {
                        MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex - JSON.getJson().widthReader(file) - 1);
                        MyPanel.getInstance().setMissingPiece(missingPieceIndex - JSON.getJson().widthReader(file) - 1);

                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (keyEvent.getKeyCode() == keyEvent.VK_W) {
                try {
                    if (missingPieceIndex > JSON.getJson().widthReader(file) - 1 && missingPieceIndex % JSON.getJson().widthReader(file) != JSON.getJson().widthReader(file) - 1) {
                        MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex - JSON.getJson().widthReader(file) + 1);
                        MyPanel.getInstance().setMissingPiece(missingPieceIndex - JSON.getJson().widthReader(file) + 1);

                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (keyEvent.getKeyCode() == keyEvent.VK_A) {
                try {
                    if (missingPieceIndex % JSON.getJson().widthReader(file) != 0 && missingPieceIndex < JSON.getJson().widthReader(file) * (JSON.getJson().heightReader(file) - 1)) {
                        MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex + JSON.getJson().widthReader(file) - 1);
                        MyPanel.getInstance().setMissingPiece(missingPieceIndex + JSON.getJson().widthReader(file) - 1);

                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (keyEvent.getKeyCode() == keyEvent.VK_S) {
                try {
                    if (missingPieceIndex % JSON.getJson().widthReader(file) != JSON.getJson().widthReader(file) - 1 && missingPieceIndex < JSON.getJson().widthReader(file) * (JSON.getJson().heightReader(file) - 1)) {
                        MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex + JSON.getJson().widthReader(file) + 1);
                        MyPanel.getInstance().setMissingPiece(missingPieceIndex + JSON.getJson().widthReader(file) + 1);

                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            try {
                if (missingPieceIndex % JSON.getJson().widthReader(file) != JSON.getJson().widthReader(file) - 1) {
                    MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex + 1);
                    MyPanel.getInstance().setMissingPiece(missingPieceIndex + 1);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            try {
                if (missingPieceIndex % JSON.getJson().widthReader(file) != 0) {
                    MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex - 1);
                    MyPanel.getInstance().setMissingPiece(missingPieceIndex - 1);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            try {
                if (missingPieceIndex > JSON.getJson().widthReader(file) - 1) {
                    MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex - JSON.getJson().widthReader(file));
                    MyPanel.getInstance().setMissingPiece(missingPieceIndex - JSON.getJson().widthReader(file));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            try {
                if (missingPieceIndex < JSON.getJson().widthReader(file) * (JSON.getJson().heightReader(file) - 1)) {
                    MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex + JSON.getJson().widthReader(file));
                    MyPanel.getInstance().setMissingPiece(missingPieceIndex + JSON.getJson().widthReader(file));
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
