package com.ciwr.service.hbase;

import org.apache.hadoop.hbase.client.Result;

import java.io.IOException;
import java.util.List;

/**
 * Created with IDEA
 * Author:catHome
 * Description: hbase接口
 * Time:Create on 2018/9/22 21:29
 */
public interface HbaseService {
    /**
     * 查询全表的数据
     *
     * @param tablename
     * @return
     */
    List<Result> scaner(String tablename);

    /**
     * 根据rowKey查询单条记录
     *
     * @param tableName
     * @param rowKey
     * @return
     */
    Result getRow(String tableName, String rowKey);

    /**
     * 根据regxKey正则匹配数据
     *
     * @param tableName
     * @param regxKey
     * @return
     */
    List<Result> getRegexRow(String tableName, String regxKey);

    /**
     * 根据regxKey正则匹配数据,取出num条
     *
     * @param tableName
     * @param regxKey
     * @param num
     * @return
     */
    List<Result> getRegexRow(String tableName, String regxKey, int num);

    /**
     * 根据startKey和endKey的范围匹配数据
     *
     * @param tableName
     * @param startKey
     * @param stopKey
     * @return
     */
    List<Result> getStartRowAndEndRow(String tableName, String startKey, String stopKey);

    /**
     * 确定startKey和endKey的范围，根据regKey匹配数据
     *
     * @param tableName
     * @param startKey
     * @param stopKey
     * @param regxKey
     * @return
     */
    List<Result> getRegexRow(String tableName, String startKey, String stopKey, String regxKey);

    /**
     * 确定startKey和endKey的范围，根据regKey匹配数据,取出num条
     *
     * @param tableName
     * @param startKey
     * @param stopKey
     * @param regxKey
     * @param num
     * @return
     */
    List<Result> getRegexRow(String tableName, String startKey, String stopKey, String regxKey, int num);

    /**
     * 添加数据
     *
     * @param rowKey
     * @param tableName
     * @param family
     * @param column
     * @param value
     */
    void put(String rowKey, String tableName, String family, String[] column, String[] value);

    /**
     * 删除记录
     *
     * @param tableName
     * @param rowKeys
     */
    void delRecord(String tableName, String... rowKeys);

    /**
     * 修改一条数据
     *
     * @param tableName
     * @param rowKey
     * @param familyName
     * @param column
     * @param value
     * @throws IOException
     */
    void updateTable(String tableName, String rowKey, String familyName, String column[], String value[]) throws IOException;

    /**
     * 查找最新的一条数据,或者说倒序查询
     *
     * @param tableName
     * @return
     */
    Result getLeastRow(String tableName);

    /**
     * 根据regxKey正则匹配并查找最近的一条数据
     *
     * @param tableName
     * @param regxKey
     * @return
     */
    Result getLeastRow(String tableName,String regxKey);

    /**
     * 正则查出所有匹配的key
     *
     * @param tableName
     * @param regxKey
     * @return
     */
    List<String> queryKeys(String tableName, String regxKey);

    /**
     * 增加表中对应字段的值
     *
     * @param tableName
     * @param cf
     * @param rowKey
     * @param column
     * @param num
     * @return
     */
    long incrQualifier(String tableName, String cf, String rowKey, String column, long num);

}
