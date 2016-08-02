package com.ld.practice.json2sql;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.List;

/**
 * Created by ld000 on 2016/8/2.
 */
public class Json2Sql {

    public static void main(String[] args) {
        String json = readFile("D:\\workspace\\eclipse\\practice\\src\\main\\java\\com\\ld\\practice\\json2sql\\temp.json");

        System.out.println(json);

        JSONObject jsonObject = JSON.parseObject(json);

        String tableName = jsonObject.getString("tableName");

        System.out.println(tableName);

        JSONArray columnMappings = jsonObject.getJSONArray("columnMappings");

        System.out.println(columnMappings);

        for (Object obj: columnMappings) {
            
        }
    }

    public static String readFile(String filename) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            br = new BufferedReader(new FileReader(filename));

            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
            }
        }

        return sb.toString();
    }

}
