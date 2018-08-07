package info.blockchain.transaction;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public final class UnspentTransaction extends AbstractTransaction {

    private final String tx_hash_big_endian;
    private final long tx_index;
    private final int tx_output_n;
    private final String script;
    private final String value_hex;
    private final long confirmations;

    public UnspentTransaction(
            String tx_hash,
            String tx_hash_big_endian,
            long tx_index,
            int tx_output_n,
            String script,
            long value,
            String value_hex,
            long confirmations
    ) {
        super(value, tx_hash);
        this.tx_hash_big_endian = tx_hash_big_endian;
        this.tx_index = tx_index;
        this.tx_output_n = tx_output_n;
        this.script = script;
        this.value_hex = value_hex;
        this.confirmations = confirmations;
    }
}
