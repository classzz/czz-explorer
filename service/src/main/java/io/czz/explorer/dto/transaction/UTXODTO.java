package io.czz.explorer.dto.transaction;



import java.util.List;

public class UTXODTO {

    private Integer totalCount;

    private List<TransferUtxoDTO> transferUtxos;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<TransferUtxoDTO
            > getTransferUtxos() {
        return transferUtxos;
    }

    public void setTransferUtxos(List<TransferUtxoDTO> transferUtxos) {
        this.transferUtxos = transferUtxos;
    }
}
