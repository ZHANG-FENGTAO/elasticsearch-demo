package com.zft.elasticsearch.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author zft
 * @date 2018/10/23.
 */
public class WriteJsonFileUtil {
    public static void WriteConfigJson(String args) {
        String src = "D:\\elasticsearch\\src\\main\\java\\com\\zft\\elasticsearch\\common\\conf.json";

        File file = new File(src);

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            file.delete();
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(args);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
