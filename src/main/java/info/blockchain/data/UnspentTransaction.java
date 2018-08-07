package info.blockchain.data;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public final class UnspentTransaction {

    @SerializedName("tx_age")
    private final long txAge;
    @SerializedName("tx_hash")
    private final String txHash;
    @SerializedName("tx_index")
    private final long txIndex;
    @SerializedName("tx_output_no")
    private final int txOutputNo;
    @SerializedName("script")
    private final String script;
    @SerializedName("value")
    private final long value;
}
