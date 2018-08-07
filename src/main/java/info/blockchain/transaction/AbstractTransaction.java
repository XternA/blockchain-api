package info.blockchain.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
abstract class AbstractTransaction {

    protected final long value;
    protected final String tx_hash;
}
