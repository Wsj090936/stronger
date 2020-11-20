package com.wsj.stronger.spring.dao;

import com.wsj.stronger.spring.pojo.Account;

/**
 * @author
 */
public interface AccountDao {

    Account queryAccountByCardNo(String cardNo) throws Exception;

    int updateAccountByCardNo(Account account) throws Exception;
}
