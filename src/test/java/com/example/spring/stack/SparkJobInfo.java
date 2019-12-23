package com.example.spring.stack;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SparkJobInfo {
    private String userId;
    private String datasetId;
    private String jobId;
    private long createTime;
    /**
     * 优先级
     */
    private int level;
    /**
     * 是否在运行
     */
    private boolean running;
    /**
     * 是否为任务调度
     */
    private boolean schedule;
}
