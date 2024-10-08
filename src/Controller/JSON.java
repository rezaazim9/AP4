package Controller;

import Model.Board;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JSON {
    static JSON json;

    public static JSON getJson() {
        if (json == null) {
            json = new JSON();
        }
        return json;
    }

    public ArrayList<Integer> orderReader(String source) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Board order = objectMapper.readValue(new File(source), Board.class);
        return order.getList();
    }

    public int heightReader(String source) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Board order = objectMapper.readValue(new File(source), Board.class);
        return order.getHeightTiles();

    }

    public int widthReader(String source) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Board order = objectMapper.readValue(new File(source), Board.class);
        return order.getWidthTiles();

    }

    public void writer(String source, Object object) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(source), object);

    }

    public ArrayList<String> imageReader(String source) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Board order = objectMapper.readValue(new File(source), Board.class);
        return order.getList2();
    }

}
