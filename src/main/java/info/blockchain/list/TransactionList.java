package info.blockchain.list;

import info.blockchain.transaction.Transaction;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public final class TransactionList {

    private final List<Transaction> outputs;
}
