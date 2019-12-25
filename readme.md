本项目为一个Demo框架

注意：
拉去本项目导包后，需要将org.slf4j:jcl-over-slf4j和Gradle: org.slf4j:slf4j-log4j12
去掉，这两个包为spark的依赖与slf4j的包重复，相冲突

2019-08-29
1.新增了数据源Jpa，PostGreSql
2.新增了Spark数据清洗工具
3.即将新增db2数据源
功能模块：
1.数据源
本项目采用阿里巴巴的druid多数据源连接，在本项目中有两种数据源，分别为mysql,H2和postgreSQL；
druid：配置信息在com.cache.radis.demo.config.druid中
mysql：配置信息在com.cache.radis.demo.config.druid.mysql中
mapper Dao在com.cache.radis.demo.mapper.mysql下
mapper xml在resource.mapper.mysql下
H2:    配置信息在com.cache.radis.demo.config.druid.h2中
mapper Dao在com.cache.radis.demo.mapper.h2下
mapper xml在resource.mapper.h2下
postgreSQL:
配置信息在com.cache.radis.demo.config.druid.pg中
mapper Dao在com.cache.radis.demo.mapper.pg下
mapper xml在resource.mapper.pg下
2.消息队列
消息队列采用的是rabbitMQ
配置信息在com.cache.radis.demo.config.rabbitmq
3.缓存
缓存采用的是redis
配置信息在com.cache.radis.demo.config.redis下
4.Api文档生成工具
Api文档生成工具采用的swagger
配置信息在com.cache.radis.demo.config.swagger下