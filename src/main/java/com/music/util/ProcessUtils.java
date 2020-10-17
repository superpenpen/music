package com.music.util;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * @ClassName ProcessUtils
 * @Description
 * @Author xiep
 * @Date 2020/2/4 14:23
 **/
@Component
public class ProcessUtils {


    static Logger logger = Logger.getLogger(ProcessUtils.class);


    public static int getResponse(String args[]) {
        BufferedReader br = null;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(args);
            br = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));
            String str = null;
            int code = process.waitFor();
            while (br.ready()) {
                str = br.readLine();
                logger.info("str=====" + str);
            }
            if (code > 0){
                return 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        return 0;
    }




}
