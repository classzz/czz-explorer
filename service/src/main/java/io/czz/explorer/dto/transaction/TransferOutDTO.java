package io.czz.explorer.dto.transaction;

import org.jooq.types.ULong;

import java.util.List;

public class TransferOutDTO {


    private List<String> addresses;
    private String  asm;
    private String  hex;
    private String type;
    private Integer reqsigs;

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    public String getAsm() {
        return asm;
    }

    public void setAsm(String asm) {
        this.asm = asm;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getReqsigs() {
        return reqsigs;
    }

    public void setReqsigs(Integer reqsigs) {
        this.reqsigs = reqsigs;
    }

    class Address{
        private String address;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
