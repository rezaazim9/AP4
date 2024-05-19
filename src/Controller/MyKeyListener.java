package Controller;

import Model.Variables;
import View.MyPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;



class MyKeyListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int missingPieceIndex = MyPanel.getInstance().getMissingPiece();
        if (Variables.isDiameter()
        ) {
            if (keyEvent.getKeyCode() == keyEvent.VK_Q) {
                try {
                    if (missingPieceIndex % JSON.getJson().widthReader(Variables.getFile()) != 0 && missingPieceIndex > JSON.getJson().widthReader(Variables.getFile()) - 1) {
                        MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex - JSON.getJson().widthReader(Variables.getFile()) - 1);
                        MyPanel.getInstance().setMissingPiece(missingPieceIndex - JSON.getJson().widthReader(Variables.getFile()) - 1);

                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (keyEvent.getKeyCode() == keyEvent.VK_W) {
                try {
                    if (missingPieceIndex > JSON.getJson().widthReader(Variables.getFile())- 1 && missingPieceIndex % JSON.getJson().widthReader(Variables.getFile())!= JSON.getJson().widthReader(Variables.getFile()) - 1) {
                        MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex - JSON.getJson().widthReader(Variables.getFile()) + 1);
                        MyPanel.getInstance().setMissingPiece(missingPieceIndex - JSON.getJson().widthReader(Variables.getFile()) + 1);

                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (keyEvent.getKeyCode() == keyEvent.VK_A) {
                try {
                    if (missingPieceIndex % JSON.getJson().widthReader(Variables.getFile())!= 0 && missingPieceIndex < JSON.getJson().widthReader(Variables.getFile())* (JSON.getJson().heightReader(Variables.getFile()) - 1)) {
                        MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex + JSON.getJson().widthReader(Variables.getFile()) - 1);
                        MyPanel.getInstance().setMissingPiece(missingPieceIndex + JSON.getJson().widthReader(Variables.getFile()) - 1);

                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (keyEvent.getKeyCode() == keyEvent.VK_S) {
                try {
                    if (missingPieceIndex % JSON.getJson().widthReader(Variables.getFile()) != JSON.getJson().widthReader(Variables.getFile())- 1 && missingPieceIndex < JSON.getJson().widthReader(Variables.getFile()) * (JSON.getJson().heightReader(Variables.getFile()) - 1)) {
                        MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex + JSON.getJson().widthReader(Variables.getFile()) + 1);
                        MyPanel.getInstance().setMissingPiece(missingPieceIndex + JSON.getJson().widthReader(Variables.getFile()) + 1);

                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            try {
                if (missingPieceIndex % JSON.getJson().widthReader(Variables.getFile())!= JSON.getJson().widthReader(Variables.getFile()) - 1) {
                    MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex + 1);
                    MyPanel.getInstance().setMissingPiece(missingPieceIndex + 1);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            try {
                if (missingPieceIndex % JSON.getJson().widthReader(Variables.getFile()) != 0) {
                    MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex - 1);
                    MyPanel.getInstance().setMissingPiece(missingPieceIndex - 1);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            try {
                if (missingPieceIndex > JSON.getJson().widthReader(Variables.getFile())- 1) {
                    MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex - JSON.getJson().widthReader(Variables.getFile()));
                    MyPanel.getInstance().setMissingPiece(missingPieceIndex - JSON.getJson().widthReader(Variables.getFile()));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            try {
                if (missingPieceIndex < JSON.getJson().widthReader(Variables.getFile()) * (JSON.getJson().heightReader(Variables.getFile())- 1)) {
                    MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex + JSON.getJson().widthReader(Variables.getFile()));
                    MyPanel.getInstance().setMissingPiece(missingPieceIndex + JSON.getJson().widthReader(Variables.getFile()));
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
