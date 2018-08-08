package info.blockchain.helper;

import info.blockchain.transaction.UnspentTransaction;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UnspentTransactionHelper {

    public static List<UnspentTransaction> getUnspentTransactions() {
        UnspentTransaction unspentTransaction = new UnspentTransaction(
                "b94313cb3d8ce36ad270d43fd155111a2495f43d8d156f9b570448d22f3dc739",
                "39c73d2fd24804579b6f158d3df495241a1155d13fd470d26ae38c3dcb1343b9",
                318608729,
                13,
                "76a914ea62b46cd773a10de3fc9cad3c83fe2e7fb0824d88ac",
                1685235,
                "19b6f3",
                33843
        );

        UnspentTransaction unspentTransaction2 = new UnspentTransaction(
                "946053b712c163165ff098ea1a7567856dccba3c0c17d9c4ad8dfaa1d3f2d271",
                "71d2f2d3a1fa8dadc4d9170c3cbacc6d8567751aea98f05f1663c112b7536094",
                319858131,
                19,
                "76a914ea62b46cd773a10de3fc9cad3c83fe2e7fb0824d88ac",
                2865979,
                "2bbb3b",
                32555
        );

        UnspentTransaction unspentTransaction3 = new UnspentTransaction(
                "32c2cf2adae996c628aa1a62d5975b069d9fa9263f48a0bf6aa09bce0e952852",
                "5228950ece9ba06abfa0483f26a99f9d065b97d5621aaa28c696e9da2acfc232",
                332021370,
                79,
                "76a914ea62b46cd773a10de3fc9cad3c83fe2e7fb0824d88ac",
                476325,
                "0744a5",
                25677
        );

        return Stream.of(unspentTransaction, unspentTransaction2, unspentTransaction3)
                .collect(Collectors.toList());
    }
}
