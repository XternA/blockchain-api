package info.blockchain.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
abstract class AbstractTransaction {

    private final long value;
    private final String tx_hash;
}
