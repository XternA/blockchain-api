package info.blockchain.list;

import com.google.gson.annotations.SerializedName;
import info.blockchain.transaction.UnspentTransaction;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public final class UnspentTransactionList {

    @SerializedName("unspent_outputs")
    private final List<UnspentTransaction> unspentOutputs;
}
