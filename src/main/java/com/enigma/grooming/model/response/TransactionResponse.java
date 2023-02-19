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

    private EncapsulateTransaction data;

    public TransactionResponse(Transaction trx) {
        super();
        super.setCode("00");
        super.setStatus("OK");
        super.setMessage("Success make transaction");
        this.data = new EncapsulateTransaction(trx);
    }
}
