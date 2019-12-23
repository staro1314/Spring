package com.example.spring.domain.config.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Staro
 * @date: 2019/8/23 10:36
 * @Description:
 */
@Configuration
public class SparkConfiguration {
    private static String appName = "spark.MainSparkJava";
    private static String master = "local[*]";

    @Bean
    public SparkConf sparkConfig() {
        return new SparkConf().setAppName(appName).setMaster(master)
                // 开启无关系join
                .set("spark.sql.crossJoin.enabled", "true")
                // 启动的executor数量
//                .set("spark.num.executors", "12")
//                .set("spark.executor.memory", "2G")
                // spark程序总核数
                .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
//                .set("spark.cores.max", "24")
                // 每个executor的核数
//                .set("spark.executor.core", "2")
                // 暂时为3机24核集群，并行度为核数的2-3倍
//                .set("spark.default.parallelism", "48")
                .set("spark.sql.shuffle.partitions", "10")
                // 开启consolidation机制（合并）
                .set("spark.shuffle.consolidateFiles", "true")
                .set("spark.task.maxFailures", "8")
                .set("spark.akka.timeout", "300")
                .set("spark.network.timeout", "300")
                .set("spark.shuffle.consolidateFiles", "true")
//                .set("spark.speculation", "true ")
                ;
    }

    @Bean
    public JavaSparkContext javaSparkContext(SparkConf sparkConf) {
        return new JavaSparkContext(sparkConf);
    }
}
