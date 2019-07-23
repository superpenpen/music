package com.music.util;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @author xiep
 * @version V1.0
 * @Title:  相似度转化
 * @Package com.cstor.intelligent.util
 * @Description:
 * @date 2019/1/31 9:42
 */
@Component
public class SimilarityUtils {


    static Logger logger = Logger.getLogger(SimilarityUtils.class);


    /**
     *  real：真实相似度，转化为厂商需要的相似度
     * @param real
     * @return
     */
    public static double Real2Sham(double real){
        if ( real<=0.2){
            return 0.998;
        }else if ( real<=0.3 && real>0.2){
            return 1.3333-1.6667*real;
        }else if (real<=0.5 && real>0.3){
            return 2.0833*real+0.5-8.3333*real*real*real;
        }else if ( real<=1 && real >0.5){
            return 1-real;
        }else{
            return 0.05;
        }

    }

    /**
     * sham 厂商传的阈值，需转化为本系统认可的真实相似度
     * @param sham
     * @return
     */
    public static  double Sham2Real(double sham){
        double real =0;
        if ( sham == 1 ){
            sham = 0.99;
        }
        if ( sham >0.9 && sham<=0.99){
            real = 0.8-0.6*sham;
        }else if( sham >0.5 && sham<=0.90){
            real = 0.6439*sham+0.2727-0.7576*sham*sham*sham;
        }else if ( sham <= 0.5){
            real = 1-sham;
        }

        return real;
    }


    /**
     * 相似度转化 for 1:!
     */
    public static float similityEvolution(float simility) {
        float evosimi;
        if(0.051<simility && simility<0.1) {
            evosimi=simility-0.05f;
        }
        else if (0.1<simility && simility<0.2) {
            evosimi=simility-0.07f;
        }
        else if (0.2<simility && simility<0.3) {
            evosimi=simility-0.12f;
        }
        else if (0.3<simility && simility<0.4) {
            evosimi=simility-0.18f;
        }
        else if (0.4<simility && simility<0.5) {
            evosimi=simility-0.22f;
        }
        else if (0.5<simility && simility<0.52) {
            evosimi=simility-0.1f;
        }
        else if (0.6<simility && simility<0.8) {
            evosimi=simility+0.1f;
        }
        else {
            evosimi=simility;
        }
        return Math.min(0.9998f, (1-evosimi));
    }


}
