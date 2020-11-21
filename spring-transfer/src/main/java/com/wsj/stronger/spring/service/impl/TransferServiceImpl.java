package com.wsj.stronger.spring.service.impl;

import com.wsj.stronger.spring.dao.AccountDao;
import com.wsj.stronger.spring.factory.BeanFactory;
import com.wsj.stronger.spring.pojo.Account;
import com.wsj.stronger.spring.service.TransferService;
import com.wsj.stronger.spring.utils.TransactionManager;

/**
 * @author 应癫
 */
public class TransferServiceImpl implements TransferService {

    //private AccountDao accountDao = new JdbcAccountDaoImpl();

    // private AccountDao accountDao = (AccountDao) BeanFactory.getBean("accountDao");

    // 最佳状态
//    private AccountDao accountDao = (AccountDao) BeanFactory.getBean("accountDao");
    // 最佳状态
    private AccountDao accountDao;
//    private TransactionManager transactionManager;

//    public void setTransactionManager(TransactionManager transactionManager) {
//        this.transactionManager = transactionManager;
//    }

// 构造函数传值/set方法传值

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }




    @Override
    public void transfer(String fromCardNo, String toCardNo, int money) throws Exception {
        // 开启事务

//        transactionManager.begainTransaction();
//        try {

            Account from = accountDao.queryAccountByCardNo(fromCardNo);
            Account to = accountDao.queryAccountByCardNo(toCardNo);

            from.setMoney(from.getMoney()-money);
            to.setMoney(to.getMoney()+money);

            accountDao.updateAccountByCardNo(to);
            int c = 1/0;
            accountDao.updateAccountByCardNo(from);
//            // 提交事务
//            transactionManager.commit();
//        }catch (Exception ex){
//            ex.printStackTrace();
//            // 回滚
//            transactionManager.rollback();
//            throw ex;
//
//        }







    }
}
