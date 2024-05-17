package Controller;

import View.MyPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

class MyKeyListener implements KeyListener {
    static boolean diameter = true;
    JSON json = new JSON();
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
                    if (missingPieceIndex % json.widthReader(file) != 0 && missingPieceIndex > json.widthReader(file) - 1) {
                        MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex - json.widthReader(file) - 1);
                        MyPanel.getInstance().setMissingPiece(missingPieceIndex - json.widthReader(file) - 1);

                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (keyEvent.getKeyCode() == keyEvent.VK_W) {
                try {
                    if (missingPieceIndex > json.widthReader(file) - 1 && missingPieceIndex % json.widthReader(file) != json.widthReader(file) - 1) {
                        MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex - json.widthReader(file) + 1);
                        MyPanel.getInstance().setMissingPiece(missingPieceIndex - json.widthReader(file) + 1);

                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (keyEvent.getKeyCode() == keyEvent.VK_A) {
                try {
                    if (missingPieceIndex % json.widthReader(file) != 0 && missingPieceIndex < json.widthReader(file) * (json.heightReader(file) - 1)) {
                        MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex + json.widthReader(file) - 1);
                        MyPanel.getInstance().setMissingPiece(missingPieceIndex + json.widthReader(file) - 1);

                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (keyEvent.getKeyCode() == keyEvent.VK_S) {
                try {
                    if (missingPieceIndex % json.widthReader(file) != json.widthReader(file) - 1 && missingPieceIndex < json.widthReader(file) * (json.heightReader(file) - 1)) {
                        MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex + json.widthReader(file) + 1);
                        MyPanel.getInstance().setMissingPiece(missingPieceIndex + json.widthReader(file) + 1);

                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            try {
                if (missingPieceIndex % json.widthReader(file) != json.widthReader(file) - 1) {
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
                if (missingPieceIndex > json.widthReader(file) - 1) {
                    MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex - json.widthReader(file));
                    MyPanel.getInstance().setMissingPiece(missingPieceIndex - json.widthReader(file));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            try {
                if (missingPieceIndex < json.widthReader(file) * (json.heightReader(file) - 1)) {
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
