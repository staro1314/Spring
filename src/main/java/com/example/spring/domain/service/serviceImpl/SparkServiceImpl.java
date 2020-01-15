package com.example.spring.domain.service.serviceImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.spring.domain.bean.SparkBean;
import com.example.spring.domain.returnMsg.ReturnBody;
import com.example.spring.domain.returnMsg.codeMsg.ReturnMsgImpl;
import com.example.spring.domain.returnMsg.excetion.GlobalException;
import com.example.spring.domain.service.service.SparkService;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.ForeachFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.*;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scala.Tuple2;
import scala.collection.JavaConverters;
import scala.collection.Seq;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: Staro
 * @date: 2019/7/11 5:45
 * @Description:
 */
@Service
public class SparkServiceImpl implements SparkService {
    private static String appName = "spark.MainSparkJava";
    private static String master = "local[*]";

    private final SparkConf sparkConf;
    private final JavaSparkContext javaSparkContext;

    @Autowired
    public SparkServiceImpl(SparkConf sparkConf, JavaSparkContext javaSparkContext) {
        this.sparkConf = sparkConf;
        this.javaSparkContext = javaSparkContext;
    }


    public static void sparkSql() {
        long time = new Date().getTime();
        System.out.println(time);
        //连接数据库
        JavaSparkContext sc = null;
        System.setProperty("hadoop.home.dir", "D:\\spark\\spark-2.4.3-bin-hadoop2.7");
        //初始化SparkConf
        SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
        sc = new JavaSparkContext(conf);
        time = new Date().getTime();
        System.out.println(time);
        String sql = "(select tb.id,tb.constant_name,tb.constant_desc from tb_constant tb) as tb_constant";
        SQLContext sqlContext = SQLContext.getOrCreate(JavaSparkContext.toSparkContext(sc));
        DataFrameReader reader = sqlContext.read().format("jdbc");
        reader.option("url", "jdbc:mysql://10.129.8.22:3306/db_ids_plus_phase_ii");//数据库路径
        reader.option("dbtable", sql);//数据表名
        reader.option("driver", "com.mysql.jdbc.Driver");
        reader.option("user", "ids");
        reader.option("password", "beaconweb");
        Dataset<Row> projectDataSourceDFFromMySQL = reader.load();
        //List接收
        List<Row> list = projectDataSourceDFFromMySQL.collectAsList();
        System.out.println(list);
        long end = new Date().getTime();
        System.out.println(end - time);
    }

    public static void sparkJsonDataSet() {
        System.setProperty("hadoop.home.dir", "D:\\spark\\spark-2.4.3-bin-hadoop2.7");
        SparkConf conf = new SparkConf().setAppName("GroupTop").setMaster("local[*]");
        SparkSession sparkSession = SparkSession.builder().config(conf).getOrCreate();

        Dataset<Row> df = sparkSession.read().json("C:/Users/Tina/Desktop/jsonstr.json");
        df.createOrReplaceTempView("jsonstr");
        List<Column> list = new ArrayList<>();
        Column clo1 = df.col("省份");
        Column clo2 = df.col("时间").alias("time");

        list.add(clo1);
        list.add(clo2);
        Seq<Column> columnSeq = JavaConverters.asScalaIteratorConverter(list.iterator()).asScala().toSeq();
        Dataset<Row> dataset =
                df.select(columnSeq).limit(1000);
        String[] columns = dataset.columns();
        Arrays.stream(columns).forEach(System.out::println);
        List<Row> rows = dataset.takeAsList(0);
        JavaRDD<Row> rowJavaRDD = dataset.toJavaRDD();
        List<Row> collect = rowJavaRDD.collect();
        System.out.println();
        for (Row row : collect) {
            System.out.println();
            System.out.println(row.get(1));
            System.out.println(row.get(2));
            System.out.println("--------");
        }
    }


    public static List<String> sparkJsonDataFrame() {
        SparkConf conf = new SparkConf().setAppName("GroupTop").setMaster("local[*]");
        SparkSession sparkSession = SparkSession.builder().config(conf).getOrCreate();
        JavaSparkContext sparkContext = JavaSparkContext.fromSparkContext(sparkSession.sparkContext());
        String json =
                "[\n" +
                        "    {\n" +
                        "      \"省份\": \"北京市\",\n" +
                        "      \"总成本\": 12,\n" +
                        "      \"时间\": \"2019-7-01 8:5:30\",\n" +
                        "      \"时间戳\": 1561941930,\n" +
                        "      \"产品名称\": \"柠檬花茶\",\n" +
                        "      \"销售额\": \"42,323.325\",\n" +
                        "      \"产品种类\": \"花茶\",\n" +
                        "      \"利润\": 32,\n" +
                        "      \"咖啡因类型\": \"无咖啡因\",\n" +
                        "      \"市场分布\": \"中部市场\",\n" +
                        "      \"边际利润\": 43,\n" +
                        "      \"销量\": 1\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"省份\": \"北京市\",\n" +
                        "      \"总成本\": 11,\n" +
                        "      \"时间\": \"2019-07-2 08:45:30\",\n" +
                        "      \"时间戳\": 1562028330,\n" +
                        "      \"产品名称\": \"柠檬花茶\",\n" +
                        "      \"销售额\": \"46.321\",\n" +
                        "      \"产品种类\": \"花茶\",\n" +
                        "      \"利润\": 47,\n" +
                        "      \"咖啡因类型\": \"无咖啡因\",\n" +
                        "      \"市场分布\": \"中部市场\",\n" +
                        "      \"边际利润\": 43,\n" +
                        "      \"销量\": 2\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"省份\": \"北京市\",\n" +
                        "      \"总成本\": 17,\n" +
                        "      \"时间\": \"2019-6-3 8:5:3\",\n" +
                        "      \"时间戳\": 1559522730,\n" +
                        "      \"产品名称\": \"柠檬花茶\",\n" +
                        "      \"销售额\": \"89.3654\",\n" +
                        "      \"产品种类\": \"花茶\",\n" +
                        "      \"利润\": 32,\n" +
                        "      \"咖啡因类型\": \"无咖啡因\",\n" +
                        "      \"市场分布\": \"中部市场\",\n" +
                        "      \"边际利润\": 43,\n" +
                        "      \"销量\": 1\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"省份\": \"北京市\",\n" +
                        "      \"总成本\": 33,\n" +
                        "      \"时间\": \"2019-06-04 09:30:00\",\n" +
                        "      \"时间戳\": 1559633400,\n" +
                        "      \"产品名称\": \"柠檬花茶\",\n" +
                        "      \"销售额\": \"199.5\",\n" +
                        "      \"产品种类\": \"花茶\",\n" +
                        "      \"利润\": 47,\n" +
                        "      \"咖啡因类型\": \"无咖啡因\",\n" +
                        "      \"市场分布\": \"中部市场\",\n" +
                        "      \"边际利润\": 134,\n" +
                        "      \"销量\": 2\n" +
                        "    }\n" +
                        "  ]";
        List<String> list1 = Collections.singletonList(json);
        JavaRDD<String> javaRDD = sparkContext.parallelize(list1);
        Dataset<Row> df = sparkSession.read().json(javaRDD);
        df.createOrReplaceTempView("jsonstr");
//        List<String> list = sparkSession.sql("select `时间`, `时间戳`," +
////                "DATE_FORMAT(to_date(from_unixtime(`时间戳`, 'yyyy-MM-dd HH:mm:ss'),'yyyy/MM/dd HH:mm:ss'),'yyyy/MM/dd') time1 ," +
////                "from_unixtime(unix_timestamp(`时间`,'MM/dd/yyyy HH:mm:ss'), 'yyyy-MM-dd HH:mm:ss') time1,"+
////                "cast(replace(`销售额`, ',','') as decimal(31,10)) num,"+
////                "unix_timestamp(`时间`,'MM/dd/yyyy HH:mm:ss'), " +
//                "weekofyear(`时间`) w, " +
//                "day(`时间`) d, " +
//                "dayofweek(`时间`) wd," +
//                "QUARTER(`时间`) q," +
//                "DATE_FORMAT(`时间`, 'yyyy/MM/dd HH:mm:ss') time1 from jsonstr limit 10").toJSON().collectAsList();
        List<String> list = sparkSession.sql("select `省份`, count(`省份`)" +
//                "DATE_FORMAT(to_date(from_unixtime(`时间戳`, 'yyyy-MM-dd HH:mm:ss'),'yyyy/MM/dd HH:mm:ss'),'yyyy/MM/dd') time1 ," +
//                "from_unixtime(unix_timestamp(`时间`,'MM/dd/yyyy HH:mm:ss'), 'yyyy-MM-dd HH:mm:ss') time1,"+
//                "cast(replace(`销售额`, ',','') as decimal(31,10)) num,"+
//                "unix_timestamp(`时间`,'MM/dd/yyyy HH:mm:ss'), " +
                "from jsonstr group by `省份`").toJSON().collectAsList();
        return list;
    }

    public static void sparkStream() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> collect = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        collect.forEach(System.out::println);
        System.out.println("-------------------------------------");
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);
        System.out.println("-------------------------------------");
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> collect1 = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        collect1.forEach(System.out::println);
        System.out.println("-------------------------------------");
        List<String> strings1 = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        long count = strings1.stream().filter(s -> !s.isEmpty()).count();
        System.out.println(count);
    }

    public static void loadDataToSpark() {
        SparkConf conf = new SparkConf().setAppName("GroupTop").setMaster("local[*]");
        SparkSession sparkSession = SparkSession.builder().config(conf).getOrCreate();
        JavaSparkContext sc = JavaSparkContext.fromSparkContext(sparkSession.sparkContext());
        JavaRDD<String> javaRDD = sc.textFile("C:/Users/Tina/Desktop/tb00a6f14015644579ba20cb6b0aadf7af1564468503089temp.csv");
        Dataset<Row> df = sparkSession.read().json(javaRDD);
        df.foreach((ForeachFunction<Row>) System.out::println);
    }

    public void sparkJavaJoin() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        JavaRDD<Integer> javaRDD = javaSparkContext.parallelize(list);
        JavaPairRDD<Integer, Integer> mapToPair1 = javaRDD.mapToPair(new PairFunction<Integer, Integer, Integer>() {
            @Override
            public Tuple2<Integer, Integer> call(Integer num) throws Exception {
                return new Tuple2<Integer, Integer>(num, num * num);
            }
        });

        JavaPairRDD<Integer, String> javaPairRDD2 = javaRDD.mapToPair(new PairFunction<Integer, Integer, String>() {
            @Override
            public Tuple2<Integer, String> call(Integer num) throws Exception {
                return new Tuple2<Integer, String>(num, String.valueOf((char) (64 + num * num)));
            }
        });

        JavaPairRDD<Integer, Tuple2<Integer, String>> join = mapToPair1.join(javaPairRDD2);

        JavaRDD<String> rdd = join.map(new Function<Tuple2<Integer, Tuple2<Integer, String>>, String>() {
            @Override
            public String call(Tuple2<Integer, Tuple2<Integer, String>> v1) throws Exception {
                Integer key = v1._1();
                Integer value1 = v1._2()._1();
                String value2 = v1._2()._2();
                return "<" + key + ",<" + value1 + "," + value2 + ">>";
            }
        });

        List<String> collect = rdd.collect();
        collect.forEach(System.out::println);

    }

    @Override
    public ReturnBody sparkTest(SparkBean sparkBean) throws GlobalException {
//        sparkJavaJoin();
        List<String> list = sparkJsonDataFrame();
//        List<String> list = csvToSpark();
        long starTime = System.currentTimeMillis();
        //直连数据库
        JSONArray jsonArray = new JSONArray();
        SparkSession sparkSession = SparkSession.builder()
                .sparkContext(JavaSparkContext.toSparkContext(javaSparkContext)).getOrCreate();
        String sql = sparkBean.getExecSql();
        if (sparkBean.getLimit() != null && sparkBean.getLimit() > 0) {
            sql = "(" + sql + ") limit " + sparkBean.getLimit();
        }
        String table = stringFormat("({}) as temp", sql);
        sparkSession.read().jdbc(sparkBean.getUrl(), table, getDatabaseProperties(sparkBean)).createOrReplaceTempView("city");
//        sparkSession.sql("select * from city limit 10").show();
        if (Objects.equals("api", sparkBean.getType())) {
            //根据spark的Api查询
            jsonArray = selectBySparkApi(sparkSession, sparkBean.getLimit());
        }
        if (Objects.equals("sql", sparkBean.getType())) {
            //根据sql语句查询
            jsonArray = selectBySql(sparkSession, sparkBean.getLimit());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("**************类型：" + sparkBean.getType() + " 条数：" + sparkBean.getLimit() + " 花费时间：" + (endTime - starTime) + "  ****************");
        return new ReturnBody<>(ReturnMsgImpl.SUCCESS, jsonArray);
    }

    private JSONArray selectBySql(SparkSession sparkSession, Integer limit) {
//        String sql =
//                "select * from (select * from (" +
//                        "select * from (" +
//                        "SELECT * FROM (" +
//                "SELECT `产品名称` proName," +
//                        "`产品种类` proType," +
//                        "`咖啡因类型` coffeeType," +
//                        "`省份` province," +
//                        "`市场分布` marketDistribution," +
//                        "`边际利润` marginalProfit," +
//                        "`利润` profit," +
//                        "`销售额` sales," +
//                        "`销量` salesVolume," +
//                        "`总成本` totalCost " +
//                        "FROM city" +
//                        ") T)t)a)A"
//                ;
        String sql = "SELECT `产品名称` proName," +
                "`产品种类` proType," +
                "`咖啡因类型` coffeeType," +
                "`省份` province," +
                "`市场分布` marketDistribution," +
                "`边际利润` marginalProfit," +
                "`利润` profit," +
                "`销售额` sales," +
                "`销量` salesVolume," +
                "`总成本` totalCost " +
                "FROM city";
        if (limit != null && limit > 0) {
            sql = sql + " limit " + limit;
        }
        JSONArray jsonArray = new JSONArray();
        Dataset<Row> rows = sparkSession.sql(sql);
        List<Object> collect = rows.collectAsList().stream().flatMap((java.util.function.Function<Row, Stream<Object>>) row -> {
            List<Object> list = new ArrayList<>();
            list.add(formatResult(row));
            return list.stream();
        }).collect(Collectors.toList());


        return JSONArray.parseArray(JSONArray.toJSONString(collect));
//        return  jsonArray;
    }

    private JSONArray selectBySparkApi(SparkSession sparkSession, Integer limit) {
        JSONArray jsonArray = new JSONArray();
        Dataset<Row> rows = sparkSession
                .table("city");

        String[] colNames = new String[]{"产品名称", "产品种类", "咖啡因类型", "省份", "市场分布", "边际利润", "利润", "销售额", "销量", "总成本"};
        Seq<Column> columnSeq = getSeq(rows, colNames);
        if (limit != null && limit > 0) {
            Dataset<Row> dataset = rows.select(columnSeq);
            colNames = new String[]{"proName", "proType", "coffeeType", "province", "marketDistribution", "marginalProfit", "profit", "sales", "salesVolume", "totalCost"};
            Dataset<Row> rowDataset = dataset.select(getSeq(dataset, colNames));
            colNames = new String[]{"proName", "proType", "coffeeType", "province", "marketDistribution", "marginalProfit", "profit", "sales", "salesVolume", "totalCost"};
            Dataset<Row> rowDataset1 = rowDataset.select(getSeq(rowDataset, colNames));
            colNames = new String[]{"proName", "proType", "coffeeType", "province", "marketDistribution", "marginalProfit", "profit", "sales", "salesVolume", "totalCost"};
            Dataset<Row> rowDataset2 = rowDataset1.select(getSeq(rowDataset1, colNames));
            rowDataset2.select(getSeq(rowDataset2, colNames)).limit(limit).collectAsList().forEach(row -> {
                formatResult(jsonArray, row);
            });
        } else {
            rows.select(columnSeq).collectAsList().forEach(row -> {
                formatResult(jsonArray, row);
            });
        }
        return new JSONArray();
//        return  jsonArray;
    }

    public JSONObject formatResult(Row row) {
        JSONObject jsonObject = new JSONObject();
        formatResult(jsonObject, row);
        return jsonObject;
    }

    public void formatResult(JSONObject jsonObject, Row row) {
        String[] columnNames = row.schema().names();
        for (int i = 0; i < row.size(); i++) {
            Object object = row.get(i);
            if (object == null) {
                jsonObject.put(columnNames[i], null);
            } else if (object instanceof Integer) {
                jsonObject.put(columnNames[i], row.getInt(i));
            } else if (object instanceof Double) {
                jsonObject.put(columnNames[i], row.getDouble(i));
            } else if (object instanceof Long) {
                jsonObject.put(columnNames[i], row.getLong(i));
            } else if (object instanceof Date) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                jsonObject.put(columnNames[i], dateFormat.format(object));
            } else {
                jsonObject.put(columnNames[i], object.toString());
            }
        }
    }

    public void formatResult(JSONArray jsonArray, Row row) {
        JSONObject jsonObject = new JSONObject();
        formatResult(jsonObject, row);
        jsonArray.add(jsonObject);
    }

    public Seq<Column> getSeq(Dataset<Row> rows, String[] colNames) {
        List<Column> list = new ArrayList<>();

        Column clo1 = rows.col(colNames[0]).alias("proName");
        Column clo2 = rows.col(colNames[1]).alias("proType");
//        Column clo11 = rows.col("产品名称").when(clo1.equalTo(""), "aaa").alias("proName1");
        Column clo3 = rows.col(colNames[2]).alias("coffeeType");
        Column clo4 = rows.col(colNames[3]).alias("province");
        Column clo5 = rows.col(colNames[4]).alias("marketDistribution");
        Column clo6 = rows.col(colNames[5]).alias("marginalProfit");
        Column clo7 = rows.col(colNames[6]).alias("profit");
        Column clo8 = rows.col(colNames[7]).alias("sales");
        Column clo9 = rows.col(colNames[8]).alias("salesVolume");
        Column clo10 = rows.col(colNames[9]).alias("totalCost");

        list.add(clo1);
        list.add(clo2);
        list.add(clo3);
        list.add(clo4);
        list.add(clo5);
        list.add(clo6);
        list.add(clo7);
        list.add(clo8);
        list.add(clo9);
        list.add(clo10);
//        list.add(clo11);
        return JavaConverters.asScalaIteratorConverter(list.iterator()).asScala().toSeq();
    }

    private List<String> csvToSpark() {
        SparkSession sparkSession = SparkSession.builder().sparkContext(JavaSparkContext.toSparkContext(javaSparkContext)).getOrCreate();
        sparkSession.read()
//                .format("com.databricks.spark.csv")
//                .option("sep", ",")
                .option("header", "true")
                .csv("D:\\uploadFiles\\fileaddress\\ids\\file\\2019-09-02\\tbc44405d059c547e8af64af97eed40aba1567403718410temp.csv").createOrReplaceTempView("temp");
        return sparkSession.sql("select * from temp limit 1").toJSON().collectAsList();
    }

    private Properties getDatabaseProperties(SparkBean sparkBean) {
        Properties properties = new Properties();
        properties.setProperty("driver", "com.mysql.jdbc.Driver");
        properties.setProperty("user", sparkBean.getUsername());
        properties.setProperty("password", sparkBean.getPassword());
        return properties;
    }

    private String stringFormat(String str, Object... obj) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(str, obj);
        return formattingTuple.getMessage();
    }
}
