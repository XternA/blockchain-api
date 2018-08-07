package info.blockchain.data;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public final class Transaction {

    private final long value;
    private final String tx_hash;
    private final long output_idx;
}
