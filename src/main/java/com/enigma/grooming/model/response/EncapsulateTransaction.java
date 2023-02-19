package com.enigma.grooming.model.response;

import com.enigma.grooming.model.Cat;
import com.enigma.grooming.model.Packet;
import com.enigma.grooming.model.Transaction;
import com.enigma.grooming.model.User;
import com.enigma.grooming.model.constant.TrxStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EncapsulateTransaction {
    private Integer transactionId;
    private CatEncapsulated cat;
    private Packet packet;
    private UserEncapsulated user;
    private TrxStatus status;
    public EncapsulateTransaction(Transaction trx) {
        this.transactionId = trx.getTransactionId();
        Cat cat = trx.getCat();
        setCat(new CatEncapsulated(cat));
        setPacket(trx.getPacket());
        setStatus(trx.getStatus());
        User user = trx.getCustomer();
        setUser(new UserEncapsulated(user.getUserId(), user.getName(), user.getAddress(), user.getPhoneNumber(), user.getSystemAuth().getRole()));
    }
}
