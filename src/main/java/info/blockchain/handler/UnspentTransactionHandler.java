package info.blockchain.handler;

import com.google.gson.annotations.SerializedName;
import info.blockchain.transaction.UnspentTransaction;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public final class UnspentTransactionHandler {

    @SerializedName("unspent_outputs")
    private final List<UnspentTransaction> unspentOutputs;

    public UnspentTransactionHandler(LinkedList<UnspentTransaction> unspentOutputs) {
        this.unspentOutputs = unspentOutputs;
    }
}
