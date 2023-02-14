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
public class TransactionResponse extends CommonResponse {
    @Getter
    @Setter
    private class Data {
        private CatEncapsulated cat;
        private Packet packet;
        private UserEncapsulated user;
        private TrxStatus status;
    }

    private Data data;

    public TransactionResponse(Transaction trx) {
        super();
        super.setCode("00");
        super.setStatus("OK");
        super.setMessage("Success make transaction");
        this.data = new Data();
        Cat cat = trx.getCat();
        data.setCat(new CatEncapsulated(cat));
        data.setPacket(trx.getPacket());
        data.setStatus(trx.getStatus());
        User user = trx.getCustomer();
        data.setUser(new UserEncapsulated(user.getUserId(), user.getName(), user.getAddress(), user.getPhoneNumber(), user.getSystemAuth().getRole()));
    }
}
