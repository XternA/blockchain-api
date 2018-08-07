package info.blockchain.transaction;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.inject.Named;

@Getter
public final class Transaction extends AbstractTransaction {

    private final long output_idx;

    @Named
    private Transaction(long value, String tx_hash, long output_idx) {
        super(value, tx_hash);
        this.output_idx = output_idx;
    }

    public static Transaction.TransactionBuilder builder() {
        return new TransactionBuilder();
    }

    @Setter
    @Getter
    @Accessors(chain = true)
    public static class TransactionBuilder {

        private long value;
        private String txHash;
        private long outputIdx;

        public Transaction build() {
            return new Transaction(value, txHash, outputIdx);
        }
    }
}
