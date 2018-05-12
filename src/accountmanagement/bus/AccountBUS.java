/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accountmanagement.bus;

import accountmanagement.dal.AccountDAL;
import accountmanagement.dto.AccountDTO;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class AccountBUS {
    private AccountDAL accDAL;
    
    public AccountBUS() throws IOException{
        this.accDAL = new AccountDAL();
    }
    public AccountBUS(String connectionString){
        this.accDAL = new AccountDAL(connectionString);
    }
    
    public boolean isValidFullname(AccountDTO accdto){
        
        if(accdto.getFullname() == null || accdto.getFullname().isEmpty())
            return false;
        
        return true;
    }
    public boolean insert(AccountDTO accdto){
        //1. validate data
        
        //2. insert to db
        return accDAL.insert(accdto);
    }
}
