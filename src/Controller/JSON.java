package Controller;

import Model.Board;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JSON {
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
