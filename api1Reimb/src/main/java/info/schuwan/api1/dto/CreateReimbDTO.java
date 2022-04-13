package info.schuwan.api1.dto;

public class CreateReimbDTO {
    private String type;
    private Float amount;
    private String empEmail;
    private String manEmail;

    public CreateReimbDTO() {
    }

    public CreateReimbDTO(String type, Float amount, String empEmail, String manEmail) {
        this.type = type;
        this.amount = amount;
        this.empEmail = empEmail;
        this.manEmail = manEmail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getManEmail() {
        return manEmail;
    }

    public void setManEmail(String manEmail) {
        this.manEmail = manEmail;
    }

    @Override
    public String toString() {
        return "CreateReimbDTO{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                ", empEmail='" + empEmail + '\'' +
                ", manEmail='" + manEmail + '\'' +
                '}';
    }
}
