package com.marcelmartens;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.fasterxml.jackson.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import com.fasterxml.jackson.databind.*;

@Deprecated
public class ApiController {
    private int[][] grid;
    // used for testing so i dont use to many api calls
    private String testString = "{\"newboard\":{\"grids\":[{\"value\":[[0,9,0,0,0,5,0,0,0],[0,0,0,8,6,0,0,0,0],[0,0,6,0,0,0,0,2,8],[0,3,0,0,0,0,0,5,0],[6,0,0,0,0,0,8,0,0],[0,0,4,0,0,6,0,0,2],[3,0,0,0,0,8,0,0,0],[0,0,0,0,0,0,0,0,0],[2,0,9,0,7,0,0,8,0]],\"solution\":[[8,9,7,1,2,5,3,6,4],[1,2,3,8,6,4,5,7,9],[5,4,6,7,3,9,1,2,8],[7,3,2,4,8,1,9,5,6],[6,1,5,2,9,7,8,4,3],[9,8,4,3,5,6,7,1,2],[3,6,1,5,4,8,2,9,7],[4,7,8,9,1,2,6,3,5],[2,5,9,6,7,3,4,8,1]],\"difficulty\":\"Hard\"}],\"results\":1,\"message\":\"AllOk\"}}";

    public static void main(String[] args) {
        try {
            String resp = GET(1).replaceAll("\\s", "");
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String GET(int difficulty) throws Exception {
        // placeholder strings
        String s1 = "https://sudoku-api.vercel.app/api/dosuku";
        String s3 = "https://sudoku-api.vercel.app/api/dosuku?query={newboard(limit:1){grids{value}}}";
        StringBuilder result = new StringBuilder();
        URL url = new URL(s1);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null;) {
                result.append(line);
            }
        }

        return result.toString();
    }

    public static Board getBoard(int difficulty) throws Exception {
        String jsonResp = GET(difficulty);
        ObjectMapper mapper = new ObjectMapper();

        // Assuming 'newboard' and 'grids' structure in the JSON
        Board response = mapper.readValue(jsonResp, Board.class);
        return response; // placeholder!
    }

}

class Board {
    private int[][] newGrid;
    private int[][] solution;
    private String difficulty;

    @JsonProperty("newGrid")
    private void setNewGrid(List<List<Integer>> newGrid) {
        this.newGrid = listToArray(newGrid);
    }

    @JsonProperty("solution")
    private void setSolution(List<List<Integer>> solution) {
        this.solution = listToArray(solution);
    }

    private int[][] listToArray(List<List<Integer>> list) {
        int[][] array = new int[list.size()][list.get(0).size()];
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                array[i][j] = list.get(i).get(j);
            }
        }
        return array;
    }
}