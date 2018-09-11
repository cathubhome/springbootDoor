package com.ciwr.global.common.utils;

import org.nustaq.serialization.FSTObjectInput;
import org.nustaq.serialization.FSTObjectOutput;

import java.io.*;

/**
 * 序列化工具类.jdk自带和fst极速序列化
 */
@SuppressWarnings("all")
public class SerializeUtil {

    /**
     * fst将对象序列化成二进制数组
     * @param obj
     * @return
     */
    public static byte[] fstSerialize(Object obj) {
        ByteArrayOutputStream out = null;
        FSTObjectOutput fout = null;
        try {
            out = new ByteArrayOutputStream(1024 * 16);
            fout = new FSTObjectOutput(out);
            fout.writeObject(obj);
            fout.flush();
            return out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fout != null) {
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * fst反序列化二进制数组成对象
     * @param bytes
     * @return
     */
    public static <T> T fstDeserialize(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        FSTObjectInput in = null;
        try {
            in = new FSTObjectInput(new ByteArrayInputStream(bytes));
            return (T) in.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * jdk序列化
     * @param obj
     * @return
     */
    public static byte[] jdkserialize(Object obj) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * jdk反序列化
     * @param bits
     * @return
     */
    public static Object jdkdeserialize(byte[] bits) {
        ObjectInputStream ois = null;
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(bits);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (bais != null) {
                try {
                    bais.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}