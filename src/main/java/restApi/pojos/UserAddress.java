package restApi.pojos;

import lombok.Data;

@Data
public class UserAddress {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private UserAddressGeo geo;

}
