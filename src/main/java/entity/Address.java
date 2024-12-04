package entity;

import java.util.Objects;

public class Address {
    private AddressTemplate template;
    private String tag;
    private String index;
    private String offset;
    private String address;

    public Address(AddressTemplate addressTemplate, String address) {
        this.template = addressTemplate;
        this.address = completeAddress(address);

        int tagSize = template.getTagSize();
        int indexSize = template.getIndexSize();
        int offsetSize = template.getOffsetSize();

        this.tag = this.address.substring(0, tagSize);
        this.index = this.address.substring(tagSize, tagSize + indexSize);
        this.offset = this.address.substring(tagSize + indexSize, tagSize + indexSize + offsetSize);

    }

    private String completeAddress(String address) {
        StringBuilder addressBuilder = new StringBuilder(address);

        while (addressBuilder.length() != template.getSize()) {
            addressBuilder.insert(0, "0");
        }

        return addressBuilder.toString();
    }

    public String getHexAddress() {
        int decimal = Integer.parseInt(address, 2);
        return Integer.toHexString(decimal);
    }

    public String getTag() {
        return tag;
    }

    public String getIndex() {
        return index;
    }

    public String getOffset() {
        return offset;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Address) {
            return Objects.equals(this.address, ((Address) obj).getAddress());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

}
