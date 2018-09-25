package com.ciwr.service.hbase;

import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IDEA
 * Author:catHome
 * Description:
 * Time:Create on 2018/9/22 21:32
 */
@SuppressWarnings("all")
@Service
public class HbaseServiceImpl implements HbaseService {

    @Autowired
    private HbaseTemplate hbaseTemplate;

    private final String encoding = "utf-8";

    @Override
    public List<Result> scaner(final String tableName) {
        return hbaseTemplate.execute(tableName, new TableCallback<List<Result>>() {
            List<Result> list = new ArrayList<>();
            @Override
            public List<Result> doInTable(HTableInterface table) throws Throwable {
                Scan scan = new Scan();
                ResultScanner rs = table.getScanner(scan);
                for (Result result : rs) {
                    list.add(result);
                }
                return list;
            }

        });
    }

    @Override
    public Result getRow(final String tableName, final String rowKey) {
        return hbaseTemplate.execute(tableName, new TableCallback<Result>() {
            @Override
            public Result doInTable(HTableInterface table) throws Throwable {
                Get get = new Get(rowKey.getBytes(encoding));
                return table.get(get);
            }

        });
    }

    @Override
    public List<Result> getRegexRow(final String tableName, final String regxKey) {
        return hbaseTemplate.execute(tableName, new TableCallback<List<Result>>() {
            List<Result> list = new ArrayList<>();

            @Override
            public List<Result> doInTable(HTableInterface table) throws Throwable {
                RegexStringComparator rc = new RegexStringComparator(regxKey);
                RowFilter rowFilter = new RowFilter(CompareFilter.CompareOp.EQUAL, rc);
                Scan scan = new Scan();
                scan.setFilter(rowFilter);
                ResultScanner rs = table.getScanner(scan);
                for (Result result : rs) {
                    list.add(result);
                }
                return list;
            }

        });
    }

    @Override
    public List<Result> getRegexRow(final String tableName, final String regxKey, final int num) {
        return hbaseTemplate.execute(tableName, new TableCallback<List<Result>>() {
            List<Result> list = new ArrayList<>();

            @Override
            public List<Result> doInTable(HTableInterface table) throws Throwable {
                FilterList fl = new FilterList(FilterList.Operator.MUST_PASS_ALL);
                RegexStringComparator rc = new RegexStringComparator(regxKey);
                RowFilter rf = new RowFilter(CompareFilter.CompareOp.EQUAL, rc);
                if (num > 0) {// 过滤获取的条数
                    // 每页展示条数
                    Filter filterNum = new PageFilter(num);
                    fl.addFilter(filterNum);
                }
                // 过滤器的添加
                fl.addFilter(rf);
                Scan scan = new Scan();
                scan.setFilter(fl);// 为查询设置过滤器的list
                ResultScanner rscanner = table.getScanner(scan);
                for (Result result : rscanner) {
                    list.add(result);
                }
                return list;
            }

        });
    }

    @Override
    public List<Result> getStartRowAndEndRow(final String tableName, final String startKey, final String stopKey) {
        return hbaseTemplate.execute(tableName, new TableCallback<List<Result>>() {
            List<Result> list = new ArrayList<>();

            @Override
            public List<Result> doInTable(HTableInterface table) throws Throwable {
                // 过滤器的添加
                Scan scan = new Scan();
                scan.setStartRow(startKey.getBytes(encoding));// 开始的key
                scan.setStopRow(stopKey.getBytes(encoding));// 结束的key
                ResultScanner rscanner = table.getScanner(scan);
                for (Result result : rscanner) {
                    list.add(result);
                }
                return list;
            }

        });
    }

    @Override
    public List<Result> getRegexRow(final String tableName, final String startKey, final String stopKey, final String regxKey) {
        return hbaseTemplate.execute(tableName, new TableCallback<List<Result>>() {
            List<Result> list = new ArrayList<>();

            @Override
            public List<Result> doInTable(HTableInterface table) throws Throwable {
                // 设置正则过滤器
                RegexStringComparator rc = new RegexStringComparator(regxKey);
                RowFilter rf = new RowFilter(CompareFilter.CompareOp.EQUAL, rc);
                // 过滤器的添加
                Scan scan = new Scan();
                scan.setStartRow(startKey.getBytes(encoding));// 开始的key
                scan.setStopRow(stopKey.getBytes(encoding));// 结束的key
                scan.setFilter(rf);// 为查询设置过滤器的list
                ResultScanner rscanner = table.getScanner(scan);
                for (Result result : rscanner) {
                    list.add(result);
                }
                return list;
            }
        });
    }

    @Override
    public List<Result> getRegexRow(final String tableName, final String startKey, final String stopKey, final String regxKey, final int num) {
        return hbaseTemplate.execute(tableName, new TableCallback<List<Result>>() {
            List<Result> list = new ArrayList<>();

            @Override
            public List<Result> doInTable(HTableInterface table) throws Throwable {
                FilterList fl = new FilterList(FilterList.Operator.MUST_PASS_ALL);
                // 设置正则过滤器
                RegexStringComparator rc = new RegexStringComparator(regxKey);
                RowFilter rf = new RowFilter(CompareFilter.CompareOp.EQUAL, rc);
                if (num > 0) {// 过滤获取的条数
                    // 每页展示条数
                    Filter filterNum = new PageFilter(num);
                    fl.addFilter(filterNum);
                }
                // 过滤器的添加
                fl.addFilter(rf);
                // 过滤器的添加
                Scan scan = new Scan();
                // 开始的key
                scan.setStartRow(startKey.getBytes(encoding));
                // 结束的key
                scan.setStopRow(stopKey.getBytes(encoding));
                // 为查询设置过滤器的list
                scan.setFilter(fl);
                ResultScanner rscanner = table.getScanner(scan);
                for (Result result : rscanner) {
                    list.add(result);
                }
                return list;
            }

        });
    }

    @Override
    public void put(final String rowKey, final String tableName, final String family, final String[] column, final String[] value) {
        hbaseTemplate.execute(tableName, new TableCallback<String>() {
            @Override
            public String doInTable(HTableInterface table) throws Throwable {
                // 设置rowkey
                Put put = new Put(Bytes.toBytes(rowKey));
                int i=0;
                for (String col : column) {
                    put.add(Bytes.toBytes(family), Bytes.toBytes(col), Bytes.toBytes(value[i]));
                    i++;
                }
                table.put(put);
                return "ok";
            }
        });
    }

    @Override
    public void delRecord(String tableName, String... rowKeys) {
        hbaseTemplate.execute(tableName, new TableCallback<String>() {
            @Override
            public String doInTable(HTableInterface table) throws Throwable {
                List<Delete> list = new ArrayList();
                for (String rowKey : rowKeys) {
                    Delete delete = new Delete(rowKey.getBytes());
                    list.add(delete);
                }
                table.delete(list);
                return "OK";
            }
        });
    }

    @Override
    public void updateTable(String tableName, String rowKey, String familyName, String[] column, String[] value) throws IOException {
        put(tableName,rowKey,familyName,column,value);
    }

    @Override
    public Result getLeastRow(String tableName) {
       return hbaseTemplate.execute(tableName, new TableCallback<Result>() {
            @Override
            public  Result doInTable(HTableInterface table) throws Throwable {
                // 每页展示条数
                Filter filterNum = new PageFilter(1);
                Scan scan = new Scan();
                scan.setFilter(filterNum);
                scan.setReversed(true);
                ResultScanner scanner = table.getScanner(scan);
                return scanner.next();
            }
        });
    }

    @Override
    public Result getLeastRow(String tableName, String regxKey) {
        return hbaseTemplate.execute(tableName, new TableCallback<Result>() {

            @Override
            public  Result doInTable(HTableInterface table) throws Throwable {
                FilterList fl = new FilterList(FilterList.Operator.MUST_PASS_ALL);
                RegexStringComparator rc = new RegexStringComparator(regxKey);
                RowFilter rf = new RowFilter(CompareFilter.CompareOp.EQUAL, rc);
                // 每页展示条数
                Filter filterNum = new PageFilter(1);
                fl.addFilter(rf);
                fl.addFilter(filterNum);
                Scan scan = new Scan();
                scan.setFilter(fl);
                scan.setReversed(true);
                ResultScanner scanner = table.getScanner(scan);
                return scanner.next();
            }

        });
    }

    @Override
    public List<String> queryKeys(String tableName, String regxKey) {
        return hbaseTemplate.execute(tableName, new TableCallback<List<String>>() {
            List<String> list = new ArrayList<>();
            @Override
            public List<String> doInTable(HTableInterface table) throws Throwable {
                PrefixFilter filter = new PrefixFilter(regxKey.getBytes(encoding));
                Scan scan = new Scan();
                scan.setFilter(filter);
                ResultScanner scanner = table.getScanner(scan);
                for (Result rs : scanner) {
                    list.add(new String(rs.getRow()));
                }
                return list;
            }

        });
    }

    @Override
    public long incrQualifier(String tableName, String cf, String rowKey, String column, long num) {

        return hbaseTemplate.execute(tableName, new TableCallback<Long>() {
            @Override
            public Long doInTable(HTableInterface table) throws Throwable {
                long qualifie =  table.incrementColumnValue(rowKey.getBytes(encoding), cf.getBytes(encoding), column.getBytes(encoding), num);
                return qualifie;
            }

        });
    }
}