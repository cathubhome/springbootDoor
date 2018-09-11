package com.ciwr.global.common.utils.redis;

import com.ciwr.global.enums.MutexElement;

import java.util.List;

/**
 * Created with IDEA
 * Author:catHome
 * Description:业务互斥锁服务接口
 * Time:Create on 2018/7/30 10:04
 */
@SuppressWarnings("all")
public interface BusinessLockService {
    /**
     * 锁定某个业务对象
     * timeout = 0 时，非阻塞调用，如果对象已锁定立刻返回失败
     * timeout > 0 时，阻塞调用，如果对象已锁定，会等待直到超时并返回失败
     * true：锁定成功，false：锁定失败
     * 超时时间，单位：秒
     * 超时时间过去后锁自动释放，建议手动释放
     * @param mutex 业务对象
     * @param acquireLockTimeOut 获取锁的超时时间，超过这个时间会放弃获取锁
     * @param timeout 锁的超时时间
     * @return
     */
    boolean lock(MutexElement mutex,int lockExpire,int timeout);

    /**
     * 解除某个业务对象锁定
     * @param mutex
     */
    void unlock(MutexElement mutex);

    /**
     * 批量锁定多个业务对象,任意对象已锁定立刻返回失败,全部成功锁住返回true,否则返回false
     * 不支持阻塞，锁必须手动释放
     * true：锁定成功，false：锁定失败
     * @param mutexes
     * @return
     */
    boolean lock(List<MutexElement> mutexes);

    /**
     * 批量解除多个业务对象锁定
     * @param mutexes
     */
    void unlock(List<MutexElement> mutexes);

}
