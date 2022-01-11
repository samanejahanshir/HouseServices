package data.dto;

import lombok.Data;

@Data
public class SubServiceDto {
    private Integer id;
    private String groupName;
    private String subService;
    private double basePrice;
    private String description;

    public static final class SubServiceDtoBuilder {
        private Integer id;
        private String groupName;
        private String subService;
        private double basePrice;
        private String description;

        private SubServiceDtoBuilder() {
        }

        public static SubServiceDtoBuilder aSubServiceDto() {
            return new SubServiceDtoBuilder();
        }

        public SubServiceDtoBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public SubServiceDtoBuilder withGroupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        public SubServiceDtoBuilder withSubService(String subService) {
            this.subService = subService;
            return this;
        }

        public SubServiceDtoBuilder withBasePrice(double basePrice) {
            this.basePrice = basePrice;
            return this;
        }

        public SubServiceDtoBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public SubServiceDto build() {
            SubServiceDto subServiceDto = new SubServiceDto();
            subServiceDto.setId(id);
            subServiceDto.setGroupName(groupName);
            subServiceDto.setSubService(subService);
            subServiceDto.setBasePrice(basePrice);
            subServiceDto.setDescription(description);
            return subServiceDto;
        }
    }
}
